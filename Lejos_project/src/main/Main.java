package main;

import lejos.hardware.Button;
import ressource.Robot;

public class Main {

	/**
	 * Fonction qui gère le comportement de notre robot
	 * 
	 * @Modification Xavier
	 * @param args
	 */
	public static void main(String[] args) {
		while (!Button.DOWN.isDown()) {
			Robot r2d2 = new Robot ();
			for(int i =0;i<7;i++) {
				r2d2.avanceUneCase();
				r2d2.stringColor();
				
				for(int j=0; j<5;j++) {
					if(r2d2.isSameColor(Robot.getCouleur(j),r2d2.stringColor())) {
						System.out.println(Robot.getCouleur(j));
					}
					

				}
			}
			/*r2d2.tournerRobotAngleDroit();
			for(int i =0;i<5;i++) {
				r2d2.avanceUneCase();

			}*/
		}
	}

}
