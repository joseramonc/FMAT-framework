
package fmat.arquitectura.Seguridad.Modelo;

import java.util.ArrayList;

public class Usuario {
	private int id;
    private String nombre;
    private String contraseña;
    private Perfil perfil;
    private ArrayList<Accion> listaAcciones;

    public Usuario(String nombre, String contraseña, Perfil perfil, ArrayList<Accion> listaAcciones) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.perfil = perfil;
        this.listaAcciones = listaAcciones;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public ArrayList<Accion> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(ArrayList<Accion> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }
    
    
}
