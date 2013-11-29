package fmat.arquitectura.Seguridad.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;
import fmat.arquitectura.Seguridad.Modelo.Accion;
import fmat.arquitectura.Seguridad.Modelo.Perfil;

public class DAOPerfil {
	public void insertarPerfil(Perfil perfil){
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "INSERT INTO "+TABLA_PERFILES+" (Nombre) VALUES ("+
							"'"+perfil.getNombre()+"')";
			
			st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			
			if(perfil.getListaAcciones()!=null){
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					int idPerfil = rs.getInt(1);
					insertarAccionesPerfil(idPerfil, perfil.getListaAcciones());
				}
			}
			st.close();
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void eliminarPerfil(String nombrePerfil){
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "DELETE FROM "+TABLA_PERFILES+" WHERE Nombre = '"+nombrePerfil+"'";
			
			st.executeUpdate(query);
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void actualizarPerfil(Perfil perfil){
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "UPDATE "+TABLA_PERFILES+" SET"
						  +" Nombre = '"+perfil.getNombre()+"'"
						  +" WHERE ID = "+perfil.getId();
			
			st.executeUpdate(query);
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Perfil consultarPerfilPorID(int idPerfil){
		Perfil perfil = null;
		
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "SELECT * FROM "+TABLA_PERFILES+" WHERE ID = "+idPerfil;
		
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()){
				String nombre = rs.getString("Nombre");
				
				ArrayList<Accion> listaAcciones = consultarAccionesDePerfil(idPerfil);
				
				perfil = new Perfil(nombre,listaAcciones);
				perfil.setId(idPerfil);
			}
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return perfil;
	}
	public Perfil consultarPerfilPorNombre(String nombrePerfil){
		Perfil perfil = null;
		
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "SELECT * FROM "+TABLA_PERFILES+" WHERE Nombre = '"+nombrePerfil+"'";
		
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()){
				int id = rs.getInt("ID");
				
				ArrayList<Accion> listaAcciones = consultarAccionesDePerfil(id);
				
				perfil = new Perfil(nombrePerfil,listaAcciones);
				perfil.setId(id);
			}
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return perfil;
	}
	public ArrayList<Perfil> consultarPerfiles(){
		ArrayList<Perfil> listaPerfiles = new ArrayList<Perfil>();
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "SELECT * FROM "+TABLA_PERFILES;
		
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int idPerfil = rs.getInt("ID");
				String nombre = rs.getString("Nombre");
				
				ArrayList<Accion> listaAcciones = consultarAccionesDePerfil(idPerfil);
				
				Perfil perfil = new Perfil(nombre,listaAcciones);
				perfil.setId(idPerfil);
				
				listaPerfiles.add(perfil);
			}
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaPerfiles;
	}
	
	public void insertarAccionesPerfil(int idPerfil, ArrayList<Accion> listaAcciones){

		try{
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();

			for(Accion accion: listaAcciones){
				Statement st = conn.createStatement();
				String query = "INSERT INTO "+TABLA_PERFILACCION+" (IDPerfil, IDAccion, Estado) VALUES ("
								+idPerfil+","
								+accion.getId()+","
								+accion.getEstado()
								+")";
				st.executeUpdate(query);
				st.close();
			}
			
			conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	public void eliminarAccionDePerfil(int idPerfil, int idAccion){
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "DELETE FROM "+TABLA_PERFILACCION+" WHERE IDPerfil = "+idPerfil+" ADN IDAccion = "+idAccion;
			
			st.executeUpdate(query);
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void actualizarAccionDePerfil(Accion accion, int idPerfil){//Cual es su objetivo con exactitud?
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "UPDATE "+TABLA_PERFILACCION+" SET"
						  +" Estado = "+accion.getEstado()
						  +" WHERE IDPerfil = "+idPerfil+" ADN IDAccion = "+accion.getId();
			
			st.executeUpdate(query);
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Accion> consultarAccionesDePerfil(int idPerfil){
		ArrayList<Accion> listaAcciones = new ArrayList<Accion>();
		
		try{
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "SELECT * FROM "+ TABLA_PERFILACCION +" WHERE IDPerfil = "+ idPerfil;
		
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int idAccion = rs.getInt("IDAccion");
				boolean estado = rs.getBoolean("Estado");
				
				DAOAccion daoAccion = new DAOAccion();
				Accion accion = daoAccion.consultarAccionPorID(idAccion);
				accion.setEstado(estado);
				
				listaAcciones.add(accion);
			}
			st.close();
			conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listaAcciones;
	}
	public boolean estaAccionEnPerfil(int idPerfil, int idAccion){
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "SELECT * FROM "+TABLA_PERFILACCION+" WHERE ID = "+idPerfil;
		
			ResultSet rs = st.executeQuery(query);
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	final String TABLA_PERFILES = "perfil";
	final String TABLA_PERFILACCION = "perfilaccion";
}
