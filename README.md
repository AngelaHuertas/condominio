import javax.swing.JOptionPane;
import java.util.Scanner;

class Usuario {
    private String username;
    private String password;
    private String role;

    public Usuario(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}

public class SistemaLogin {
    private static Usuario[] usuarios = {
        new Usuario("santiago", "123", "Administrador"),
        new Usuario("angela", "456", "Usuario")
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            boolean volverAlLogin = login(scanner);
            if (!volverAlLogin) {
                JOptionPane.showMessageDialog(null, "Gracias por usar el sistema ", "Salir", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
        scanner.close();
    }

    private static boolean login(Scanner scanner) {
        while (true) {
            String username = JOptionPane.showInputDialog(null, "Ingrese su nombre de usuario:", "Inicio de sesión", JOptionPane.QUESTION_MESSAGE);
            if (username == null) return false;

            String password = JOptionPane.showInputDialog(null, "Ingrese su contraseña:", "Inicio de sesión", JOptionPane.QUESTION_MESSAGE);
            if (password == null) return false;

            Usuario user = autenticar(username.trim(), password.trim());
            if (user != null) {
                JOptionPane.showMessageDialog(null, "✅ Bienvenido, " + user.getUsername() + "!", "Acceso concedido", JOptionPane.INFORMATION_MESSAGE);
                return mostrarMenuPorRol(scanner, user.getRole());
            } else {
                JOptionPane.showMessageDialog(null, "❌ Credenciales incorrectas. Intente nuevamente.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static Usuario autenticar(String username, String password) {
        for (Usuario user : usuarios) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static boolean mostrarMenuPorRol(Scanner scanner, String role) {
        while (true) {
            StringBuilder menu = new StringBuilder(" Menú " + role + "\n");

            if (role.equals("Administrador")) {
                menu.append("1. Registrar propietario\n");
                menu.append("2. Actualizar datos\n");
                menu.append("3. Ver cuotas pagadas\n");
                menu.append("4. Ver cuotas pendientes\n");
                menu.append("5. Cerrar sesión\n");
                menu.append("6. Salir del sistema\n");
            } else {
                menu.append("1. Gestión de pagos\n");
                menu.append("2. Cerrar sesión\n");
                menu.append("3. Salir del sistema\n");
            }

            String opcion = JOptionPane.showInputDialog(null, menu.toString(), "Menú " + role, JOptionPane.PLAIN_MESSAGE);
            if (opcion == null) return false;

            opcion = opcion.trim();

            if (role.equals("Administrador")) {
                switch (opcion) {
                    case "1": JOptionPane.showMessageDialog(null, "Registro de propietario (en desarrollo)"); break;
                    case "2": JOptionPane.showMessageDialog(null, "Actualización de datos (en desarrollo)"); break;
                    case "3": JOptionPane.showMessageDialog(null, "Cuotas pagadas (en desarrollo)"); break;
                    case "4": JOptionPane.showMessageDialog(null, "Cuotas pendientes (en desarrollo)"); break;
                    case "5": return true;
                    case "6": return false; 
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                switch (opcion) {
                    case "1": JOptionPane.showMessageDialog(null, "Gestión de pagos (en desarrollo)"); break;
                    case "2": return true;  
                    case "3": return false; 
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
