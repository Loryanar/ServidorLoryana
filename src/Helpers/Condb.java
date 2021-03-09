package Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Condb extends LeerProperties{
	public PreparedStatement stmt;
	public Connection con;
	public ResultSet rs;
	public Properties leer= new LeerProperties().getFile("C:\\Users\\DELL\\web22\\Servidor\\WebContent\\queries.properties");
	
	public Condb(){
		  try{
			
		    Class.forName ("org.postgresql.Driver");
		    this.con = DriverManager.getConnection("jdbc:postgresql://ec2-108-128-104-50.eu-west-1.compute.amazonaws.com\r\n:5432/d1pqmg7keeueoa", "apwivklgqriamj?sslmode=require", "4fcfc030995cdf3f2245fce47112a407c2d66cebae7b2cd43eb8f34109228ea0");
		    //this.con = DriverManager.getConnection (leer.getProperty("url"), leer.getProperty("user"), leer.getProperty("password"));   
		  } 
		  catch (Exception e) {
			e.printStackTrace ();
		  }	
	}
	public ResultSet buscar(String query,Object...value) throws SQLException{
		int i;
			this.stmt = (PreparedStatement) stmt.executeQuery(leer.getProperty(query));
			
			for(i=0; i < value.length; i++ )
				this.stmt.setObject(i + 1, value[i]);
			
			System.out.println(i);
			return this.stmt.executeQuery();			
	}
	public ResultSet executeStatement(String query,Object...value) throws SQLException{
		int i;
			this.stmt = this.con.prepareStatement(leer.getProperty(query));
			for(i=0; i < value.length; i++ )
				this.stmt.setObject(i + 1, value[i]);
			
			System.out.println(i);
			return this.stmt.executeQuery();			
	}
	
	
	public int executeUpdate(String query,Object...value) throws SQLException{
		this.stmt = this.con.prepareStatement(leer.getProperty(query));
		for(int i=0; i < value.length; i++ )
		this.stmt.setObject(i + 1, value[i]);
		
		return this.stmt.executeUpdate();
	}
	
	public int excuteDelete(String query, int id) throws SQLException{
		this.stmt = this.con.prepareStatement(leer.getProperty(query));
		this.stmt.setInt(0, id);
		System.out.println("id usuario en executeDelete:"+id);
		System.out.println("query delete--"+query);
		return this.stmt.executeUpdate();
	}
	
	
	protected void closeMainResource() throws SQLException {
		if(this.stmt != null) 
			this.stmt.close();
	}
	
	public void dbPrepareStatement(Object value) {
		// TODO Auto-generated method stub
		
	}
	public static Condb getIntsnaces() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
