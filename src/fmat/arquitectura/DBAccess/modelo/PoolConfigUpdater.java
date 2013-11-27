package fmat.arquitectura.DBAccess.modelo;

import fmat.arquitectura.pool.configPool.ConfigPool;

public class PoolConfigUpdater {
	
	public PoolConfigUpdater(){
		monitorChanges();
	}
	
	private void monitorChanges(){
		PoolConfigFileMonitor poolConfigFileMonitor = new PoolConfigFileMonitor();
		final int INTERVAL = 60000;
		while(true){
			if(poolConfigFileMonitor.hasConfigFileChanged()){
				notifyPool();
				try {
					Thread.sleep(INTERVAL);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void notifyPool() {
		ConfigPool.getInstance().notificarCambioPool(new PoolConfigInfo());
	}
	
	
		
}
