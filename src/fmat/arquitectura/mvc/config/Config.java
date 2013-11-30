/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmat.arquitectura.mvc.config;

import fmat.arquitectura.Seguridad.Modelo.Usuario;
import fmat.arquitectura.mvc.control.ApplicationController;
import fmat.arquitectura.mvc.view.ApplicationView;
import fmat.arquitectura.test.Alumno;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class Config {
    
    private static ArrayList<Relation> relations;
    private String path;
    private static Config configuration = new Config("src/fmat/arquitectura/test/ejemplo.xml");
    
    private Config(String path){
        this.path = path;
        readXML();
    }
    
    public static Config getInstance(){
        return configuration;
    }
    
    private void readXML() {
        JXMLReader reader = new JXMLReader(path);
        relations = reader.getRelations();
//        System.out.println("------------------------------------------------");
//        for (int i = 0; i < relations.size(); i++) {
//            System.out.println("------------------------------------------------");
//            System.out.println("Relation " + String.valueOf(i+1));
//            System.out.println("View: " + relations.get(i).getView());
//            System.out.println("Controller: " + relations.get(i).getController());
//            System.out.println("ViewAction: " + relations.get(i).getViewMethod());
//            System.out.println("ControllerAction: " + relations.get(i).getControllerMethod());
//            String[] params = relations.get(i).getParametersAsArray();
//            for (int j = 0; j < params.length; j++)
//                System.out.println("Parametro => " + params[j]);
//            System.out.println("------------------------------------------------");
//        }
//        System.out.println("------------------------------------------------");
    }
    
    public  String[] getParametersOf(String view, String viewMethod){
        for (int i = 0; i < relations.size(); i++) {
            if(relations.get(i).getView().equals(view) && relations.get(i).getViewMethod().equals(viewMethod))
//            	System.out.println(relations.get(i).getView());
//            	System.out.println("jejeje");
                return relations.get(i).getParametersAsArray();
        }
        return null;
    }

    public  String[] getParametersTypeOf(String view, String viewMethod){
        for (int i = 0; i < relations.size(); i++) {
            if(relations.get(i).getView().equals(view) && relations.get(i).getViewMethod().equals(viewMethod))
                return relations.get(i).getParametersTypeAsArray();
        }
        return null;
    }
    
    public boolean hasPermission(Usuario user, String viewMethodName){
    	try {
    		return user.hasPermissionFor(viewMethodName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }
    
    public boolean sendParamsToController(String viewName, String viewMethodName, Object[] params, ApplicationView av, Usuario user){
        if(!hasPermission(user, viewMethodName)){
        	return false;
        }
        
        String controllerName = getControllerName(viewName, viewMethodName);
        ApplicationController controller = instanceController(controllerName);
        try {
        	try {
            boolean response = false;
//            	 String[] paramsValues = getParametersOf(viewName, viewMethodName);
			if (params.length == 0){
				response = (boolean)controller.getClass().getMethod(getControllerMethod(viewName, viewMethodName)).invoke(controller);
			} else {
				controller.setParams(params);
				response = (boolean)controller.getClass().getMethod(getControllerMethod(viewName, viewMethodName)).invoke(controller);
			}
			if(response){
				av.setReturnedVariable(controller.getreturnedVariable());
			} 
			return response;
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private ApplicationController instanceController(String name) {
        try {
            Class c= Class.forName(name);
            return (ApplicationController) c.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String getControllerName(String view, String viewMethod){
        for (int i = 0; i < relations.size(); i++) {
            if(relations.get(i).getView().equals(view) && relations.get(i).getViewMethod().equals(viewMethod))
                return relations.get(i).getController();
        }
        return null;
    }
    
    private String getControllerMethod(String view, String viewMethod){
        for (int i = 0; i < relations.size(); i++) {
            if(relations.get(i).getView().equals(view) && relations.get(i).getViewMethod().equals(viewMethod))
                return relations.get(i).getControllerMethod();
        }
        return null;
    }
}
