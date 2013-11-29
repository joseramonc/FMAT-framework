
package fmat.arquitectura.Seguridad.Modelo;

import java.util.ArrayList;

public class Perfil {
	private int id;
    private String nombre;
    private ArrayList<Accion> listaAcciones;
    
    public Perfil (String nombre, ArrayList<Accion> listaAcciones){
        this.nombre = nombre;
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

    public ArrayList<Accion> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(ArrayList<Accion> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }
}
