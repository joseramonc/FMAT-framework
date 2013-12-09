package fmat.arquitectura.MAPEO.parseTO;


import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;
import fmat.arquitectura.DBAccess.modelo.DBConnection;
import fmat.arquitectura.pool.AdminPool.GestorPool.Controlador_Pool;
import fmat.arquitectura.pool.AdminPool.dominio.Conexion;
import fmat.arquitectura.pool.init.initPool;

public class ConexionBDMapeo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		TestBD tes = new TestBD();
//		tes.BD("clientes");
	}
	
	public ResultSet BD(String nombreDetabla){
		
		
		DBConnection st =getPoolConexion();

		ResultSet rs = null;
		String Query = "SELECT * FROM " + nombreDetabla;
		
		  rs = st.executeQuery( Query );
		
		return rs;
		
	}
	
	private DBConnection getPoolConexion(){
		initPool iniciarPool=new initPool();
		iniciarPool.init();
		
		Controlador_Pool controller= Controlador_Pool.getInstance();
		Conexion conexion=controller.obtenerConexion();
		//DBConnectionFactory DBC = new DBConnectionFactory();
		//Connection conn = DBC.createConnection();
		DBConnection conn=conexion.getConexion();
		return conn;
		
	}

}
