
package fmat.arquitectura.Seguridad.UsuarioPerfilAccion;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String contrasena;
    private Perfil perfil;
    private ArrayList<Accion> listaAcciones;

    public Usuario(String nombre, String contrasena, Perfil perfil, ArrayList<Accion> listaAcciones) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.perfil = perfil;
        this.listaAcciones = listaAcciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
