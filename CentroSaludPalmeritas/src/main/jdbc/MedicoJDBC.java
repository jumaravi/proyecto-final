package main.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MedicoDAO;
import main.dto.Ingreso;
import main.dto.Medico;

public class MedicoJDBC implements MedicoDAO {

	/** ATRIBUTOS */
	private GestorConexion con;
	private IngresoJDBC ingresoJDBC = new IngresoJDBC();

	/** CONSTRUCTOR */
	public MedicoJDBC() {
		// Crear objeto de la conexion
		this.con = new GestorConexion("mysql", "palmeritas", "root", "");
	}

	/** MOSTRAR TODOS LOS MEDICOS */
	@Override
	public List<Medico> getMedicos() {
		this.con.abrirConexion();
		ArrayList<Medico> medicos = new ArrayList<>();
		ResultSet res = this.con.ejecutarSQL("SELECT * from medico;");

		// MOVIMIENTO DEL CURSOR PARA LEER LOS REGISTROS Y RELLENAR LA LISTA
		try {
			while (this.con.getRegistro().next()) {
				Medico medico = new Medico(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5));
				medicos.add(medico);
			}
		} catch (SQLException ex) {
		}
		this.con.cerrarConexion();
		return medicos;
	}

	/** MOSTRAR MEDICOS POR ESPECIALIDAD */
	@Override
	public List<Medico> getMedicosPorEspecialidad(String especialidad) {
		this.con.abrirConexion();
		ArrayList<Medico> medicos = new ArrayList<>();
		ResultSet res = this.con.ejecutarSQL("SELECT * FROM medico WHERE especialidad=" + especialidad + ";");

		// MOVIMIENTO DEL CURSOR PARA LEER LOS REGISTROS Y RELLENAR LA LISTA
		try {
			while (this.con.getRegistro().next()) {
				Medico medico = new Medico(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5));
				medicos.add(medico);
			}
		} catch (SQLException ex) {
		}
		this.con.cerrarConexion();
		return medicos;
	}

	/** MOSTRAR MEDICO POR SU ID */
	@Override
	public Medico getMedico(int id) {
		this.con.abrirConexion();
		Medico medico = null;
		ResultSet res = this.con.ejecutarSQL("SELECT * FROM medico WHERE codigo_m=" + id + ";");

		// MOVIMIENTO DEL CURSOR PARA LEER LOS REGISTROS
		try {
			while (this.con.getRegistro().next()) {
				medico = new Medico(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5));
			}
		} catch (SQLException ex) {
		}
		this.con.cerrarConexion();
		return medico;
	}

	/** INSERTAR MEDICO */
	@Override
	public void crearMedico(Medico m) {

		try {
			Connection conexion = this.con.abrirConexion();
			String sql = "INSERT INTO medico (nombre,apellidos,telefono,especialidad,codigo_m) VALUES (?,?,?,?,?)";

			// Creamos un objeto de tipo PreparedStatement:
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			// asignamos los parámetros:
			sentencia.setString(1, m.getNombre());
			sentencia.setString(2, m.getApellidos());
			sentencia.setString(3, m.getTelefono());
			sentencia.setString(4, m.getEspecialidad());

			// Auto incrementable del código del médico
			// Me traigo todos los médicos
			ArrayList<Medico> medicos = (ArrayList<Medico>) this.getMedicos();

			// Compruebo el id del último médico y le sumo uno
			int codigo = medicos.get(medicos.size() - 1).getCodigo_m();
			codigo += 1;
			sentencia.setInt(5, codigo);

			// Ejecutamos la consulta
			sentencia.execute();
			this.con.cerrarConexion();
			System.out.println("Insercción Completada");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/** ACTUALIZAR MEDICO */
	@Override
	public void actualizarMedico(Medico medico) {
		Connection conexion = this.con.abrirConexion();

		String sql = "UPDATE medico SET nombre = ?, apellidos = ?, telefono = ?, especialidad = ? WHERE codigo_m = ?;";

		// Creamos un objeto de tipo PreparedStatement:
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);

			// asignamos los parámetros:
			sentencia.setString(1, medico.getNombre());
			sentencia.setString(2, medico.getApellidos());
			sentencia.setString(3, medico.getTelefono());
			sentencia.setString(4, medico.getEspecialidad());
			sentencia.setInt(5, medico.getCodigo_m());
			// Ejecutamos la actualización
			sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.con.cerrarConexion();
		System.out.println("Update Completada");
	}

	/** BORRAR MEDICO */
	@Override
	public void borrarMedico(int medico) {

		// PARA BORRAR REGISTROS DE TABLAS ASOCIADAS
		for (Ingreso i : ingresoJDBC.getIngresosPorMedico(medico)) {
			ingresoJDBC.borrarIngreso(i.getCodigo_i());
		}

		Connection conexion = this.con.abrirConexion();
		String sql = "DELETE FROM medico WHERE codigo_m = ?;";

		// Creamos un objeto de tipo PreparedStatement:
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);

			// asignamos los parámetros:
			sentencia.setInt(1, medico);

			// Ejecutamos la actualización
			sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.con.cerrarConexion();
		System.out.println("Delete Completada");
	}

}
