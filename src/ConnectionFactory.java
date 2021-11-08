/*/////////////////En t�te////////////////////////////
 * Class : ConnectionFactory
 *
 * Description : Cette class permet de se connecter � une base
 *               de donn�e.
 *
 * Auteur : Christophe LAGAILLARDE
 *
 * Date : 08/11/2021
 * 
 * Version : 1.0
 *        
 *///////////////////////////////////////////////////

// D�claration des bibliotheques de fonctions...
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

//////////////////D�finition//////////////////////////

//////////////////D�but//////////////////////////////
public class ConnectionFactory {
	
	//Attributs
	Connection connectionABD;
	ResultSet resultatRequete;
	Statement requeteAFaire;

	// M�thodes
	public ConnectionFactory(String nomBD, String username, String pwd){
		try {
			this.connectionABD= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ nomBD,username,pwd);
			this.requeteAFaire = this.connectionABD.createStatement();

		} catch (SQLException echecDeConnection) {
			JOptionPane.showMessageDialog(null, "Echec de connection � la BD");
		}

	}
}
//////////////////Fin//////////////////////////////
