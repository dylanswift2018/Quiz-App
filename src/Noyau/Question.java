package Noyau;
import java.io.*;

public  abstract class Question implements Serializable {
	// liste d'attributs 
	private String question; //question d�finit par le formateur l'�nonc� de la question
	//setters and getters 
	//getters
	
	public String getQuestion()
	{
		return question;
	}
	//setters 
	public void setQuetsion(String q)
	{
		this.question=q; 
	}
	//remplir les classes des questions par les m�thodes de r�ponses /r�cup�ration/evaluation des questions qui sera invoqu� plus tard par la m�thode autoEvaluation du quiz 
    
    
}
