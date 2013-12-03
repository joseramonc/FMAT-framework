package fmat.arquitectura.Seguridad.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;
import fmat.arquitectura.Seguridad.Modelo.Accion;
import fmat.arquitectura.Seguridad.Modelo.Perfil;
import fmat.arquitectura.Seguridad.Modelo.Usuario;

public class DAOUsuario {
	public void insertarUsuario(Usuario usuario){
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "INSERT INTO "+TABLA_USUARIOS+" (Alias, Contrasenia, Perfil) VALUES ("+
							"'"+usuario.getNombre()+"',"+
							"'"+usuario.getContrasenia()+"',"+
							    usuario.getPerfil().getId()+")";
			
			st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			
			if(usuario.getListaAcciones()!=null){
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					int idUsuario = rs.getInt(1);
					insertarAccionesUsuario(idUsuario, usuario.getListaAcciones());
				}
			}
			
			st.close();
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void eliminarUsuario(String nombreUsuario){
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "DELETE FROM "+TABLA_USUARIOS+" WHERE Alias = '"+nombreUsuario+"'";
			
			st.executeUpdate(query);
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void actualizarUsuario(Usuario usuario){
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "UPDATE "+TABLA_USUARIOS+" SET"
						  +" Nombre = '"+usuario.getNombre()+"'"
						  +" WHERE ID = "+usuario.getId();
			
			st.executeUpdate(query);
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Usuario consultarUsuarioPorNombre(String nombreUsuario){
		Usuario usuario = null;
		
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "SELECT * FROM "+TABLA_USUARIOS+" WHERE Alias = '"+nombreUsuario+"'";
		
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()){
				int idUsuario = rs.getInt("ID");
				String contrasena = rs.getString("Contrasenia");
				int idPerfil = rs.getInt("Perfil");
				
				DAOPerfil daoPerfil = new DAOPerfil();
				Perfil perfil = daoPerfil.consultarPerfilPorID(idPerfil);
				
				ArrayList<Accion> listaAcciones = consultarAccionesDeUsuario(idUsuario);
				
				usuario = new Usuario(nombreUsuario, contrasena, perfil, listaAcciones);
				usuario.setId(idUsuario);
			}
			st.close();
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	public ArrayList<Accion> consultarAccionesDeUsuario(int idUsuario){
		ArrayList<Accion> listaAcciones = new ArrayList<Accion>();
		
		try{
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "SELECT * FROM "+ TABLA_USUARIOACCION +" WHERE IDUsuario = "+ idUsuario;
		
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
	public ArrayList<Usuario> consultarUsuarios(){
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "SELECT * FROM "+TABLA_USUARIOS;
		
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int idUsuario = rs.getInt("ID");
				String nombre = rs.getString("Alias");
				String contrasena = rs.getString("Contrasenia");
				int idPerfil = rs.getInt("Perfil");
				
				DAOPerfil daoPerfil = new DAOPerfil();
				Perfil perfil = daoPerfil.consultarPerfilPorID(idPerfil);
				
				ArrayList<Accion> listaAcciones = consultarAccionesDeUsuario(idUsuario);
				
				Usuario usuario = new Usuario(nombre, contrasena, perfil, listaAcciones);
				usuario.setId(idUsuario);
				
				listaUsuarios.add(usuario);
			}
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaUsuarios;
	}
	
	public void insertarAccionesUsuario(int idUsuario, ArrayList<Accion> listaAcciones){
		
		try{
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			for(Accion accion: listaAcciones){
				Statement st = conn.createStatement();
				String query = "INSERT INTO "+TABLA_USUARIOACCION+" (IDUsuario, IDAccion, Estado) VALUES ("
								+idUsuario+","
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
	public void eliminarAccionDeUsuario(int idUsuario, Accion accion){
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "DELETE FROM "+TABLA_USUARIOACCION+" WHERE IDUsuario = "+idUsuario+" ADN IDAccion = "+accion.getId();
			
			st.executeUpdate(query);
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void actualizarAccionDeUsuario(int idUsuario, Accion accion){
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			Connection conn = DBC.createConnection();
			
			Statement st = conn.createStatement();
			String query = "UPDATE "+TABLA_USUARIOACCION+" SET"
						  +" Estado = "+accion.getEstado()
						  +" WHERE IDUsuario = "+idUsuario+" ADN IDAccion = "+accion.getId();
			
			st.executeUpdate(query);
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	final String TABLA_USUARIOS = "usuario";
	final String TABLA_USUARIOACCION = "usuarioaccion";
}
