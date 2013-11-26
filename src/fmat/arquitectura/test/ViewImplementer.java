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
    private final View v;
    private String campos1;
    private String campos2;
    
    public ViewImplementer() {
        v = new View(this);
        v.setVisible(true);
    }
    
    public static void main(String[] args) throws NoSuchFieldException {
        ViewImplementer v = new ViewImplementer();
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
        sendToController("presionoBoton");
        if(islastResponse()){
            System.out.println(islastResponse());
        } else {
            System.out.println(islastResponse());
        }
    }
}
