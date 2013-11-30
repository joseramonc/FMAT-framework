package fmat.arquitectura.MAPEO.tableparse;


import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;

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
		DBConnectionFactory DBC = new DBConnectionFactory();
		ResultSet rs = null;
		try {
			Connection conn = DBC.createConnection();
			java.sql.Statement st = conn.createStatement();
			
			 String Query = "SELECT * FROM " + nombreDetabla;
			
			  rs = st.executeQuery( Query );
//			 while (rs.next()){
//				 
//				System.out.println(rs.getString("nombre"));
//			 }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
		
	}

}
