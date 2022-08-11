package projet;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class IDandPasswords {
	Properties p = new Properties();
	PasswordAuthentication pa = new PasswordAuthentication();
	private static final String NOM_FICHIER_MDP = "mdp.txt";


	IDandPasswords() {

		// loginInfo.put("admin", "root");
		// loginInfo.put("acceuil", "toto");
		// loginInfo.put("Doc1", "toto");
		// loginInfo.put("Doc2", "toto");
		// loginInfo.put("chiro", "toto");
		// loginInfo.put("dentiste", "toto");
		try (FileInputStream fis = new FileInputStream(NOM_FICHIER_MDP)) {
			p.load(fis); // Cahrger les paires clé/valeur

			System.out.println(p); // Afficher le dictionnaire
		} catch (FileNotFoundException ex) {
			Logger.getLogger(IDandPasswords.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(IDandPasswords.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public boolean checkPassword(String userID, String passwordToCheck) {
		boolean retour = false;
		boolean isOk = false;
		if (p.containsKey(userID)) {
			String hashedPw = p.get(userID).toString();

			// Comparaison des mots de passe
	        isOk = pa.authenticate(passwordToCheck.toCharArray(), hashedPw);
			if (isOk) {
				System.out.println("Login successful");
				retour = true;
			} else {

				System.out.println("Wrong password");
			}

		} else {
			System.out.println("username not found");
		}
		return retour;
	}

	public boolean changePassword(String userID) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Nouveau mot de passe : ");
		String password = sc.nextLine();
		boolean retour = false;

		if (p.containsKey(userID)) {
			String mdphash = pa.hash(password.toCharArray());
			p.replace(userID, p.get(userID), mdphash);
			retour = true;

			// try avec ressource pointant sur un fichier en écriture out.prop
			try (FileOutputStream fos = new FileOutputStream(NOM_FICHIER_MDP)) {
				// Ajouter une paire clé/valeur à l'objet Properties
				// p.setProperty("myKey", "my value");
				// Enregistrer un nouveau fichier avec tout le contenu de p
				p.store(fos, "Modification du mot de passe de " + userID + "  " + LocalDateTime.now());
			} catch (FileNotFoundException ex) {
				Logger.getLogger(IDandPasswords.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(IDandPasswords.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		System.out.println("Change successfull");

		return retour;
	}

	public boolean changePassword(String userID, String mdpHash) {

		boolean retour = false;

		if (p.containsKey(userID)) {
			p.replace(userID, p.get(userID), mdpHash);
			retour = true;

			// try avec ressource pointant sur un fichier en écriture out.prop
			try (FileOutputStream fos = new FileOutputStream(NOM_FICHIER_MDP)) {
				// Ajouter une paire clé/valeur à l'objet Properties
				// p.setProperty("myKey", "my value");
				// Enregistrer un nouveau fichier avec tout le contenu de p
				p.store(fos, "Modification du mot de passe de " + userID + "  " + LocalDateTime.now());
			} catch (FileNotFoundException ex) {
				Logger.getLogger(IDandPasswords.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(IDandPasswords.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		System.out.println("Change successfull");

		return retour;
	}

	public void init() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Username : ");
		String user = sc.nextLine();
		System.out.println("password : ");
		String mdp = sc.nextLine();
		String mdphash = pa.hash(mdp.toCharArray());
		//System.out.println(mdphash); // vérif
		if (changePassword(user, mdphash)) {
			System.out.println(user + " password init à " + mdphash);
		}

	}
}
