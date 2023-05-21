	package main.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IngresoDAO;
import main.dto.Ingreso;

public class IngresoJDBC implements IngresoDAO {

	/** ATRIBUTOS */
	private GestorConexion con;

	/** CONSTRUCTOR */
	public IngresoJDBC() {
		// Crear objeto de la conexion
		this.con = new GestorConexion("mysql", "palmeritas", "root", "");
	}

	/** MOSTRAR TODOS LOS INGRESO */
	@Override
	public List<Ingreso> getIngresos() {
		this.con.abrirConexion();
		ArrayList<Ingreso> ingresos = new ArrayList<>();
		ResultSet res = this.con.ejecutarSQL("SELECT * from ingreso;");
		MedicoJDBC medicoJDBC = new MedicoJDBC();
		PacienteJDBC pacienteJDBC = new PacienteJDBC();

		//MOVIMIENTO DEL CURSOR PARA LEER LOS REGISTROS Y RELLENAR LA LISTA 
		try {
			while (this.con.getRegistro().next()) {
				Ingreso ingreso = new Ingreso(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4),
						pacienteJDBC.getPaciente(res.getInt(5)), medicoJDBC.getMedico(res.getInt(6)));
				ingresos.add(ingreso);
			}
		} catch (SQLException ex) {
		}
		this.con.cerrarConexion();
		return ingresos;
	}

	/** MOSTRAR INGRESOS POR MEDICO */
	@Override
	public List<Ingreso> getIngresosPorMedico(int codMedico) {
		this.con.abrirConexion();
		ArrayList<Ingreso> ingresos = new ArrayList<>();
		ResultSet res = this.con.ejecutarSQL("SELECT * FROM ingreso WHERE codigo_m =" + codMedico + ";");
		MedicoJDBC medicoJDBC = new MedicoJDBC();
		PacienteJDBC pacienteJDBC = new PacienteJDBC();

		// MOVIMIENTO DEL CURSOR PARA LEER LOS REGISTROS Y RELLENAR LA LISTA
		try {
			while (this.con.getRegistro().next()) {
				Ingreso ingreso = new Ingreso(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4),
						pacienteJDBC.getPaciente(res.getInt(5)), medicoJDBC.getMedico(res.getInt(6)));
				ingresos.add(ingreso);
			}
		} catch (SQLException ex) {
		}
		this.con.cerrarConexion();
		return ingresos;

	}

	/** MOSTRAR INGRESOS POR ID DE INGRESO */
	@Override
	public Ingreso getIngreso(int idIngreso) {
		this.con.abrirConexion();
		Ingreso ingreso = null;
		ResultSet res = this.con.ejecutarSQL("SELECT * FROM ingreso WHERE codigo_i =" + idIngreso + ";");
		MedicoJDBC medicoJDBC = new MedicoJDBC();
		PacienteJDBC pacienteJDBC = new PacienteJDBC();

		// MOVIMIENTO DEL CURSOR PARA LEER LOS REGISTROS
		try {
			while (this.con.getRegistro().next()) {
				ingreso = new Ingreso(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4),
						pacienteJDBC.getPaciente(res.getInt(5)), medicoJDBC.getMedico(res.getInt(6)));
			}
		} catch (SQLException ex) {
		}
		this.con.cerrarConexion();
		return ingreso;
	}

	/** MOSTRAR INGRESOS POR ID DE PACIENTE */
	@Override
	public List<Ingreso> getIngresosPorPaciente(int codPaciente) {
		this.con.abrirConexion();
		ArrayList<Ingreso> ingresos = new ArrayList<>();
		ResultSet res = this.con.ejecutarSQL("SELECT * FROM ingreso WHERE codigo_p =" + codPaciente + ";");
		MedicoJDBC medicoJDBC = new MedicoJDBC();
		PacienteJDBC pacienteJDBC = new PacienteJDBC();

		// MOVIMIENTO DEL CURSOR PARA LEER LOS REGISTROS Y RELLENAR LA LISTA
		try {
			while (this.con.getRegistro().next()) {
				Ingreso ingreso = new Ingreso(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4),
						pacienteJDBC.getPaciente(res.getInt(5)), medicoJDBC.getMedico(res.getInt(6)));
				ingresos.add(ingreso);
			}
		} catch (SQLException ex) {
		}
		this.con.cerrarConexion();
		return ingresos;

	}

	/** INSERTAR INGRESO */
	@Override
	public void crearIngreso(Ingreso m) {

		try {
			Connection conexion = this.con.abrirConexion();
			String sql = "INSERT INTO ingreso (numeroHabitacion,cama,fechaIngreso,codigo_p,codigo_m,codigo_i) VALUES (?,?,?,?,?,?)";

			// Creamos un objeto de tipo PreparedStatement:
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			// asignamos los parámetros:
			sentencia.setInt(1, m.getNumeroHabitacion());
			sentencia.setString(2, m.getCama());
			sentencia.setString(3, m.getFechaIngreso());
			sentencia.setInt(4, m.getPaciente().getCodigo_p());
			sentencia.setInt(5, m.getMedico().getCodigo_m());

			// Auto incrementable del código del paciente
			// Me traigo todos los paciente
			ArrayList<Ingreso> ingresos = (ArrayList<Ingreso>) this.getIngresos();

			// Compruebo el id del último paciente y le sumo uno
			int codigo = ingresos.get(ingresos.size() - 1).getCodigo_i();
			codigo += 1;
			sentencia.setInt(6, codigo);

			// Ejecutamos la consulta
			sentencia.execute();
			this.con.cerrarConexion();
			System.out.println("Insercción Completada");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/** ACTUALIZAR INGRESO */
	@Override
	public void actualizarIngreso(Ingreso m) {
		Connection conexion = this.con.abrirConexion();

		String sql = "UPDATE ingreso SET numeroHabitacion = ?, cama = ?, fechaIngreso = ?, codigo_p = ?, codigo_m = ? WHERE codigo_i = ?;";

		// Creamos un objeto de tipo PreparedStatement:
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);

			// asignamos los parámetros:
			sentencia.setInt(1, m.getNumeroHabitacion());
			sentencia.setString(2, m.getCama());
			sentencia.setString(3, m.getFechaIngreso());
			sentencia.setInt(4, m.getPaciente().getCodigo_p());
			sentencia.setInt(5, m.getMedico().getCodigo_m());
			sentencia.setInt(6, m.getCodigo_i());

			// Ejecutamos la actualización
			sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.con.cerrarConexion();
		System.out.println("Update Completada");
	}

	/** BORRAR INGRESO */
	@Override
	public void borrarIngreso(int ingreso) {
		Connection conexion = this.con.abrirConexion();
		String sql = "DELETE FROM ingreso WHERE codigo_i = ?;";

		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);

			// asignamos los parámetros:
			sentencia.setInt(1, ingreso);

			// Ejecutamos la actualización
			sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.con.cerrarConexion();
		System.out.println("Delete Completada");
	}

}
