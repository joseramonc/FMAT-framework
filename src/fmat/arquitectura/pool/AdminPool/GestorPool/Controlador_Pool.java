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
	
	public synchronized  Conexion obtenerConexion() {
		return poolConexiones.obtenerConexionDisp();
	}
	
	public void configurarPool(int segmentos,int tamanioSegmentos){
		asignarNumeroSegmentos(segmentos);
		asignarTamSegmentos(tamanioSegmentos);
		crearConexiones();
		crearConexiones.run();
	}
	
	
	public boolean cambiarConfigPool(int numeroSegmento, int tamanioSegmentos) {
		crearConexiones.stop();
		ArrayList<Conexion> conexionRespaldo=poolConexiones.conexiones_enUso();
		int numeroConexiones=conexionRespaldo.size()+1;
		double[] datosReconfig=requisitosReconfiguracion(numeroConexiones,tamanioSegmentos);
		boolean poolReconfig=reconfigPool(conexionRespaldo,datosReconfig,numeroSegmento,tamanioSegmentos);
		if(poolReconfig){
			poolConexiones.setSegmentos(numeroSegmento);
			poolConexiones.setTamanioSegmentos(tamanioSegmentos);
			crearConexiones.start();
			return true;
		}else{
			new Exception("No se ha podido reconfigurar el pool");
			return false;
		}
	}
	
	Runnable conexionesAgotadas=new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int segundos=5;
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
	
	private Controlador_Pool(){}
	
	private synchronized static Controlador_Pool createInstance() {
		INSTANCE = new Controlador_Pool();
		return INSTANCE;
    }
	
	private void crearConexiones(){
		if(poolConexiones.getSegmentosCreados()<poolConexiones.getSegmentos()){
			ArrayList<Conexion>  conexiones= poolConexiones.crearSegmentoConexiones();
			for(int indice=0; indice<poolConexiones.getTamanioSegmentos();indice++){
				Conexion conexion =obtenerConexionDB();
				conexiones.add(conexion);
				System.out.println("Numero conexión creada: "+(indice+1));
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
	
	private void asignarNumeroSegmentos(int size){
		System.out.println("Numero de conexiones por segmento: "  + poolConexiones.getSegmentos());
		System.out.println("Modificando...");
		poolConexiones.setSegmentos(size);
		System.out.println("Tamanio de segmentos modificado a: " + poolConexiones.getSegmentos());
	}
	
	private void asignarTamSegmentos(int number){
		System.out.println("Cantidad total de segmentos: " + poolConexiones.getTamanioSegmentos());
		System.out.println("Modificando...");
		poolConexiones.setTamanioSegmentos(number);
		System.out.println("Total actual de segmentos: " + poolConexiones.getTamanioSegmentos());
	}
	
	private double[] requisitosReconfiguracion(int tamanioRespaldo,int tamanioSegmento){
		double[] requisitosReconfigPool=new double[3];
		double segmentosNecesarios=(double)tamanioRespaldo/tamanioSegmento;
		double segmentosCompletos=tamanioRespaldo/tamanioSegmento;
		double conexionesFaltantes=tamanioRespaldo%tamanioSegmento;
		requisitosReconfigPool[0]=segmentosNecesarios;
		requisitosReconfigPool[1]=segmentosCompletos;
		requisitosReconfigPool[2]=conexionesFaltantes;
		return requisitosReconfigPool;
	}
	
	private boolean reconfigPool(ArrayList<Conexion> respaldo,double[] requisitosReconfig, int segmentos,int tamanioSegmentos){
		int segmentosCreados=0;
		double segmentosNecesarios=requisitosReconfig[0];
		double segmentosCompletos=requisitosReconfig[1];
		double conexionesFaltantes=requisitosReconfig[2];
		if(segmentosNecesarios<segmentos){
			for(int indice=1; indice<=segmentosCompletos;indice++){
				ArrayList<Conexion> nuevoSegmento= new ArrayList<Conexion>();
				for(int segm=0;segm<tamanioSegmentos;segm++){
					nuevoSegmento.add(respaldo.get(segm));
					respaldo.remove(segm);
				}
				poolConexiones.asignarSegmentoRespaldo(indice, nuevoSegmento);
				segmentosCreados++;
			}
			if(conexionesFaltantes!=0){
				ArrayList<Conexion> nuevoSegmento= new ArrayList<Conexion>();
				for(int ind=0;ind<respaldo.size();ind++){
					nuevoSegmento.add(respaldo.get(ind));
				}
				respaldo.removeAll(respaldo);
				double conexionesNecCrear=tamanioSegmentos-conexionesFaltantes;
				for(int con=0;con<conexionesNecCrear;con++){
					Conexion conexion =obtenerConexionDB();
					nuevoSegmento.add(conexion);
				}
				poolConexiones.asignarSegmentoRespaldo(segmentosCreados, nuevoSegmento);
			}
			return true;
		}else{
			return false;
		}
	}

	
	
}
