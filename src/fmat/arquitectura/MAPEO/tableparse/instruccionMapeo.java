package fmat.arquitectura.MAPEO.tableparse;

import java.util.ArrayList;

public class instruccionMapeo {

	/**
	 * @param args
	 */
	private ArrayList<String[][]> instruccionAtributo;
	private ArrayList<String[][]> instruccionSetAtributos;
	
	
	public instruccionMapeo(){		
		instruccionAtributo = new ArrayList<>();
		instruccionSetAtributos = new ArrayList<>();
	}


	public void agregarInstruccion(String [][] inst){
		instruccionAtributo.add(inst);
	}
	
	public ArrayList<String[][]> getInstruccionAtributo() {
		return instruccionAtributo;
	}
	
	public  ArrayList<String[][]> getInstruccionesSet(){
		return instruccionSetAtributos;
	}
	
	public void eliminarElementosDeLista(){
		for (int i = 0; i < instruccionAtributo.size(); i++) {
			instruccionAtributo.remove(i);
			instruccionSetAtributos.remove(i);
		}
		
	}

	public void  GenerarInstruccionesSet(){
		
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
		
	}

	public static void main(String[] args) {
		
	//	instruccionMapeo a = new instruccionMapeo();
	

	}
	
	
	

}
