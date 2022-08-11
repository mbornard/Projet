package projet;
/**
 *
 * @author Loris BRIGNATZ
 */
public class Dentiste extends Praticien{
    
    public Dentiste (String userID, String nom, String prenom, String mail, String numtel){
        super(userID, nom, prenom, mail, numtel);
        duree = 30;
        specialite="dentiste";

    }
}
