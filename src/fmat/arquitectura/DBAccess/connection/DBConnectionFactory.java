package fmat.arquitectura.DBAccess.connection;

import fmat.arquitectura.DBAccess.modelo.DBConnection;
import fmat.arquitectura.DBAccess.modelo.DataBaseConfigInfo;

public class DBConnectionFactory {
	private DataBaseConfigInfo DBConfigInfo;
	
	
	public DBConnectionFactory() {
		DBConfigInfo = DataBaseConfigInfo.getDataBaseConfigInfo();
	}
	
	public DBConnection createConnection(){
		return new DBConnection(DBConfigInfo.getUrl(),DBConfigInfo.getUser(),DBConfigInfo.getPass());
	}

}
