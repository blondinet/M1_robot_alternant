package ressource;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.DataInputStream;

import java.util.Random;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.Color;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

/**
 * Classe qui modélise un robot avec ses attributs et des fonction de
 * déplacement etc...
 * 
 * @author blondin
 *
 */
public class Robot {

	//private HashMap<String, Color> mapCouleurCourante; // C'est la map des couleurs qu'on initialise au début du
	// fonctionnement du robot
	private String[][] tableauCouleurInitial = new String [7][5] ;
	;
	public int HAUT  = 8; //etre droit dans la longueur du plateau (le 7)
	public int BAS= 5;
	public int DROITE  = 6;
	public int GAUCHE  = 4; // etre vers le bas du tableau ( voir le tableau des couleurs);

	private String[] tableauCorrespondanceColorInt = { "Rouge", "Bleu", "Vert", "Orange", "Blanc" };

	private NXTRegulatedMotor roueGauche;
	private NXTRegulatedMotor roueDroite;

	//SampleProvider sp; pour le rgb

	private EV3ColorSensor capteurCouleur;
	private Position posCourante = new Position(0, 0);
	private Position posGoal = new Position(4, 5);
	private int direction = 8;
	
	/**
	 * Constructeur du robot
	 * 
	 */
	public Robot() {
		System.out.println("yo");
		this.roueGauche = Motor.B; // moteur B = roue de gauche
		this.roueDroite = Motor.C; // moteur B = roue de droite
		this.capteurCouleur = new EV3ColorSensor(SensorPort.S3);

		initMapColorInitial();
		// ouvrir tous les capteurs ?
		//this.sp = capteurCouleur.getRGBMode();  pour le rgb
		//initColor();
		
	}

	/**
	 * Initialisation de la carte des couleur qui sera ensuite mise à jour au début
	 * de l'action par l'utilisateur
	 */
	/*private void initColor() {
		mapCouleurCourante.put("Rouge", null);
		mapCouleurCourante.put("Bleu", null);
		mapCouleurCourante.put("Vert", null);
		mapCouleurCourante.put("Orange", null);
		mapCouleurCourante.put("Blanc", null);
	}*/

	/**
	 * Tableau qui initiale la carte des couleurs (triche) Il utilise la carte de
	 * tabString et traduit l'entier avec le tableau de correspondance des couleurs
	 * avec l'incrementeur en l'incorporant à la position
	 */
	public void initMapColorInitial() {

		int[] tabString = { 0, 2, 1, 1, 1,
				1, 2, 2, 2, 1, 
				1, 2, 3, 3, 1, 
				1, 2, 4, 2, 1, 
				1, 3, 1, 2, 1, 
				1, 3, 2, 2, 0, 
				1, 1, 1, 1, 1 
				};
		int incrementeur = 0;
		//tableauCouleurInitial[0][0] = tableauCorrespondanceColorInt[tabString[incrementeur]];

		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j <= 4; j++) {
				try {
					tableauCouleurInitial[i][j] = tableauCorrespondanceColorInt[tabString[incrementeur++]];
					//System.out.println(incrementeur);
				}catch (Exception e) {
					System.out.println(e);
				}
			}
		}

	}



	/**
	 * Methode qui fait avancer d'une case le robot
	 */
	public void avanceUneCase() {
		Wheel wheel1 = WheeledChassis.modelWheel(this.roueGauche, 56).offset(-60);
		Wheel wheel2 = WheeledChassis.modelWheel(this.roueDroite, 56).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, 2);

		MovePilot pilot = new MovePilot(chassis);

		float[] angle = new float[] { 0.0f };

		pilot.setLinearSpeed(30.);
	    pilot.setLinearSpeed(30.); // unit per second
		pilot.setLinearSpeed(30.); // unit per second
		pilot.travel(120);
	}
	
	

	public void tournerRobotAngleDroit(String dir) {
		int rouG=-60;
		int rouD=60;
		if(dir== "gauche") {
			rouG=rouD;
			rouD=-60;
		}
		Wheel wheel1 = WheeledChassis.modelWheel(this.roueGauche, 56).offset(rouG);
		Wheel wheel2 = WheeledChassis.modelWheel(this.roueDroite, 56).offset(rouD);
		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, 2);

		MovePilot pilot = new MovePilot(chassis);
		pilot.setLinearSpeed(30.);
		pilot.setLinearSpeed(30.); 
		pilot.setAngularSpeed(30);
			while (pilot.isMoving()) Thread.yield();
			System.out.println("Distance:" +pilot.getMovement().getDistanceTraveled());
			pilot.rotate(85);
			while (pilot.isMoving())Thread.yield();
			System.out.println("Angle: "+pilot.getMovement().getAngleTurned());
		
	}
	
	
	public String stringColor() {
		//float[] tab = new float[3]; 
		//sp.fetchSample(tab, 0); // RBG en float
		int clr = this.capteurCouleur.getColorID();
		return getCouleur(clr);
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

	public static String getCouleur(int c) {
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
	
	/*// ceci ne fonctionnera jamais
	public void bluetooth() {
		String connected = "Connected";
	    String waiting = "Waiting...";
	    String closing = "Closing...";

	    while (true) {
	      LCD.drawString(waiting,0,0);
	      NXTConnection connection = Bluetooth.getAddress(00:16:53:43:4E:26);
	      LCD.clear();
	      LCD.drawString(connected,0,0);

	      DataInputStream dis = (DataInputStream) connection.openDataInputStream();
	      DataOutputStream dos = (DataOutputStream) connection.openDataOutputStream();

	      for(int i=0;i<100;i++) {
	        String s = dis.read_string();
	        LCD.drawString(s,0,0);
	        dos.write_string(s);
	      }
	      ((BaseRegulatedMotor) dis).close();
	      ((BaseRegulatedMotor) dos).close();

	      LCD.clear();
	      LCD.drawString(closing,0,0);

	      connection.close();
	      LCD.clear();
	    }
	}*/

	/**
	 * Getteur de la roue droite
	 * 
	 * @return
	 */
	public NXTRegulatedMotor getRoueDroite() {
		return roueDroite;
	}

	/**
	 * Setteur de la roue droite
	 * 
	 * @return
	 */
	public void setRoueDroite(NXTRegulatedMotor roueDroite) {
		this.roueDroite = roueDroite;
	}

	/**
	 * Getteur de la roue gauche
	 * 
	 * @return
	 */
	public NXTRegulatedMotor getRoueGauche() {
		return roueGauche;
	}

	/**
	 * Setteur de la roue droite
	 * 
	 * @return
	 */
	public void setRoueGauche(NXTRegulatedMotor roueDroite) {
		this.roueGauche = roueGauche;
	}
	
	/**
	* Fonction qui renvoie la case courante du robot
	* 
	* @Modification Xavier Jacob--Guizon
	*/
	public Position getCaseCourante(){
		return posCourante;
	}

	/**
	* Fonction qui renvoie la case Objectif du robot
	* 
	* @Modification Xavier Jacob--Guizon
	*/
	public Position getCaseGoal(){
		return posGoal;
	}
	


	public int getDirection() {
		return this.direction;
	}
	
	public void setDirection(int dir) {
		this.direction=dir;
	}
}
