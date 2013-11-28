package fmat.arquitectura.Seguridad.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;
import fmat.arquitectura.Seguridad.Modelo.Accion;

public class DAOAccion {
	public void insertarAccion(Accion accion){
		try{
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "INSERT INTO "+TABLA_ACCION+" (Nombre) VALUES ("+accion.getNombre()+")";
			
			st.executeUpdate(query);
			st.close();
			conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void eliminarAccion(String nombreAccion){
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "DELETE FROM "+TABLA_ACCION+" WHERE Nombre = '"+nombreAccion+"'";
			
			st.executeUpdate(query);
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void actualizarAccion(Accion accion){
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "UPDATE "+TABLA_ACCION+" SET"
						  +" Nombre = '"+accion.getNombre()+"'"
						  +" WHERE ID = "+accion.getId();
			
			st.executeUpdate(query);
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Accion consultarAccionPorID(int idAccion){
		Accion accion = null;
		
		try{
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "SELECT * FROM "+ TABLA_ACCION +" WHERE ID = "+ idAccion;
		
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()){
				String nombre = rs.getString("Nombre");
				
				accion = new Accion(nombre);
				accion.setId(idAccion);
			}
			st.close();
			conn.close();
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
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "SELECT * FROM "+ TABLA_ACCION +" WHERE Nombre = '"+ nombreAccion+"'";
		
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()){
				int id = rs.getInt("ID");
				accion = new Accion(nombreAccion);
				accion.setId(id);
			}
			st.close();
			conn.close();
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
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "SELECT * FROM "+TABLA_ACCION;
		
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				String nombre = rs.getString("Nombre");
				boolean estado = rs.getBoolean("Estado");
				Accion accion = new Accion(nombre, estado);
				listaAcciones.add(accion);
			}
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaAcciones;
	}

	final String TABLA_ACCION = "accion";
}
