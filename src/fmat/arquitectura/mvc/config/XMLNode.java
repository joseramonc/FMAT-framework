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
public class XMLNode {
    
    private String name;
    private String value;
    private ArrayList<XMLNode> childs;
    private XMLNode parent;

    public XMLNode(XMLNode parent, String name) {
        this.parent = parent;
        this.name = name;
        this.childs = new ArrayList();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public XMLNode getChild(String name){
        for (int i = 0; i < childs.size(); i++) {
            if(childs.get(i).getName().equals(name)){
                return childs.get(i);
            }
        }
        return null;
    }
    
    public XMLNode getChild(String name, int index){
        int a = 0;
        for (int i = 0; i < childs.size(); i++) {
            if(childs.get(i).getName().equals(name)){
                a++;
                if(a==index)
                    return childs.get(i);
            }
        }
        return null;
    }
    

    public XMLNode getParent() {
        return parent;
    }

    public void setParent(XMLNode parent) {
        this.parent = parent;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getChilds() {
        return childs;
    }
    
    public void addChild(XMLNode child){
        childs.add(child);
    }

    public void setChilds(ArrayList childs) {
        this.childs = childs;
    }
    
}
