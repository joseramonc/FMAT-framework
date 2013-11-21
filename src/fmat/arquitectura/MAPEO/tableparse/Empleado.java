package fmat.arquitectura.MAPEO.tableparse;

public class Empleado {

	private int id;
	private String nombre;
	private String mail;
	public String notas;
		
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setMail(String mail){
		this.mail = mail;
	}
	
	public String getMail(){
		return mail;
	}
	
	public void imprimirMensaje(){
		System.out.println("Hola!");
	}
}
