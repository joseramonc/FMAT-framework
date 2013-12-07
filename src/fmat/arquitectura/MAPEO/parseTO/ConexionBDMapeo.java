package fmat.arquitectura.MAPEO.parseTO;


import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;
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
		
		
		Connection conn=getPoolConexion();

		ResultSet rs = null;
		try {
		
			java.sql.Statement st = conn.createStatement();
			
			 String Query = "SELECT * FROM " + nombreDetabla;
			
			  rs = st.executeQuery( Query );

			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al consultar la BD en el Modulo de MAPEO: " + e.toString());
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	private Connection getPoolConexion(){
//		initPool iniciarPool=new initPool();
//		iniciarPool.init();
		
//		Controlador_Pool controller= Controlador_Pool.getInstance();
//		Conexion conexion=controller.obtenerConexion();
		DBConnectionFactory DBC = new DBConnectionFactory();
		Connection conn = DBC.createConnection();
//		Connection conn=conexion.getConexion();
		return conn;
		
	}

}
