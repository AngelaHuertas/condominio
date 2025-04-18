package condominio;

import java.util.Scanner;

public class GestorDePagos {
	Scanner in = new Scanner(System.in);
	String estadoPago = "Pendiente";
	String metodoPago;
	EjemploDatos apartamentoSeleccionado;

	public boolean mostrarMontoDeCuotaMensual(EjemploDatos[] apartamentos) {
		System.out.println("Indica el codigo de su apartamento");
		int codApartamento = in.nextInt();
		in.nextLine();
		boolean encontrado = false;

		for (EjemploDatos apto : apartamentos) {

			if (apto.numero == codApartamento) {

				apartamentoSeleccionado = apto;
				System.out.println("\n Factura del apartamento Numero: " + apto.numero);
				System.out.println("Dueño: " + apto.dueno);
				System.out.println("Inquilino: " + apto.inquilino);
				System.out.println("Total a pagar: $" + apto.total);
				encontrado = true;
				break;

			}
		}

		if (!encontrado) {

			System.out.println("El codigo de apartamento no ha sido encontrado");
		}
		return encontrado;

	}

	public void gestionarMetodoDePago() {
		
		if (estadoPago.equalsIgnoreCase("Aprobado")) {
			System.out.println("El pago ya se ha realizado");
			return;
		}
		String menu = "\nElija un metodo de pago a continuación\n";
		menu += "1. PSE\n";
		menu += "2. Pago con tarjeta\n ";
		menu += "3. Cancelar pago\n";

		System.out.println(menu);
		int opc = in.nextInt();
		in.nextLine();

		switch (opc) {
		case 1:
			procesarPagoPSE();
			break;
		case 2:
			procesarPagoTarjeta();
			break;

		case 3:
			System.out.println("Pago cancelado por el usuario.");
			estadoPago = "Cancelado";
			break;

		default:
			System.out.println(" Opción inválida. Intente de nuevo.");
			gestionarMetodoDePago();
		}
		if (estadoPago.equalsIgnoreCase("Aprobado")) {
			generarComprobanteDePago(apartamentoSeleccionado);
		}
	}

	public void confirmarEstadoPago() {
		System.out.println("El estado de su pago se encuentra: " + estadoPago);
	}

	public void generarComprobanteDePago(EjemploDatos apto) {

		if (estadoPago.equalsIgnoreCase("cancelado")) {

			System.out.println("no se realizo comprobante de pago ya que el proceso fue cancelado");
		} else {

			String mensaje = "\n COMPROBANTE DE PAGO\n";
			mensaje += "Método de pago: " + metodoPago.toUpperCase() + "\n";
			mensaje += "Estado del pago: " + estadoPago + "\n";
			mensaje += "Fecha: " + java.time.LocalDate.now() + "\n";
			mensaje += "valor: " + apto.total + "\n";
			mensaje += "Gracias por confiar en nosotros";

			System.out.println(mensaje);
		}
	}

	private void procesarPagoPSE() {
		metodoPago = "pse";
		System.out.println("Cargando pagos PSE...");
		System.out.print("Ingrese su número de la cuenta: ");
		String cuentaPSE = in.nextLine();
		System.out.print("Ingrese la contraseña: ");
		String clavePSE = in.nextLine();
		System.out.println("Pago exitoso");
		estadoPago = "Aprobado";
	}

	private void procesarPagoTarjeta() {
		metodoPago = "tarjeta";
		System.out.print("Ingrese número de tarjeta: ");
		String tarjeta = in.nextLine();
		System.out.print("Ingrese nombre del titular: ");
		String titular = in.nextLine();
		System.out.print("Ingrese código CVV: ");
		String cvv = in.nextLine();
		System.out.println("Pago Exitoso");
		estadoPago = "Aprobado";
	}

	public void iniciar(EjemploDatos[] apartamentos) {
		if (mostrarMontoDeCuotaMensual(apartamentos)) {
			boolean volver = false;
			while (!volver) {
				String menu = "\n¿Qué deseas hacer a continuación?\n";
				menu += "1. Pagar factura\n";
				menu += "2. Verificar estado de pago\n";
				menu += "3. Volver al menú principal\n";
				System.out.println(menu);

				int opc = in.nextInt();
				in.nextLine();

				switch (opc) {
				case 1:
					gestionarMetodoDePago();
					break;
				case 2:
					confirmarEstadoPago();
					break;
				case 3:
					volver = true;
					break;
				default:
					System.out.println("Opción inválida. Intenta nuevamente.");
				}
			}
		}

	}

}
