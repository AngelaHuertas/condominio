package LginCondominio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuAdmin extends JFrame {

	public MenuAdmin() {
		setTitle("Menú Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		setLayout(new GridLayout(6, 1, 10, 10));

		add(new JLabel("Bienvenido, Administrador", SwingConstants.CENTER));

		JButton btnRegistro = new JButton("Registrar propietario");
		JButton btnActualizar = new JButton("Actualizar datos");
		JButton btnPagadas = new JButton("Cuotas pagadas");
		JButton btnPendientes = new JButton("Cuotas pendientes");
		JButton btnSalir = new JButton("Salir");

		add(btnRegistro);
		add(btnActualizar);
		add(btnPagadas);
		add(btnPendientes);
		add(btnSalir);

		btnSalir.addActionListener(e -> {
			dispose();
			new Logeo().setVisible(true);
		});
	}
}
