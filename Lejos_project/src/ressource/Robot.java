package ressource;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class Robot {
	private NXTRegulatedMotor roueGauche;
	private NXTRegulatedMotor roueDroite;
	
	public Robot() {
		this.roueGauche = Motor.B; //moteur B = roue de gauche
		this.roueDroite = Motor.C; //moteur B = roue de droite
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
