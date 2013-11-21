package fmat.arquitectura.DBAccess.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fmat.arquitectura.DBAccess.modelo.DataBaseConfigInfo;

public class DBConnectionFactory {
	private DataBaseConfigInfo DBConfigInfo;
	
	
	public DBConnectionFactory() {
		DBConfigInfo = DataBaseConfigInfo.getDataBaseConfigInfo();
	}
	
	public Connection createConnection(){
		try {
			return DriverManager.getConnection(DBConfigInfo.getUrl(),DBConfigInfo.getUser(),DBConfigInfo.getPass());
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
