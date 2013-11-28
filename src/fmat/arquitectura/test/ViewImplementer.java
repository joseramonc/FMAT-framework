package fmat.arquitectura.test;

import fmat.arquitectura.mvc.config.Config;
import fmat.arquitectura.mvc.view.ApplicationView;

import javax.swing.JOptionPane;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class ViewImplementer extends ApplicationView{
    private View v;
    private String campos1;
    private String campos2;
    
    public ViewImplementer(final View view) {
        setView(view);
    }
    
    public void setView(View view){
    	v = view;
    }
    
    public static void main(String[] args) throws NoSuchFieldException {
//        ViewImplementer v = new ViewImplementer();
    }
    
    public String getcampos1(){
        return campos1;
    }
    
    public String getcampos2(){
        return campos2;
    }
    
    public void presionoBoton() throws NoSuchFieldException{
        campos1 = v.getJLabel1().getText();
        campos2 = v.getJLabel2().getText();
        sendToController("presionaBoton");
        if(islastResponse()){
            System.out.println(islastResponse());
            System.out.println(((Alumno)getReturnedVariable()).getMatricula());
        } else {
            System.out.println(islastResponse());
        }
    }
}
