package dao;

import java.util.List;

import main.dto.Medico;

public interface MedicoDAO {

    public List<Medico> getMedicos();
    public void crearMedico(Medico medico);
    public void actualizarMedico(Medico medico);
    public void borrarMedico(int idMedico);
    public Medico getMedico(int idMedico);
    public List<Medico> getMedicosPorEspecialidad(String especialidad); 
}
