package fmat.arquitectura.MAPEO.tableparse;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.reflect.*;

public class MapeoCT {

	/**
	 * @param args
	 */
	ConexionBDMapeo BD;
	public MapeoCT(){
		BD = new ConexionBDMapeo();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		MapeoCT map = new MapeoCT();
//	Class<?> ob =	map.getIntanceObject("fmatModelo.cliente");
//		if(ob ==null){
//			System.out.println("HOLAS");
//		}else{
//			System.out.println("HEYYY");
//		}
	}
	
	private Class<?> getObjectClass(String Classname) {
		Class<?> ObjectClass = null;
		try {
			ObjectClass = Class.forName(Classname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No se encontro: " + e);
			
		}
		return ObjectClass;
	}
	
	private Object getInstance(Constructor<?> constructor){
		Object obj = null;
		try {
			obj = constructor.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}
	
	
	public ArrayList<Object> objetosDeTipo(instruccionMapeo instruccion){
		ArrayList<Object> objetos = new ArrayList<>();
		
		ArrayList<String[][]> instruccionAtributos = instruccion.getInstruccionAtributo();
		ArrayList<String[][]> nombresSetAtributos = instruccion.GenerarInstruccionesSet();
		
		String className = (instruccionAtributos.get(0))[0][0];
		String tableName = (instruccionAtributos.get(0))[0][1];
		
		Class<?> ObjectClass = getObjectClass(className);
		ResultSet entidades =  consultarBD(tableName);
		
		Field[] atributosDeclase = atributosDeClase(ObjectClass);
		
		try {
			while (entidades.next()) {
			//	Constructor [] a =ObjectClass.getConstructors();
				Object entidadAux  = getInstance((ObjectClass.getConstructors())[0]);
				
				for (int i = 1; i < instruccionAtributos.size(); i++) {
						String [][] tempInstrucc = instruccionAtributos.get(i);
						String [][] temSetAtribu = nombresSetAtributos.get(i);
						for (int j = 0; j < atributosDeclase.length; j++) {
								if(atributosDeclase[j].getName().equals(tempInstrucc[0][0])){
									String nombreSet = temSetAtribu[0][0];
									
										Method metodoSetAtributo = ObjectClass.getMethod(nombreSet, atributosDeclase[j].getType());
										switch ( atributosDeclase[j].getType().toString()) {
										case "class java.lang.String":
											metodoSetAtributo.invoke(entidadAux, entidades.getString(tempInstrucc[0][1]));
											break;
										case "int":
											metodoSetAtributo.invoke(entidadAux, entidades.getInt(tempInstrucc[0][1]));
											break;
										case "boolean":
											metodoSetAtributo.invoke(entidadAux, entidades.getBoolean(tempInstrucc[0][1]));
											break;

										default:
											break;
										}
										//System.out.println("Metodo encontrado "+ nombreSet + " parametro del argumento " +  entidades.getString(tempInstrucc[0][1]) );
										//metodoSetAtributo.invoke(entidadAux, entidades.getString(tempInstrucc[0][1]));
										
								}
						}
				}
				objetos.add(entidadAux);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return objetos;
	}
	
	public Field[] atributosDeClase(Class<?> ObjectClass){
		Field[] atributos = ObjectClass.getDeclaredFields();
		return atributos;
		
	}
	
	public ResultSet consultarBD(String nombreTabla){
		ResultSet element =	BD.BD(nombreTabla);
		return element;
	}

}
