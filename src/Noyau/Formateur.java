package Noyau;
import java.util.*;
import java.io.*;

public class Formateur extends Utilisateur implements Serializable {
    // liste d'attributs 
	// le compte de formateur est h�rit� de l'utilsateur
	private ArrayList<Apprenant> groupe=new ArrayList<Apprenant>(); //chaque formateur a un groupe des apprenants
//	private int nbApp; //nombre des apprenants du goupe
	private Formation formation; // et une formation 
	private ArrayList<Quiz> listQuiz=new ArrayList<Quiz>(); //liste des quiz cr�er par le formateur apr�s validation de ces quiz il peut les int�gr�es dans sa formation
	private String nom;
	private String prenom;
	private Compte compte;  // le compte d'utilisateur
	//liste des m�thodes et des accesseurs /constructeurs
	//constructeur pour cr�er un formateur avec son nom/prenom
	public Formateur()
	{
		
	}
	public Formateur(String nom,String prenom)
	{
		this.nom=nom;
		this.prenom=prenom;
	}
	//Setters and getters 
	//setters 
	    public void setGroupe(ArrayList<Apprenant> groupe)
	    {
		this.groupe=groupe;
	     }
		public void setNom(String nom)
		{
			this.nom=nom;
		}
		
		public void setPrenom(String pre)
		{
			this.prenom=pre;
		}
		
		public void setFormation(Formation formation)
		{
			this.formation=formation;
		}
	//getters 
		public String getNom ()
		{
			return nom;
		}
		
		public String getPrenom ()
		{
			return prenom;
		}
		 
		public Compte getCompte()
		{
			return compte;
		}
		
		public Formation getFormation()
		{
			return formation;
		}
		
		public ArrayList<Apprenant> getGroupe()
		{
			return groupe;
		}
		public ArrayList<Quiz> getListeQuiz()
		{
			return listQuiz;
		}
	/****************************************************/	
	//methode pour la cr�ation du groupe d'apprenants du formateur 
	public void creerGroupe(int nombreapprenant)
	{
		Scanner sc=new Scanner(System.in);
		String nom,prenom,date,adresse;
		for(int i=0;i<nombreapprenant;i++)
		{
			System.out.println("Entrez le nom de l'apprenant ");
			nom=sc.next();
			System.out.println("Entrez le prenom de l'apprenant ");
			prenom=sc.next();
			System.out.println("Entrez le date de naissance de l'apprenant ");
			date=sc.next();
			System.out.println("Entrez l'adresse de l'apprenant ");
			adresse=sc.next();
			Apprenant app=new Apprenant(nom,prenom,date,adresse);
			groupe.add(app);
		}
		
	}
	
	// methode pour cr�er un compte 
	public void creerCompte(String login,String password)
	{
		// appel au constructeur avec 2 args pour le formateur 
		this.compte=new Compte(login,password);
	}
	// methode pour le sign-in --surcharg�e de la superclasse
	public boolean authentifier(Compte compte)
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
	//methode pour cr�er une formation par  formateur--invoque le constructeur de la classe formation 
	public void creerFormation(String nom,String date1,String date2)
	{
		Formation form=new Formation(nom,date1,date2);
		setFormation(form); // form dans formation --reference
		//ajouter notions 
	}
	
	//cr�ation de compte pour chaque apprenant d'un groupe par le formateur
	public void creerCompte(ArrayList<Apprenant> groupe)
	{
		for(int i=0;i<groupe.size();i++)
		{
			Compte compte=new Compte(groupe.get(i)); //instance of compte 
			groupe.get(i).setCompte(compte);  //copie des r�ference
		}
	}
	//creation d'un nouveau quiz par le formateur 
	public Quiz creerQuiz(String titre,String date1,String date2,Formation formation,ArrayList<Notion> notion,ArrayList<Question> question)
	{
		Quiz quiz=new Quiz(titre,date1,date2);
		quiz.setSatisfaite(false);
		quiz.setSauve(false);
		quiz.setSoumis(false); 
		quiz.setNotionsCouvert(notion);
		quiz.setQuestionQuiz(question);
		//saisirQuestionsQuiz(nbrequestion,quiz);
		listQuiz.add(quiz); //ajout dans la liste des quiz cr�e et non valid�es pour la formation 
		return quiz;	
	}
	//introduire les notions que seront enseign�s durant la formation
	public void introduireNotions(Formation formation,ArrayList<Notion> notions)
	{
		formation.setNotions(notions); //copie les r�ference 
	}
	
	//introduire les notions que le quizz doit couvrir 
	public void ajouterNotionQuiz(Quiz quiz,ArrayList<Notion> notions)
	{
		quiz.setNotionsCouvert(notions); //ajout des notions couverts dans le quizz
	}
	
	//affichage des quiz dans la formation
	public void visualiser()
	{
		formation.afficherQuiz();
	}
			
	//ajouter un nouveau quiz � une formation par le formateur
	public void ajouterQuiz(Formation formation,Quiz quiz)
	{
		if(formation.getQuizRef().contains(quiz)) //on v�rifi� si la quiz existe d�ja 
		{
			System.out.println("Cette Quiz exist deja ");
		}
		else 
		{
			formation.getQuizRef().add(quiz); //on l'ajoute s'il n'existe pas 
		}
	}
	//modification de compte de formateur 
	public boolean modifier(Compte compte,String modifier,String input)
	{
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
	// m�thodes de modification du quiz 
	// A. Modifier le titre ou les dates d�ouverture et d�expiration du quiz.
	// m�thode de modification de titre de quiz selon le numero de quiz dans la formation
	public boolean modifierTitle()
	{
		System.out.println("Saisir le numero de quiz que vous voulez le modifier son titre ");
		Scanner sc=new Scanner(System.in);
		int numero=sc.nextInt();
		if(numero<0)
		{
			System.out.println("Quiz non existant ");
			return false;
		} 
		else 
		{    
			System.out.println("Saisir le nouveau titre du Quiz");
			String titre=sc.next();
			listQuiz.get(numero).setQuiz(titre);
			return true;
		}
	}
	
	//m�thode de modification de la date d'ouverture de quiz
	public boolean modifierDateOpen()
	{
		System.out.println("Saisir le numero de quiz que vous voulez le modifier sa date d'ouverture ");
		Scanner sc=new Scanner(System.in);
		int numero=sc.nextInt();
		if(numero<0)
		{
			System.out.println("Quiz non existant ");
			return false;
		} 
		else 
		{    
			System.out.println("Saisir le nouvelle date d'ouverture du Quiz");
			String date=sc.next();
			listQuiz.get(numero).setQuiz(date);
			return true;
		}
		
		
	}
	
	//m�thode de modification de la date d'expiration de quiz 
	public boolean modifierDateClosing()
	{
		System.out.println("Saisir le numero de quiz que vous voulez le modifier sa date d'expiration ");
		Scanner sc=new Scanner(System.in);
		int numero=sc.nextInt();
		if(numero<0)
		{
			System.out.println("Quiz non existant ");
			return false;
		} 
		else 
		{    
			System.out.println("Saisir le nouvelle date d'expiration du Quiz");
			String date=sc.next();
			listQuiz.get(numero).setQuiz(date);
			return true;
		}
	}
	
	// Supprimer une quiz
	//supression d'un quiz choisi par le formatteur 
	public boolean deleteQuiz()
	{
		System.out.println("Saisir le numero de quiz que vous voulez supprimer ");
		Scanner sc=new Scanner(System.in);
		int numero=sc.nextInt();
		if(numero<0)
		{
			System.out.println("Quiz non existant");
			return false;
		} else 
		{
			listQuiz.remove(numero);
			return true;
		}
			
	}
	//B. Supprimer une question d'une quiz choisi de la formation 
	public void deleteQuestionQuiz(int NumQuiz,String quest)
	{
		if(formation.getOpen())
		{
			System.out.println("Cette Quiz est ouverte aux apprenant vous ne pouvez pas la modifier ");
		}
		else
		{
			Quiz source=formation.getQuizRef().get(NumQuiz);//source contient reference vers liste des quiz de la formation 
			source.supprimerQuestion(quest);
		}
		
	}
	//methodes h�rit�s de la classe utilisateur
	//C. Ajouter une question
	//m�thode d'ajout d'une question al�atoire portant sur une notion choisi par le formateur dans la formation
	@Override
	void ajouter() {
		System.out.println("Preciser le titre de la notion de la question ");
		Scanner sc=new Scanner(System.in);
		String titre=sc.next();
		System.out.println("Saisir le numero de Quiz que vous voulez l'ajouter une question");
		int num=sc.nextInt();
		//on va cr�er une copie de la list des r�f�rence des questions de la notions  
			for(int i=0;i<formation.getNotionRef().size();i++)
			{
				if(formation.getNotionRef().get(i).getTitre()==titre)
				{
					TreeSet<Question>source=new TreeSet<Question>(); //cr�ation du TreeSet pour contenir la liste des questions de la notion choisi
					source.addAll(formation.getQuizRef().get(num).getQuestionQuiz()); //les questions de la quiz
					ArrayList<Question> destination=new ArrayList<Question>(); //cr�ation de la liste des question de la quiz choisi 
					destination= formation.getNotionRef().get(i).getRefQuestion(); //les questions de la notion
					destination.removeAll(source); //on supprime les questions de la quiz dans la source 
				    Collections.shuffle(destination);  
					formation.getQuizRef().get(num).getQuestionQuiz().add(destination.get(1)); //ajout de la question dans la quiz 
				   
				}
			}
			
	}
	//D. Changer une question 
	//m�thode pour effectuer le changement des questions par le formateur de la quiz N�=x 
	public void changerQuestion(int x)
	{
		//System.out.println("Preciser le numero de question que vous voulez changer");
		Scanner sc1=new Scanner(System.in);
		int numero=3;
		//=sc1.nextInt();
		for(int i=0;i<listQuiz.size();i++)
		{
			if(formation.getQuizRef().get(x).getQuestionQuiz().get(numero)==listQuiz.get(i).getNotionCOuverte().get(i).getRefQuestion().get(i))
			{
			
				//on fait des copies des deux listes de questions et on fait la comparaison apr�s 
				//la liste des quiz dans la formation 
				TreeSet<Question> source=new TreeSet<Question>(); 
				source.addAll(formation.getQuizRef().get(x).getQuestionQuiz());
				//la liste des quiz que ne sont pas encore souvegarder dans la fomration
				ArrayList<Question> destination= new ArrayList<Question>();
				destination=listQuiz.get(i).getNotionCOuverte().get(i).getRefQuestion();
			    destination.removeAll(source); //on supprime les question dupliqu�e du destination
			    Collections.shuffle(destination); //on mix les questions 
			    formation.getQuizRef().get(x).getQuestionQuiz().add(numero, destination.get(1)); //on ajoute la nouvelle question � la place du question chang�e
			}
			
		}
		
	}
	
	//m�thode pour effectuer le sauvegarde des quiz ouverts aux apprenants � la formation (si open==vrai on v�rifie satisfait aussi )
	@Override
	void sauvegarder() {
		for(int i=0;i<listQuiz.size();i++)
		{
			if(listQuiz.get(i).getOpen()==true)
			{
				if(listQuiz.get(i).getSatisfaite()==true)
				{
					formation.getQuizRef().add(listQuiz.get(i));//
					System.out.println("Ajout du Quiz Effectuee ");
				}
			}
		}
	 
	}
	//m�thode de validation de la liste des quiz pour que �a sera ouverte aux apprenants 
	@Override
	void valider() {
		for(int i=0;i<listQuiz.size();i++)
		{
			if(listQuiz.get(i).getSatisfaite()==true)
			{
				listQuiz.get(i).setOpen(true); //ouvertes aux apprenants appr�s ajout � la fomration
			} 
		}
	}
	
	//supression d'une question pr�cise par le formateur 
	public boolean deleteQuestion(Quiz quiz,String titre)
	{
		Question ref=null; //initialisation forc�ment sinon erreur 
		for(int i=0;i<quiz.getQuestionQuiz().size();i++ )
		{
			if(quiz.getQuestionQuiz().get(i).getQuestion().equalsIgnoreCase(titre))
			{
				ref=quiz.getQuestionQuiz().get(i);
				quiz.getQuestionQuiz().remove(i); //supression effectu�
			}
		}
		if(ref==null)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	//m�hodes de gestion du groupe d'apprenant selon le besoin affichage/ajout/supression .. etc part 1
	//ajout d'un nouveau apprenant 
	public boolean nouveauApprenant(String nom,String prenom,String date,String adr)
	{
		Apprenant app=new Apprenant(nom,prenom,date,adr); //cr�ation de r�f�rence pour l'apprenant 
		Compte cp=new Compte(app); //cr�ationd de compte automatiquement par le constructeur 
		app.setCompte(cp); //ajout de compte 
	    app.setFormation(formation); //ajout de l'apprenant � la formation de groupe 
		groupe.add(app); //ajout de l'apprenant dans le groupe 
		return true;
        //dead code si je fais une v�rification de r�f�rence ?????
	}
	
	//supprimer un apprenant du groupe et du formation (le suprimer le compte) la suppression se fait par nom 
		public boolean deleteApprenant(String nom)
		{
			Apprenant ref = null; //renfrorcement d'initialisation
			//supression du groupe 
		    for(int i=0;i<groupe.size();i++)
		    {
		    	
		    	if(groupe.get(i).getNom().equalsIgnoreCase(nom))
		    	{
		    		 ref=groupe.get(i); //recup�ration de la r�f�rence de l'apprenant pour le test du fin 
		    		//supression du compte de cet apprenant 
		    		groupe.remove(i); //supression du groupe
		    		
		    	} 	
		    }
		    //test de success de la suppression
		    if(ref==null)
		    {
		    	return true;
		    }
		    else 
		    {
		    	return false;
		    }
		    
		}
		
		//m�thode pour affecter le groupe d'apprenant � la formation
		public void affecterFormation()
		{
			for(int i=0;i<groupe.size();i++)
			{
				groupe.get(i).setFormation(formation); //affectation de la formation pour chaque apprenant dans le groupe 
			}
		}
		
		//m�thode pour modifier la formation 
		//A- modifier le titre de la formation 
		public void changerTitreForm(String titre)
		{
			System.out.println("Vous avez changee le titre de la formation ");
			formation.setTitre(titre); //
			System.out.println("Formation : "+formation.getNom());
		}
		//B- modifier la date de debut de la formation 
		public void changerDateDebut(String date)
		{
			System.out.println("Vous avez changee la date de debut de la formation");
			formation.setDatedebut(date);
			System.out.println("Date debut :"+formation.getDatedebut());
		}
		//C- modifier date de fin de la formation 
		public void changerDatefin(String date)
		{
			System.out.println("Vous avez changee la date de fin de la formation");
			formation.setDatefin(date);
			System.out.println("Date de fin  :"+formation.getDatefin());
		}
		//D- modifier l'acc�s � la formation
		public void fermerForm(Boolean boo)
		{
			if(formation.getOpen())
			{
				formation.setOpen(boo);
				System.out.println("cette formation est officiellement fermee");
			}
		}
		
		//m�thode de saisir question dans la quiz qu'il va ajouter plus tard
		public void saisirQuestionsQuiz(int x,Quiz quiz)
		{
			System.out.println("Les types de questions :QCM/QCU/QO");
			for(int i=0;i<=x;i++)
			{
			Scanner sc=new Scanner(System.in);
			System.out.println("Saisir le Type de question");
			String type=sc.next();//input du type pour effectuer l'ajout du question
			
			//traitement des diff�rents cas de figure 
			if(type.equals("QCM"))// cas du QCM
			{
				System.out.println("Saisir la question :) ");
				String qcm=sc.next();
				QCM q=new QCM(qcm); //appel du constructeur QCM
				quiz.getQuestionQuiz().add(q); //ajout de QCM
			} 
			else if(type.equals("QCU"))//cas du QCU
			{
				System.out.println("Saisir la question :) ");
				String qcu=sc.next();
				System.out.println("Saisir la reponse correcte du QCU ^^");
				String reponse=sc.next();
				QCU q=new QCU(qcu,reponse);//appel du constructeur QCU
				quiz.getQuestionQuiz().add(q); //ajout du QCU
			}
			else if(type.equals("QO"))//cas du QO
			{
				System.out.println("Saisir la question :) ");
				String qo=sc.next();
				QO q=new QO(qo);//appel du constructeur QO
				quiz.getQuestionQuiz().add(q); //ajout du QO
			}
			
		}		  
			//fin d'ajout des questions 
		}
		
		//m�thodes de gestion de groupe d'apprnenants part - 2 
		public void progressGroupe()
		{
			
			//source.comparator(groupe);
			//Iterator<Apprenant> iterateur=source.iterator(); 
			for(int i=0;i<groupe.size();i++)
			{
				System.out.println(groupe.get(i).getNom()+" "+groupe.get(i).getPrenom()+" "+groupe.get(i).getProcess()); //affichage des apprennat par NOM/PRENOM/PROGRESSION de chacun
			}
		}
		
		//m�thode de consultation d'activt� d'un apprenant /recherche par son nom et prenom
		public void progressApprenant(String nom,String prenom)
		{
			Iterator<Apprenant> iterateur=groupe.iterator(); //it�rateur de groupe 
			while(iterateur.hasNext())
			{
				if(iterateur.next().getNom().equals(nom) && iterateur.next().getPrenom().equals(prenom))
				{
					System.out.println(iterateur.next().getNom()+" "+iterateur.next().getPrenom()+ "est de progress :"+iterateur.next().getProcess());
					System.out.println("Les Quiz non Accomplis de cet Apprenant :");
					for(int i=0;i<iterateur.next().getQuizNonAccompli().size();i++)
					{
						System.out.println("Quiz : \n"+iterateur.next().getQuizNonAccompli().get(i).getQuiz()); //affichage par nom/date debut /date expiration de quiz 
					}
					System.out.println("Les Quiz Accomplis par cet Apprennat :");
					iterateur.next().afficherQuizAccomplis(); //affichage par nom de quiz 
				}
				else 
				{
					System.out.println("Cet Apprenant n'existe pas dans votre groupe ");
				}
			}
		}
		
		//m�thode d'affichage la liste d'apprenants pour v�rification 
		public void visualiserListapp()
		{
			for(int i=0;i<groupe.size();i++)
			{
				System.out.println(groupe.get(i).getNom()+" "+groupe.get(i).getPrenom());
			}
		}
		
		public void afficher()
		{
			System.out.println(nom+" "+prenom);
		}
		
		
	
	
	
	
	
	
	
	
	
}
