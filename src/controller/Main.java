package controller;

import java.sql.Connection;
import java.sql.SQLException;
import view.Fenetre;
import controller.CnxBDD;
import model.Connecteur;

public class Main {
	public Main(){
	}
	public static void main(String[] args)
        {
        	
        	Object connecteur = Connecteur.connecteurUL;
        	new Fenetre();
        	
        }


	
}

