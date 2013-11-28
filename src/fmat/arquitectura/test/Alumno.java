package fmat.arquitectura.test;

public class Alumno {
	private String matricula;
	private String nombre;

	public String getMatricula() {
		return matricula;
	}
	
	public String toString(){
		return nombre + "\n" + matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
