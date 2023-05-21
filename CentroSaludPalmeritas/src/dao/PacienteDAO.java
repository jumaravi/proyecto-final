package dao;

import java.util.List;

import main.dto.Paciente;

public interface PacienteDAO {
	
	  public List<Paciente> getPacientes();
	    public void crearPaciente(Paciente paciente);
	    public void actualizarPaciente(Paciente paciente);
	    public void borrarPaciente(int idPaciente);
	    public Paciente getPaciente(int idPaciente);
	    public List<Paciente> getPacientesPorProvincia(String provincia); 
}
