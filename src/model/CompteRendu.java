package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CompteRendu {
	public String medecin;
	public String motif;
	public String commentaire;
	public String echantillon;
	public int id_utilisateur;
	public int id_rapport;
	
	public CompteRendu() { 
	
	DB_Connection obj_DB_Connection = new DB_Connection();
	Connection connection = obj_DB_Connection.get_connection_bdAV();
	PreparedStatement ps=null;
	ResultSet rs=null;
	

		try {
			// choix de la db
			String query = String.format("INSERT INTO 'rapport' (`idRapport`, `date`, `bilan`, `motif`, `idUtilisateur`,`echantillon`) VALUES (id_rapport,date,commentaire,motif,id_utilisateur, echantillon");
			ps = connection.prepareStatement(query);
			//ps.setString(1, sl_no);
			//System.out.println(ps);
			rs=ps.executeQuery();
	
	
			rs=ps.executeQuery();
			
				String id_rapport = rs.getString("idRapport");		
				commentaire = rs.getString("bilan");
				motif = rs.getString("motif");			
				String id_utilisateur = rs.getString("idUtilisateur");	
				System.out.println("connection reussite");
						
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
