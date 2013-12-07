package fmat.arquitectura.DBAccess.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnection {
	
	public DBConnection(String url, String user, String pass){
		this.url = url;
		this.user = user;
		this.pass = pass;
		try {
			this.connection =  DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean execute(String query){
		try {
			return connection.createStatement().execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet executeQuery(String query){
		try{
			return connection.createStatement().executeQuery(query);
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean executeTransaction(ArrayList<String> queries){
		try {
		setAutoCommitFalse();
		for(int i=0; i < queries.size(); i++){
			PreparedStatement query = connection.prepareStatement(queries.get(i));
			query.execute();
		}
		finishTransaction();
		return true;
		} catch (SQLException e) {
			e.printStackTrace();
			executeRollback();
			return false;
		}finally{
			setAutoCommitTrue();
		}
		
	}
	
	private void setAutoCommitFalse(){
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void setAutoCommitTrue(){
		try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void finishTransaction(){
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void executeRollback(){
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection connection;
	private final String url;
	private final String user;
	private final String pass;
}
