package condominioFinal;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Scanner;


public class SistemaLogin {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    static {
        usuarios.add(new Usuario("admin", "123", "Administrador"));
    }

    public static void addUsuario(Usuario u) {
        usuarios.add(u);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            boolean volverAlLogin = login(scanner);
            if (!volverAlLogin) {
                JOptionPane.showMessageDialog(null, "Gracias por usar el sistema", "Salir", JOptionPane.INFORMATION_MESSAGE);
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
        GestorDePagos gestor = new GestorDePagos();
        while (true) {
            StringBuilder menu = new StringBuilder("Menú " + role + "\n");
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
                Procesos procesos = new Procesos();
                switch (opcion) {
                    case "1": procesos.registrarPropietario(); break;
                    case "2": procesos.actualizacion(); break;
                    case "3": procesos.reporteCuotasPagadas(); break;
                    case "4": procesos.reporteCuotasPendientes(); break;
                    case "5": return true;
                    case "6": return false;
                    default:  JOptionPane.showMessageDialog(null, "Opción no válida", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                switch (opcion) {
                    case "1":
                        gestor.iniciar(Procesos.getApartamentos());  // LISTA ESTÁTICA
                        break;
                    case "2": return true;
                    case "3": return false;
                    default:  JOptionPane.showMessageDialog(null, "Opción no válida", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
