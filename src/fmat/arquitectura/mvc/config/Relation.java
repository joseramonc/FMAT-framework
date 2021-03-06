/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmat.arquitectura.mvc.config;

import java.util.ArrayList;

/**
 *
 * @author javier
 */
public class Relation {
    
    private String view;
    private String controller;
    private String viewMethod;
    private String controllerMethod;
    private ArrayList<String> parameters, types;

    public Relation(String view, String controller, String viewMethod, String controllerMethod, ArrayList<String> parameters,ArrayList<String> types) {
        this.view = view;
        this.controller = controller;
        this.viewMethod = viewMethod;
        this.controllerMethod = controllerMethod;
        this.parameters = parameters;
	this.types = types;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public ArrayList<String> getParametersType() {
        return types;
    }

    public void setParametersType(ArrayList<String> types) {
        this.types = types;
    }
    
    public String[] getParametersAsArray(){
        String[] params = new String[parameters.size()];
        for (int i = 0; i < parameters.size(); i++) {
            params[i] = parameters.get(i);
        }
        return params;
    }

    public String[] getParametersTypeAsArray(){
        String[] type = new String[types.size()];
        for (int i = 0; i < types.size(); i++) {
            type[i] = types.get(i);
        }
        return type;
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }
    
    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getViewMethod() {
        return viewMethod;
    }

    public void setViewMethod(String viewMethod) {
        this.viewMethod = viewMethod;
    }

    public String getControllerMethod() {
        return controllerMethod;
    }

    public void setControllerMethod(String controllerMethod) {
        this.controllerMethod = controllerMethod;
    }
}
