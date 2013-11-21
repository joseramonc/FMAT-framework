package fmat.arquitectura.pool.AdminPool.dominio;

import java.sql.Connection;
public class Conexion {
 
	public Conexion(Connection conexion,boolean estado){
		this.conexion=conexion;
		this.estado=estado;
	}
	
	public Connection getConexion() {
		return conexion;
	}

	public boolean getEstado() {
		return estado;
	}
	
	public void setEstado(boolean estado){
		this.estado=estado;
	}
	
	private Connection conexion;
	private boolean estado;
}
 
