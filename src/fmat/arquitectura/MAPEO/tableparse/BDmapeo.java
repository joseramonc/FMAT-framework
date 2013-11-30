package fmat.arquitectura.MAPEO.tableparse;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;



import java.io.File;
//import java.lang.instrument.Instrumentation;
import java.util.ArrayList;



public class BDmapeo {

	/**
	 * @param args
	 */
	private NodeList lista;
	private instruccionMapeo instrucciones;
	//private MapeoCT map;
		
	public BDmapeo(){
		instrucciones = new instruccionMapeo();
	//	map = new MapeoCT();
		CargarXML();
		
		
	}
		public static void main(String args[]) {
//			BDmapeo read = new BDmapeo();
			
//			ArrayList<Object> a = read.objetoDeTipo("clientes");
	//	ArrayList<Object> a =	read.objetosDeTipo();
		
//		for (int i = 0; i < a.size(); i++) {
//			cliente cl = (cliente) a.get(i);
//			System.out.println("------------------");
//			System.out.println("Nombre: " + cl.getName());
//			System.out.println("RFC: " + cl.getRFC());
//			System.out.println("Telefono: " + cl.getTelefono());
//			System.out.println("pais: " + cl.getNumber());
//		}
			
			}
		
	public ArrayList<Object> objetoDeTipo(String nombreTabla){
		//ArrayList<Object> object = new ArrayList<>();
		BuscarNodo(nombreTabla);
		MapeoCT map = new MapeoCT();
		ArrayList<Object> object = map.objetosDeTipo(instrucciones);
		instrucciones.eliminarElementosDeLista();
		return object;
	}	
		
		private void CargarXML(){
			 try {
				 	
					File fXmlFile = new File("configMapeoXML.xml");
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(fXmlFile);
				 
				//	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				 
				
					Element raiz = doc.getDocumentElement();
					lista = raiz.getChildNodes();
					
				
				    } catch (Exception e) {
					e.printStackTrace();
				    }
			
		}
		
		private void divideCadenaDeInstrucciones(String cadena, int NumAtributos){
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
			
			
//		RecorrerInstrucciones();
//			Class ob = map.getIntanceObject((instrucciones.getInstruccion().get(0))[0][0]);
//			if(ob != null){
//				System.out.println("JAJAJAJA");
//			}
		}
		
private void RecorrerInstrucciones(){
	ArrayList<String[][]> Mapeo =	instrucciones.getInstruccionAtributo();
	ArrayList<String[][]> setMapeo = instrucciones.GenerarInstruccionesSet();
	
	for (int i = 0; i < Mapeo.size(); i++) {
			if(i == 0){
			String [][] tem = Mapeo.get(i);
			System.out.println("Nombre de clase: " + tem[0][0] + " Nombre de tabla: " + tem[0][1]);
			}else{
				String [][] tem = Mapeo.get(i);
				System.out.println("Atributo de clase: " + tem[0][0] + " Atributo de tabla: " + tem[0][1]);
			}
	}
	
	System.out.println("------------------------------");
	for (int i = 0; i < setMapeo.size(); i++) {
		if(i == 0){
		String [][] tem = setMapeo.get(i);
		System.out.println("Nombre de clase: " + tem[0][0] + " Nombre de tabla: " + tem[0][1]);
		}else{
			String [][] tem = setMapeo.get(i);
			System.out.println("Atributo de clase: " + tem[0][0] + " Atributo de tabla: " + tem[0][1]);
		}
}
	
	
}		
		
		private String ascIIToHex(char aux){
			StringBuilder hex = new StringBuilder();
			hex.append(Integer.toHexString(aux));
			return hex.toString();
		}
		
		

		private void BuscarNodo(String Nombre){
			//System.out.println(lista.getLength());
			for (int i = 0; i < lista.getLength(); i++) {
				Node nodo = lista.item(i);
			
				if(nodo.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nodo;
		
				if(eElement.getAttribute("tabla").equals(Nombre)){
			
					//System.out.println("Atributos : clase " + eElement.getAttribute("clase") + "tabla " +  eElement.getAttribute("tabla"));
					
					String [][] nombreTablaYClase = new String[1][2];
					nombreTablaYClase[0][0] = eElement.getAttribute("clase");
					nombreTablaYClase[0][1] = eElement.getAttribute("tabla");
					instrucciones.agregarInstruccion(nombreTablaYClase);
					
					divideCadenaDeInstrucciones(eElement.getTextContent(), Integer.parseInt(eElement.getAttribute("size")));
					break;
				}
			}		
			}
			
		}
			
		
	

}
