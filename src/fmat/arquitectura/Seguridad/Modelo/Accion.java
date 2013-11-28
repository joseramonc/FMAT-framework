
package fmat.arquitectura.Seguridad.Modelo;

public class Accion {
	private int id;
    private String nombre;
    private boolean estado;

    public Accion(String nombre, boolean estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    public Accion(String nombre) {
        this.nombre = nombre;
        this.estado = false;
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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public boolean equals(Object o){
    	if(o==null){
    		return false;
    	}else{
    		if(this==o){
    			return true;
    		}else{
    			if(o instanceof Accion){
    				if(((Accion)o).getNombre().compareTo(this.nombre) == 0){
    					return true;
    				}else
    					return false;
    			}else{
    				return false;
    			}
    		}
    	}
    }
}
