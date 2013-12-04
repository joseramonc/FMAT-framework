package fmat.arquitectura.pool.AdminPool.GestorPool;

import java.sql.Connection;


import java.util.ArrayList;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;
import fmat.arquitectura.pool.AdminPool.dominio.Pool;
import fmat.arquitectura.pool.AdminPool.dominio.Conexion;

public class Controlador_Pool  {
 
	public static Controlador_Pool getInstance(){
		return INSTANCE;
	}
	
	public Conexion obtenerConexion() {
		return poolConexiones.obtenerConexionDisp();
	}
	
	public void configurarPool(int segmentos,int tamanioSegmentos){
		asignarNumeroSegmentos(segmentos);
		asignarTamSegmentos(tamanioSegmentos);
		crearConexiones();
		crearConexiones.run();
	}
	
	public void asignarNumeroSegmentos(int size){
		System.out.println("Numero de conexiones por segmento: "  + poolConexiones.getTamanioSegmentos());
		System.out.println("Modificando...");
		poolConexiones.setTamanioSegmentos(size);
		System.out.println("Tamanio de segmentos modificado a: " + poolConexiones.getTamanioSegmentos());
	}
	
	public void asignarTamSegmentos(int number){
		System.out.println("Cantidad total de segmentos: " + poolConexiones.getSegmentos());
		System.out.println("Modificando...");
		poolConexiones.setSegmentos(number);;
		System.out.println("Total actual de segmentos: " + poolConexiones.getSegmentos());
	}
	
	
	Runnable conexionesAgotadas=new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int segundos=600;
			try{
				if(!poolConexiones.conexionesRestantes()){
					crearConexiones();
					Thread.sleep(segundos*1000);
				}else{
					Thread.sleep(segundos*1000); 
				}
			}catch(Exception e){
				
			}
			
		}
		
	};
	private static Pool poolConexiones= Pool.getInstance();
	private Thread crearConexiones=new Thread(conexionesAgotadas);
	private static Controlador_Pool INSTANCE=createInstance();
	
	public Controlador_Pool(){
	}
	private synchronized static Controlador_Pool createInstance() {
		INSTANCE = new Controlador_Pool();
		return INSTANCE;
    }
	
	public void crearConexiones(){
		if(poolConexiones.getSegmentosCreados()<poolConexiones.getSegmentos()){
			ArrayList<Conexion>  conexiones= poolConexiones.crearSegmentoConexiones();
			for(int indice=0; indice<poolConexiones.getTamanioSegmentos();indice++){
				Conexion conexion =obtenerConexionDB();
				conexiones.add(conexion);
				}
			poolConexiones.asignarSegmentoCreado(conexiones);
		}

	}
	
	private Conexion obtenerConexionDB(){ //metodo privado que se llamara por admin conexion
		DBConnectionFactory factoryDB = new DBConnectionFactory();
		Connection conn = factoryDB.createConnection();
		Conexion conexion=poolConexiones.crearConexion(conn);
		return conexion;
	}

	
	public void cambiarConfigPool(int numeroSegmento, int tamanioSegmentos) {
		poolConexiones.setSegmentos(numeroSegmento);
		poolConexiones.setTamanioSegmentos(tamanioSegmentos);
	}

	public void reasignarConexion(int numeroSegmento, int tamanioSegmento) {
		if (numeroSegmento > poolConexiones.getSegmentos()) {
			ArrayList<Conexion> nuevoSegmento = poolConexiones.crearSegmentoConexiones();
			ArrayList<Conexion> respaldoconexion = poolConexiones.conexiones_enUso();
			for (int indice = 0; indice < tamanioSegmento; indice++) {
				Conexion conexion = respaldoconexion.get(indice);
				nuevoSegmento.add(conexion);
			}
			poolConexiones.asignarSegmentoCreado(respaldoconexion);
			crearConexiones.run();
		}else{
		if (numeroSegmento < poolConexiones.getSegmentos()){
			ArrayList<Conexion> nuevoSegmento = poolConexiones.eliminarSegmentoConexiones();
			ArrayList<Conexion> respaldoconexion = poolConexiones.conexiones_enUso();
			for (int indice = 0; indice < tamañoSegmento; indice++) {
				Conexion conexion = respaldoconexion.get(indice);
				nuevoSegmento.add(conexion);
		}
			poolConexiones.asignarSegmentoCreado(respaldoconexion);
			crearConexiones.run();
		}
		}
}
}
