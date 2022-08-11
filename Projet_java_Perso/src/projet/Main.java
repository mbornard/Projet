package projet;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean goodLoginPassword = false;
		IDandPasswords idPass = new IDandPasswords();
		Scanner sc = new Scanner(System.in);
		String userID = null;
		String password = null;
		do {
			System.out.println("Username : ");
			userID = sc.nextLine();

			System.out.println("Password : ");
			password = sc.nextLine();

			goodLoginPassword = idPass.checkPassword(userID, password);
			if (goodLoginPassword) {
				System.out.println("Login " + userID + " ok");
				// idPass.changePassword(userID);

				if (userID.equals("admin")) {
					System.out.println("initialisation mot de passe ? (y/n)");
					String rep = sc.nextLine();
					if (rep.toUpperCase().equals("Y")) {
						idPass.init();
					}
					goodLoginPassword = true;
					break;

				}

			} else {
				System.out.println("Login " + userID + " not ok");
			}
		} while (!goodLoginPassword);

		// Test creation d'un patient, d'un medecin et d'un rdv
		// loader la liste des praticiens depuis le fichier
		/*
		Patient Michel = new Patient("JD", "Michel", "03", "m@gmail.com", "001", "22/01/1998");
		Patient jean = new Patient("JD", "jean", "03", "m@gmail.com", "001", "22/01/2002");
		Patient tibo = new Patient("tibo", "pabo", "03", "t@gmail.com", "009", "25/01/1999");

		Chiropracteur drLama = new Chiropracteur("chiro", "Lama", "Chine", "04", "mi@gmail.com");
		Dentiste drHippo = new Dentiste("dentiste", "Hippo", "tame", "06", "hippo@gmail.com");
		Generaliste drGiraffe = new Generaliste("doc1", "Giraffe", "savane", "08", "giraffe@gmail.com");
		Generaliste drZebre = new Generaliste("doc2", "Zebre", "noir", "09", "zebre@gmail.com");

		// RDV michelinelama = new RDV(Michel,drLama, "22/05/2022 11:31",
		// "vomisssement");
		// System.out.println(michelinelama);
		ArrayList<Agenda> lesAgendas = new ArrayList<>();

		Agenda chiro = new Agenda(drLama);
		lesAgendas.add(chiro);
		Agenda dentiste = new Agenda(drHippo);
		lesAgendas.add(dentiste);
		Agenda generaliste1 = new Agenda(drGiraffe);
		lesAgendas.add(generaliste1);
		Agenda generaliste2 = new Agenda(drZebre);
		lesAgendas.add(generaliste2);

		// loader la liste des patients du chiro depuis fichier chiroPatient.csv
		chiro.ajouterPatient(Michel);
		chiro.ajouterPatient(jean);
		chiro.ajouterPatient(tibo);

		// loader la liste des rdv du chiro depuis fichier chiroRDV.csv
		RDV rdv1 = new RDV(tibo, drLama, "23/05/2022 15:00", "covid");
		RDV rdv2 = new RDV(jean, drLama, "23/05/2022 14:00", "covid");
		RDV rdv3 = new RDV(Michel, drLama, "23/05/2022 09:00", "covid");

		chiro.ajouterRDV(rdv1);
		chiro.ajouterRDV(rdv2);
		chiro.ajouterRDV(rdv3);
		*/
		if (userID.equals("admin")) {
			System.out.println("admin session deconnectée");

		} else {
		
		Cabinet cabinet = new Cabinet();

		//chargement du fichier praticiens
		cabinet.loadPraticiens();

		// choisir patricien 
		
		
		Praticien praticienCourant = cabinet.choisirPraticien(userID);
		if (praticienCourant==null) {
			System.out.println(" praticien non trouvé");
		}
			
		
		
		
		Agenda agendaCourant = new Agenda (praticienCourant);
		String nomFichierAgenda = praticienCourant.getUserID()+".csv";
		agendaCourant.loadAgendas(nomFichierAgenda);
		
		
		
		
		
		
		/*
		if (userID.equals("accueil")||userID.equals("admin")) {
			// choix du praticien pour lequel on va creer, lister ...rdv
			int i = 1;
			String numAgenda;
			System.out.println(" ------------------- Liste des pratciens disponibles --------------------");

			for (Agenda a : lesAgendas) {
				System.out.println(i + ":" + a.getPraticien());
				i++;
			}

			int choix = Integer.parseInt(sc.nextLine());
			int choixf = choix - 1;
			agendaCourant = lesAgendas.get(choixf);
			//System.out.println(" choixf " + choixf); 

		}
		if (userID.equals("dentiste")) {
			// on postionne arbitrairement l'agenda du dentise
			agendaCourant = dentiste;	

		}
		if (userID.equals("chiro")) {
			// on postionne arbitrairement l'agenda du chiro
			agendaCourant = chiro;	
		}
		if (userID.equals("doc1")) {
			// on postionne arbitrairement l'agenda du doc1
			agendaCourant = generaliste1;	
		}
		if (userID.equals("doc2")) {
			// on postionne arbitrairement l'agenda du doc2
			agendaCourant = generaliste2;	
		}
		*/

		System.out.println("agenda selectionné " + agendaCourant.getPraticien());
		// loader l'agenda 
		// agendaCourant.load();
		
		
		String choix;
		do {
			System.out.println(" ------------------- Choix --------------------");
			System.out.println("1 : Créer rdv");
			System.out.println("2 : Afficher la liste des rdv");
			System.out.println("3 : Modifier rdv");
			System.out.println("4 : Supprimer rdv");
			System.out.println("5 : Modifier mot de passe");
			System.out.println("6 : Quitter");

			choix = sc.nextLine();
			switch (choix) {

			case "1":
				agendaCourant.creationRDV();
				agendaCourant.saveAgendas(nomFichierAgenda);

				break;

			case "2":
				System.out.println(agendaCourant);
				break;
			case "3":
				agendaCourant.modifierRDV(agendaCourant.choisirRDV());
				agendaCourant.saveAgendas(nomFichierAgenda);

				break;
			case "4":
				agendaCourant.supprimerRDV(agendaCourant.choisirRDV());
				agendaCourant.saveAgendas(nomFichierAgenda);

				break;

			case "5":
				idPass.changePassword(userID);
				break;
			case "6":
				System.out.println("Déconnexion en cours...");
				cabinet.savePraticiens();
				agendaCourant.saveAgendas(nomFichierAgenda);
				System.out.println("Déconnecté");
				break;

				
			default:
				System.out.println("Choix incorrect");
				break;
			}

		} while (!choix.equals("6"));
		}
	}
}
