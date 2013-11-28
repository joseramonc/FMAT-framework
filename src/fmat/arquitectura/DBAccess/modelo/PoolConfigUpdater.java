ackage fmat.arquitectura.DBAccess.modelo;

import fmat.arquitectura.pool.configPool.ConfigPool;
//quien instanciara las clases updaters y como notificar pool?
//posiblemente los hilso sigan aun despues de terminado el programa
public class PoolConfigUpdater extends Thread{
	public PoolConfigUpdater(){
		this.start();
	}
	
	public void run(){
		monitorChanges();
	}
	
	private void monitorChanges(){
		
		PoolConfigFileMonitor poolConfigFileMonitor = new PoolConfigFileMonitor();
		while(true){
			if(poolConfigFileMonitor.hasConfigFileChanged()){
				notifyPool();
				try {
					final int INTERVAL = 60000;
					Thread.sleep(INTERVAL);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void notifyPool() {
		ConfigPool configPool = new ConfigPool();
		configPool.notificarCambioPool(new PoolConfigInfo());
	}
	
	
		
}
