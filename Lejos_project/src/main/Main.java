package main;

import comportement.Deplacement;
import lejos.hardware.Button;
import ressource.Robot;

public class Main {

	/**
	 * Fonction qui g�re le comportement de notre robot
	 * 
	 * @Modification Xavier
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Appuyez BAS pour lancer");
		while (!Button.DOWN.isDown()) {
			
		}
		Robot r2d2 = new Robot ();
		Deplacement cerveau = new Deplacement(r2d2);
		System.out.println("testeuuuur");
		cerveau.rechercheChemin();
		
		/*while (!Button.DOWN.isDown()) {	
			/*for(int i =0;i<7;i++) {
				r2d2.avanceUneCase();
				r2d2.stringColor();
				
				for(int j=0; j<5;j++) {
					if(r2d2.isSameColor(Robot.getCouleur(j),r2d2.stringColor())) {
						System.out.println(Robot.getCouleur(j));
					}
					

				}
			}*/
			/*r2d2.tournerRobotAngleDroit();
			for(int i =0;i<5;i++) {
				r2d2.avanceUneCase();

			}*/
		/*}*/
	}

}
