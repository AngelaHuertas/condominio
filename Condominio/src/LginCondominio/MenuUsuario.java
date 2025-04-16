package LginCondominio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuUsuario extends JFrame {

	public MenuUsuario() {
		setTitle("Menú Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		setLayout(new GridLayout(3, 1, 10, 10));

		add(new JLabel("Bienvenido, Usuario", SwingConstants.CENTER));

		JButton btnPagos = new JButton("Gestión de Pagos");
		JButton btnSalir = new JButton("Salir");

		add(btnPagos);
		add(btnSalir);

		btnSalir.addActionListener(e -> {
			dispose();
			new Logeo().setVisible(true);
		});
	}
}

