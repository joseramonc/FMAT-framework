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
    private Object[] params;
    public Object returnedVariable;

    protected Object[] getParams(){
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
    
    public void setParams(Object[] newParams){
        params = newParams;
    }
}
