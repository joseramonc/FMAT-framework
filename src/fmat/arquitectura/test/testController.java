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
    
    
    public void add(){
        getParams();
    }
    
    public boolean saludaAlConfig(){
        System.out.println("hola config");
        return true;
    }
}
