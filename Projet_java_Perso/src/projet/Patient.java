package projet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author nanie
 */
class Patient extends Personne{
    private String NumSecu;
    private String DateNaissance;

    public Patient(String nom, String prenom, String numtel, String mail, String NumSecu, String DateNaissance) {
    	super(nom, prenom,numtel,mail);
    	this.NumSecu = NumSecu;
        this.DateNaissance = DateNaissance;
    }
    
    



    
    
    public static Patient createPatient() {// méthode pour créer un patient
        Scanner sc = new Scanner(System.in);

        System.out.println("Nom du patient");
        String nomP = sc.nextLine();
        System.out.println("Prénom du patient");
        String prenomP = sc.nextLine();
        System.out.println("numéro de téléphone du patient");
        String numtelP = sc.nextLine();
        System.out.println("mail du patient");
        String mailP = sc.nextLine();
        String numsecu;
        do {
        System.out.println("numéro de sécurité sociale du patient (15 Chiffres)");
        numsecu = sc.nextLine();
        }while (numsecu.length() != 15);
        System.out.println("date de naissance");
        String dateNaissanceP = sc.nextLine();
        Patient patient = new Patient(nomP, prenomP, numtelP, mailP, numsecu, dateNaissanceP );
        return patient;
    }
    public String getDateNaissance() {
    	return DateNaissance;
    }
    public String getNumSecu() {
    	return NumSecu;
    }

}
