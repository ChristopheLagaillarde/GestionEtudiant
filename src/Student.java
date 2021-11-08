/*/////////////////En tête////////////////////////////
 * Class : Student
 *
 * Description : Cette class permet créer les données de
 *               un étudiant.
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
public class Student {

	// Attributs
	String nom;
	String prenom;
	String sexe;
	String dob;
	String classe;
	String moyenne;
	int ID;

	
	//Méthodes
	Student(String nom, String prenom, String sexe, String dob, String classe, String moyenne, int ID){
       this.nom = nom;
       this.prenom = prenom;
       this.sexe = sexe;
       this.dob = dob;
       this.classe = classe;
       this.moyenne = moyenne;
       this.ID = ID;
	}
}
//////////////////Fin//////////////////////////////
