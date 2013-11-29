package fmat.arquitectura.mvc.view;

import fmat.arquitectura.Seguridad.Modelo.Usuario;
import fmat.arquitectura.mvc.config.Config;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class ApplicationView {
	private boolean lastResponse;
	public Object returnedVariable;
	private Usuario user;
        
	public void sendToController(String action) throws NoSuchFieldException{
//			System.out.println(this.getClass().getName());
            String[] variableNames = Config.getInstance().getParametersOf(this.getClass().getName(), action);
            for(int i = 0; i < variableNames.length; i++){
            	System.out.println(variableNames[i]);
            }
            Field[] actualVariables = this.getClass().getDeclaredFields();
            ArrayList<Field> coincidentalVariables = new ArrayList<Field>();

            for(int j = 0; j < variableNames.length; j++){
                for(int i=0; i < actualVariables.length; i++){
                    if(variableNames[j].equals(actualVariables[i].getName())){
                        coincidentalVariables.add(actualVariables[i]);
                    }
                }
            }
            sendToConfig(coincidentalVariables, action);
	}
	
	private void sendToConfig(ArrayList<Field> fieldParams, String action){
            Object[] params = new Object[fieldParams.size()];
		for(int i = 0; i < fieldParams.size(); i++){
                try {
                        try {
                                params[i] = fieldParams.get(i).getDeclaringClass().getMethod("get" + fieldParams.get(i).getName()).invoke(this);
                                
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(ApplicationView.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(ApplicationView.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(ApplicationView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(ApplicationView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(ApplicationView.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                setlastResponse(    
                    Config.getInstance().sendParamsToController(this.getClass().getName(), action, params, this, getUser())
                );
	}

        public boolean islastResponse() {
            return lastResponse;
        }

        public void setlastResponse(boolean lastResponse) {
            this.lastResponse = lastResponse;
        }

		public Object getReturnedVariable() {
			return returnedVariable;
		}

		public void setReturnedVariable(Object returnedVariable) {
			this.returnedVariable = returnedVariable;
		}

		public Usuario getUser() {
			return user;
		}

		public void setUser(Usuario user) {
			this.user = user;
		}
}
