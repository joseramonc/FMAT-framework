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
    public Object returnedVariable;
    
    protected ArrayList getParams(){
        return params;
    }
    
    public Object getreturnedVariable(){
    	return returnedVariable;
    }
    
    public void setReturnedVariable(Object returnedVar){
    	returnedVariable = returnedVar;
    }
    
    public ApplicationController getController(){
        return this;
    }
    
    public void setParams(ArrayList<Object> newParams){
        params = newParams;
    }
}
