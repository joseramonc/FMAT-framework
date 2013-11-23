package fmat.arquitectura.DBAccess.modelo;

import fmat.arquitectura.DBAccess.xml.PoolConfigReader;

public class PoolConfigFileMonitor {
	private PoolConfigReader currentPoolConfigReader = new PoolConfigReader();
	private PoolConfigReader poolConfigReader;
	
	public boolean hasConfigFileChanged(){
		poolConfigReader = new PoolConfigReader();
		boolean hasChanged = hasNumSegmentosChanged() || hasTamSegmentoChanged();
		if(hasChanged)
			currentPoolConfigReader = poolConfigReader;
		return hasChanged;
	}
	
	private boolean hasNumSegmentosChanged(){
		if(currentPoolConfigReader.getNumSegmentos()==poolConfigReader.getNumSegmentos())
			return false;
		else
			return true;
	}
	
	private boolean hasTamSegmentoChanged(){
		if(currentPoolConfigReader.getTamSegmento()==poolConfigReader.getTamSegmento())
			return false;
		else
			return true;
	}
	
	/*
	public static void main(String[] args){
		PoolConfigFileMonitor pcfm = new PoolConfigFileMonitor();
		while(true){
			System.out.println(pcfm.hasConfigFileChanged());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}//*/
	
}
