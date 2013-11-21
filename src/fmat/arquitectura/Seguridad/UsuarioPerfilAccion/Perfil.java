
package fmat.arquitectura.Seguridad.UsuarioPerfilAccion;

import java.util.ArrayList;

public class Perfil {
    private String nombre;
    private ArrayList<Accion> listaAcciones;
    
    public Perfil (String nombre, ArrayList<Accion> listaAcciones){
        this.nombre = nombre;
        this.listaAcciones = listaAcciones;
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
