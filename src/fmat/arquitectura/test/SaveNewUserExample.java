package fmat.arquitectura.test;

import fmat.arquitectura.Seguridad.Controlador.ControladorPerfil;
import fmat.arquitectura.Seguridad.Controlador.ControladorUsuario;
import fmat.arquitectura.Seguridad.Encriptado.Encriptador;
import fmat.arquitectura.Seguridad.Modelo.Perfil;
import fmat.arquitectura.Seguridad.Modelo.Usuario;

public class SaveNewUserExample {

	public static void main(String[] args) {
		ControladorPerfil prflctrl = new ControladorPerfil();
		Perfil estudiante = prflctrl.obtenerPerfilPorNombre("estudiante");
		
		String clave = "4d89g13j4j91j27c582ji69373y788r6";
		Encriptador.crearEncriptador(clave);
		Encriptador encriptador = Encriptador.getInstance();
		Usuario Pedro = new Usuario("Pedro",encriptador.encriptar("Medio Mundo"),estudiante, estudiante.getListaAcciones());
		ControladorUsuario usrctrl= new ControladorUsuario();
		usrctrl.guardarUsuarioNuevo(Pedro);
		System.out.println("Usuario Guardado");
	}
}
