import java.util.Scanner;

public class datos {


			public static void main(String[] args) {
				Scanner scanner = new Scanner(System.in);
		        final int MAX_APARTAMENTOS;

		        System.out.print("¿Cuántos apartamentos existen? ");
		        MAX_APARTAMENTOS = Integer.parseInt(scanner.nextLine());

		        EjemploDatos[] apartamentos = new EjemploDatos[MAX_APARTAMENTOS];
		        int cantidadRegistrada = 0;

		        while (true) {
		            if (cantidadRegistrada >= MAX_APARTAMENTOS) {
		                System.out.println("Ya se registraron todos los apartamentos permitidos.");
		                break;
		            }

		            System.out.print("\nNúmero del apartamento a registrar: ");
		            int numeroIngresado = Integer.parseInt(scanner.nextLine());

		            boolean yaExiste = false;
		            for (int i = 0; i < cantidadRegistrada; i++) {
		                if (apartamentos[i].numero == numeroIngresado) {
		                    yaExiste = true;
		                    break;
		                }
		            }

		            if (yaExiste) {
		                System.out.println(" Ese número de apartamento ya está registrado.");
		                continue;
		            }

		            EjemploDatos apto = new EjemploDatos(numeroIngresado, null, null, numeroIngresado, numeroIngresado);
		            apto.numero = numeroIngresado;

		            System.out.print("Dueño: ");
		            apto.dueno = scanner.nextLine();
		            System.out.print("Inquilino: ");
		            apto.inquilino = scanner.nextLine();
		            System.out.println(" Cuántos adultos viven: ");
		            apto.adultos = scanner.nextInt(); 
		            System.out.println(" Cuántos niños viven: ");
		            apto.ninos = scanner.nextInt();
		            scanner.nextLine();
		            
		           
		          
		            Calculo.calcularCostos(apto);
		            apartamentos[cantidadRegistrada] = apto;
		            cantidadRegistrada++;
		            
		          

		            
		            
		            
		            System.out.println("\nApartamento registrado con éxito.");
		            System.out.println("\n--- DATOS DEL APARTAMENTO REGISTRADO ---");
		            System.out.println("Número de apartamento: " + apto.numero);
		            System.out.println("Dueño: " + apto.dueno);
		            System.out.println("Inquilino: " + apto.inquilino);
		            System.out.println("-----------------------------------------");
		            System.out.println("Adultos: " + apto.adultos);
		            System.out.println("Niños: " + apto.ninos);
		            System.out.println("Costo Piscina: $" + apto.piscina);
		            System.out.println("Costo Juegos: $" + apto.juegos);
		            System.out.println("Costo Zonas Sociales: $" + apto.zonasSociales);
		            System.out.println("Costo Aseo: $" + apto.aseo);
		            System.out.println("Subtotal: $" + apto.subtotal);
		            System.out.println("Descuento: $" + apto.descuento);
		            System.out.println("TOTAL A PAGAR: $" + apto.total);
		            System.out.println("--------------------------------");
		            
		            
		      

		            System.out.print("\n¿Deseas registrar otro apartamento? (s/n): ");
		            String continuar = scanner.nextLine();
		            if (!continuar.equalsIgnoreCase("s")) {
		                break;
		            }
		            
		        }
		        System.out.println("\nRegistro finalizado.");
		        
		        
		        System.out.println("\n--- Datos de apartamentos registrados ---");

		        for (int i = 0; i < apartamentos.length; i++) {
		            if (apartamentos[i] != null) {
		                System.out.println("Apartamento #" + apartamentos[i].numero);
		                System.out.println("  Dueño: " + apartamentos[i].dueno);
		                System.out.println("  Inquilino: " + apartamentos[i].inquilino);
		    
		            	}
		            }

		
		    
		}
}
