package fmat.arquitectura.pool.configPool;

import fmat.arquitectura.DBAccess.modelo.PoolConfigInfo;
import fmat.arquitectura.pool.AdminPool.GestorPool.Controlador_Pool;


public class ConfigPool {
 
	
	public static  ConfigPool  getInstance() {
        return INSTANCE;
    } 
	
	
	public boolean notificarCambioPool(PoolConfigInfo datosConfig) {
		int segmento=datosConfig.getNumSegmentos();
		int tamanioSegmentos=datosConfig.getTamSegmento();
		boolean cambioRealizado=controladorPool.cambiarConfigPool(segmento, tamanioSegmentos);
		if(cambioRealizado){
			return true;
		}else{
			return false;	
		}
	}
	
	public void configurarPool(){
		int segmentos,tamanioSegmentos;
		segmentos=data.getNumSegmentos();
		tamanioSegmentos=data.getTamSegmento();
		controladorPool.configurarPool(segmentos,tamanioSegmentos);
	}
	

	
	private PoolConfigInfo data= new PoolConfigInfo();
	private Controlador_Pool controladorPool= Controlador_Pool.getInstance();
	private static ConfigPool INSTANCE=createInstance();
	
	private ConfigPool(){
	}
	
	private synchronized static ConfigPool createInstance() {
		INSTANCE = new ConfigPool();
		return INSTANCE;
    }
	
	
}
 
