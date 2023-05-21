package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.dto.Ingreso;
import main.dto.Medico;
import main.dto.Paciente;
import main.jdbc.IngresoJDBC;
import main.jdbc.MedicoJDBC;
import main.jdbc.PacienteJDBC;

public class App {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);

		/**
		 * MENU PRINCIPAL
		 */

		int opcion = 4;

		// ELECCION DE ACCION
		while (opcion != 0) {
			System.out.println("CENTRO DE SALUD PALMERITAS\n");
			System.out.println("Pulse 1 para acceder a los pacientes.");
			System.out.println("Pulse 2 para acceder a los médicos.");
			System.out.println("Pulse 3 para acceder a los ingresos.");
			System.out.println("Pulse 0 para salir del programa.");

			try {
				opcion = entrada.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nError al elegir la opción. No se aceptan caracteres no numéricos. \n");
				entrada.nextLine();// Consume salto de linea para que no entre en bucle.
			}

			switch (opcion) {
			
			case 1:

				// MENU DE PACIENTES
				System.out.println("DATOS DE PACIENTES\n");

				System.out.println("Pulse 1 para insertar paciente.");
				System.out.println("Pulse 2 para modificar paciente.");
				System.out.println("Pulse 3 para mostrar paciente.");
				System.out.println("Pulse 4 para borrar paciente.");
				System.out.println("Pulse 0 para salir de este menú.");
				int opc_p = 9;

				try {
					opc_p = entrada.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("\nError al elegir la opción. No se aceptan caracteres no numéricos. \n");
				}
				PacienteJDBC pacDao = new PacienteJDBC();

				// ELECCION DE ACCION CRUD DE PACIENTE
				switch (opc_p) {

				case 1:

					// RECOGIDA DE DATOS SIN TRATAMIENTO
					entrada = new Scanner(System.in);
					System.out.println("Inserta el nombre");
					String nombre = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta los apellidos");
					String apellidos = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta la fecha de nacimiento (yyyy-mm-dd)");
					String fechanacimiento = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta el telefono");
					String telefono = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta la direccion");
					String direccion = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta el codigo postal");
					String cp = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta la poblacion");
					String poblacion = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta la provincia");
					String provincia = entrada.nextLine();

					Paciente p1 = new Paciente(0, nombre, apellidos, direccion, poblacion, provincia, cp, telefono, fechanacimiento);
					pacDao.crearPaciente(p1);
					System.out.println(p1.toString());
					break;

				case 2:
					
					System.out.println("Inserta el ID del paciente a actualizar");
					int id_paciente=entrada.nextInt();
					System.out.println(pacDao.getPaciente(id_paciente));
					
					// RECOGIDA DE DATOS SIN TRATAMIENTO
					System.out.println("MODIFICA SOLO LOS VALORES A ACTUALIZAR. EL RESTO COPIALOS TAL COMO ESTABAN");
					entrada = new Scanner(System.in);
					System.out.println("Inserta el nombre");
					nombre = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta los apellidos");
					apellidos = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta la fecha de nacimiento (yyyy-mm-dd)");
					fechanacimiento = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta el telefono");
					telefono = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta la direccion");
					direccion = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta el codigo postal");
					cp = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta la poblacion");
					poblacion = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta la provincia");
					provincia = entrada.nextLine();
					
					Paciente p2 = new Paciente(id_paciente, nombre, apellidos, direccion, poblacion, provincia, cp, telefono, fechanacimiento);
					pacDao.actualizarPaciente(p2);
					System.out.println(p2.toString());
					break;

				case 3:
					
					mostrarPacientes();
					break;

				case 4:
					
					System.out.println("Inserta el código del paciente para borrar el registro");
					int id_p = entrada.nextInt();
					pacDao.borrarPaciente(id_p); 
					System.out.println("Registro con id " + id_p + "eliminado");
					break;
					
				default:
					
					break;
				}
				break;

			case 2:

				// MENU DE MEDICOS
				System.out.println("DATOS DE MEDICOS\n");

				System.out.println("Pulse 1 para insertar médico.");
				System.out.println("Pulse 2 para modificar médico.");
				System.out.println("Pulse 3 para mostrar médico.");
				System.out.println("Pulse 4 para borrar médico.");
				System.out.println("Pulse 0 para salir de este menú.");
				int opc_m = 9;

				try {
					opc_m = entrada.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("\nError al elegir la opción. No se aceptan caracteres no numéricos. \n");
				}

				MedicoJDBC medDao = new MedicoJDBC();
				// ELECCION DE ACCION CRUD DE MEDICO
				switch (opc_m) {
				case 1:

					// RECOGIDA DE DATOS SIN TRATAMIENTO
					entrada = new Scanner(System.in);
					System.out.println("Inserta el nombre");
					String nombre = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta los apellidos");
					String apellidos = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta el telefono");
					String telefono = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta la especialidad");
					String especialidad = entrada.nextLine();

					Medico m1 = new Medico(0, nombre, apellidos, telefono, especialidad);
					medDao.crearMedico(m1);
					System.out.println(m1.toString());

					break;
					
				case 2:

					System.out.println("Inserta el ID del médico a actualizar");
					int id_medico=entrada.nextInt();
					System.out.println(medDao.getMedico(id_medico));
					
					// RECOGIDA DE DATOS SIN TRATAMIENTO
					System.out.println("MODIFICA SOLO LOS VALORES A ACTUALIZAR. EL RESTO COPIALOS TAL COMO ESTABAN");
					entrada = new Scanner(System.in);
					System.out.println("Inserta el nombre");
					nombre = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta los apellidos");
					apellidos = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta el telefono");
					telefono = entrada.nextLine();
					entrada = new Scanner(System.in);
					System.out.println("Inserta la especialidad");
					especialidad = entrada.nextLine();
					
					Medico m2 = new Medico(id_medico, nombre, apellidos, telefono, especialidad);
					medDao.actualizarMedico(m2);
					System.out.println(m2.toString());
					break;
					
				case 3:
					
					mostrarMedicos();

					break;
					
				case 4:
					
					System.out.println("Inserta el código del médico para borrar el registro");
					int id_m = entrada.nextInt();
					medDao.borrarMedico(id_m); 
					System.out.println("Registro con id " + id_m + "eliminado");
					

					break;
					
				default:
					
					break;
				}
				break;

			case 3:

				// MENU DE INGRESOS
				System.out.println("DATOS DE INGRESOS\n");

				System.out.println("Pulse 1 para insertar ingreso.");
				System.out.println("Pulse 2 para modificar ingreso.");
				System.out.println("Pulse 3 para mostrar ingreso.");
				System.out.println("Pulse 4 para borrar ingreso.");
				System.out.println("Pulse 0 para salir de este menú.");
				int opc_i = 9;

				try {
					opc_i = entrada.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("\nError al elegir la opción. No se aceptan caracteres no numéricos. \n");
				}

				IngresoJDBC IngDao = new IngresoJDBC();
				// ELECCION DE ACCION CRUD DE INGRESOS
				switch (opc_i) {
				case 1:

					PacienteJDBC p5 = new PacienteJDBC();
					p5.getPaciente(4);
					MedicoJDBC m5 = new MedicoJDBC();
					m5.getMedico(3);

					// RECOGIDA DE DATOS SIN TRATAMIENTO
					entrada = new Scanner(System.in);
					System.out.println("Inserta el numero de la habitacion");
					int numerohabitacion = entrada.nextInt();
					entrada = new Scanner(System.in);
					System.out.println("Inserta la cama A, B O C");
					String cama = entrada.nextLine();
					char camaletra = cama.charAt(0);
					entrada = new Scanner(System.in);
					System.out.println("Inserta la fecha del ingreso");
					String fechaingreso = entrada.nextLine();
					
					//IMPRESION DE DATOS PARA ELEGIR EL ID
					entrada = new Scanner(System.in);
					mostrarPacientes();
					System.out.println("\n\n\nInserta el id del paciente");
					int id_paciente = entrada.nextInt();

					//CREACCION DE OBJETO PACIENTE Y COMPROBACION DE EXISTENCIA EN LA BBDD
					Paciente paciente = new Paciente();
					pacDao = new PacienteJDBC();
					paciente = pacDao.getPaciente(id_paciente);
					while (paciente == null) {
						System.out.println("Error. No existe ningún registro con ese ID. Introduce otro ID");
						entrada = new Scanner(System.in);
						id_paciente = entrada.nextInt();
						paciente = pacDao.getPaciente(id_paciente);
					}
					
					//IMPRESION DE DATOS PARA ELEGIR EL ID
						entrada = new Scanner(System.in);
					mostrarMedicos();
					System.out.println("\n\n\nInserta el id del medico");
					int id_medico = entrada.nextInt();
					
					//CREACCION DE OBJETO MEDICO Y COMPROBACION DE EXISTENCIA EN LA BBDD
					Medico medico = new Medico();
					medDao = new MedicoJDBC();
					medico = medDao.getMedico(id_medico);
					while (medico == null) {
						System.out.println("Error. No existe ningún registro con ese ID. Introduce otro ID");
						entrada = new Scanner(System.in);
						id_medico= entrada.nextInt();
						medico = medDao.getMedico(id_medico);
					}
					
					Ingreso I1 = new Ingreso(0, numerohabitacion, (camaletra + ""), fechaingreso, paciente, medico);
					IngDao.crearIngreso(I1);
					System.out.println(I1.toString());
					break;
				
				case 2:
					
					System.out.println("Inserta el ID del ingreso a actualizar");
					int id_ingreso=entrada.nextInt();
					System.out.println(IngDao.getIngreso(id_ingreso));
					
					// RECOGIDA DE DATOS SIN TRATAMIENTO
					entrada = new Scanner(System.in);
					System.out.println("Inserta el numero de la habitacion");
					numerohabitacion = entrada.nextInt();
					entrada = new Scanner(System.in);
					System.out.println("Inserta la cama A, B O C");
					cama = entrada.nextLine();
					camaletra = cama.charAt(0);
					entrada = new Scanner(System.in);
					System.out.println("Inserta la fecha del ingreso");
					fechaingreso = entrada.nextLine();
					
					//IMPRESION DE DATOS PARA ELEGIR EL ID DE PACIENTE
					entrada = new Scanner(System.in);
					mostrarPacientes();
					System.out.println("\n\n\nInserta el id del paciente");
					id_paciente = entrada.nextInt();
					
					//CREACCION DE OBJETO PACIENTE Y COMPROBACION DE EXISTENCIA EN LA BBDD POR SI SE EQUIVOCA AL MODIFICARLO
					pacDao = new PacienteJDBC();
					paciente = pacDao.getPaciente(id_paciente);
					while (paciente == null) {
						System.out.println("Error. No existe ningún registro con ese ID. Introduce otro ID");
						entrada = new Scanner(System.in);
						id_paciente = entrada.nextInt();
						paciente = pacDao.getPaciente(id_paciente);
					}
					
					//IMPRESION DE DATOS PARA ELEGIR EL ID
						entrada = new Scanner(System.in);
					mostrarMedicos();
					System.out.println("\n\n\nInserta el id del medico");
					 id_medico = entrada.nextInt();
					
					//CREACCION DE OBJETO MEDICO Y COMPROBACION DE EXISTENCIA EN LA BBDD
					medDao = new MedicoJDBC();
					medico = medDao.getMedico(id_medico);
					while (medico == null) {
						System.out.println("Error. No existe ningún registro con ese ID. Introduce otro ID");
						entrada = new Scanner(System.in);
						id_medico= entrada.nextInt();
						medico = medDao.getMedico(id_medico);
					}
					
					Ingreso I2 =  new Ingreso(id_ingreso, numerohabitacion, (camaletra + ""), fechaingreso, paciente, medico);
					IngDao.actualizarIngreso(I2);
					System.out.println(I2.toString());
					break;
				case 3:
					
					mostrarIngresos();
					
					break;
				case 4:

					System.out.println("Inserta el código del ingreso para borrar el registro");
					int id_i = entrada.nextInt();
					IngDao.borrarIngreso(id_i); 
					System.out.println("Registro con id " + id_i + " eliminado");
					
					break;
				default:
					break;
				}
				break;

			case 0:

				System.out.println("Sesión cerrada.");
				break;
			default:

				break;
			}
		}
		System.out.println("\nHasta la vista.");
	}

	/** METODOS PARA MOSTRAR DATOS */
	private static void mostrarPacientes() {
		PacienteJDBC pacienteDao = new PacienteJDBC();

		for (Paciente p : pacienteDao.getPacientes()) {
			System.out.println(p);
		}
	}

	private static void mostrarMedicos() {
		MedicoJDBC medDao = new MedicoJDBC();

		for (Medico m : medDao.getMedicos()) {
			System.out.println(m);
		}
	}
	
	private static void mostrarIngresos() {
		IngresoJDBC ingDao = new IngresoJDBC();

		for (Ingreso i : ingDao.getIngresos()) {
			System.out.println(i);
		}
	}
}
