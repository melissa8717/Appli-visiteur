package controller;

import java.sql.Connection;
import java.sql.SQLException;
import view.Fenetre;

public class Main {
	public Main(){
	}
	public static void main(String[] args)
        {
        	CnxBDD toto = new CnxBDD();
        	Connection connection = toto.connecteurUserLab();
        	System.out.println("connection :"+connection);
        	new Fenetre();
        	
        }


	
}

