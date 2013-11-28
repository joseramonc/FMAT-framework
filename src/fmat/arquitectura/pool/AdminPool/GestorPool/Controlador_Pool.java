package fmat.arquitectura.pool.AdminPool.GestorPool;

import java.sql.Connection;


import java.util.ArrayList;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;
import fmat.arquitectura.pool.AdminPool.dominio.Pool;
import fmat.arquitectura.pool.AdminPool.dominio.Conexion;

public class Controlador_Pool  {
 
	
	
	public Conexion obtenerConexion() {
		return poolConexiones.obtenerConexionDisp();
	}
	
	public void crearConexiones(){
		if(poolConexiones.getSegmentosCreados()<poolConexiones.getSegmentos()){
			ArrayList<Conexion>  conexiones= poolConexiones.crearSegmentoConexiones();
			for(int indice=0; indice<poolConexiones.getTamañoSegmentos();indice++){
				Conexion conexion =obtenerConexionDB();
				conexiones.add(conexion);
				}
			poolConexiones.asignarSegmentoCreado(conexiones);
			crearConexiones.run();
		}

	}
	
	Runnable conexionesAgotadas=new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int segundos=5;
			try{
				//to do ver si existen conexiones 
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
	
	private Conexion obtenerConexionDB(){ //metodo privado que se llamara por admin conexion
		DBConnectionFactory factoryDB = new DBConnectionFactory();
		Connection conn = factoryDB.createConnection();
		Conexion conexion=poolConexiones.crearConexion(conn);
		return conexion;
	}
	private static Pool poolConexiones= Pool.getInstance();
	private Thread crearConexiones=new Thread(conexionesAgotadas);
}
 
