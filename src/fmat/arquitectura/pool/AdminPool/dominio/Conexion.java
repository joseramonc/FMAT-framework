package fmat.arquitectura.pool.AdminPool.dominio;


import fmat.arquitectura.DBAccess.modelo.DBConnection;
public class Conexion {
 
	public Conexion(DBConnection conexion,boolean estado){
		this.conexion=conexion;
		this.estado=estado;
	}
	
	public DBConnection getConexion() {
		return conexion;
	}

	public boolean getEstado() {
		return estado;
	}
	
	public void setEstado(boolean estado){
		this.estado=estado;
	}
	
	private DBConnection conexion;
	private boolean estado;
}
 
