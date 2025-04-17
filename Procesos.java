import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Procesos {

	int numApto, numAdultos, numNinos;
	String nomPropietario, nomInquilino;
	
	ArrayList<Apartamento> apartamentos = new ArrayList<Apartamento>();
	  
	public Procesos() {
		JOptionPane.showMessageDialog(null, "---   ¡¡Bienvenido a el sistema!!   ----");
		opciones();
	}
	

	
	private void opciones() {
		int opc;
		
		
		 do {
		 opc= Integer.parseInt(JOptionPane.showInputDialog(null,"Digite 1 para realizar un registro"
				+ "\nDigite 2 para realizar cambios a un registro existente"));
		
	    if (opc==1) {
           ingresarNumApto();
       } else if (opc==2) {
          actualizacion();
       } else {
           JOptionPane.showMessageDialog(null, "Opción inválida, intentelo nuevamente");
       }
   
	} while (opc!=1&&opc!=2);
	}



	public void ingresarNumApto() {
	    JOptionPane.showMessageDialog(null, "Bienvenido a REGISTROS");
	    boolean valido = false;

	    while (!valido) {
	        numApto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de su apartamento"));

	        if (numApto < 100) {
	            JOptionPane.showMessageDialog(null, "Nuestro condominio cuenta con apartamentos a partir del N°100");
	        }

	        boolean repetido = false;
	        for (Apartamento a : apartamentos) {
	            if (a.numApt == numApto) {
	                repetido = true;
	                JOptionPane.showMessageDialog(null, "Error: el apartamento ya está registrado.");
	                break;
	            }
	        }

	        if (!repetido) {
	            valido = true;
	        }
	    }

	    ingresarNombres();
	}

	
	public void ingresarNombres() {
		
		do {
			nomPropietario=JOptionPane.showInputDialog("Ingrese el nombre del propietario");
			if (nomPropietario.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Recuerde que todos los campos deben ser llenados");
			}	
		} while (nomPropietario.isEmpty());
		
		do {
			nomInquilino=JOptionPane.showInputDialog("Ingrese el nombre del inquilino");
			if (nomInquilino.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Recuerde que todos los campos deben ser llenados");
			}	
		} while (nomInquilino.isEmpty());	
		
		ingresarPersonas();
	}
	
	public void ingresarPersonas() {
		 String adultos;
		 String ninos;
		do {
		    adultos = JOptionPane.showInputDialog("Ingrese el numero de adultos que viven en el apartamento " + numApto);
		    if (adultos == null||adultos.trim().isEmpty()) { // .trim= asegura que no haya entradas vacias 
		        JOptionPane.showMessageDialog(null, "Recuerde que todos los campos deben ser llenados.");
		    } else {
		        numAdultos = Integer.parseInt(adultos); 
		        }
		} while (adultos == null||adultos.trim().isEmpty());
		
		
		do {
		    ninos = JOptionPane.showInputDialog("Ingrese el numero de niños que viven en el apartamento " + numApto);
		    if (ninos == null||ninos.trim().isEmpty()) { // .trim= asegura que no haya entradas vacias 
		        JOptionPane.showMessageDialog(null, "Recuerde que todos los campos deben ser llenados.");
		    } else {
		        numNinos = Integer.parseInt(ninos); 
		        }
		} while (ninos == null||ninos.trim().isEmpty());
		
		guardarApto();
	}
	
	public void guardarApto() {
		 Apartamento apto = new Apartamento();
		    apto.numApt = numApto;
		    apto.propietario = nomPropietario;
		    apto.adultos = numAdultos;
		    apto.ninos = numNinos;
		    apto.inquilino=nomInquilino;
		    apartamentos.add(apto);

		    JOptionPane.showMessageDialog(null, "Apartamento registrado con éxito, A continuacion podra ver un resumen del registro");
		    
		    mostrarApto();
	}
	
	public void mostrarApto() {
	JOptionPane.showMessageDialog(null, "RESUMEN REGISTRO:\n"
			+ "Numero del apartamento: "+numApto
			+"\nPropietario: "+nomPropietario
			+"\nIniquilino: "+nomInquilino
			+"\nAdultos: "+numAdultos
			+"\nNiños: "+numNinos);
	String repetir;
	do {
		 repetir= JOptionPane.showInputDialog("¿Desea registrar otro apartamento, o hacer cambios a uno existente?(S/N)");
		if (repetir.equalsIgnoreCase("s")) {
			opciones();
		}else if (repetir.equalsIgnoreCase("n")) {
			registros();
		}else {
			JOptionPane.showMessageDialog(null, "Opcion incorrecta, intentelo nuevamente");
		}
	} while (!repetir.equalsIgnoreCase("s")||!repetir.equalsIgnoreCase("n"));

	}
	
	public void registros() {
		  String resumen = "";
	
	    for (Apartamento a : apartamentos) {
	        resumen += "Apartamento: " + a.numApt + "\n";
	        resumen += "Propietario: " + a.propietario + "\n";
	        resumen += "Inquilino: " + a.inquilino + "\n";
	        resumen += "Adultos: " + a.adultos + "\n";
	        resumen += "Niños: " + a.ninos + "\n";
	        resumen += "-------------------------\n";
	}
	    JOptionPane.showMessageDialog(null, resumen);
	}
	


// 2da historia de usuario: actualizaciones

	public void actualizacion() {
	    JOptionPane.showMessageDialog(null, "Bienvenido a actualizaciones");

	    boolean encontrado = false;
	    Apartamento aptoSeleccionado = null;
	    while (!encontrado) {
	        int buscarApto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número del apartamento que desea modificar"));

	        for (Apartamento a : apartamentos) {
	            if (buscarApto == a.numApt) {
	                encontrado = true;
	                aptoSeleccionado = a;
	                break;
	            }
	        }

	        if (!encontrado) {
	            JOptionPane.showMessageDialog(null, "No se encontró ningún apartamento con ese número. Inténtalo de nuevo.");
	        }
	    }
	    int modificar;
	    do {
	        modificar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opción del dato que desea cambiar:\n"
	                + "1. Nombre de Propietario\n"
	                + "2. Nombre de Inquilino\n"
	                + "3. Número de Adultos\n"
	                + "4. Número de Niños\n"
	                + "5. Todos los datos"));

	        switch (modificar) {
	            case 1:
	                aptoSeleccionado.propietario = JOptionPane.showInputDialog("Digite el nuevo Propietario");
	                break;
	            case 2:
	                aptoSeleccionado.inquilino = JOptionPane.showInputDialog("Digite el nuevo Inquilino");
	                break;
	            case 3:
	                aptoSeleccionado.adultos = Integer.parseInt(JOptionPane.showInputDialog("Digite el nuevo número de adultos"));
	                break;
	            case 4:
	                aptoSeleccionado.ninos = Integer.parseInt(JOptionPane.showInputDialog("Digite el nuevo número de niños"));
	                break;
	            case 5:
	                aptoSeleccionado.propietario = JOptionPane.showInputDialog("Digite el nuevo Propietario");
	                aptoSeleccionado.inquilino = JOptionPane.showInputDialog("Digite el nuevo Inquilino");
	                aptoSeleccionado.adultos = Integer.parseInt(JOptionPane.showInputDialog("Digite el nuevo número de adultos"));
	                aptoSeleccionado.ninos = Integer.parseInt(JOptionPane.showInputDialog("Digite el nuevo número de niños"));
	                break;
	            default:
	                JOptionPane.showMessageDialog(null, "Opción incorrecta, inténtalo de nuevo");
	        }

	    } while (modificar < 1 || modificar > 5);

	    JOptionPane.showMessageDialog(null, "¡Registro actualizado con éxito!");
	    registros();

	    opciones();
	}

}