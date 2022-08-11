package projet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nanie
 */
public class Agenda {
	Scanner sc = new Scanner(System.in);

	private TreeSet<RDV> lesRDV;
	public static HashSet<Patient> lesPatients = new HashSet<>();
	private Praticien m;

	// Délimiteurs qui doivent être dans le fichier CSV
	private static final String DELIMITER = ",";
	private static final String SEPARATOR = "\n";

	// En-tête de fichier
	private static final String HEADER_AGENDAS = "  nom,  prenom,  numtel,  mail,  NumSecu,  DateNaissance, DateRDV, duree, motif";

	public Agenda(Praticien m) {
		this.m = m;
		lesRDV = new TreeSet<>();
	}

	public void ajouterPatient(Patient p) {
		lesPatients.add(p);
	}

	public Praticien getPraticien() {
		return m;
	}

	public void ajouterRDV(RDV rdv) {
		rdv.creationRDV();
		lesRDV.add(rdv);
	}

	public void supprimerRDV(RDV rdv) {
		lesRDV.remove(rdv);
	}
	// getrdv

	public void modifierRDV(RDV rdv) {

		boolean bilan = false;

		while (bilan == false) {
			System.out.println("Que voulez-vous modifier ? (patient, praticien, date, duree, motif)");
			String reponse = sc.next();
			if (reponse.equals("patient")) {
				System.out.println("Merci de vouloir ressaisir un RDV");
				rdv.modifPatient();
			}
			if (reponse.equals("praticien")) {
				System.out.println("Merci de vouloir ressaisir un RDV");
				rdv.modifPraticien();
			}
			if (reponse.equals("date")) {
				rdv.modifDate();
			}
			if (reponse.equals("duree")) {
				rdv.modifDuree(rdv);
			}
			if (reponse.equals("motif")) {
				rdv.modifMotif(rdv);
			} else {
				System.out.println("Veuillez renseigner un des champs, comme indiqué dans la question.");
			}
			System.out.println(rdv);
			System.out.println("Avez-vous d'autres modifications à effectuer ? (o/n)");
			String rep = sc.next();

			if (rep.equals("n")) {
				bilan = true;
			} else {
				bilan = false;
			}

		}
	}

	public Patient recherchePatient() { // il faudra penser a gérer les exception si la personne ne rentre pas un entier
										// ou un entier trop petit ...

		ArrayList<Patient> list = new ArrayList<Patient>();

		System.out.println("Quel est le nom du patient");
		String rep1 = sc.nextLine();
		for (Patient pa : lesPatients) {
			if (rep1.toUpperCase().equals(pa.getNom().toUpperCase())
					| rep1.toUpperCase().equals(pa.getPrenom().toUpperCase())) {
				list.add(pa);
			}
		}
		if (list.size() == 0) {
			System.out.println("Il n'y a pas de patient de ce nom. \n Création d'un patient");
			return Patient.createPatient();
		}
		if (list.size() == 1) {
			return list.get(0);
		} else {
			System.out.println("Il y a plusieurs patients");
			int i = 0;
			for (Patient p : list) {
				i++;
				System.out
						.println(i + " =  " + p.getNom() + "  " + p.getPrenom() + "  né.e le " + p.getDateNaissance());
			}
			int choix = Integer.parseInt(sc.nextLine());
			int choixf = choix - 1;
			return list.get(choixf);
		}
	}

	public void creationRDV() {
		Patient p;
		System.out.println("Est ce que le patient est client ? (y/n)");
		String rep = sc.nextLine();
		if (rep.toUpperCase().equals("N")) {
			p = Patient.createPatient();
			lesPatients.add(p);

		} else {
			p = recherchePatient();
		}
		boolean flagRDVok = false;
		RDV rdv = null;
		do {
			System.out.println("A quelle date et quelle heure (dd/MM/yyyy hh:mm)");
			String date = sc.nextLine();
			System.out.println("Motif  : ");
			String motif = sc.nextLine();
			rdv = new RDV(p, m, date, motif);
			System.out.println("Voulez vous mofier la durée du rdv (y/n");
			String rep1 = sc.nextLine();
			if (rep1.toUpperCase().equals("Y")) {
				System.out.println("Quelle durée en minutes ?");
				int dureeRdv = sc.nextInt();
				rdv.setDuree(dureeRdv);
			}

			// verif format date
			LocalDateTime dateDebut = rdv.formatterDate(date);
			if (dateDebut != null) {

				if (rdv.estDansTrancheHoraire() == false) {
					flagRDVok = false;
				} else {

					LocalDateTime maintenant = LocalDateTime.now();

					if (dateDebut.isBefore(maintenant)) {
						flagRDVok = false;
						System.out.println("ce RDV   " + rdv + " est dans le passé");

					} else {

						if (lesRDV.size() == 0) {
							flagRDVok = true;
						} else {
							for (RDV rdv2 : lesRDV) {
								if (rdv.compatibleRDV(rdv2) == true) {
									flagRDVok = true;
									// System.out.println("ce RDV " + rdv + " est ok");

								} else {
									flagRDVok = false;
									System.out.println("ce RDV   " + rdv + " se chevauche avec   " + rdv2);
									break;

								}

							}
						}
					}
				}
			}
		} while (flagRDVok == false);
		if (rdv != null) {
			lesRDV.add(rdv);
		}
		System.out.println(rdv);

	}

	public RDV choisirRDV() {
		RDV rdvRetour = null;
		Scanner sc1 = new Scanner(System.in);
		int i = 1;
		System.out.println(" ------------------- Liste des RDV --------------------");

		for (RDV rdv : lesRDV) {
			System.out.println(i + ":" + rdv.toString());
			i++;
		}
		System.out.println("Numéro du RDV ?");

		int choix = Integer.parseInt(sc1.nextLine());
		i = 1;
		for (RDV rdv : lesRDV) {
			if (i == choix) {
				rdvRetour = rdv;
			}
			i++;
		}
		System.out.println("rdv selectionné " + rdvRetour.toString());
		return rdvRetour;
	}

	public void saveAgendas(String nomFichier) {

		if (lesRDV.size() >= 1) {

			FileWriter file = null;
			try {
				file = new FileWriter(nomFichier);
				// Ajouter l'en-tête
				file.append(HEADER_AGENDAS);
				// Ajouter une nouvelle ligne après l'en-tête
				file.append(SEPARATOR);

				for (RDV p : lesRDV) {

					file.append(p.getPatient().getNom());
					file.append(DELIMITER);
					file.append(p.getPatient().getPrenom());
					file.append(DELIMITER);
					file.append(p.getPatient().getNumtel());
					file.append(DELIMITER);
					file.append(p.getPatient().getMail());
					file.append(DELIMITER);
					file.append(p.getPatient().getNumSecu());
					file.append(DELIMITER);
					file.append(p.getPatient().getDateNaissance());
					file.append(DELIMITER);
					file.append(p.getDate());
					file.append(DELIMITER);
					file.append(Integer.toString(p.getDuree()));
					file.append(DELIMITER);
					file.append(p.getMotifRDV());
					file.append(SEPARATOR);
				}

				file.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void loadAgendas(String nomFichier) {

		try {
			File getCSVFiles = new File(nomFichier);
			Scanner sc = new Scanner(getCSVFiles);
			sc.useDelimiter(SEPARATOR);

			Patient p;

			String ligne = sc.nextLine();
			while (sc.hasNext()) {
				ArrayList<Patient> list = new ArrayList<Patient>();
				// on lit une nouvelle ligne
				ligne = sc.nextLine();
				String[] res = ligne.split(DELIMITER);

				// check existence patient avec numSecu
				for (Patient pa : lesPatients) {
					if (res[4].equals(pa.getNumSecu())) {
						list.add(pa);
						// System.out.println(" ------- secu existant " + res[4]);
					}
				}
				if (list.size() == 1) {
					p = list.get(0);
					// System.out.println(" ------- recup patient " + res[0]);
				} else {
					// pas trouvé : on cree et ajoute a la liste des patients
					p = new Patient(res[0], res[1], res[2], res[3], res[4], res[5]);
					// System.out.println(" ------- creation patient " + res[0]);
					lesPatients.add(p);
				}

				RDV rdv = new RDV(p, m, res[6], res[8]);
				rdv.setDuree(Integer.parseInt(res[7]));

				lesRDV.add(rdv);

			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * for (Patient pa : lesPatients) { System.out.println(pa.toString()); }
		 */
	}

	@Override
	public String toString() {
		return "Agenda  de " + m + "\n" + lesRDV.toString();
	}

}
