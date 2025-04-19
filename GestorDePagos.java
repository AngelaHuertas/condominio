package condominioFinal;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestorDePagos {
    private String estadoPago = "Pendiente";
    private String metodoPago;
    private Apartamento apartamentoSeleccionado;

    public boolean mostrarMontoDeCuotaMensual(ArrayList<Apartamento> apartamentos) {
        String input = JOptionPane.showInputDialog(null, "Ingrese código de apartamento:", "Pago", JOptionPane.QUESTION_MESSAGE);
        if (input == null) return false;
        int codApartamento = Integer.parseInt(input.trim());

        for (Apartamento apto : apartamentos) {
            if (apto.numApt == codApartamento) {
                Calculo.calcularCostos(apto);
                apartamentoSeleccionado = apto;
                JOptionPane.showMessageDialog(null, String.format(
                    "Factura Nº %d\nDueño: %s\nInquilino: %s\nTotal: $%.2f",
                    apto.numApt, apto.propietario, apto.inquilino, apto.total));
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Apartamento no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public void gestionarMetodoDePago() {
        if ("Aprobado".equalsIgnoreCase(estadoPago)) {
            JOptionPane.showMessageDialog(null, "Pago ya realizado.");
            return;
        }
        String opcion = JOptionPane.showInputDialog(null, "Método de pago:\n1. PSE\n2. Tarjeta\n3. Cancelar",
            "Pago", JOptionPane.PLAIN_MESSAGE);
        if (opcion == null) return;
        switch (opcion.trim()) {
            case "1":
                metodoPago = "PSE";
                estadoPago = "Aprobado";
                if (apartamentoSeleccionado != null) apartamentoSeleccionado.pagoRealizado = true;  
                JOptionPane.showMessageDialog(null, "Pago PSE exitoso.");
                break;
            case "2":
                metodoPago = "TARJETA";
                estadoPago = "Aprobado";
                if (apartamentoSeleccionado != null) apartamentoSeleccionado.pagoRealizado = true;  
                JOptionPane.showMessageDialog(null, "Pago tarjeta exitoso.");
                break;
            case "3":
                estadoPago = "Cancelado";
                return;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida.");
                return;
        }
        generarComprobanteDePago();
    }

    public void confirmarEstadoPago() {
        JOptionPane.showMessageDialog(null, "Estado: " + estadoPago);
    }

    private void generarComprobanteDePago() {
        if (apartamentoSeleccionado == null) return;
        JOptionPane.showMessageDialog(null, String.format(
            "COMPROBANTE\nMétodo: %s\nEstado: %s\nFecha: %s\nValor: $%.2f",
            metodoPago, estadoPago, java.time.LocalDate.now(), apartamentoSeleccionado.total));
    }

    public void iniciar(ArrayList<Apartamento> apartamentos) {
        if (!mostrarMontoDeCuotaMensual(apartamentos)) return;
        while (true) {
            String opcion = JOptionPane.showInputDialog(null, "¿Qué hacer?\n1. Pagar\n2. Ver estado\n3. Volver",
                "Pago", JOptionPane.PLAIN_MESSAGE);
            if (opcion == null || opcion.trim().equals("3")) break;
            if (opcion.trim().equals("1")) gestionarMetodoDePago();
            else if (opcion.trim().equals("2")) confirmarEstadoPago();
            else JOptionPane.showMessageDialog(null, "Opción inválida.");
        }
    }
}