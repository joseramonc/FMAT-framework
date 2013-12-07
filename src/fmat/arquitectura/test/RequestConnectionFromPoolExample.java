package fmat.arquitectura.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import fmat.arquitectura.DBAccess.modelo.DBConnection;
import fmat.arquitectura.pool.AdminPool.GestorPool.Controlador_Pool;
import fmat.arquitectura.pool.AdminPool.dominio.Conexion;
import fmat.arquitectura.pool.init.initPool;

public class RequestConnectionFromPoolExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initPool iniciarPool=new initPool();
		iniciarPool.init();
		
		Controlador_Pool controller= Controlador_Pool.getInstance();
		Conexion conexion=controller.obtenerConexion();
		DBConnection DBcon=conexion.getConexion();
		try{
		String query = "select * from usuario";
		
			ResultSet rs = DBcon.executeQuery(query);
			rs.next();
			System.out.println(rs.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
