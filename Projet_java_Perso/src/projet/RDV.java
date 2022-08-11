package projet;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import java.text.Format;  
import java.util.Date;    
import java.time.LocalDate;  

import java.time.Period;  

//import sun.util.calendar.LocalGregorianCalendar.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nanie
 */
public class RDV implements Comparable<RDV>{

    private Patient patient;
    private Praticien medecin;
    private String date;
    private int duree;
    /* en minute */
    private String motifRDV;
    
    private static final int  HEURE_DEBUT = 8; // premier rdv min

    private static final int  HEURE_FIN = 20; // fin max du dernier RDV 



    public RDV(Patient p, Praticien m, String j, String motif) {
        patient = p;
        medecin = m;
        date = j;

        motifRDV = motif;
        duree = m.getDureeDefaut();
    }
    
    public LocalDateTime formatterDate(String date) {
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm");
        
        LocalDateTime localTimeObj2 = null;
        
        // localTimeObj2 = LocalDateTime.parse(date, formatter3);
        
        
        try { 
            
        	localTimeObj2 = LocalDateTime.parse(date, formatter3);
        } catch (DateTimeParseException e) {
        	System.out.println("Mauvais format de date dd/MM/yyyy hh:mm ");        
        	}
 
        
        return(localTimeObj2);
    }
    
    
    
    
    
    public LocalDateTime dateFinRDV(){
        LocalDateTime dateDebut = formatterDate(date);
        LocalDateTime dateFinale = dateDebut.plusMinutes(duree-1);// on enlève une minute à la durée du rdv pour autoriser les rdv qui se suivent
        return(dateFinale);
    }
    
    public LocalTime getHeureDebutRDV(){
        LocalDateTime dateDebut = formatterDate(date);
        return dateDebut.toLocalTime();
    }
    
    public LocalTime getHeureFinRDV(){
        LocalDateTime dateFinale = dateFinRDV();
        return dateFinale.toLocalTime();
    }
    
    public DayOfWeek getjourRDVweek(){
        LocalDateTime dateDebut = formatterDate(date);
        return dateDebut.getDayOfWeek();
    }
    
    public int getjourRDVmonth(){
        LocalDateTime dateDebut = formatterDate(date);
        return dateDebut.getDayOfMonth();
    }
    
     public Month getMoisRDVlettre(){
        LocalDateTime dateDebut = formatterDate(date);
        return dateDebut.getMonth();
    }
     
    public int getMoisRDVint(){
        LocalDateTime dateDebut = formatterDate(date);
        return dateDebut.getMonthValue();
    }
    
     public int getAnneeRDV(){
        LocalDateTime dateDebut = formatterDate(date);
        return dateDebut.getYear();
    }
     
     public boolean compatibleRDV(RDV rdv2){
    	 boolean retour=true;
         if (this.getAnneeRDV() == rdv2.getAnneeRDV() && this.getMoisRDVint()==rdv2.getMoisRDVint() && this.getjourRDVmonth()==rdv2.getjourRDVmonth()){
            LocalTime HeureDebut = this.getHeureDebutRDV();
            LocalTime HeureFin = this.getHeureFinRDV();
            LocalTime HeureDebut1 = rdv2.getHeureDebutRDV();
            LocalTime HeureFin1 = rdv2.getHeureFinRDV();      
                
            
	if (HeureDebut.isAfter(HeureFin1)==true || HeureFin.isBefore(HeureDebut1)==true ){
    	// if (HeureDebut.isAfter(HeureFin1)==true && HeureFin.isBefore(HeureDebut1)==true && HeureDebut.isAfter(HeureDebutMin)==true && HeureFin.isBefore(HeureFinMax)==true){
                retour=true;
            }else{
                retour=false;
            }
         }
         
        return retour;
     }
     // estDansTrancheHoraire si heure de debut aprè 8h et si data de fin est avant 20h et si le RDV n'est pas un dimanche
     public boolean estDansTrancheHoraire(){
    	 	boolean retour=true;
            LocalTime HeureDebut = this.getHeureDebutRDV();
            LocalTime HeureFin = this.getHeureFinRDV();
            
            LocalTime HeureDebutMin = HeureDebut.withHour(HEURE_DEBUT);

            LocalTime HeureFinMax = HeureFin.withHour(HEURE_FIN);
            
            //System.out.println("date debut min " + HeureDebutMin);
            
            // System.out.println("date fin max rdv " + HeureFinMax);

        	if (HeureFin.isBefore(HeureFinMax)==false){
        		
        		System.out.println(HeureFin + " trop tard , heure de fin au plus tard =  "+HEURE_FIN + "h");
        		retour= false;
            }
        	
        	
        	//if (HeureDebut.isAfter(HeureDebutMin)==false){		
        	if (HeureDebutMin.isAfter(HeureDebut)==true){	
     
        		System.out.println(HeureDebut + " trop tot, heure de debut au plus tôt =  "+HeureDebutMin);
        		retour= false;
            }
        	
        	DayOfWeek dateRDV = getjourRDVweek();
        	//System.out.println(" " + dateRDV);

        	
        	if (dateRDV.getValue()==7){
        		System.out.println(" pas de RDV le dimanche");
        		retour= false;
        	}
        	
        	return retour;
     }
     

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
   

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Praticien getMedecin() {
        return medecin;
    }

    public void setMedecin(Praticien medecin) {
        this.medecin = medecin;
    }

    public String getDate() {
        return date;
    }

    //public void setDate(String date) {
      //  this.date = date;
    //}

    public String getMotifRDV() {
        return motifRDV;
    }

    public void setMotifRDV(String motifRDV) {
        this.motifRDV = motifRDV;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("'le' EEEE dd MMMM YYYY 'à' kk:mm");
        LocalDateTime a = formatterDate(date);
        String dateRDV = a.format(formatter1);
        return patient.getNom()+" "+patient.getPrenom()+"  "+  dateRDV +", pendant "+  duree + " min :"+"  "+motifRDV+"\n";
    }

    public void modifPatient() {
        
    }

    void modifPraticien() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void modifDate() {
       
    }
    
    
    
    public void modifDuree(RDV rdv) {
        System.out.println("Veuillez renseigner la nouvelle durée en minutes :");
            Scanner sc = new Scanner(System.in);
            int newDuree = sc.nextInt();
            rdv.setDuree(newDuree);
    }
    
    public void modifMotif(RDV rdv) {
        System.out.println("Veuillez renseigner le nouveau motif :");
            Scanner sc = new Scanner(System.in);
            String newMotif = sc.next();
            rdv.setMotifRDV(newMotif);
    }

    public void creationRDV() {
    	
            }

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj) return true;
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass())) return false;
		RDV other = (RDV)obj;
		if (this.duree != other.duree) return false;
		if (this.date != other.date) return false;
		if (this.medecin.equals(other.medecin)) return false;
		if (this.patient.equals(other.patient)) return false;
		return true;
	}

	@Override
	public int compareTo(RDV o) {
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm");
		LocalDateTime current = LocalDateTime.parse(this.date, formatter3);
		LocalDateTime other = LocalDateTime.parse(o.date, formatter3);
		if (current.isBefore(other)) return -1;
		else if (current.isAfter(other)) return 1;
		else return 0;
	}
    

}
    
   
    


