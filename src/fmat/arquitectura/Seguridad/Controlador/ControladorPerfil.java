package fmat.arquitectura.Seguridad.Controlador;

import java.util.ArrayList;
import java.util.List;

import fmat.arquitectura.Seguridad.DAO.DAOPerfil;
import fmat.arquitectura.Seguridad.Modelo.Accion;
import fmat.arquitectura.Seguridad.Modelo.Perfil;

public class ControladorPerfil {

	public void guardarPerfilNuevo(Perfil perfil){
		DAOPerfil perfilDAO = new DAOPerfil();
		perfilDAO.insertarPerfil(perfil);
	}
	public void eliminarPerfil(String nombrePerfil){
		DAOPerfil perfilDAO = new DAOPerfil();
		perfilDAO.eliminarPerfil(nombrePerfil);
	}
	public void actualizarPerfil(Perfil perfil){
		DAOPerfil perfilDAO = new DAOPerfil();
		perfilDAO.actualizarPerfil(perfil);
	}
	
	public Perfil obtenerPerfilPorID(int idPerfil){
		DAOPerfil perfilDAO = new DAOPerfil();
		return perfilDAO.consultarPerfilPorID(idPerfil);
	}
	public Perfil obtenerPerfilPorNombre(String nombrePerfil){
		DAOPerfil perfilDAO = new DAOPerfil();
		return perfilDAO.consultarPerfilPorNombre(nombrePerfil);
	}
	public ArrayList<Perfil> obtenerTodosLosPerfiles(){
		DAOPerfil perfilDAO = new DAOPerfil();
		return perfilDAO.consultarPerfiles();
	}
	
	public void insertarAccionesPerfil(int idPerfil, ArrayList<Accion> listaAcciones){
		DAOPerfil perfilDAO = new DAOPerfil();
		perfilDAO.insertarAccionesPerfil(idPerfil, listaAcciones);
	}
	public void eliminarAccionDePerfil(int idPerfil, int idAccion){
		DAOPerfil perfilDAO = new DAOPerfil();
		perfilDAO.eliminarAccionDePerfil(idPerfil, idAccion);
	}

	public List<Accion> obtenerAccionesDePerfil(int idPerfil){
		DAOPerfil perfilDAO = new DAOPerfil();
		return perfilDAO.consultarAccionesDePerfil(idPerfil);
	}
	
	//Funcion para UNICAMENTE saber si el perfil tiene la accion o no
	public boolean estaAccionEnPerfil(Perfil perfil, String nombreAccion){
		ArrayList<Accion> listaAcciones = perfil.getListaAcciones();
		Accion accion = new Accion(nombreAccion);
		boolean estaAccion = listaAcciones.contains(accion);
		if(estaAccion){
			return true;
		}else{
			return false;
		}
	}
	//Funcion para saber si el perfil tiene la accion activada, primero determina si el perfil tiene la accion
	public boolean estaAccionActivaEnPerfil(Perfil perfil, String nombreAccion)throws Exception{
		
		if(estaAccionEnPerfil(perfil, nombreAccion)){
			Accion accion = buscarAccionEnPerfil( perfil, nombreAccion);
			return accion.getEstado();
		}else{
			throw new Exception("El Perfil no tiene la accion");
		}
	}
	//Funcion que devuelve la accion del perfil que se esta buscando
	public Accion buscarAccionEnPerfil(Perfil perfil, String nombreAccion){
		Accion accionEncontrada = null;
		for(Accion accion: perfil.getListaAcciones()){
			if(accion.getNombre().compareTo(nombreAccion)==0){
				accionEncontrada = accion;
			}
		}
		
		return accionEncontrada;
	}
}
