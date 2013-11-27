package fmat.arquitectura.pool.configPool;

import fmat.arquitectura.DBAccess.modelo.PoolConfigInfo;
import fmat.arquitectura.pool.AdminPool.GestorPool.Controlador_Pool;
import fmat.arquitectura.pool.AdminPool.dominio.Pool;

import java.util.Arrays;

public class ConfigPool {
 
	private Controlador_Pool controladorPool;
	private static ConfigPool INSTANCE=createInstance();
	
	public static  ConfigPool  getInstance() {
        return INSTANCE;
    } 
	
	
	public boolean notificarCambioPool(PoolConfigInfo datosConfig) {
		return false;
	}
	 
	private synchronized static ConfigPool createInstance() {
		INSTANCE = new ConfigPool();
		return INSTANCE;
    }
	
}
 
