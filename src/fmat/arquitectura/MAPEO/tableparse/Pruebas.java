package fmat.arquitectura.MAPEO.tableparse;
import java.lang.reflect.*;
public class Pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			/*Primero creamos el objeto, si necesitamos
			 * el nombre de la clase en el tiempo de compilación, podemos
			 * obtener el objeto de la calse de esta manera*/
			Class myObjectClass = Empleado.class;
			
			
			/*Si no sabemos el nombre, en tiempo de compilación
			 * pero tenemos el nombre de la clase como un string 
			 * en tiempo de ejecución, podemos crear el objeto de esta manera
			 * */
			String className = "Empleado";
			/*Si utilizamos forName(String className), debemos de proporcionar
			 * la ruta completa de donde se encuentra la clase la cual queremos
			 * instanciar*/
			Class myObjectClass2 = Class.forName(className);
			System.out.println("Nombre de la clase obtenida de myObjectClass: " + myObjectClass.getName());
			
			/*Si queremos obtener el nombre de la clase desde un objeto, podemos utilizar
			 * dos métodos, la primera nos duevuelve la ruta completa de donde se encuentra
			 * la clase.*/
			String className2 = myObjectClass2.getName();
			System.out.println("Nombre de la clase a partir del objeto myObjectClass2: " + className2);
			/*La segunda manera, sólo nos devuelve el nombre de la clase*/
			String className3 = myObjectClass2.getSimpleName();
			System.out.println("Nombre de la clase (forma simple) a partir del objeto myObjectClass2: " + className3);
			
			/*Podemos acceder a los modificadores de la clase, es decir las palabras: Public
			 * Private, Static, etc, mediante la siguiente función*/
			int modifiers = myObjectClass2.getModifiers();
			/*Cada modificador es una bandera que indicará que modificadores
			 * se utilizan*/
			//Se utilizan metodos abstractos?
			boolean respuesta;
			boolean respuesta2;
			boolean respuesta3;
			respuesta = Modifier.isAbstract(modifiers);
			System.out.print("Se utilian metodos abstractos? " +  respuesta);
			System.out.println();
			//se utilizan métodos privados?
			respuesta2 = Modifier.isPrivate(modifiers);
			System.out.print("Se utilian metodos privadoss? " +  respuesta2);
			System.out.println();
			//se utilizan métodos publicos?
			respuesta3 = Modifier.isPublic(modifiers);
			System.out.print("Se utilian metodos publicos? " +  respuesta3);
			System.out.println();
			System.out.println();
			
			/*Para acceder a los métodos de la clase utilizamos la siguiente instruccion*/
			Method[] method = myObjectClass2.getMethods();
			System.out.println("***Lista de metodos***");
			for(int i = 0; i < method.length; i++){
				System.out.println(method[i]);
			}
			System.out.println();
			/*Para acceder a los atributos de la clase (publicos)*/
			Field[] field = myObjectClass2.getFields();
			System.out.println("***Lista de atributos***");
			for(int i = 0; i < field.length; i++){
				System.out.println(field[i]);
			}
			System.out.println();
			
		}
		catch(ClassNotFoundException e){
			System.out.println("No se encontró la clase" + e);
		}
	}
}
