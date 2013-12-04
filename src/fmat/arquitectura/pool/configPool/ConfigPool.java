package fmat.arquitectura.pool.configPool;

import fmat.arquitectura.DBAccess.modelo.PoolConfigInfo;
import fmat.arquitectura.pool.AdminPool.GestorPool.Controlador_Pool;
import fmat.arquitectura.pool.AdminPool.dominio.Pool;


public class ConfigPool {
 
	
	public static  ConfigPool  getInstance() {
        return INSTANCE;
    } 
	
	
	public boolean notificarCambioPool(PoolConfigInfo datosConfig) {
		return false;
	}
	 
	
	Pool connectionPool;
	
	public void modifyPool(){
		int segmentos,tamanioSegmentos;
		segmentos=data.getNumSegmentos();
		tamanioSegmentos=data.getTamSegmento();
		controladorPool.configurarPool(segmentos,tamanioSegmentos);
	}
	

	
	private PoolConfigInfo data;
	private Controlador_Pool controladorPool= Controlador_Pool.getInstance();
	private static ConfigPool INSTANCE=createInstance();
	
	private ConfigPool(){
	}
	
	private synchronized static ConfigPool createInstance() {
		INSTANCE = new ConfigPool();
		return INSTANCE;
    }
	
	
}
 
