/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmat.arquitectura.mvc.control;

import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public abstract class ApplicationController {
    private ArrayList<Object> params;
    
    protected ArrayList getParams(){
        return params;
    }
    
    public ApplicationController getController(){
        return this;
    }
    
    public void setParams(ArrayList<Object> newParams){
        params = newParams;
    }
}
