package main.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PacienteDAO;
import main.dto.Ingreso;
import main.dto.Paciente;

public class PacienteJDBC implements PacienteDAO {

	/** ATRIBUTOS */
	private GestorConexion con;
	private IngresoJDBC ingresoJDBC = new IngresoJDBC();

	/** CONSTRUCTOR */
	public PacienteJDBC() {
		// Crear objeto de la conexion
		this.con = new GestorConexion("mysql", "palmeritas", "root", "");
	}

	/** MOSTRAR TODOS LOS PACIENTES */
	@Override
	public List<Paciente> getPacientes() {
		this.con.abrirConexion();
		ArrayList<Paciente> pacientes = new ArrayList<>();
		ResultSet res = this.con.ejecutarSQL("SELECT * from paciente;");

		// MOVIMIENTO DEL CURSOR PARA LEER LOS REGISTROS Y RELLENAR LA LISTA
		try {
			while (this.con.getRegistro().next()) {
				Paciente paciente = new Paciente(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9));
				pacientes.add(paciente);
			}
		} catch (SQLException ex) {
		}
		this.con.cerrarConexion();
		return pacientes;
	}

	/** MOSTRAR PACIENTES POR PROVINCIA */
	@Override
	public List<Paciente> getPacientesPorProvincia(String especialidad) {
		this.con.abrirConexion();
		ArrayList<Paciente> pacientes = new ArrayList<>();
		ResultSet res = this.con.ejecutarSQL("SELECT * FROM paciente WHERE especialidad=" + especialidad + ";");

		// MOVIMIENTO DEL CURSOR PARA LEER LOS REGISTROS Y RELLENAR LA LISTA
		try {
			while (this.con.getRegistro().next()) {
				Paciente paciente = new Paciente(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9));
				pacientes.add(paciente);
			}
		} catch (SQLException ex) {
		}
		this.con.cerrarConexion();
		return pacientes;
	}

	/** MOSTRAR PACIENTE POR SU ID */
	@Override
	public Paciente getPaciente(int id) {
		this.con.abrirConexion();
		Paciente paciente = null;
		ResultSet res = this.con.ejecutarSQL("SELECT * FROM paciente WHERE codigo_p =" + id + ";");

		// MOVIMIENTO DEL CURSOR PARA LEER LOS REGISTROS
		try {
			while (this.con.getRegistro().next()) {
				paciente = new Paciente(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9));
			}
		} catch (SQLException ex) {
		}
		this.con.cerrarConexion();
		return paciente;
	}
	
	

	/** INSERTAR PACIENTE */
	@Override
	public void crearPaciente(Paciente m) {

		try {
			Connection conexion = this.con.abrirConexion();

			// Inserta un nuevo paciente a la BBDD
			String sql = "INSERT INTO paciente (nombre,apellidos,fechaNacimiento,telefono,direccion,codigoPostal,poblacion,provincia,codigo_p) VALUES (?,?,?,?,?,?,?,?,?)";
			// Creamos un objeto de tipo PreparedStatement:
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			// asignamos los parámetros:
			sentencia.setString(1, m.getNombre());
			sentencia.setString(2, m.getApellidos());
			sentencia.setString(3, m.getFechaNacimiento());
			sentencia.setString(4, m.getTelefono());
			sentencia.setString(5, m.getDireccion());
			sentencia.setString(6, m.getCodigoPostal());
			sentencia.setString(7, m.getPoblacion());
			sentencia.setString(8, m.getProvincia());

			// Auto incrementable del código del paciente
			// Me traigo todos los paciente
			ArrayList<Paciente> pacientes = (ArrayList<Paciente>) this.getPacientes();

			// Compruebo el id del último médico y le sumo uno
			int codigo = pacientes.get(pacientes.size() - 1).getCodigo_p();
			codigo += 1;
			sentencia.setInt(9, codigo);

			// Ejecutamos la consulta
			sentencia.execute();
			this.con.cerrarConexion();
			System.out.println("Insercción Completada");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/** ACTUALIZAR PACIENTE */
	@Override
	public void actualizarPaciente(Paciente paciente) {
		Connection conexion = this.con.abrirConexion();

		String sql = "UPDATE paciente SET nombre = ?, apellidos = ?, fechaNacimiento = ?, Telefono = ?, Direccion = ?, Poblacion = ?, Provincia = ? WHERE codigo_p = ?;";
		// Creamos un objeto de tipo PreparedStatement:
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);
			// asignamos los parámetros:
			sentencia.setString(1, paciente.getNombre());
			sentencia.setString(2, paciente.getApellidos());
			sentencia.setString(3, paciente.getFechaNacimiento());
			sentencia.setString(4, paciente.getTelefono());
			sentencia.setString(5, paciente.getDireccion());
			sentencia.setString(6, paciente.getPoblacion());
			sentencia.setString(7, paciente.getProvincia());
			sentencia.setInt(8, paciente.getCodigo_p());

			// Ejecutamos la actualización
			sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.con.cerrarConexion();
		System.out.println("Update Completada");
	}

	/** BORRAR PACIENTE */
	@Override
	public void borrarPaciente(int paciente) {

		// PARA BORRAR REGISTROS DE TABLAS ASOCIADAS
		for (Ingreso i : ingresoJDBC.getIngresosPorPaciente(paciente)) {
			ingresoJDBC.borrarIngreso(i.getCodigo_i());
		}

		Connection conexion = this.con.abrirConexion();
		String sql = "DELETE FROM paciente WHERE codigo_p = ?;";

		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);
			// asignamos los parámetros:
			sentencia.setInt(1, paciente);
			// Ejecutamos la actualización
			sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.con.cerrarConexion();
		System.out.println("Delete Completada");
	}

}
