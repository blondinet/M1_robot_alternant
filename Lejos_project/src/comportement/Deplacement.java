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
		Position posCourante = new Position(this.robot.getCaseCourante().getPosX(),this.robot.getCaseCourante().getPosY());
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
	public void deplacementList(ArrayList<Position> list) {
		
		for(Position pos : list) {
			if(!this.robot.getCaseCourante().estCase(pos)) {
				if(this.robot.getCaseCourante().getPosX() < pos.getPosX()) {
					//tourner le robot pour la bonne position
					while(this.robot.getDirection()!=this.robot.DROITE) {
						this.tournerDir(this.robot.DROITE);
					}
					this.robot.avanceUneCase();
					this.robot.getCaseCourante().setPosX(this.robot.getCaseCourante().getPosX()+1);
				}else if(this.robot.getCaseCourante().getPosX() > pos.getPosX()) {
					while(this.robot.getDirection()!=this.robot.GAUCHE) {
						this.tournerDir(this.robot.GAUCHE);
					}
					this.robot.avanceUneCase();
					this.robot.getCaseCourante().setPosX(this.robot.getCaseCourante().getPosX()-1);
				}
				
				if(this.robot.getCaseCourante().getPosY() < pos.getPosY()) {
					while(this.robot.getDirection()!=this.robot.HAUT) {
						this.tournerDir(this.robot.HAUT);
					}
					this.robot.avanceUneCase();
					this.robot.getCaseCourante().setPosX(this.robot.getCaseCourante().getPosY()-1);
				}else if(this.robot.getCaseCourante().getPosY() > pos.getPosY()) {
					while(this.robot.getDirection()!=this.robot.BAS) {
						this.tournerDir(this.robot.BAS);
					}
					this.robot.avanceUneCase();
					this.robot.getCaseCourante().setPosX(this.robot.getCaseCourante().getPosY()+1);
				}
				
			}
		}
	}
	
	/**
	 * méthode qui fait tourner le robot jusqu'a ce qu'il soit dans la bonne direction
	 */
	public void tournerDir(int dir) {
		int directionRobot = this.robot.getDirection();
		
		int[] listDir ={this.robot.HAUT, this.robot.DROITE, this.robot.BAS, this.robot.GAUCHE};
		for(int i =0;i<=listDir.length-1;i++) {
			if(directionRobot == listDir[i]) {
				if(i==listDir.length-1) {
					if(dir == listDir[i-1]) {
						this.robot.tournerRobotAngleDroit("gauche");
						this.robot.setDirection(listDir[i-1]);
					}else if(dir == listDir[0]) {
						this.robot.tournerRobotAngleDroit("droite");
						this.robot.setDirection(listDir[0]);
					}else {this.robot.tournerRobotAngleDroit("droite");} // si le robot est a plus de deux case de la direction voulu, on tourne à droite
				}else{
					if(dir == listDir[i-1]) {
						this.robot.tournerRobotAngleDroit("gauche");
						this.robot.setDirection(listDir[i-1]);
					}else if(dir == listDir[i+1]) {
						this.robot.tournerRobotAngleDroit("droite");
						this.robot.setDirection(listDir[i+1]);
					}else {this.robot.tournerRobotAngleDroit("droite");} // si le robot est a plus de deux case de la direction voulu, on tourne à droite
				}
				break;
			}
		}
		//if(dir != this.robot.getDirection()) {
			
		//}
	}
	
	/**
	 * fonction qui test si la case correspond à la carte qu'on à fourni au robot
	 */
	//public 
}
