package fmat.arquitectura.MAPEO.tableparse;

import java.util.ArrayList;

public class instruccionMapeo {

	/**
	 * @param args
	 */
	ArrayList<String[][]> instruccionAtributo;
	ArrayList<String[][]> instruccionSetAtributos;
	
	
	public instruccionMapeo(){		
		instruccionAtributo = new ArrayList<>();
		instruccionSetAtributos = new ArrayList<>();
	}

/*
	public void llenarIntrucciones(){
		String [][] mapeo = { {"Clientes","Persona"},
							{"nombre","name"},
							{"apellidos","lastName"},
							{"edad","age"},		
								};
		System.out.println("filas: " + mapeo.length + "Columnas: " + mapeo[0].length);
//		for (int i = 0; i < mapeo.length; i++) {
//			for (int j = 0; j < mapeo.length; j++) {
//				System.out.println(mapeo[i][j]);
//			}
//		}
		
	}
*/
	public void agregarInstruccion(String [][] inst){
		instruccionAtributo.add(inst);
	}
	
	public ArrayList<String[][]> getInstruccionAtributo() {
		return instruccionAtributo;
	}
	
//	public ArrayList<String[][]> getInstruccionSetAtributos() {
//		return instruccionSetAtributos;
//	}

	public void eliminarElementosDeLista(){
		for (int i = 0; i < instruccionAtributo.size(); i++) {
			instruccionAtributo.remove(i);
			instruccionSetAtributos.remove(i);
		}
		
	}

	public ArrayList<String[][]>  GenerarInstruccionesSet(){
		
		for (int i = 0; i < instruccionAtributo.size(); i++) {
			String [][] aux = instruccionAtributo.get(i);
			if(i !=  0){
				String setAtributo = aux[0][0];
				String nombreTabla = aux[0][1];
				
				String letraMayu = "";
				letraMayu = letraMayu + setAtributo.charAt(0);
				letraMayu = letraMayu.toUpperCase();
				
				setAtributo = setAtributo.replaceFirst(String.valueOf(setAtributo.charAt(0)), letraMayu);
				String setFinal = "set" + setAtributo;
				
				
				String [][] temp = new String[1][2];
				temp[0][0] = setFinal;
				temp[0][1] = nombreTabla;
				instruccionSetAtributos.add(temp);
				
			}else{
				instruccionSetAtributos.add(aux);
			}
			
		}
		
		return instruccionSetAtributos;
		
	}

	public static void main(String[] args) {
		
	//	instruccionMapeo a = new instruccionMapeo();
	

	}
	
	
	

}
