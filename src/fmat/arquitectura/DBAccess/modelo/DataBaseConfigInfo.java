package fmat.arquitectura.DBAccess.modelo;

import fmat.arquitectura.DBAccess.xml.DBConfigReader;

public class DataBaseConfigInfo {
	private String url,user,pass;
	private static DataBaseConfigInfo dataBaseConfigInfo;	

	private DataBaseConfigInfo() {
		// TODO Auto-generated constructor stub
		setConfig();
	}

	 
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public static DataBaseConfigInfo getDataBaseConfigInfo() {
		if(dataBaseConfigInfo==null)
			dataBaseConfigInfo = new DataBaseConfigInfo();
		
		return dataBaseConfigInfo;
	}
	
	public void setConfig(){
		DBConfigReader dBConfigReader = new DBConfigReader();
		setUrl(dBConfigReader.getUrl());
		setUser(dBConfigReader.getUser());
		setPass(dBConfigReader.getPass());
	}

}
