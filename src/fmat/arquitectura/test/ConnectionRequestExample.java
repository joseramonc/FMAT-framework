package fmat.arquitectura.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;
import fmat.arquitectura.DBAccess.modelo.DBConnection;

public class ConnectionRequestExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBConnectionFactory DBC = new DBConnectionFactory();
		try {
		DBConnection conn = DBC.createConnection();
		
		String query = "select * from perfil where perfil.Nombre = \"Maestro\"";
		
		ResultSet rs = conn.executeQuery(query);
		rs.next();
		System.out.println(rs.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
