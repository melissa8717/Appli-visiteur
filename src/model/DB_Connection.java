package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class DB_Connection {
	public static final DB_Connection connectionActive = new DB_Connection();

	public static Connection connectionbduser = getConnectionBduser();
	public static Connection connectionbdmedoc = getConnectionBdmedoc() ;

	//public static void main(String[] args) {
		connectionActive.getConnectionBduser();
	//}
	
	
	//connection a la base de donn� bduser
	public static Connection getConnectionBduser(){
		try {
			Class.forName("com.mysql.jdbc.Driver");              
			connectionbduser = DriverManager.getConnection("jdbc:mysql:http://bdlab.gsb.lan/phpmyadmin/sql.php?db=bduserLab&table=specialitepraticien&server=1&target=&token=75fd07ac1d3b861c642e9f46e3fd15c5#PMAURL-1:sql.php?db=bduserLab&table=message&server=1&target=&token=75fd07ac1d3b861c642e9f46e3fd15c5","rootuser", "Aristee.2018..//");
		} catch (Exception e) {
			System.out.println("Erreur du premier connecteur : " + e);
		}
		
		return connectionbduser;
	}
	
	
	//connection a la base de donn�e bdmedoc 
	public static Connection getConnectionBdmedoc(){
		try {
			Class.forName("com.mysql.jdbc.Driver");              
			connectionbdmedoc = DriverManager.getConnection("jdbc:mysql:http://bdlab.gsb.lan/phpmyadmin/index.php?db=bduserLab&table=message&target=sql.php&token=865360e8f5e9efd46199b406cd85b0ef#PMAURL-18:db_structure.php?db=bdmedocLab&table=&server=1&target=&token=865360e8f5e9efd46199b406cd85b0ef","rootmedoc", "Aristee.2018..//");
		} catch (Exception e) {
			System.out.println("Erreur du second connecteur : " + e);
		}
		
		return connectionbdmedoc;
	}

	/*public Connection get_connection_bdAV() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");              
			connectionbdAV = DriverManager.getConnection("jdbc:mysql:http://localhost/phpmyadmin/","root", "");
		} catch (Exception e) {
			System.out.println("Erreur : " + e);
		}
		
		return connectionbdAV;
		
	}*/
	
}