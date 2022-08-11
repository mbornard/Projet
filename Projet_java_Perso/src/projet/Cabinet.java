package projet;

import java.io.*;
import java.util.*;

public class Cabinet {
	ArrayList<Praticien> lesPraticiens = new ArrayList<>();
	// Délimiteurs qui doivent être dans le fichier CSV
	private static final String DELIMITER = ",";
	private static final String SEPARATOR = "\n";
	private static final String NOM_FICHIER_PRATICIENS = "praticiens.csv";

	// En-tête de fichier
	private static final String HEADER_PRATICIENS = " userID, specialite, nom, prenom, numtel , mail";

	public Cabinet() {

		/*
		 * creation d'une liste de patraticiens pour tester la sauvegarde Chiropracteur
		 * drLama = new Chiropracteur("chiro", "Lama", "Chine", "04", "mi@gmail.com");
		 * lesPraticiens.add(drLama); Dentiste drHippo = new Dentiste("dentiste",
		 * "Hippo", "tame", "06", "hippo@gmail.com"); lesPraticiens.add(drHippo);
		 * Generaliste drGiraffe = new Generaliste("doc1", "Giraffe", "savane", "08",
		 * "giraffe@gmail.com"); lesPraticiens.add(drGiraffe); Generaliste drZebre = new
		 * Generaliste("doc2", "Zebre", "noir", "09", "zebre@gmail.com");
		 * lesPraticiens.add(drZebre);
		 */

	}

	public void loadPraticiens() {

		try {
			File getCSVFiles = new File(NOM_FICHIER_PRATICIENS);
			Scanner sc = new Scanner(getCSVFiles);
			sc.useDelimiter(SEPARATOR);

			String ligne = sc.nextLine();
			while (sc.hasNext()) {
				// on lit une nouvelle ligne
				ligne = sc.nextLine();
				String[] res = ligne.split(",");

				// System.out.println(res[0] + "- " + res[1] + "-" + res[2]);

				switch (res[1]) {

				case "generaliste":
					lesPraticiens.add(new Generaliste(res[0], res[2], res[3], res[4], res[5]));

					// System.out.println("generaliste créé " );

					break;

				case "dentiste":
					lesPraticiens.add(new Dentiste(res[0], res[2], res[3], res[4], res[5]));

					// System.out.println("dentiste créé " );

					break;

				case "chiropracteur":
					lesPraticiens.add(new Chiropracteur(res[0], res[2], res[3], res[4], res[5]));

					// System.out.println("chiro créé " );

					break;

				default:
					System.out.println("----------specialité inconnue");
					break;
				}

			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void savePraticiens() {

		if (lesPraticiens.size() >= 1) {

			FileWriter file = null;
			try {
				file = new FileWriter(NOM_FICHIER_PRATICIENS);
				// Ajouter l'en-tête
				file.append(HEADER_PRATICIENS);
				// Ajouter une nouvelle ligne après l'en-tête
				file.append(SEPARATOR);

				for (Praticien p : lesPraticiens) {
					// HEADER_PRATICIENS = " userID, specialite, nom, prenom, numtel , mail";
					file.append(p.getUserID());
					file.append(DELIMITER);
					file.append(p.getspecialite());
					file.append(DELIMITER);
					file.append(p.getNom());
					file.append(DELIMITER);
					file.append(p.getPrenom());
					file.append(DELIMITER);
					file.append(p.getNumtel());
					file.append(DELIMITER);
					file.append(p.getMail());
					file.append(SEPARATOR);
				}

				file.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public Praticien choisirPraticien(String userID) {
		Praticien prat = null;
		Scanner sc = new Scanner(System.in);
		if (userID.equals("accueil")) {
			// choix du praticien pour lequel on va creer, lister ...rdv
			int i = 1;
			String numAgenda;
			System.out.println(" ------------------- Liste des praticiens disponibles --------------------");

			for (Praticien p : lesPraticiens) {
				System.out.println(i + ":" + p.getNom() + " specialité " + p.getspecialite());
				i++;
			}

			int choix = Integer.parseInt(sc.nextLine());
			int choixf = choix - 1;
			prat = lesPraticiens.get(choixf);

		} else {

			for (Praticien p : lesPraticiens) {
				if (userID.equals(p.getUserID())) {
					prat = p;
					break;
				}
			}
		}
		return prat;
	}

}
