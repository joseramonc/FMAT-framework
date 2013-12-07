package fmat.arquitectura.MAPEO.inicio;

import java.util.ArrayList;

import fmat.arquitectura.MAPEO.interprete.MapeoReaderXML;
import fmat.arquitectura.MAPEO.interprete.instruccionMapeo;
import fmat.arquitectura.MAPEO.parseTO.MapeoParseT_O;

public class MapperTable {

	
	
	public ArrayList<Object> getAll(String nombreTabla)throws ExcepcionesMapeo{		
		MapeoReaderXML generaInstrucciones = new MapeoReaderXML();
		instruccionMapeo instrucciones = generaInstrucciones.getInstruccionesMapeo(nombreTabla);
		ArrayList<Object> objects;
		if(instrucciones != null){
			MapeoParseT_O mapeoTO = new MapeoParseT_O();
			objects = mapeoTO.getObjetosDeTabla(instrucciones);
		}else{
			throw new ExcepcionesMapeo("No existe el nombre de la tabla " + nombreTabla +" en el XML");
		}
		
		return objects;
	}
	

}
