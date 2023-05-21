package main.dto;

public class Ingreso {

	/** ATRIBUTOS */
	private int codigo_i;
	private int numeroHabitacion;
	private String cama;
	private String fechaIngreso;
	private Paciente paciente; // Clave foránea de la clase Paciente
	private Medico medico; // Clave foránea de la clase Medico

	/** CONSTRUCTOR VACIO */
	public Ingreso() {
	}

	/**
	 * CONSTRUCTOR @param codigo_i @param numeroHabitacion @param cama @paramfechaIngreso @param paciente
	 *  @param medico
	 */
	public Ingreso(int codigo_i, int numeroHabitacion, String cama, String fechaIngreso, Paciente paciente,
			Medico medico) {

		this.codigo_i = codigo_i;
		this.numeroHabitacion = numeroHabitacion;
		this.cama = cama;
		this.fechaIngreso = fechaIngreso;
		this.paciente = paciente;
		this.medico = medico;
	}

	/** GETTERS Y SETTERS */
	public int getCodigo_i() {
		return codigo_i;
	}

	public void setCodigo(int codigo_i) {
		this.codigo_i = codigo_i;
	}

	public int getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(int numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public String getCama() {
		return cama;
	}

	public void setCama(String cama) {
		this.cama = cama;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	/** METODO TOSTRING */
	@Override
	public String toString() {
		String vis="";
		vis+="====INGRESOS====\n";
		vis+="Código: "+codigo_i+"\n";
		vis+="Número de habitación: "+numeroHabitacion+"\n";
		vis+="Cama: "+cama+"\n";
		vis+="Fecha de ingreso: "+fechaIngreso+"\n";
		vis+="Paciente: "+paciente.getCodigo_p()+ " "+paciente.getNombre()+" "+paciente.getApellidos()+"\n";
		vis+="Médico: "+medico.getCodigo_m()+ " "+medico.getNombre()+" "+medico.getApellidos()+"\n";
		vis+="==================\n";
		return vis;
	}

}
