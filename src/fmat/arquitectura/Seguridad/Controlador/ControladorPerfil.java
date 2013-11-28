package fmat.arquitectura.Seguridad.Controlador;

import java.util.ArrayList;
import java.util.List;

import fmat.arquitectura.Seguridad.DAO.DAOPerfil;
import fmat.arquitectura.Seguridad.Modelo.Accion;
import fmat.arquitectura.Seguridad.Modelo.Perfil;

public class ControladorPerfil {

	public void guardarPerfilNuevo(Perfil perfil){
		perfilDAO.insertarPerfil(perfil);
	}
	public void eliminarPerfil(String nombrePerfil){
		perfilDAO.eliminarPerfil(nombrePerfil);
	}
	public void actualizarPerfil(Perfil perfil){
		perfilDAO.actualizarPerfil(perfil);
	}
	
	public Perfil obtenerPerfilPorID(int idPerfil){
		return perfilDAO.consultarPerfilPorID(idPerfil);
	}
	public void obtenerPerfilPorNombre(String nombrePerfil){
		perfilDAO.consultarPerfilPorNombre(nombrePerfil);
	}
	public ArrayList<Perfil> obtenerTodosLosPerfiles(){
		return perfilDAO.consultarPerfiles();
	}
	
	public void insertarAccionesPerfil(int idPerfil, ArrayList<Accion> listaAcciones){
		perfilDAO.insertarAccionesPerfil(idPerfil, listaAcciones);
	}
	public void eliminarAccionDePerfil(int idPerfil, int idAccion){
		perfilDAO.eliminarAccionDePerfil(idPerfil, idAccion);
	}
	//ACTUALIZAR ACCION DE PERFIL?
	public List<Accion> obtenerAccionesDePerfil(int idPerfil){
		return perfilDAO.consultarAccionesDePerfil(idPerfil);
	}
	public boolean estaAccionEnPerfil(int idPerfil, int idAccion){
		return perfilDAO.estaAccionEnPerfil(idPerfil, idAccion);
	}
	
	private DAOPerfil perfilDAO = new DAOPerfil();
}
