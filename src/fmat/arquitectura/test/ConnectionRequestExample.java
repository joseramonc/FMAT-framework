package fmat.arquitectura.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;

public class ConnectionRequestExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBConnectionFactory DBC = new DBConnectionFactory();
		try {
		Connection conn = DBC.createConnection();
		
		Statement st = conn.createStatement();
		String query = "select * from perfil";
		
			ResultSet rs = st.executeQuery(query);
			rs.next();
			System.out.println(rs.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
