package projet;
/**
 *
 * @author Loris BRIGNATZ
 */
public abstract class Praticien extends Personne {
	
	 int duree;
	 String specialite;
	 String userID; 
    
    public Praticien ( String userID, String nom, String prenom, String numtel ,String mail){
    	super(nom, prenom, numtel, mail);
    	this.userID=userID;
    }

	public String toString() {
		return nom + " "+ prenom;
	}
	public int getDureeDefaut() {
		// System.out.println(" duree defaut  " + Integer.toString(duree)); 
		return duree;
	}
	public String getspecialite() {
		// System.out.println(" spec " + specialite); 
		return specialite;
	}

	public String getUserID() {
		return userID;
	}
    
    
}
