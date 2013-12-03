package fmat.arquitectura.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fmat.arquitectura.pool.AdminPool.GestorPool.Controlador_Pool;
import fmat.arquitectura.pool.AdminPool.dominio.Conexion;

public class RequestConnectionFromPoolExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Controlador_Pool controller=new Controlador_Pool();
		controller.crearConexiones();
		Conexion conexion=controller.obtenerConexion();
		Connection conn=conexion.getConexion();
		try{
		Statement st = conn.createStatement();
		String query = "select * from usuario";
		
			ResultSet rs = st.executeQuery(query);
			rs.next();
			System.out.println(rs.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
