package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
	public static final DB_Connection connectionActive = new DB_Connection();
	public static Connection connection = null;
	public static void main(String[] args) {
		connectionActive.get_connection();  
	}
	public Connection get_connection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");              
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/appli_visiteur?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "root");
		} catch (Exception e) {
			System.out.println("Erreur : " + e);
		}
		
		return connection;
	}
}