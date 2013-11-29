package fmat.arquitectura.Seguridad.Controlador;

import java.util.ArrayList;

import fmat.arquitectura.Seguridad.DAO.DAOAccion;
import fmat.arquitectura.Seguridad.Modelo.Accion;

public class ControladorAccion {

	public void guardarAccionNueva(Accion accion){
		accionDAO.insertarAccion(accion);
	}
	public void eliminarAccion(String nombreAccion){
		accionDAO.eliminarAccion(nombreAccion);
	}
	public void actualizarAccion(Accion accion){
		accionDAO.actualizarAccion(accion);
	}
	
	public Accion obtenerAccionPorID(int idAccion){
		return accionDAO.consultarAccionPorID(idAccion);
	}
	public Accion obtenerAccionPorNombre(String nombreAccion){
		return accionDAO.consultarAccionPorNombre(nombreAccion);
	}
	
	public ArrayList<Accion> obtenerTodosLasAcciones(){
		return accionDAO.consultarAcciones();
	}
	
	private DAOAccion accionDAO = new DAOAccion();
}
