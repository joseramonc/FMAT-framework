package fmat.arquitectura.Seguridad.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;
import fmat.arquitectura.DBAccess.modelo.DBConnection;
import fmat.arquitectura.Seguridad.Modelo.Accion;

public class DAOAccion {
	public void insertarAccion(Accion accion){
		DBConnectionFactory DBC = new DBConnectionFactory();
		DBConnection conn = DBC.createConnection();
			
		String query = "INSERT INTO "+TABLA_ACCION+" (Nombre) VALUES ("+accion.getNombre()+")";
			
		conn.execute(query);
	}
	public void eliminarAccion(String nombreAccion){
		DBConnectionFactory DBC = new DBConnectionFactory();
		DBConnection conn = DBC.createConnection();
		
		String query = "DELETE FROM "+TABLA_ACCION+" WHERE Nombre = '"+nombreAccion+"'";
		
		conn.execute(query);
	}
	public void actualizarAccion(Accion accion){
		DBConnectionFactory DBC = new DBConnectionFactory();
		DBConnection conn = DBC.createConnection();
		
		String query = "UPDATE "+TABLA_ACCION+" SET"
						+" Nombre = '"+accion.getNombre()+"'"
						+" WHERE ID = "+accion.getId();
		
		conn.execute(query);
	}
	
	public Accion consultarAccionPorID(int idAccion){
		Accion accion = null;
		
		try{
			DBConnectionFactory DBC = new DBConnectionFactory();
			DBConnection conn = DBC.createConnection();
			
			String query = "SELECT * FROM "+ TABLA_ACCION +" WHERE ID = "+ idAccion;
		
			ResultSet rs = conn.executeQuery(query);
			
			if(rs.next()){
				String nombre = rs.getString("Nombre");
				
				accion = new Accion(nombre);
				accion.setId(idAccion);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return accion;
	}
	public Accion consultarAccionPorNombre(String nombreAccion){
		Accion accion = null;
		try{
			DBConnectionFactory DBC = new DBConnectionFactory();
			DBConnection conn = DBC.createConnection();
			
			String query = "SELECT * FROM "+ TABLA_ACCION +" WHERE Nombre = '"+ nombreAccion+"'";
		
			ResultSet rs = conn.executeQuery(query);
			
			if(rs.next()){
				int id = rs.getInt("ID");
				accion = new Accion(nombreAccion);
				accion.setId(id);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return accion;
	}

	public ArrayList<Accion> consultarAcciones(){
		ArrayList<Accion> listaAcciones = new ArrayList<Accion>();
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			DBConnection conn = DBC.createConnection();
			
			String query = "SELECT * FROM "+TABLA_ACCION;
		
			ResultSet rs = conn.executeQuery(query);
			while(rs.next()){
				String nombre = rs.getString("Nombre");
				boolean estado = rs.getBoolean("Estado");
				Accion accion = new Accion(nombre, estado);
				listaAcciones.add(accion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAcciones;
	}

	final String TABLA_ACCION = "accion";
}
