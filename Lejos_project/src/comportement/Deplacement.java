package comportement;

import ressource.*;

public class Deplacement {

	private Position[] listPosCheminGoal;
	private Robot robot;
	
	/**
	 * @Modification Xavier Jacob--Guizon
	 * @param r le robot dont on cherche les chemins
	 */
	public Deplacement(Robot r) {
		this.robot = r;
	}
	
	/**
	 * Fonction qui cherche le chemin et le stock dans listPosCheminGoal
	 * @Modification Xavier Jacob--Guizon
	 */
	public void rechercheChemin() {
		try {
			System.out.println(this.robot.getCaseCourante());
			System.out.println(this.robot.getCaseGoal());
		}catch(Exception e) {
			//System.out.println(e);
		}
		Position posCourante = this.robot.getCaseCourante();
		Position goal = this.robot.getCaseGoal();
		// On recherche le chemin en x (en largeur)
		while(posCourante.getPosX() > goal.getPosX()) {
			this.listPosCheminGoal[this.listPosCheminGoal.length+1]=posCourante;
			posCourante.setPosX(posCourante.getPosX()-1);
		}
		while(posCourante.getPosX() < goal.getPosX()) {
			this.listPosCheminGoal[this.listPosCheminGoal.length+1]=posCourante;
			posCourante.setPosX(posCourante.getPosX()+1);
		}
		
		// On recherche le chemin en y (en longeur)
		while(posCourante.getPosY() > goal.getPosY()) {
			this.listPosCheminGoal[this.listPosCheminGoal.length+1]=posCourante;
			posCourante.setPosY(posCourante.getPosY()-1);
		}
		while(posCourante.getPosY() < goal.getPosY()) {
			this.listPosCheminGoal[this.listPosCheminGoal.length+1]=posCourante;
			posCourante.setPosY(posCourante.getPosY()+1);
		}
		
		for(Position val : this.listPosCheminGoal) {
			System.out.println(val);
		}
	}
	
	
	/**
	 * Fonction qui analyse le tableau et 
	 * la position du robot ainsi que la position futur du robot 
	 * et retour si c'est possible de s'y déplacer
	 * @return boolean deplacment possible
	 */
	public boolean previsionDeplacement() {
		return false;
		//Le robot à un argument de case courante
		//Le robot à un argument de case futur
		//Le robot à un argument de case goal
		//Le robot à la taille d'une case et d'une ligne
		
		
		
	}
	
	/**
	 * methode qui prend en parametre une liste et fait ce déplacer le robot par rapport aux position de la liste
	 * @Modification Xavier Jacob--Guizon
	 */
	public void deplacementList() {
		
	}
	
	/**
	 * fonction qui test si la case correspond à la carte qu'on à fourni au robot
	 */
	//public 
}
