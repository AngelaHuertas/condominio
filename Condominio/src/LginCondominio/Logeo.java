package LginCondominio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Logeo extends JFrame {

	private JPanel contentPane;
	private JTextField TxtUsuario;
	private JPasswordField jpassClave;
	private JButton btnIngresar;
	private HashMap<String, Usuario> usuarios = new HashMap<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Logeo frame = new Logeo();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Logeo() {
		setTitle("Inicio de Sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Simulación de usuarios
		usuarios.put("santiago", new Usuario("santiago", "123", "Administrador"));
		usuarios.put("angela", new Usuario("angela", "456", "Usuario"));
		usuarios.put("Anngelh", new Usuario("Anngelh","789","Administrador"));

		JLabel loginTitle = new JLabel("LOGIN");
		loginTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		loginTitle.setBounds(160, 30, 100, 25);
		contentPane.add(loginTitle);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(50, 80, 100, 20);
		contentPane.add(lblUsuario);

		TxtUsuario = new JTextField();
		TxtUsuario.setBounds(150, 80, 180, 20);
		contentPane.add(TxtUsuario);

		JLabel lblClave = new JLabel("Contraseña:");
		lblClave.setBounds(50, 120, 100, 20);
		contentPane.add(lblClave);

		jpassClave = new JPasswordField();
		jpassClave.setBounds(150, 120, 180, 20);
		contentPane.add(jpassClave);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(140, 170, 100, 30);
		contentPane.add(btnIngresar);

		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreUsuario = TxtUsuario.getText();
				String claveFinal = new String(jpassClave.getPassword());

				if (usuarios.containsKey(nombreUsuario)) {
					Usuario u = usuarios.get(nombreUsuario);
					if (u.getClave().equals(claveFinal)) {
						JOptionPane.showMessageDialog(null, "¡Bienvenido " + u.getNombre() + "!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						if (u.getRol().equalsIgnoreCase("Administrador")) {
							new MenuAdmin().setVisible(true);
						} else {
							new MenuUsuario().setVisible(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}

