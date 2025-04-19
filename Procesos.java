package condominioFinal;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import condominioFinal.SistemaLogin;
import condominioFinal.Usuario;

public class Procesos {
	int numApto, numAdultos, numNinos;
	String nomPropietario, nomInquilino, elPassword;
	private static ArrayList<Apartamento> apartamentos = new ArrayList<>();
	  
	 // Método para obtener los apartamentos
    public static ArrayList<Apartamento> getApartamentos() {
        return apartamentos; // Devuelve la lista de apartamentos registrados
    }
	
    public void registrarPropietario() {
        ingresarNumApto();
    }

    public void actualizarDatos() {
	    actualizacion();
	}

    // Reporte de cuotas pagadas
    public void reporteCuotasPagadas() {
        StringBuilder sb = new StringBuilder("Cuotas pagadas:\n");
        for (Apartamento a : apartamentos) {
            if (a.pagoRealizado) {
                sb.append(String.format("%d - %s \n", a.numApt, a.propietario));
            }
        }
        JOptionPane.showMessageDialog(null, sb.length() > 14 ? sb.toString() : "No hay cuotas pagadas.");
    }

    // Reporte de cuotas pendientes
    public void reporteCuotasPendientes() {
        StringBuilder sb = new StringBuilder("Cuotas pendientes:\n");
        for (Apartamento a : apartamentos) {
            if (!a.pagoRealizado) {
                sb.append(String.format("%d - %s \n", a.numApt, a.propietario));
            }
        }
        JOptionPane.showMessageDialog(null, sb.length() > 18 ? sb.toString() : "No hay cuotas pendientes.");
    }

	public void ingresarNumApto() {
	    JOptionPane.showMessageDialog(null, "Bienvenido a REGISTROS");
	    boolean valido = false;

	    while (!valido) {
	        numApto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de su apartamento"));

	        do {
		        if (numApto < 100) {
		            JOptionPane.showMessageDialog(null, "Nuestro condominio cuenta con apartamentos a partir del N°100");
		            numApto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de su apartamento"));
		        }
	        }while(numApto < 100);

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

			nomInquilino=JOptionPane.showInputDialog("Ingrese el nombre del inquilino");
	
		
		ingresarPersonas();
	}
	
	public void ingresarPersonas() {
		 String adultos;
		 String ninos;
		do {
		    adultos = JOptionPane.showInputDialog("Ingrese el numero de adultos que viven en el apartamento " + numApto);
		    if (adultos == null||adultos.trim().isEmpty()) { 
		        JOptionPane.showMessageDialog(null, "Recuerde que todos los campos deben ser llenados.");
		    } else {
		        numAdultos = Integer.parseInt(adultos); 
		        }
		} while (adultos == null||adultos.trim().isEmpty());
		
		
		do {
		    ninos = JOptionPane.showInputDialog("Ingrese el numero de niños que viven en el apartamento " + numApto);
		    if (ninos == null||ninos.trim().isEmpty()) { 
		        JOptionPane.showMessageDialog(null, "Recuerde que todos los campos deben ser llenados.");
		    } else {
		        numNinos = Integer.parseInt(ninos); 
		        }
		} while (ninos == null||ninos.trim().isEmpty());
		
		do {
			elPassword=JOptionPane.showInputDialog("Ingrese una contraseña para el inquilino");
			if (elPassword.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Recuerde que todos los campos deben ser llenados");
			}	
		} while (elPassword.isEmpty());
		
		guardarApto();
	}
	
	
	public void guardarApto() {
		 Apartamento apto = new Apartamento();
		    apto.numApt = numApto;
		    apto.propietario = nomPropietario;
		    apto.adultos = numAdultos;
		    apto.ninos = numNinos;
		    apto.inquilino = nomInquilino;
		    apto.password = elPassword;
		    apartamentos.add(apto);

		
	        // Decide el username
	        String usernameUsuario = !apto.inquilino.trim().isEmpty()
	                                  ? apto.inquilino
	                                  : apto.propietario;
	        
	        // Crea el usuario y lo registra en el sistema de login
	        Usuario newUser = new Usuario(
	            usernameUsuario,
	            apto.password,
	            "Usuario"
	        );
	        SistemaLogin.addUsuario(newUser);
	        

	        JOptionPane.showMessageDialog(null,
	            "Apartamento registrado con éxito, y usuario creado automáticamente.\n"
	            + "Ahora verás un resumen del registro:");
	        
	        mostrarApto();
	}
	
	public void mostrarApto() {
	JOptionPane.showMessageDialog(null, "RESUMEN REGISTRO:\n"
			+ "Numero del apartamento: "+numApto
			+"\nPropietario: "+nomPropietario
			+"\nIniquilino: "+nomInquilino
			+"\nAdultos: "+numAdultos
			+"\nNiños: "+numNinos);
	
			JOptionPane.showMessageDialog(null, "Volviendo al menú principal...");
	        return;
		
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
	    
	    JOptionPane.showMessageDialog(null, "Volviendo al menú principal...");
        return;
	    
	}



}
