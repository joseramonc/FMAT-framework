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
	
	Pool connectionPool;
	
	public void modifySegmSize(int size){
		System.out.println("Número de conexiones por segmento: "  + connectionPool.getTamañoSegmentos());
		System.out.println("Modificando...");
		connectionPool.setTamañoSegmentos(size);
		System.out.println("Tamaño de segmentos modificado a: " + connectionPool.getTamañoSegmentos());
	}
	
	public void modifySegmNum(int number){
		System.out.println("Cantidad total de segmentos: " + connectionPool.getSegmentos());
		System.out.println("Modificando...");
		connectionPool.setSegmentos(number);;
		System.out.println("Total actual de segmentos: " + connectionPool.getSegmentos());
	}
	
	PoolConfigInfo data;
	
	public void modifyPool(){
		modifySegmSize(data.getTamSegmento());
		modifySegmNum(data.getNumSegmentos());
	}
}
 
