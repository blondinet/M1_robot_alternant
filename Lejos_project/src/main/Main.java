package main;

import ressource.Robot;

public class Main {

	/**
	 * Fonction qui gère le comportement de notre robot
	 * 
	 * @Modification Xavier
	 * @param args
	 */
	public static void main(String[] args) {
		Robot r2d2 = new Robot ();
		for(int i =0;i<7;i++) {
			r2d2.avanceUneCase();

		}
		r2d2.tournerRobotAngleDroit();
		for(int i =0;i<5;i++) {
			r2d2.avanceUneCase();

		}
	}

}
