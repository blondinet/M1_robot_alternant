package ressource;

import java.util.HashMap;
import java.util.Random;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

/**
 * Classe qui modélise un robot avec ses attributs et des fonction de déplacement etc...
 * 
 * @author blondin
 *
 */
public class Robot {
	
	private HashMap<String, Color> mapCouleurCourante; //C'est la map des couleurs qu'on initialise au début du fonctionnement du robot
	private String[][] tableauCouleurInitial = {};
	
	private String[] tableauCorrespondanceColorInt = {"Rouge", "Bleu", "Vert", "Orange", "Blanc"};
	
	private NXTRegulatedMotor roueGauche;
	private NXTRegulatedMotor roueDroite;
	EV3ColorSensor capteurCouleur;

	//SampleProvider sp; pour le rgb
	
	/**
	 * Constructeur du robot
	 * 
	 */
	public Robot() {
		this.roueGauche = Motor.B; //moteur B = roue de gauche
		this.roueDroite = Motor.C; //moteur B = roue de droite
		
		initMapColorInitial();
		initColor();
		// ouvrir tous les capteurs ?
		this.capteurCouleur = new EV3ColorSensor(SensorPort.S3);
		//this.sp = capteurCouleur.getRGBMode();  pour le rgb
	}

	/**
	 * Initialisation de la carte des couleur qui sera ensuite mise à jour au début de l'action
	 * par l'utilisateur
	 */
	private void initColor() {
		mapCouleurCourante.put("Rouge", null);
		mapCouleurCourante.put("Bleu", null);
		mapCouleurCourante.put("Vert", null);
		mapCouleurCourante.put("Orange", null);
		mapCouleurCourante.put("Blanc", null);
	}
	
	/**
	 * Tableau qui initiale la carte des couleurs (triche)
	 * Il utilise la carte de tabString et traduit l'entier avec le tableau de correspondance des couleurs avec l'incrementeur
	 * en l'incorporant à la position
	 */
	private void initMapColorInitial() {
		int[] tabString= {
				0,2,1,1,1,
				1,2,2,2,1,
				1,2,3,3,1,
				1,2,4,2,1,
				1,3,1,2,1,
				1,3,2,2,0,
				1,1,1,1,1};
		int incrementeur = 0;
		
		for(int i=0;i<=6;i++) {
			for(int j=0;j<=4;j++) {
				tableauCouleurInitial[i][j]=tableauCorrespondanceColorInt[tabString[incrementeur++]];
			}
		}
	}
	
	/**
	 * Methode qui fait avancer d'une case le robot
	 */
	public void avanceUneCase() {
		Wheel wheel1 = WheeledChassis.modelWheel(this.roueGauche, 56).offset(-60);
		Wheel wheel2 = WheeledChassis.modelWheel(this.roueDroite, 56).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[] {wheel1, wheel2}, 2);
		
		MovePilot pilot = new MovePilot(chassis);
		float[] angle = new float[] { 0.0f };
		pilot.setLinearSpeed(30.);
	    pilot.setLinearSpeed(30.); // unit per second
	}
	
	public void tournerRobotAngleDroit() {
		/*
	    pilot.setAngularSpeed(30.);
	    gyro.getAngleMode();
        float[] tabAngle = new float[4];
        float threshold = 90.f;

        for (int i = 0; i < 4; i++) {
        	pilot.travel(20);
        	while (pilot.isMoving())
        		Thread.yield();
                Motor.C.forward();
                while (Math.abs(angle[0]) < threshold) {
                    Delay.msDelay(500);
                    angleProvider.fetchSample(angle, 0);
                }
                Motor.C.stop(true);
                threshold += 90.f;
        	}
		}*/
	}
	
	
	public String stringColor() {
		//float[] tab = new float[3]; 
		//sp.fetchSample(tab, 0); // RBG en float		
		int clr = this.capteurCouleur.getColorID();
		return getCouleur(clr);
	}

	private static String getCouleur(int c) {
		switch (c) {
			case Color.BLUE: 
				return "Bleu";
			case Color.GREEN: 
				return "Vert";
			case Color.RED: 
				return "Rouge";
			case Color.WHITE: 
				return "Blanc";
			case Color.ORANGE: 
				return "Orange";
			default : 
				return "Unknown";
		}  
	}
	
	public boolean isSameColor(String colorCase, String colorReturn) {
		return colorCase == colorReturn;
	}
	
	/**
	 * Méthode pour obtenir une couleur aléatoire pour commencer le déplacement des robots
	 * @return la couleur
	 */
	public String randomColorByRobot() {
	    Random rand = new Random();
	    int randomIndex = rand.nextInt(tableauCorrespondanceColorInt.length);
        String randomElement = tableauCorrespondanceColorInt[randomIndex];
        //System.out.println(randomElement);
		return randomElement;
	}
	
	/**
	 * Getteur de la roue droite
	 * @return
	 */
	public NXTRegulatedMotor getRoueDroite() {
		return roueDroite;
	}

	/**
	 * Setteur de la roue droite
	 * @return
	 */
	public void setRoueDroite(NXTRegulatedMotor roueDroite) {
		this.roueDroite = roueDroite;
	}
	
	/**
	 * Getteur de la roue gauche
	 * @return
	 */
	public NXTRegulatedMotor getRoueGauche() {
		return roueGauche;
	}

	/**
	 * Setteur de la roue droite
	 * @return
	 */
	public void setRoueGauche(NXTRegulatedMotor roueDroite) {
		this.roueGauche = roueGauche;
	}
}
