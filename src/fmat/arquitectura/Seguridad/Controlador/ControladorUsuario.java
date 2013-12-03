package fmat.arquitectura.Seguridad.Controlador;

import java.util.ArrayList;
import java.util.List;

import fmat.arquitectura.Seguridad.DAO.DAOUsuario;
import fmat.arquitectura.Seguridad.Encriptado.Encriptador;
import fmat.arquitectura.Seguridad.Modelo.Accion;
import fmat.arquitectura.Seguridad.Modelo.Usuario;

public class ControladorUsuario {

	public void guardarUsuarioNuevo(Usuario usuario){
		DAOUsuario usuarioDAO = new DAOUsuario();
		usuarioDAO.insertarUsuario(usuario);
	}
	public void eliminarUsuario(String nombreUsuario){
		DAOUsuario usuarioDAO = new DAOUsuario();
		usuarioDAO.eliminarUsuario(nombreUsuario);
	}
	public void actualizarUsuario(Usuario usuario){
		DAOUsuario usuarioDAO = new DAOUsuario();
		usuarioDAO.actualizarUsuario(usuario);
	}
	
	public Usuario buscarUsuarioPorNombre(String nombreUsuario){
		DAOUsuario usuarioDAO = new DAOUsuario();
		return usuarioDAO.consultarUsuarioPorNombre(nombreUsuario);
	}
	
	public List<Usuario> obtenerTodosLosUsuarios(){
		DAOUsuario usuarioDAO = new DAOUsuario();
		List<Usuario> listaUsuarios = usuarioDAO.consultarUsuarios();
		return listaUsuarios;
	}
	
	//Funcion para UNICAMENTE saber si el usuario tiene la accion o no
	public boolean estaAccionEnUsuario(Usuario usuario, String nombreAccion){
		ArrayList<Accion> listaAcciones = usuario.getListaAcciones();
		Accion accion = new Accion(nombreAccion);
		boolean estaAccion = listaAcciones.contains(accion);
		if(estaAccion){
			return true;
		}else{
			return false;
		}
	}
	//Funcion para saber si el usuario tiene la accion activada; primero determina si el perfil tiene la accion
	public boolean estaAccionActivaEnUsuario(Usuario usuario, String nombreAccion)throws Exception{
		
		if(estaAccionEnUsuario(usuario, nombreAccion)){
			Accion accion = buscarAccionEnUsuario( usuario, nombreAccion);
			return accion.getEstado();
		}else{
			throw new Exception("El usuario no tiene la accion");
		}
	}
	//Funcion que devuelve la accion del usuario que se esta buscando
	private Accion buscarAccionEnUsuario(Usuario usuario, String nombreAccion){
		Accion accionEncontrada = null;
		for(Accion accion: usuario.getListaAcciones()){
			if(accion.getNombre().compareTo(nombreAccion)==0){
				accionEncontrada = accion;
			}
		}
		
		return accionEncontrada;
	}
	
	public Usuario login(String nombreUsuario, String contrasena) throws Exception{
		DAOUsuario usuarioDAO = new DAOUsuario();
		Usuario usuario;
		usuario = usuarioDAO.consultarUsuarioPorNombre(nombreUsuario);
		
		if(usuario == null){
			throw new Exception("No existe el usuario");
		}
		else{
			Encriptador encriptador =  Encriptador.getInstance();
			if(encriptador != null){
				String contraseniaBD = encriptador.desencriptar(usuario.getContrasenia());
				if(contraseniaBD.compareTo(contrasena)!=0){
					throw new Exception("Contrasenia incorrecta.");
				}
			}
			else {
				throw new Exception("No has creado el encriptador.");
			}
		}
		return usuario;
	}
}
