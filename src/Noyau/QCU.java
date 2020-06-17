package Noyau;
import java.util.*;
import java.io.*;

public class QCU extends Question implements Serializable{
	private ArrayList<String> repFaux=new ArrayList<String>(); //liste des reponses fausses
	private String repCorrecte; //la r�ponse correcte 
    private ArrayList<String> repMix=new ArrayList<String>(); //proposisitons 
    private String reponseApp; //reponse soumise pour la question QCU
    //redifinition de methode d'evaluation avec reponses soumises dans parametres et meme chose pour qco et qcm 
    private boolean evalue; //indicateur de validit� de la r�ponse soumise par l'apprnenat et pour l'utiliser dans la comptage apr�s 
    
    //constructeur de QCU 
    public QCU(String question,String Correcte)
    {
    	super.setQuetsion(question);//appel du constructeur
    	this.repCorrecte=Correcte; //Correcte �tant la r�ponse correcte du QCO 
    	remplirPropoFaux(); //remplissage des propositions fausses
    	mixReponses(); //mix r�ponses 
    	
    }
    
    
    //Setters and getters 
    //getters 
    public boolean getEvalue()
    {
    	return evalue;
    }
    
    public String getRepCorrecte()
    {
    	return repCorrecte;
    }
    public ArrayList<String>  getRepFaux()
    {
    	return repFaux;
    }
    
    
    public ArrayList<String> getRepMix()
    {
    	return repMix;
    }
    
    //setters
    public void setEvalue(boolean e)
    {
    	this.evalue=e;
    }
    public void setRepFaux(ArrayList<String> rep)
    {
    	this.repFaux=rep;
    }
    
    public void setRepCorrecte(String rep)
    {
    	this.repCorrecte=rep;
    }
    //m�thode pour m�longer les reponses fausses avec la r�ponse correcte
    public void mixReponses()
    {
    	repMix.addAll(repFaux);
    	repMix.add(repCorrecte);
    	Collections.shuffle(repMix); //shuffle au hasard du repMix 
    }
   //methode pour remplir les propositions fausses 
  	public void remplirPropoFaux()
  	{
  		System.out.println("Vous allez saisir les propositions fausses du QCU ");
  		Scanner sc=new Scanner(System.in); 
  		System.out.println("Saisir le nombre de propositions fausses ");
  		int faux=sc.nextInt();
  		for(int i=0;i<faux;i++)
  		{
  			System.out.println("Saisir votre Proposition ici :");
  			String input=sc.next();
  			repFaux.add(input); //ajout de l'input dans la list des propositions 
  		}
  		
  	}
  	//methode de repondre au QCU 
  	public void repondreQCU()
  	{
  		System.out.println("les propositions : "+super.getQuestion());
  		for(int i=0;i<repMix.size();i++)
  		{
  			System.out.println(repMix.get(i));
  		}
  		System.out.println("saisir le numero de la bonne reponse ");
  		int input;
  		Scanner numero=new Scanner(System.in);
  		input=numero.nextInt();
  		reponseApp=repMix.get(input);
  	}
  	
  //m�thode d'�valuation de la question QCU 
  	public void evaluerQCU()
  	{
  		if(repCorrecte.equalsIgnoreCase(reponseApp))
  		{
  			evalue=true; // si la r�ponse est vraie 
  		}
  
  	}
    
    
}
