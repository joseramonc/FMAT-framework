package fmat.arquitectura.DBAccess.controller;

import fmat.arquitectura.DBAccess.modelo.DataBaseConfigInfo;

public class DBConfigController {
	private DataBaseConfigInfo DBConfigInfo;
	public DBConfigController() {
		// TODO Auto-generated constructor stub
		DBConfigInfo = DataBaseConfigInfo.getDataBaseConfigInfo();
	}
	
	public DataBaseConfigInfo getDataBaseConfigInfo(){
		return DBConfigInfo;
	}
	
}
