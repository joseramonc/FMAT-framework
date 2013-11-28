/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmat.arquitectura.test;

import fmat.arquitectura.mvc.control.ApplicationController;

/**
 *
 * @author Asus
 */
public class testController extends ApplicationController {
    
    public boolean saludaAlConfig(){
        System.out.println("hola config");
        Alumno a = new Alumno();
        a.setMatricula("123456");
        a.setNombre("juanito G.");
        setReturnedVariable(a); 
        return true;
    }
    
    public boolean greetingWithParms(){
    	Object[] a = getParams();
    	for(int i = 0 ;i<a.length;i++){
    		System.out.println(a[i].toString());
    	}
    	return true;
    }
    
}

