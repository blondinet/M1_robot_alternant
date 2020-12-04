package main;

import ressource.Position;

import java.io.IOException;
import java.util.ArrayList;

import comportement.Deplacement;
import lejos.hardware.Button;
import ressource.Bluetooth;
import ressource.Robot;

public class Main {

	/**
	 * Fonction qui gère le comportement de notre robot
	 * 
	 * @Modification Xavier
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Appuyez BAS pour lancer");
		while (!Button.DOWN.isDown()) {}
		
		//while(!Button.RIGHT.isDown() || !Button.LEFT.isDown()) {}
		//if(Button.LEFT.isDown()) {
		Robot r2d2 = new Robot ();
		r2d2.setDirection(8);
		r2d2.setCaseCourante(new Position(0,0));
		r2d2.setCaseGoal(new Position(4, 6));
		r2d2.deplacementRobotToGoal();
		
		
		/*r2d2.setEmetteur(true); // devient l'émetteur
		Bluetooth.connectBt(r2d2, "BLEU");
		//}else {
		/*Robot r2 = new Robot ();		
		Bluetooth.waitBt(r2);*/
		//}
		
		// TEST BLUETOOTH
		//
		

		Deplacement cerveau = new Deplacement(r2d2);
		
		ArrayList list = cerveau.rechercheChemin();
		while (!Button.DOWN.isDown()) {

		cerveau.deplacementList(list);
		}

		
		/*
		while (!Button.DOWN.isDown()) {	
			for(int i =0;i<2;i++) {
				r2d2.avanceUneCase();
				r2d2.stringColor();
				
				for(int j=0; j<5;j++) {
					if(r2d2.isSameColor(Robot.getCouleur(j),r2d2.stringColor())) {
						System.out.println(Robot.getCouleur(j));
					}
					

				}
				
			}
			/*
			 r2d2.tournerRobotAngleDroit("gauche");
			
			r2d2.tournerRobotAngleDroit("gauche");
			/*for(int i =0;i<5;i++) {
				r2d2.avanceUneCase();

			}
		}*/
		
		// TEST BLUETOOTH
		/*try {
			Bluetooth.connectBt();
			Bluetooth.waitBt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
	}

}
