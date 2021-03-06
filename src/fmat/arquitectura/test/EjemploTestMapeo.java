package fmat.arquitectura.test;

import java.util.ArrayList;


import fmat.arquitectura.MAPEO.inicio.ExcepcionesMapeo;
import fmat.arquitectura.MAPEO.inicio.MapperTable;

public class EjemploTestMapeo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MapperTable mapeo = new MapperTable();
		
		ArrayList<Object> objetos;
		try {
			objetos = mapeo.getAll("perfil");
			
			for (int i = 0; i < objetos.size(); i++) {
				EjemploMapeoModeloPerfil ejemplo = (EjemploMapeoModeloPerfil) objetos.get(i);
				
				System.out.println("id: " + ejemplo.getId());
				System.out.println("nombre: " + ejemplo.getNombre());
			}
			
			
		} catch (ExcepcionesMapeo e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
