package fmat.arquitectura.DBAccess.modelo;

public class DBConfigUpdater {
	private DataBaseConfigInfo DBConfigInfo = DataBaseConfigInfo.getDataBaseConfigInfo();
	private final int INTERVAL = 60000;
	
	public DBConfigUpdater(){
		monitorChanges();
	}
	
	private void monitorChanges(){
		DBConfigFileMonitor dBConfigFileMonitor = new DBConfigFileMonitor();
		while(true){
			if(dBConfigFileMonitor.hasConfigFileChanged()){
				DBConfigInfo.update();
			}
			try {
				Thread.sleep(INTERVAL);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
