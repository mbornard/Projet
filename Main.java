package projet;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean goodLoginPassword = false;
		IDandPasswords idPass = new IDandPasswords(
				"/Users/matissebornard/eclipse-workspace/Projet_java_Perso/src/projet/Properties.csv");
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
				//idPass.changePassword(userID);

				if(userID.equals("admin")){
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

	}
}
