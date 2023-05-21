package dao;

import java.util.List;

import main.dto.Ingreso;

public interface IngresoDAO {
	 public List<Ingreso> getIngresos();
	    public void crearIngreso(Ingreso ingreso);
	    public void actualizarIngreso(Ingreso ingreso);
	    public void borrarIngreso(int ingreso);
	    public Ingreso getIngreso(int idIngreso);
	    public List<Ingreso> getIngresosPorMedico(int codMedico);
	    public List<Ingreso> getIngresosPorPaciente(int codPaciente);
}
