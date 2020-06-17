package Noyau;

public abstract class Utilisateur {
	  // liste des m�thodes 
	//m�thode d�authentification pour la connexion au compte formateur ou bien l�apprenant
	abstract boolean authentifier(Compte compte);
	//m�thode pour effectuer les modifications et renvoie un bool�en pour connaitre si l�op�ration est bien effectu�e
	abstract boolean modifier(Compte compte,String modifier,String input);
	//m�thode pour effectuer la sauvegarde des ajouts et des modifications
	abstract void sauvegarder();
	//m�thode pour effectuer l�ajout dans un quiz ou bien un compte
	abstract void ajouter();
	//m�thode pour affichage et visualiser les objets entr�e en param�tre
	abstract void visualiser();
    //m�thode pour validation des informations concernant les objets
	abstract void valider();
      
}
