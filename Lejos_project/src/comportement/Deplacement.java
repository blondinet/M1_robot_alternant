package comportement;

import java.util.ArrayList;

import ressource.*;

public class Deplacement {

	private ArrayList<Position> listPosCheminGoal = new ArrayList<Position>();
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
	 * @return ArrayList le chemin 
	 */
	public ArrayList rechercheChemin() {
		Position posCourante = this.robot.getCaseCourante();
		Position goal = this.robot.getCaseGoal();
		// On recherche le chemin en x (en largeur)
		while(posCourante.getPosX() > goal.getPosX()) {
			this.listPosCheminGoal.add(posCourante);
			posCourante.setPosX(posCourante.getPosX()-1);
		}
		while(posCourante.getPosX() < goal.getPosX()) {
			this.listPosCheminGoal.add(posCourante);
			posCourante.setPosX(posCourante.getPosX()+1);
		}
		
		// On recherche le chemin en y (en longeur)
		while(posCourante.getPosY() > goal.getPosY()) {
			this.listPosCheminGoal.add(posCourante);
			posCourante.setPosY(posCourante.getPosY()-1);
		}
		while(posCourante.getPosY() < goal.getPosY()) {
			this.listPosCheminGoal.add(posCourante);
			posCourante.setPosY(posCourante.getPosY()+1);
		}
		
		return this.listPosCheminGoal;
	}
	
	
	
	/**
	 * Fonction qui analyse le tableau et 
	 * la position du robot ainsi que la position futur du robot 
	 * et retour si c'est possible de s'y d�placer
	 * @return boolean deplacment possible
	 */
	public boolean previsionDeplacement() {
		return false;
		//Le robot � un argument de case courante
		//Le robot � un argument de case futur
		//Le robot � un argument de case goal
		//Le robot � la taille d'une case et d'une ligne
		
		
		
	}
	
	/**
	 * methode qui prend en parametre une liste et fait ce d�placer le robot par rapport aux position de la liste
	 * @Modification Xavier Jacob--Guizon
	 */
	public void deplacementList(ArrayList<Position> list) {
		for(Position pos : list) {
			if(this.robot.getCaseCourante().getPosX() < pos.getPosX()) {
				
			}else if(this.robot.getCaseCourante().getPosX() > pos.getPosX()) {
				
			}
			
			if(this.robot.getCaseCourante().getPosY() < pos.getPosY()) {
				
			}else if(this.robot.getCaseCourante().getPosY() > pos.getPosY()) {
				
			}
		}
	}
	
	/**
	 * fonction qui test si la case correspond � la carte qu'on � fourni au robot
	 */
	//public 
}
