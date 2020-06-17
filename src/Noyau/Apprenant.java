package Noyau;
import java.util.*;
public class Apprenant extends Utilisateur {
	// liste d'attributs 
	// l'apprenant aussi va h�rit� le compte de l'utilisateur 
	private String nom;
	private String prenom;
	private String datebirth; //la date de naissance sera de la forme 'jj/mm/aaaa'
	private String adresse;
	private ArrayList<Quiz> quizaccompli=new ArrayList<Quiz>();    //listes des quiz accompli par l'apprenant ou bien entamm� 
	private ArrayList<Quiz> quiznonaccompli=new ArrayList<Quiz>();
	private double process; //indique le pourcentage de r�ussite 
	private Compte compte; //compte d'apprenant pour l'acces
	private Formation formation; //la formation dont l'apprenant apparetient toute modification concernant la formation est effectu�e par le formateur 
	
	//constructeur de l'apprenant 
	public Apprenant(String nom,String prenom,String datebirth,String adresse)
	{
		this.nom=nom;
		this.prenom=prenom;
		this.datebirth=datebirth;
		this.adresse=adresse;
	}
	//Setters and getters pour les modification
	//getters
	public Compte getCompte()
	{
		return compte;
	}
	public double getProcess()
	{
		return process;
	}
	public ArrayList<Quiz> getQuizNonAccompli()
	{
		return quiznonaccompli;
	}
	public ArrayList<Quiz> getQuizAccompli()
	{
		return quizaccompli;
	}
	public String getNom ()
	{
		return nom;
	}
	
	public String getPrenom ()
	{
		return prenom;
	}
	
	public String getDatebirth()
	{
		return datebirth;
	}
	
	public String getAdresse()
	{
		return adresse;
	}
	public Formation getFormation()
	{
		return formation;
	}
	//setters
	public void setQuizAccompli(ArrayList<Quiz> q)
	{
		this.quizaccompli=q;
	}
	
	public void setQuizNonAccompli(ArrayList<Quiz> q)
	{
		this.quiznonaccompli=q;
	}
	
    public void setFormation(Formation form)
    {
    	this.formation=form;
    }
	public void setProcess(double pro)
	{
		this.process=pro;
	}
	public void setNom(String nom)
	{
		this.nom=nom;
	}
	
	public void setPrenom(String pre)
	{
		this.prenom=pre;
	}
	
	public void setAdresse(String adr)
	{
		this.adresse=adr;
	}
	
	public void setDatebirth(String date)
	{
		this.datebirth=date;
	}
	
	public void setCompte(Compte compte)
	{
		this.compte=compte;
	}
	//liste des methodes 
	public char firstchar(String param)
	{
		//cette methodes donne le premier lettre d'une chaine de caract�re 
		char c=param.charAt(0);
		return c;
	}
	//
   // les m�thodes de la classe abstraite 
  
	@Override
	// methode pour le sign-in --surcharg�e de la superclasse
	boolean authentifier(Compte compte)
		{
			if(compte==null)
			{
				System.out.println("Sign-up First");
				return false;
			} 
			else
			{
				System.out.println("Signed-in ");
				return true;
			}
		}
	@Override
	//m�thode pour que l'apprenant modifie son compte 
	boolean modifier(Compte compte ,String modifier, String input) {
		if(compte==null)
		{
			return false;
		} else 
		{
			switch(modifier)
			{
			case  "login":
                System.out.println("You're going to change the login  \n");
                compte.setLogin(input);
                break;
			case "password" :
				System.out.println("You're going to change the password \n");
                compte.setPassword(input);
                break;
            default :
            	System.out.println("Erreur Try Again :) ");
    
			}
			return true;
		}
	}
    //m�thodes de modification des champs de l'apprenant 
	//modifier son nom
	public void modifierNom(String nom)
	{
		setNom(nom);
	}
	//modifier son prenom
	public void modifierPrenom(String prenom)
	{
		setPrenom(prenom);
	}
	//modifier sa date de naissance 
	public void modifierBday(String date)
	{
		setDatebirth(date);
	}
	//modifier son adresse 
	public void changerAdresse(String add)
	{
		setAdresse(add);
	}
	//les m�thodes h�rit�s de la classe utilisateur 
	@Override
	void sauvegarder() {

		
	}

	//ajouter les quiz de la formation � sa liste des quiz non accompli
	@Override
	void ajouter() {
		visualiser(); //visualiser avant choisir 
		setQuizNonAccompli(formation.getQuizRef());
	
	}

	@Override
	//methodes d'affichage des Quiz disponible dans la formation dont l'apprenant appartient
	void visualiser() 
	{
		if(formation!=null)
		{
			if(formation.getOpen()==true)
			{
				formation.afficherQuiz(); //affichage des quiz avec les dates de d�buts et de fin 
			}
		}
	}
    //m�thode pour valider les quiz Accompli pour les soumettre � evaluation 
	@Override
	void valider() {
		Iterator<Quiz> it=quizaccompli.iterator();
		while(it.hasNext())
		{
			if(!it.next().getSoumis())
			{
			it.next().setSoumis(true); //soumis=true donc on soumettre � �valuation 
			} 
			else
			{
				System.out.println("La Quiz :"+it.next().getQuiz()+" est soumise \n");
			}
		}
		
	}
	//m�thode pour afficher la liste des quiz accomplis
	public void afficherQuizAccomplis()
	{
		for(int i=0;i<quizaccompli.size();i++)
		{
			System.out.println(quizaccompli.get(i).getQuiz());
		}
	}
	//m�thode pour afficher les quiz non accomplis avec leur dates 
	public void afficherQuizNonAccompli()
	{
		for(int i=0;i<quiznonaccompli.size();i++)
		{
			System.out.println(quiznonaccompli.get(i).getQuiz());
			System.out.println(quiznonaccompli.get(i).getDateOuverture());
			System.out.println(quiznonaccompli.get(i).getDateExpiration());

		}
	}
	//m�thode d'affichage des question d'une quiz (quiz doit etre ouvert )
	public void afficherQuestion(Quiz quiz)
	{
		if(quiz.getOpen()==true)
		{
		      quiz.afficherQuestionQuiz();
			
	    }
	}
	
    //m�thode d'affichage de pourcentage du r�ussite des quiz accompli 
	public void afficherProcessQuiz()
	{
		for(int i=0;i<quizaccompli.size();i++)
		{
			System.out.println(quizaccompli.get(i).getQuiz()); //le titre de quiz 
			System.out.println("Pourcentage de Reussite du Quiz:"+quizaccompli.get(i).getTauxSuccess());//taux de r�ussite de la quiz 
		}
	}
	//m�thode de s�l�ction d'une quiz non accompli par numero 
	public Quiz choisirQuiz(int num)
	{
		Quiz choix=quiznonaccompli.get(num); //on r�cup�re le quiz choisi pour effectuer des modifications 
		return choix;
	}
	
	//m�thode de repondre aux quizs 
	public void repondre()
	{
		Scanner carry=new Scanner(System.in);
	
		for(int i=0;i<formation.getQuizRef().size();i++)
		{
			formation.getQuizRef().get(i).afficherQuestionQuiz(); //affichage des question 
			ArrayList<Question>liste=formation.getQuizRef().get(i).getQuestionQuiz();
			int y=1,j=0;
			while(y!=0)
			{
				if(liste.get(j) instanceof QCM)
				{
					QCM ref;
					ref=((QCM)liste.get(j));
					ref.repondreQCM();
				}
				else if(liste.get(j) instanceof QCU)
				{
					QCU refer;
					refer=((QCU)liste.get(j));
					refer.repondreQCU();
				
				} 
				else if(liste.get(j) instanceof QO)
				{
					QO reference;
					reference=((QO)liste.get(j));
					reference.repondreQO();					
				}
				System.out.println("entrez 0 pour arreter de repondre");
				formation.getQuizRef().get(i).setTauxProcess(i);
				j++;
				y=carry.nextInt();
				if(y==0)
				{
					System.out.println(" arret ________ ");
				}
				if(j==liste.size())
				{
					y=0;
					System.out.println(" arret ___________");
				}
			}
		}
	}
	  
}
