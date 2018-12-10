package controller;

import model.User;

public class connectionControleur extends User {


	public User curentUser;
	/*
	public connectionControleur(String login1, String mdp) {
		super(login1, mdp);
		// TODO Auto-generated constructor stub
	}
*/

	
	
	public connectionControleur(String login, String password) {
		super(login, password);
		User curentUser = new model.User(login, password);
		
	}




	public static Boolean  testCredancial (String login, String motDePasse) {
		User curentUser = null;	
		if ((login instanceof String) && (motDePasse instanceof String)){
				curentUser = setConnection(login, motDePasse);
				
			} 
		
		
		return curentUser!=null;
	}
	
	
	public static User setConnection (String login, String motDePasse) {
		User currentUser = new User(login, motDePasse);
		
		return currentUser;
	}
}
