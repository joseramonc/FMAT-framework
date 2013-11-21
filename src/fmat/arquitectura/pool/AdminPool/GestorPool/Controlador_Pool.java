package fmat.arquitectura.pool.AdminPool.GestorPool;

import java.sql.Connection;


import java.util.ArrayList;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;
import fmat.arquitectura.pool.AdminPool.dominio.Pool;
import fmat.arquitectura.pool.AdminPool.dominio.Conexion;

public class Controlador_Pool  {
 
	
	
	public Conexion obtenerConexion() {
		DBConnectionFactory DBC = new DBConnectionFactory();
		
		return new Conexion(DBC.createConnection(),true);
		//return poolConexiones.obtenerConexionDisp();
	}
	
	public void crearConexiones(){
		if(poolConexiones.getSegmentosCreados()<=poolConexiones.getSegmentosCreados()){
			ArrayList<Conexion>  conexiones= poolConexiones.crearSegmentoConexiones();
			for(int indice=0; indice<poolConexiones.getTamañoSegmentos();indice++){
				Conexion conexion =obtenerConexionDB();
				conexiones.add(conexion);
				}
			poolConexiones.asignarSegmentoCreado(conexiones);
		}

	}
	
	private Conexion obtenerConexionDB(){ //metodo privado que se llamara por admin conexion
		DBConnectionFactory factoryDB = new DBConnectionFactory();
		Connection conn = factoryDB.createConnection();
		Conexion conexion=poolConexiones.crearConexion(conn);
		return conexion;
	}
	 
	private Pool poolConexiones= Pool.getInstance();
}
 
