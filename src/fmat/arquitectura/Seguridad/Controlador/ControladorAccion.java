package fmat.arquitectura.Seguridad.Controlador;

import java.util.ArrayList;

import fmat.arquitectura.Seguridad.DAO.DAOAccion;
import fmat.arquitectura.Seguridad.Modelo.Accion;

public class ControladorAccion {

	public void guardarAccionNueva(Accion accion){
		DAOAccion accionDAO = new DAOAccion();
		accionDAO.insertarAccion(accion);
	}
	public void eliminarAccion(String nombreAccion){
		DAOAccion accionDAO = new DAOAccion();
		accionDAO.eliminarAccion(nombreAccion);
	}
	public void actualizarAccion(Accion accion){
		DAOAccion accionDAO = new DAOAccion();
		accionDAO.actualizarAccion(accion);
	}
	
	public Accion obtenerAccionPorID(int idAccion){
		DAOAccion accionDAO = new DAOAccion();
		return accionDAO.consultarAccionPorID(idAccion);
	}
	public Accion obtenerAccionPorNombre(String nombreAccion){
		DAOAccion accionDAO = new DAOAccion();
		return accionDAO.consultarAccionPorNombre(nombreAccion);
	}
	public ArrayList<Accion> obtenerTodosLasAcciones(){
		DAOAccion accionDAO = new DAOAccion();
		return accionDAO.consultarAcciones();
	}
}
