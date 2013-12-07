package fmat.arquitectura.pool.AdminPool.dominio;

import java.util.ArrayList;
import java.util.HashMap;

import fmat.arquitectura.DBAccess.modelo.DBConnection;

public class Pool  {
	
	
	
	public static Pool getInstance() {
        return INSTANCE;
    }
	
	public Conexion obtenerConexionDisp() {
		Conexion conexionDisponible=null;
		for (int segmento=1;segmento<this.segmentos;segmento++){
			if(piscinaConexiones.containsKey(segmento)){
				conexiones= piscinaConexiones.get(segmento);
				for(int i=0; i<conexiones.size();i++){
					Conexion conexion=conexiones.get(i);
					if(conexion.getEstado()){
						conexion.setEstado(false);
						conexionDisponible= conexion;
						return conexionDisponible;	
					}
				}
			}else{
				break;
			}
		}
		return conexionDisponible;
	}
	
	public Conexion crearConexion(DBConnection conn){
		boolean estadoConexion=true;
		Conexion conexion =new Conexion(conn,estadoConexion);
		return conexion;
	}
	
	public ArrayList<Conexion> crearSegmentoConexiones(){
		segmentosCreados+=1;
		return this.conexiones=new ArrayList<Conexion>();
	}
	
	public ArrayList<Conexion> crearSegmento(){
		return this.conexiones=new ArrayList<Conexion>();
	}
	
	public void asignarSegmentoCreado(ArrayList<Conexion> conexiones){
		this.conexiones=conexiones;
		piscinaConexiones.put(segmentosCreados, this.conexiones);
	}
	
	public void asignarSegmentoRespaldo(int segmento,ArrayList<Conexion> conexiones){
		this.conexiones=conexiones;
		piscinaConexiones.put(segmento, this.conexiones);
	}
	
	public int getSegmentos() {
		return segmentos;
	}

	public void setSegmentos(int segmentos) {
		this.segmentosCreados=0;
		this.segmentos = segmentos;
	}

	public int getTamanioSegmentos() {
		return tamanioSegmentos;
	}

	public void setTamanioSegmentos(int tamanioSegmentos) {
		this.tamanioSegmentos = tamanioSegmentos;
	}
	
	public int getSegmentosCreados(){
		return this.segmentosCreados;
	}
	
	public void setSegmentosCreados(int segmentosCreados){
		this.segmentosCreados=segmentosCreados;
	}
	
	public boolean conexionesRestantes(){
		int conexionesRestantes=conexionesDisponibles();
		int conexionesPiscina=this.segmentosCreados*this.tamanioSegmentos;
		int porcentajeConRestante=conexionesRestantes/conexionesPiscina;
		if(porcentajeConRestante>.2 || porcentajeConRestante==0){
			return true;
		}else{
			return false;
		}
		
	}
	
	public ArrayList <Conexion> conexiones_enUso(){
		respaldoConexion= new ArrayList <Conexion>();
		for (int segmento=1;segmento<this.segmentos;segmento++){
			if(piscinaConexiones.containsKey(segmento)){
				conexiones= piscinaConexiones.get(segmento);
				for(int i=0; i<conexiones.size();i++){
					Conexion conexion=conexiones.get(i);
					if(!conexion.getEstado()){
						respaldoConexion.add(conexion);
					}
				}
			}
		} return respaldoConexion;
	}
	 
	private ArrayList<Conexion> conexiones;
	private ArrayList <Conexion> respaldoConexion;
	private HashMap<Integer,ArrayList<Conexion>> piscinaConexiones;
	private int segmentos, tamanioSegmentos;
	private int segmentosCreados=0;
	private static Pool INSTANCE=createInstance();
	
	private Pool(){
		piscinaConexiones=new HashMap<Integer,ArrayList<Conexion>>();
	}

	private synchronized static Pool createInstance() {
		INSTANCE = new Pool();
		return INSTANCE;
    }
	
	
	private int conexionesDisponibles(){
		int conexionesRestantes=0;
		for (int segmento=0;segmento<this.segmentos;segmento++){
			if(piscinaConexiones.containsKey(segmento)){
				conexiones= piscinaConexiones.get(segmento);
				for(int i=0; i<conexiones.size();i++){
					Conexion conexion=conexiones.get(i);
					if(conexion.getEstado()){
						conexionesRestantes+=1;
					}
				}
			}else{
				break;
			}
		}
		return conexionesRestantes;
	}
	
	
	
}
 
