package fmat.arquitectura.test;

import fmat.arquitectura.Seguridad.Encriptado.Encriptador;

public class EncryptPasswordExample {

	public static void main(String[] args) {
		String msj = "holamundo"; 
        String key = "4d89g13j4j91j27c582ji69373y788r6"; 
        Encriptador.crearEncriptador(key);
        Encriptador encriptador =  Encriptador.getInstance();
        System.out.println("Mensaje : " + msj); 
        String encript = encriptador.encriptar(msj); 
        System.out.println("Encriptado : " + encript); 
        System.out.println("Desencriptado : " + encriptador.desencriptar(encript));
	}

}
