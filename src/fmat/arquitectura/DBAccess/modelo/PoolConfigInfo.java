package fmat.arquitectura.DBAccess.modelo;

import fmat.arquitectura.DBAccess.xml.PoolConfigReader;

public class PoolConfigInfo {
	private int numSegmentos,tamSegmento;
	public PoolConfigInfo() {
		setConfig();
	}
	
	public int getNumSegmentos() {
		return numSegmentos;
	}
	public int getTamSegmento() {
		return tamSegmento;
	}

	private void setConfig(){
		PoolConfigReader poolConfigReader = new PoolConfigReader();
		numSegmentos = poolConfigReader.getNumSegmentos();
		tamSegmento = poolConfigReader.getTamSegmento();
	} 
}
