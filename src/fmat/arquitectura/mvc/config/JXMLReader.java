/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmat.arquitectura.mvc.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class JXMLReader {

    private ArrayList<String> lines;
    private ArrayList<XMLNode> nodes;
    private XMLNode nodeOnWork;

    public JXMLReader(String filePath) {
        lines = new ArrayList();
        nodes = new ArrayList();
        FileReader file = null;
        try {
            file = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();
            nodes.add(new XMLNode(null, line.replaceAll("<", "").replaceAll(">", "")));
            nodeOnWork = nodes.get(0);
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            deleteRootLine();
            analize();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JXMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JXMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(JXMLReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteRootLine() {
        lines.remove(0);
        lines.remove(lines.size() - 1);
    }

    private void analize() {
        XMLNode actualNode;
        String actualLine;
        int level;
        for (int i = 0; i < lines.size(); i++) {
            actualLine = lines.get(i).replaceAll("\t", "");
            if (actualLine.startsWith("</")) {
                nodeOnWork = nodeOnWork.getParent();
            } else if(actualLine.replaceAll("\t", "").startsWith("<")){
                level = actualLine.indexOf("<");
                actualNode = new XMLNode(nodeOnWork, actualLine.
                        replaceAll("<", "").replaceAll(">","").
                        replaceAll("</", "").replaceAll("\t",""));
                nodeOnWork.addChild(actualNode);
                nodes.add(actualNode);
                nodeOnWork = actualNode;
            } else{
                nodeOnWork.setValue(actualLine);
            }
        }
    }
    
    public ArrayList<Relation> getRelations(){
        ArrayList<XMLNode> childs;
        ArrayList<Relation> relations = new ArrayList();
        String controller="", view="", viewAction="", controllerAction="";
        ArrayList<String> parameters = new ArrayList();
	ArrayList<String> types = new ArrayList();
        XMLNode param;
        int i=0;
        while(i<nodes.size()){
            if(nodes.get(i).getName().equals("relation")){
                childs = nodes.get(i).getChilds();
                for (int j = 0; j < childs.size(); j++) {
                    switch(childs.get(j).getName()){
                        case "controller":
                            controller = nodes.get(i).getChild("controller").getValue();
                            break;
                        case "view":
                            view = nodes.get(i).getChild("view").getValue();
                            break;
                        case "viewAction":
                            viewAction = nodes.get(i).getChild("viewAction").getValue();
                            break;
                        case "controllerAction":
                            controllerAction = nodes.get(i).getChild("controllerAction").getValue();
                            break;
                    }   
                }
                parameters = getParameters(nodes.get(i));
		types = getParametersType(nodes.get(i));
                relations.add(new Relation(view, controller, viewAction, controllerAction,parameters,types));
            }
            i++;
        }
        return relations;
    }
    
    public ArrayList<String> getParameters(XMLNode node){
        ArrayList<String> parameters = new ArrayList();
        ArrayList<XMLNode> childs = node.getChilds();
        XMLNode parameter;
        int id=1;
        for (int i = 0; i < childs.size(); i++) {
            if(childs.get(i).getName().equals("parameter")){
                parameter = node.getChild("parameter", id);
                parameters.add(parameter.getChild("id").getValue());
                id++;
            }
        }
        return parameters;
    }

    public ArrayList<String> getParametersType(XMLNode node){
        ArrayList<String> type = new ArrayList();
        ArrayList<XMLNode> childs = node.getChilds();
        XMLNode parameter;
        int id=1;
        for (int i = 0; i < childs.size(); i++) {
            if(childs.get(i).getName().equals("parameter")){
                parameter = node.getChild("parameter", id);
                type.add(parameter.getChild("type").getValue());
                id++;
            }
        }
        return type;
    }	
}
