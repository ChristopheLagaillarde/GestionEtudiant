/*/////////////////En tête////////////////////////////
 * Class : QueryStatement
 *
 * Description : Cette class permet de faire des requete
 *               SQL à partir du JDBC
 *
 * Auteur : Christophe LAGAILLARDE
 *
 * Date : 08/11/2021
 * 
 * Version : 1.0
 *        
 *///////////////////////////////////////////////////

//////////////////Définition/////////////////////////

//////////////////Début//////////////////////////////
public class QueryStatement {
	
	// Attribut
	Student eleveConcerne;
	String requete;
	
	 QueryStatement(Student eleveConcerne,String requete) {
		 this.eleveConcerne = eleveConcerne;
		 this.requete = requete;
	 }

	 String getQueryStatement(Student eleveConcerne,String requete) {
		 if(requete.equals("ajouter")) {
			 return "INSERT INTO eleve(nom,prenom,sexe,dob,classe,moyenne) VALUES ('" 
					 + eleveConcerne.nom 
					 + "','" 
					 + eleveConcerne.prenom 
					 +"','"
					 +eleveConcerne.sexe 
					 + "','"
					 +eleveConcerne.dob 
					 + "','" 
					 + eleveConcerne.classe 
					 + "','"
					 + eleveConcerne.moyenne 
					 + "');";
		 }

		 else if(requete == "supprimer") {
			 return "DELETE FROM eleve WHERE ID ="
		 + eleveConcerne.ID
		 +";";
		 }
		 else if(requete == "selectionner") {
			 return "Select * FROM eleve;";
		 }
		 return "";
	 }
}
//////////////////Fin//////////////////////////////

