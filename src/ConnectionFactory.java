/*/////////////////En tête////////////////////////////
 * Class : ConnectionFactory
 *
 * Description : Cette class permet de se connecter à une base
 *               de donnée.
 *
 * Auteur : Christophe LAGAILLARDE
 *
 * Date : 08/11/2021
 * 
 * Version : 1.0
 *        
 *///////////////////////////////////////////////////

// Déclaration des bibliotheques de fonctions...
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

//////////////////Définition//////////////////////////

//////////////////Début//////////////////////////////
public class ConnectionFactory {
	
	//Attributs
	Connection connectionABD;
	ResultSet resultatRequete;
	Statement requeteAFaire;

	// Méthodes
	public ConnectionFactory(String nomBD, String username, String pwd){
		try {
			this.connectionABD= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ nomBD,username,pwd);
			this.requeteAFaire = this.connectionABD.createStatement();

		} catch (SQLException echecDeConnection) {
			JOptionPane.showMessageDialog(null, "Echec de connection à la BD");
		}

	}
}
//////////////////Fin//////////////////////////////
