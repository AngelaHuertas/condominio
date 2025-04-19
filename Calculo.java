package condominioFinal;

public class Calculo {
	public static void calcularCostos(Apartamento apto) { 
	 /*Calcular piscina*/
		if (apto.adultos>0) {    
			apto.piscina = apto.adultos * 2000;
			
			
		}  
		/*Calcular los juegos*/
		if (apto.ninos > 0) {
			apto.juegos = 5000;
		}
		
		/*Calcular zonas sociales*/
		if (!apto.inquilino.isEmpty()) {
			apto.zonasSociales = 10000;
		}
		
		/*Calcular aseo*/
		int piso = apto.numApt / 100;
		if (piso == 1 || piso == 2) {
			apto.aseo = 15000;
		}
		
		/*Descuento del dueño viva en su apartamento*/
		apto.subtotal=apto.piscina + apto.juegos + apto.zonasSociales + apto.aseo;
		
		if (apto.propietario.equalsIgnoreCase(apto.inquilino)) {
			apto.descuento = (apto.subtotal + 50000) * 0.20;
			
		}
		
		/*total*/
		apto.total = 50000 + apto.subtotal - apto.descuento;
		
	}
}