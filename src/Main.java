/*/////////////////En tête////////////////////////////
 * Programme : Gestion étudiant
 *
 * Description : Programme permet de gérer des étudiants
 *
 * Auteur : Christophe LAGAILLARDE
 *
 * Date : 08/11/2021
 * 
 * Version : 1.0
 *        
 *///////////////////////////////////////////////////

// Déclaration des bibliotheques de fonctions...
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;

//////////////////Le Programme principal/////////////

//////////////////Début//////////////////////////////
public class Main {

	private JFrame fenetreGestionEtudiant;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main fenetre = new Main();
					fenetre.fenetreGestionEtudiant.setVisible(true);
				} catch (Exception erreurFenetre) {
					erreurFenetre.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	// Variable utilisé dans les ActionEvents
	JTextField barreID = null;                         // On les initialise à l'exterieur
	JTextField	barreNom = null;                       // de initialize sinon il y a une erreur
	JTextField	barrePrenom = null;
	JTextField	barreDOB = null;
	JTextField barreMoyenne = null;
	JTextField barreClasse = null;
	JRadioButton choixHomme = null;
	JRadioButton choixFemme = null;
	String sexe = null;
	String menuActuel = "";
	JLabel IDEcrit = null;
	JLabel moyenneEcrit = null;


	
	Student unEtudiant;                                              // Voir documentation
	QueryStatement requeteSQL = new QueryStatement(unEtudiant,"");   // et fichier java pour plus de d'info
	ConnectionFactory connectionBDEtudiant;                                    // sur ces 3 class

	private void initialize() {
		fenetreGestionEtudiant = new JFrame();
		fenetreGestionEtudiant.setBounds(100, 100, 510, 350);
		fenetreGestionEtudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreGestionEtudiant.getContentPane().setLayout(null);
		
		JPanel menuPrincipal;
		JPanel menuListeEleve;
		JPanel menuAjouterEleve;
		JPanel menuSupprimerEleve;
		JPanel menuAltererMoyenne;
		
		JButton boutonVoir;
		JButton boutonSupprimer;
		JButton boutonAjouter;
		JButton boutonAlterer;
		JButton boutonRetour ;
		JButton boutonValider;
		ButtonGroup choixSexe;

		JTable tableauEleve;
		JScrollPane barreDeDefilement;
		
		JLabel nomEcrit;
		JLabel prenomEcrit;
		JLabel DOBEcrit;
		JLabel sexeEcrit;
		JLabel classeEcrit;
		
		Object[]  titre = {"nom","prenom","sexe","dob","classe","moyenne","ID"};
		DefaultTableModel tableau = new DefaultTableModel();
		Object[] donneeDe1Etudiant = new Object[7];

		connectionBDEtudiant = new ConnectionFactory("gestionetudiant","root","");

		tableau.setColumnIdentifiers(titre);
		
		menuPrincipal = new JPanel();
		menuPrincipal.setBounds(10, 11, 414, 289);
		fenetreGestionEtudiant.getContentPane().add(menuPrincipal);
		menuPrincipal.setLayout(null);
		
		
		menuListeEleve = new JPanel();
		menuListeEleve.setBounds(10, 11, 474, 239);
		fenetreGestionEtudiant.getContentPane().add(menuListeEleve);
		menuListeEleve.setLayout(null);
		menuListeEleve.setVisible(false);
		
		menuAjouterEleve = new JPanel();
		menuAjouterEleve.setBounds(10, 11, 414, 239);
		fenetreGestionEtudiant.getContentPane().add(menuAjouterEleve);
		menuAjouterEleve.setLayout(null);
		menuAjouterEleve.setVisible(false);
		
		menuSupprimerEleve = new JPanel();
		menuSupprimerEleve.setBounds(10, 11, 414, 239);
		menuSupprimerEleve.setLayout(null);
		fenetreGestionEtudiant.getContentPane().add(menuSupprimerEleve);
		menuSupprimerEleve.setVisible(false);
		
		menuAltererMoyenne = new JPanel();
		menuAltererMoyenne.setBounds(10, 11, 414, 239);
		menuAltererMoyenne.setLayout(null);
		fenetreGestionEtudiant.getContentPane().add(menuAltererMoyenne);
		menuSupprimerEleve.setVisible(false);
		
		boutonValider = new JButton("Valider");
		boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clique) {
				if(menuActuel=="Supprimer élève") {
					try {
						unEtudiant.ID = Integer.parseInt(barreID.getText());       // Il faut récupérer l'ID d'étudiant pour qu'on puisse le supprimer de la BD
						connectionBDEtudiant.requeteAFaire.executeUpdate(requeteSQL.getQueryStatement(unEtudiant, "supprimer"));
						JOptionPane.showMessageDialog(null, "l'élève avec le ID "+ barreID.getText() + " a été supprimé");
					}catch(SQLException erreurDeRequete) {
						JOptionPane.showMessageDialog(null, "Erreur de saisie");
					}
					catch(NumberFormatException idNonValide) {
						JOptionPane.showMessageDialog(null, "L'id n'est pas valide");
					}
				}
				if(menuActuel=="Ajouter à la liste") {
					if(choixHomme.isSelected()) {
						sexe = "Homme";
					}
					else if(choixFemme.isSelected()) {
						sexe = "Femme";
					}
					try {	
						unEtudiant = new Student(barreNom.getText(),barrePrenom.getText(),sexe,barreDOB.getText(),barreClasse.getText(),barreMoyenne.getText(),0);
						connectionBDEtudiant.requeteAFaire.executeUpdate(requeteSQL.getQueryStatement(unEtudiant, "ajouter"));
						JOptionPane.showMessageDialog(null, " Etudiant rajouté !");

					}catch(SQLException erreurDeRequete) {
						JOptionPane.showMessageDialog(null, "Erreur de saisie");
						erreurDeRequete.printStackTrace();

					}
				}
				if(menuActuel=="Altérer moyenne") {
					try {	
						unEtudiant.ID = Integer.parseInt(barreID.getText());
						unEtudiant.moyenne = barreMoyenne.getText();
						connectionBDEtudiant.requeteAFaire.executeUpdate(requeteSQL.getQueryStatement(unEtudiant, "altérer"));
						JOptionPane.showMessageDialog(null, " Moyenne Altéré !");

					}catch(SQLException erreurDeRequete) {
						JOptionPane.showMessageDialog(null, "Erreur de saisie");
						erreurDeRequete.printStackTrace();

					}
				}
			}
		});
		boutonValider.setBounds(240, 72, 146, 42);
		
		boutonRetour = new JButton("Retour");
		boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clique) {
				fenetreGestionEtudiant.setBounds(100, 100, 510, 350);
				menuPrincipal.setVisible(true);
				menuListeEleve.setVisible(false);
				menuAjouterEleve.setVisible(false);
				menuSupprimerEleve.setVisible(false);
				menuAltererMoyenne.setVisible(false);
				boutonValider.setVisible(false);
				boutonRetour.setVisible(false);

			}
		});
		boutonRetour.setBounds(118, 0, 146, 42);
		menuAjouterEleve.add(boutonRetour);
		
		boutonVoir = new JButton("Voir liste");
		boutonVoir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clique) {
				menuActuel = "Voir liste";
				menuPrincipal.setVisible(false);
				menuListeEleve.setVisible(true);
				menuListeEleve.add(boutonRetour);
				boutonRetour.setBounds(118, 0, 146, 42);
				boutonRetour.setVisible(true);
				try {
					tableau.setRowCount(0);
					connectionBDEtudiant.resultatRequete = connectionBDEtudiant.requeteAFaire.executeQuery(requeteSQL.getQueryStatement(unEtudiant,"selectionner"));
					while(connectionBDEtudiant.resultatRequete.next()) {
						unEtudiant = new Student(connectionBDEtudiant.resultatRequete.getString("nom"),connectionBDEtudiant.resultatRequete.getString("prenom"),connectionBDEtudiant.resultatRequete.getString("sexe"),connectionBDEtudiant.resultatRequete.getString("dob"),connectionBDEtudiant.resultatRequete.getString("classe"),connectionBDEtudiant.resultatRequete.getString("moyenne"),connectionBDEtudiant.resultatRequete.getInt("ID"));
					    donneeDe1Etudiant[0] =  unEtudiant.nom;
					    donneeDe1Etudiant[1] =  unEtudiant.prenom;
					    donneeDe1Etudiant[2] =  unEtudiant.sexe;
					    donneeDe1Etudiant[3] =  unEtudiant.dob;
					    donneeDe1Etudiant[4] =  unEtudiant.classe;
					    donneeDe1Etudiant[5] =  unEtudiant.moyenne;
					    donneeDe1Etudiant[6] =  unEtudiant.ID;
						tableau.addRow(donneeDe1Etudiant);
					}
				} catch (SQLException requeteNonValide) {
					requeteNonValide.printStackTrace();
				}    
			}
		});
		boutonVoir.setBounds(44, 11, 324, 58);
		menuPrincipal.add(boutonVoir);
		boutonVoir.setBounds(44, 88, 324, 58);
		
		boutonSupprimer = new JButton("Supprimer élève");
		boutonSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clique) {
				menuActuel = "Supprimer élève";
				menuPrincipal.setVisible(false);
				menuSupprimerEleve.setVisible(true);
				menuSupprimerEleve.add(IDEcrit);
				menuSupprimerEleve.add(barreID);
				menuSupprimerEleve.add(boutonValider);
				boutonValider.setVisible(true);
				boutonRetour.setVisible(true);
				menuSupprimerEleve.add(boutonRetour);
				boutonRetour.setBounds(240, 0, 146, 42);
				boutonValider.setVisible(true);
				fenetreGestionEtudiant.setBounds(100, 100, 510, 200);

			}
		});
		boutonSupprimer.setBounds(44, 158, 324, 58);
		menuPrincipal.add(boutonSupprimer);
		
		boutonAlterer = new JButton("Altérer moyenne");
		boutonAlterer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clique) {
				menuActuel = "Altérer moyenne";
				menuPrincipal.setVisible(false);
				moyenneEcrit.setBounds(50, 50,57, 14);
				barreMoyenne.setBounds(108, 50,86, 20);
				menuAltererMoyenne.add(IDEcrit);
				menuAltererMoyenne.add(barreID);
				menuAltererMoyenne.add(moyenneEcrit);
				menuAltererMoyenne.add(barreMoyenne);
				menuAltererMoyenne.setVisible(true);
				menuAltererMoyenne.add(boutonValider);
				boutonValider.setVisible(true);
				boutonRetour.setVisible(true);
				menuAltererMoyenne.add(boutonRetour);
				boutonRetour.setBounds(240, 0, 146, 42);
				boutonValider.setVisible(true);
				fenetreGestionEtudiant.setBounds(100, 100, 510, 200);

			}
		});
		boutonAlterer.setBounds(44, 230, 324, 58);
		menuPrincipal.add(boutonAlterer);
		
		boutonAjouter = new JButton("Ajouter à la liste");
		boutonAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clique) {
				menuActuel = "Ajouter à la liste";
				menuPrincipal.setVisible(false);
				menuAjouterEleve.setVisible(true);
				menuAjouterEleve.add(boutonRetour);
				menuAjouterEleve.add(boutonValider);
				menuAjouterEleve.add(moyenneEcrit);
				menuAjouterEleve.add(barreMoyenne);
				moyenneEcrit.setVisible(true);
				barreMoyenne.setVisible(true);
				boutonRetour.setBounds(240, 0, 146, 42);
				moyenneEcrit.setBounds(40, 190, 57, 14);
				barreMoyenne.setBounds(96, 187, 86, 20);
				boutonValider.setVisible(true);
				boutonRetour.setVisible(true);
				fenetreGestionEtudiant.setBounds(100, 100, 510, 280);

			}
		});
		boutonAjouter.setBounds(44, 11, 324, 66);
		menuPrincipal.add(boutonAjouter);

		tableauEleve = new JTable(tableau);
		tableauEleve.setBounds(10, 43, 474, 207);
		tableauEleve.getColumnModel().getColumn(3).setPreferredWidth(100);
		menuListeEleve.add(tableauEleve); 
		
		barreDeDefilement = new JScrollPane(tableauEleve,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		barreDeDefilement.setBounds(10, 43, 463, 196);
		menuListeEleve.add(barreDeDefilement);
		
		nomEcrit = new JLabel("Nom");
		nomEcrit.setBounds(40, 5, 46, 14);
		menuAjouterEleve.add(nomEcrit);
		
		prenomEcrit = new JLabel("Prénom");
		prenomEcrit.setBounds(40, 42, 46, 14);
		menuAjouterEleve.add(prenomEcrit);
		
		DOBEcrit = new JLabel("DOB");
		DOBEcrit.setBounds(40, 77, 46, 14);
		menuAjouterEleve.add(DOBEcrit);
		
		sexeEcrit = new JLabel("Sexe");
		sexeEcrit.setBounds(40, 113, 46, 14);
		menuAjouterEleve.add(sexeEcrit);
		
		classeEcrit = new JLabel("Classe");
		classeEcrit.setBounds(40, 150, 46, 14);
		menuAjouterEleve.add(classeEcrit);
		
		moyenneEcrit = new JLabel("Moyenne");
		moyenneEcrit.setBounds(40, 190, 57, 14);
		menuAjouterEleve.add(moyenneEcrit);
		
		barreNom = new JTextField();
		barreNom.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent appuyerTouche) {
				char caractere = appuyerTouche.getKeyChar();
				if(caractere == KeyEvent.VK_SPACE || caractere == KeyEvent.VK_MINUS || appuyerTouche.getKeyChar() =='\'' ) { 
					if(barreNom.getText().length() >= 33) {
						appuyerTouche.consume();	
					}
				}
				else if(!(Character.isAlphabetic(caractere)) || (caractere == KeyEvent.VK_BACK_SPACE) || (caractere == KeyEvent.VK_DELETE) || (barreNom.getText().length() >= 35) ){
					appuyerTouche.consume();
				}
			}
		});
		barreNom.setBounds(96, 5, 86, 20);
		menuAjouterEleve.add(barreNom);
		barreNom.setColumns(10);
		
		barrePrenom = new JTextField();
		barrePrenom.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent appuyerTouche) {
				char caractere = appuyerTouche.getKeyChar();
				if(caractere == KeyEvent.VK_SPACE || caractere == KeyEvent.VK_MINUS || appuyerTouche.getKeyChar() =='\'' ) {
					if(barrePrenom.getText().length() >= 33) {
						appuyerTouche.consume();
					}
				}
				else if(!(Character.isAlphabetic(caractere)) || (caractere == KeyEvent.VK_BACK_SPACE) || (caractere == KeyEvent.VK_DELETE) || (barrePrenom.getText().length() >= 35)  ){
					appuyerTouche.consume();
				}
			}
		});
		barrePrenom.setBounds(96, 39, 86, 20);
		menuAjouterEleve.add(barrePrenom);
		barrePrenom.setColumns(10);
		
		barreDOB = new JTextField();
		barreDOB.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent appuyerTouche) {
				char caractere = appuyerTouche.getKeyChar();
				if(barreDOB.getText().length() >= 0 && barreDOB.getText().length() < 4) {
					if(!(Character.isDigit(caractere))) {
						appuyerTouche.consume();
					}
				}
				if(barreDOB.getText().length() == 4) {
					if(!(appuyerTouche.getKeyChar() =='-')) {
						appuyerTouche.consume();
					}
				}
				if(barreDOB.getText().length() > 4 && barreDOB.getText().length() < 6 ) {
					if(!(Character.isDigit(caractere))) {
						appuyerTouche.consume();
					}
				}
				
				if(barreDOB.getText().length() == 7) {
					if(!(appuyerTouche.getKeyChar() =='-')) {
						appuyerTouche.consume();
					}
				} 
				if(barreDOB.getText().length() >= 10) {
					appuyerTouche.consume();
				} 
			}
		});
		barreDOB.setBounds(96, 74, 86, 20);
		menuAjouterEleve.add(barreDOB);
		barreDOB.setColumns(10);
		
		choixHomme = new JRadioButton("Homme");
		choixHomme.setBounds(88, 109, 70, 23);
		menuAjouterEleve.add(choixHomme);
		
		choixFemme = new JRadioButton("Femme");
		choixFemme.setBounds(166, 109, 70, 23);
		menuAjouterEleve.add(choixFemme);
		
		choixSexe = new ButtonGroup();
		choixSexe.add(choixHomme);
		choixSexe.add(choixFemme);
		
		barreClasse = new JTextField();
		barreClasse.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent appuyerTouche) {
				if(barreClasse.getText().length() >= 16) {
					appuyerTouche.consume();
				}
			}
		});
		barreClasse.setBounds(96, 147, 86, 20);
		menuAjouterEleve.add(barreClasse);
		barreClasse.setColumns(10);
		
		barreMoyenne = new JTextField();
		barreMoyenne.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent appuyerTouche) {
				char caractere = appuyerTouche.getKeyChar();
				if(!(Character.isDigit(caractere)) && !(appuyerTouche.getKeyChar() =='.')) {
					appuyerTouche.consume();
				}
				if(barreMoyenne.getText().length() >= 5) {
					appuyerTouche.consume();
				}
			}
		});
		barreMoyenne.setBounds(96, 187, 86, 20);
		menuAjouterEleve.add(barreMoyenne);
		barreMoyenne.setColumns(10);
		
		IDEcrit = new JLabel("ID");
		IDEcrit.setBounds(82, 88, 46, 20);
		menuSupprimerEleve.add(IDEcrit);
		
		barreID = new JTextField();
		barreID.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent appuyerTouche) {
				char caractere = appuyerTouche.getKeyChar();
				if(!(Character.isDigit(caractere)) || (caractere == KeyEvent.VK_BACK_SPACE) || (caractere == KeyEvent.VK_DELETE) || barreID.getText().length() >= 2 ){
					appuyerTouche.consume();
				}
			}
		});
		barreID.setBounds(108, 88, 86, 20);
		menuSupprimerEleve.add(barreID);
		barreID.setColumns(10); 			         
	} 
}
//////////////////Fin//////////////////////////////