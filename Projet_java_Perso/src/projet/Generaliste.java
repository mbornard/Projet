package projet;
/**
 *
 * @author Loris BRIGNATZ
 */
public class Generaliste extends Praticien{
    
    public Generaliste (String userID, String nom, String prenom, String mail, String numtel){
        super (userID, nom, prenom, mail, numtel);
        duree = 20;
        specialite="generaliste";
    }
    
}
