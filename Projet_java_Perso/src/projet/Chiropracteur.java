package projet;
/**
 *
 * @author Loris BRIGNATZ
 */
public class Chiropracteur extends Praticien {
    
    public Chiropracteur (String userID, String nom, String prenom, String mail, String numtel){
        super (userID, nom, prenom, mail, numtel);
        duree = 45;
        specialite="chiropracteur";
        //System.out.println(" constructeur spec  " + specialite); 
        //System.out.println(" constructeur duree defaut  " + Integer.toString(duree)); 

    }
    
}
