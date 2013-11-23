package fmat.arquitectura.DBAccess.modelo;

import fmat.arquitectura.DBAccess.xml.DBConfigReader;

public class DBConfigFileMonitor {
	//private DataBaseConfigInfo currentDbConfigInfo = DataBaseConfigInfo.getDataBaseConfigInfo();
	private DBConfigReader currentDBConfigReader = new DBConfigReader();
	private DBConfigReader dbConfigReader;
	
	public boolean hasConfigFileChanged(){
		dbConfigReader = new DBConfigReader();
		boolean hasChanged = hasUserChanged() ||hasPassChanged() || hasUrlChanged(); 
		if(hasChanged)
			currentDBConfigReader = dbConfigReader;
		return hasChanged; 
	}
	
	private boolean hasUserChanged(){
		if(currentDBConfigReader.getUser().equals(dbConfigReader.getUser()))
			return false;
		else
			return true;
	}
	
	private boolean hasPassChanged(){
		if(currentDBConfigReader.getPass().equals(dbConfigReader.getPass()))
			return false;
		else
			return true;
	}
	
	private boolean hasUrlChanged(){
		if(currentDBConfigReader.getUrl().equals(dbConfigReader.getUrl()))
			return false;
		else
			return true;
	}
	/*
	public static void main(String[] args){
		DBConfigMonitor dbConfigMon = new DBConfigMonitor();
		while(true){
			System.out.println(dbConfigMon.hasConfigChanged());
			System.out.println("actual "+dbConfigMon.currentDBConfigReader.getUrl());
			System.out.println("leida " +dbConfigMon.dbConfigReader.getUrl());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}//*/
}
