package Noyau;
import java.util.*;
public class Reponse {
	//cette classes repr�sente les reponses soumises de l'apprenant pour une question de la quiz 
	
	//liste d'attributs 
	//associe les questions avec les r�ponses
	  private String repQO; //la reponse soumise par l'apprenant si la question est un QO 
	  private String reponseApp; //reponse soumise pour la question QCU
	  private ArrayList<String> repApprenant=new ArrayList<String>(); //liste des r�ponses de QCM

	// setter and getter 
	//getter
	  public String getRepQO()
	   {
		   return repQO;
	   }
	  
	  public String getRepQCU()
	    {
	    	return reponseApp;
	    }
	  
	  public ArrayList<String> getRepQCM()
		{
			return repApprenant;
		}
	//setter
	  public void setRepQO(String rep)
	   {
		   this.repQO=rep;
	   }
	  
	  public void setRepQCU(String rep)
	    {
	    	this.reponseApp=rep;
	    }
	  
	  public void setRepQCM(ArrayList<String> tab)
		{
			this.repApprenant=tab;
		}
	//remplir ce qu'il faut pour finalis� les reponses 
	
			
}
