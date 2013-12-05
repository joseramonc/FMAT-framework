package fmat.arquitectura.MAPEO.tableparse;

import java.util.ArrayList;

public class MapperTable {

	public ArrayList<Object> getAll(String nombreTabla){		
		MapeoReaderXML generaInstrucciones = new MapeoReaderXML();
		instruccionMapeo instrucciones = generaInstrucciones.getInstruccionesMapeo(nombreTabla);
		MapeoParseT_O mapeoTO = new MapeoParseT_O();
		ArrayList<Object> objects = mapeoTO.getObjetosDeTabla(instrucciones);
		return objects;
	}
	

}
