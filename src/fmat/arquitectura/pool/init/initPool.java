package fmat.arquitectura.pool.init;

import fmat.arquitectura.pool.configPool.ConfigPool;

public class initPool {
 
	public void init() {
		configurar.configurarPool();
	}
	
	ConfigPool configurar=ConfigPool.getInstance();

	 
}
 
