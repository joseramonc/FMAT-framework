package fmat.arquitectura.Seguridad.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;
import fmat.arquitectura.DBAccess.modelo.DBConnection;
import fmat.arquitectura.Seguridad.Modelo.Accion;
import fmat.arquitectura.Seguridad.Modelo.Perfil;
import fmat.arquitectura.Seguridad.Modelo.Usuario;

public class DAOUsuario {
	public void insertarUsuario(Usuario usuario){
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			DBConnection conn = DBC.createConnection();
			
			String query = "INSERT INTO "+TABLA_USUARIOS+" (Alias, Contrasenia, Perfil) VALUES ("+
							"'"+usuario.getNombre()+"',"+
							"'"+usuario.getContrasenia()+"',"+
							    usuario.getPerfil().getId()+")";
			
			ResultSet rs = conn.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			
			if(usuario.getListaAcciones()!=null && rs!=null && rs.next()){
				int idUsuario = rs.getInt(1);
				insertarAccionesUsuario(idUsuario, usuario.getListaAcciones());
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void eliminarUsuario(String nombreUsuario){
		DBConnectionFactory DBC = new DBConnectionFactory();
		DBConnection conn = DBC.createConnection();
		
		String query = "DELETE FROM "+TABLA_USUARIOS+" WHERE Alias = '"+nombreUsuario+"'";
		
		conn.execute(query);
	}
	public void actualizarUsuario(Usuario usuario){
		DBConnectionFactory DBC = new DBConnectionFactory();
		DBConnection conn = DBC.createConnection();
		
		String query = "UPDATE "+TABLA_USUARIOS+" SET"
					  +" Nombre = '"+usuario.getNombre()+"'"
					  +" WHERE ID = "+usuario.getId();
			
		conn.execute(query);
			
	}
	
	public Usuario consultarUsuarioPorNombre(String nombreUsuario){
		Usuario usuario = null;
		
		try {
			DBConnectionFactory DBC = new DBConnectionFactory();
			DBConnection conn = DBC.createConnection();
			
			String query = "SELECT * FROM "+TABLA_USUARIOS+" WHERE Alias = '"+nombreUsuario+"'";
		
			ResultSet rs = conn.executeQuery(query);
			
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
			DBConnection conn = DBC.createConnection();
			
			String query = "SELECT * FROM "+ TABLA_USUARIOACCION +" WHERE IDUsuario = "+ idUsuario;
			
			ResultSet rs = conn.executeQuery(query);
			
			while(rs.next()){
				int idAccion = rs.getInt("IDAccion");
				boolean estado = rs.getBoolean("Estado");
				
				DAOAccion daoAccion = new DAOAccion();
				Accion accion = daoAccion.consultarAccionPorID(idAccion);
				accion.setEstado(estado);
				
				listaAcciones.add(accion);
			}
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
			DBConnection conn = DBC.createConnection();
			
			String query = "SELECT * FROM "+TABLA_USUARIOS;
		
			ResultSet rs = conn.executeQuery(query);
			
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaUsuarios;
	}
	
	public void insertarAccionesUsuario(int idUsuario, ArrayList<Accion> listaAcciones){

		DBConnectionFactory DBC = new DBConnectionFactory();
		DBConnection conn = DBC.createConnection();
		
		for(Accion accion: listaAcciones){
			String query = "INSERT INTO "+TABLA_USUARIOACCION+" (IDUsuario, IDAccion, Estado) VALUES ("
							+idUsuario+","
							+accion.getId()+","
							+accion.getEstado()
							+")";
			
			conn.execute(query);
		}
	}
	public void eliminarAccionDeUsuario(int idUsuario, Accion accion){
		DBConnectionFactory DBC = new DBConnectionFactory();
		DBConnection conn = DBC.createConnection();
		
		String query = "DELETE FROM "+TABLA_USUARIOACCION+" WHERE IDUsuario = "+idUsuario+" ADN IDAccion = "+accion.getId();
		
		conn.execute(query);
	}
	public void actualizarAccionDeUsuario(int idUsuario, Accion accion){
		DBConnectionFactory DBC = new DBConnectionFactory();
		DBConnection conn = DBC.createConnection();
		
		String query = "UPDATE "+TABLA_USUARIOACCION+" SET"
					  +" Estado = "+accion.getEstado()
					  +" WHERE IDUsuario = "+idUsuario+" ADN IDAccion = "+accion.getId();
		
		conn.execute(query);
	}
	
	final String TABLA_USUARIOS = "usuario";
	final String TABLA_USUARIOACCION = "usuarioaccion";
}
