package projet;
import java.util.*;
import java.util.Scanner;
public class LoginPage {	
	HashMap<String, String> login = new HashMap<String, String> ();
	LoginPage(HashMap<String, String> logOriginal, String userID, String password){
		login = logOriginal;

		if(login.containsKey(userID)) {
			if(login.get(userID).equals(password)) {
				System.out.println("Login successful");
			}
			else {

				System.out.println("Wrong password");
			}

		}
		else {
			System.out.println("username not found");
		}
	}

	}	
