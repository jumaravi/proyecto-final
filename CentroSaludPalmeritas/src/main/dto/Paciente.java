package main.dto;

public class Paciente {

	/** ATRIBUTOS */
	private int codigo_p;
	private String nombre;
	private String apellidos;
	private String fechaNacimiento;
	private String telefono;
	private String direccion;
	private String codigoPostal;
	private String poblacion;
	private String provincia;

	/**
	 * CONSTRUCTOR VACIO
	 */
	public Paciente() {
	}

	/**
	 * CONSTRUCTOR @param codigo_p @param nombre @param apellidos @param fechaNacimiento @param fechaNacimiento 
	 * @param telefono @param direccion @param codigoPostal @param codigoPostal @param poblacion @param provincia
	 */
	public Paciente(int codigo_p, String nombre, String apellidos,String direccion,  String poblacion,
			String provincia, String codigoPostal,  String telefono, String fechaNacimiento) {
		this.codigo_p = codigo_p;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.provincia = provincia;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
	}

	/** GETTERS Y SETTERS */
	public int getCodigo_p() {
		return codigo_p;
	}

	public void setCodigo(int codigo_p) {
		this.codigo_p = codigo_p;
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/** METODO TOSTRING */
	@Override
	public String toString() {
		String vis="";
		vis+="====PACIENTE====\n";
		vis+="Código: "+codigo_p+"\n";
		vis+="Nombre: "+nombre+"\n";
		vis+="Apellidos: "+apellidos+"\n";
		vis+="Fecha de nacimiento: "+fechaNacimiento+"\n";
		vis+="Teléfono: "+telefono+"\n";
		vis+="Dirección: "+direccion+"\n";
		vis+="CP: "+codigoPostal+"\n";
		vis+="Población: "+poblacion+"\n";
		vis+="Provincia: "+provincia+"\n";
		vis+="================\n";
		return vis;
	}

	
	
}
