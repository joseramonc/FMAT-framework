package fmat.arquitectura.MAPEO.interprete;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;



import java.io.File;





public class MapeoReaderXML {

	/**
	 * @param args
	 */
	private NodeList listaXML;

		
	public MapeoReaderXML(){		
		CargarXML();
	}

		
	public instruccionMapeo getInstruccionesMapeo(String nombreTabla){		
		instruccionMapeo instrucciones = BuscarNodo(nombreTabla);		
		return instrucciones;
	}	
		
		private void CargarXML(){
			 try {
				 	
					File fXmlFile = new File("configMapeoXML.xml");
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(fXmlFile);
					Element raiz = doc.getDocumentElement();
					listaXML = raiz.getChildNodes();
					
				
				    } catch (Exception e) {
					e.printStackTrace();
				    }
			
		}
		
		private instruccionMapeo divideCadenaDeInstrucciones(Element eElement, int NumAtributos){
			instruccionMapeo instrucciones = new instruccionMapeo();
			String [][] nombreTablaYClase = new String[1][2];
			nombreTablaYClase[0][0] = eElement.getAttribute("clase");
			nombreTablaYClase[0][1] = eElement.getAttribute("tabla");
			instrucciones.agregarInstruccion(nombreTablaYClase);
			String cadena = eElement.getTextContent();
			int temp = 0;
			for (int i = 0; i < NumAtributos; i++) {
				
				String inst = "";
				char aux = ' ';
				int auxColumna = 0;
				String[][] parse = new String[1][2];
				
				for (int j = temp; j < cadena.length(); j++) {
					
				if(cadena.charAt(j) != ' ' && ascIIToHex(cadena.charAt(j)).charAt(0)!= 'a' && ascIIToHex(cadena.charAt(j)).charAt(0)!= '9'){
				
					if(cadena.charAt(j) == '-'){
						parse[0][auxColumna] =  inst;
						inst = "";
						auxColumna = auxColumna +1;
						
						
					}else{
						if(cadena.charAt(j) != '/'){
							aux = cadena.charAt(j);							
							inst = inst+aux;			
							}else{
								parse[0][auxColumna] =  inst;
								temp = j+1;
								break;
							}
					}
				}	
					
				}
				instrucciones.agregarInstruccion(parse);
			}			
			instrucciones.GenerarInstruccionesSet();
			return instrucciones;
		}
		
		private String ascIIToHex(char aux){
			StringBuilder hex = new StringBuilder();
			hex.append(Integer.toHexString(aux));
			return hex.toString();
		}
		
		

		private instruccionMapeo BuscarNodo(String Nombre){		
			instruccionMapeo instrucciones = null;
			for (int i = 0; i < listaXML.getLength(); i++) {
				Node nodo = listaXML.item(i);			
				if(nodo.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nodo;		
				if(eElement.getAttribute("tabla").equals(Nombre)){			
					//System.out.println("Atributos : clase " + eElement.getAttribute("clase") + "tabla " +  eElement.getAttribute("tabla"));
					instrucciones =	divideCadenaDeInstrucciones(eElement, Integer.parseInt(eElement.getAttribute("size")));
					break;
				}
			}		
			}
		return 	instrucciones;
		}
			
		
	

}
