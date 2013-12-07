package fmat.arquitectura.test;

import java.util.ArrayList;

import fmat.arquitectura.DBAccess.connection.DBConnectionFactory;
import fmat.arquitectura.DBAccess.modelo.DBConnection;

public class TransactionExample {
	
	public static void main(String[] args){
		DBConnectionFactory cf = new DBConnectionFactory();
		DBConnection dbc = cf.createConnection();
		ArrayList<String> queries = new ArrayList<String>();
		
		queries.add("select * from perfil where perfil.Nombre = \"Maestro\"");
		queries.add("select * from perfil");
		queries.add("DELETE FROM accion WHERE ID='3'");
		queries.add("INSERT INTO accion (ID,NOMBRE) values ('3','prestar')");
		queries.add("UPDATE accion SET NOMBRE='otroPrestar' WHERE ID = '3' ");
		dbc.executeTransaction(queries);
	}
}
