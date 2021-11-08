/*/////////////////En t�te////////////////////////////
 * Class : Student
 *
 * Description : Cette class permet cr�er les donn�es de
 *               un �tudiant.
 *
 * Auteur : Christophe LAGAILLARDE
 *
 * Date : 08/11/2021
 * 
 * Version : 1.0
 *        
 *///////////////////////////////////////////////////

//////////////////D�finition/////////////////////////

//////////////////D�but//////////////////////////////
public class Student {

	// Attributs
	String nom;
	String prenom;
	String sexe;
	String dob;
	String classe;
	String moyenne;
	int ID;

	
	//M�thodes
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
