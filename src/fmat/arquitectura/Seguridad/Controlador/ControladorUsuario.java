package fmat.arquitectura.Seguridad.Controlador;

import java.util.ArrayList;
import java.util.List;

import fmat.arquitectura.Seguridad.DAO.DAOUsuario;
import fmat.arquitectura.Seguridad.Encriptado.Encriptador;
import fmat.arquitectura.Seguridad.Modelo.Accion;
import fmat.arquitectura.Seguridad.Modelo.Usuario;

public class ControladorUsuario {

	public void guardarUsuarioNuevo(Usuario usr){
		DAOUsuario usuarioDAO = new DAOUsuario();
		usuarioDAO.insertarUsuario(usr);
	}
	
	public void eliminarUsuario(String nomUsua){
		DAOUsuario usuarioDAO = new DAOUsuario();
		usuarioDAO.eliminarUsuario(nomUsua);
	}
	
	public void actualizarUsuario(Usuario usr){
		DAOUsuario usuarioDAO = new DAOUsuario();
		usuarioDAO.actualizarUsuario(usr);
	}
	
	public Usuario buscarUsuarioPorNombre(String nombre){
		DAOUsuario usuarioDAO = new DAOUsuario();
		return usuarioDAO.consultarUsuarioPorNombre(nombre);
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
	//Funcion para saber si el usuario tiene la accion activada, primero determina si el perfil tiene la accion
	public boolean estaAccionActivaEnUsuario(Usuario usuario, String nombreAccion)throws Exception{
		
		if(estaAccionEnUsuario(usuario, nombreAccion)){
			Accion accion = buscarAccionEnUsuario( usuario, nombreAccion);
			return accion.getEstado();
		}else{
			throw new Exception("El usuario no tiene la accion");
		}
	}
	//Funcion que devuelve la accion del usuario que se esta buscando
	private Accion buscarAccionEnUsuario(Usuario usuario, String nAccion){
		Accion accionEncontrada = null;
		for(Accion accion: usuario.getListaAcciones()){
			if(accion.getNombre().compareTo(nAccion)==0){
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
				String contraseñaBD = encriptador.desencriptar(usuario.getContraseña());
				if(contraseñaBD.compareTo(contrasena)!=0){
					throw new Exception("Contraseña incorrecta.");
				}
			}
			else {
				throw new Exception("No has creado el encriptador.");
			}
		}
		return usuario;
	}
}
