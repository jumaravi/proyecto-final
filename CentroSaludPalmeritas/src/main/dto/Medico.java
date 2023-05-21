package main.dto;

public class Medico {

	/** ATRIBUTOS */
	private Integer codigo_m;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String especialidad;

	/**
	 * CONSTRUCTOR VACIO
	 */
	public Medico() {
	}

	/**
	 * CONSTRUCTOR @param codigo_m @param nombre @param apellidos @param telefono @param especialidad
	 */
	public Medico(int codigo_m, String nombre, String apellidos, String telefono, String especialidad) {

		this.codigo_m = codigo_m;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.especialidad = especialidad;
	}

	// DEBE IR AL MAIN
//	public static Medico crearMedico() {
//		Medico m= new Medico();
//		
//		Scanner entrada = new Scanner(System.in);
//		System.out.prIntegerln("Integerroduce el nombre:\n");
//		m.setNombre(entrada.nextLine());
//		
//		entrada = new Scanner(System.in);
//		System.out.prIntegerln("Integerroduce los apellidos:\n");
//		m.setApellidos(entrada.nextLine());
//		
//		entrada = new Scanner(System.in);
//		System.out.prIntegerln("Integerroduce el teléfono:\n");
//		m.setTelefono(entrada.nextLine());
//		
//		entrada = new Scanner(System.in);
//		System.out.prIntegerln("Integerroduce la especialidad:\n");
//		m.setEspecialidad(entrada.nextLine());
//		
//		
//		return null;
//	}

	/** GETTERS Y SETTERS */
	public int getCodigo_m() {
		return codigo_m;
	}

	public void setCodigo_m(int codigo_m) {
		this.codigo_m = codigo_m;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	/** METODO TOSTRING */
	@Override
	public String toString() {
		String vis="";
		vis+="====Médico====\n";
		vis+="Código: "+codigo_m+"\n";
		vis+="Nombre: "+nombre+"\n";
		vis+="Apellidos: "+apellidos+"\n";
		vis+="Teléfono: "+telefono+"\n";
		vis+="Especialidad: "+especialidad+"\n";
		vis+="==============\n";
		return vis;
	}


}
