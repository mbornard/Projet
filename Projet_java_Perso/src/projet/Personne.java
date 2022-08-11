package projet;
/**
 *
 * @author Loris BRIGNATZ
 */
public abstract class Personne {
    
    protected String nom;
    protected String prenom;
    protected String numtel;
    protected String mail;
    protected String user;
    protected String password;
    

public Personne(String nom, String prenom, String numtel, String mail){
    this.nom = nom;
    this.prenom = prenom;
    this.numtel = numtel;
    this.mail = mail;
    
}

    public String getNom() {
        return nom;
    }
    

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Personnes{" + "nom=" + nom + ", prenom=" + prenom + ", numtel=" + numtel + ", mail=" + mail + ", user=" + user + ", password=" + password + '}';
    }
    @Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj) return true;
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass())) return false;
		Personne other = (Personne)obj;
		if (this.nom != other.nom) return false;
		if (this.prenom != other.prenom) return false;
		if (this.numtel.equals(other.numtel)) return false;
		if (this.mail.equals(other.mail)) return false;
		return true;
	}

}
