package ressources_twister;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

/**
 * Classe qui permet de sauvegarder la m�moire d'un robot et de la charger
 * M�moire du robot : liste des couleurs et map
 * 
 * @author William
 */
public class Enregistreur {
	
	// M�moire Couleurs
	/**
	 * Permet de sauvegarder la m�moire des couleurs dans le fichier "memoire_couleurs.ser"
	 * 
	 * @param couleurs Liste des couleurs m�moris�es par le premier robot 
	 */
	public static void serialiserCouleurs(ArrayList<Color_twister> memoire_couleurs) {
		// Cr�ation d'une nouvelle liste de couleur (copie)
		final ArrayList<Color_twister> save_couleurs = new ArrayList<Color_twister>();
		for(Color_twister c:memoire_couleurs) {
			save_couleurs.add(c);
		}
		ObjectOutputStream output = null;
		
		try {
			LCD.clear();
			final FileOutputStream fichier_out = new FileOutputStream("memoire_couleurs.ser");
			LCD.drawString("fichier cr��", 0, 0);
			output = new ObjectOutputStream(fichier_out);
			LCD.drawString("output cr��", 0, 1);
			output.writeObject(save_couleurs);
			LCD.drawString("couleurs enregistr�es", 0, 2);
			output.flush();
		} catch(final IOException e) {
			LCD.clear();
			LCD.drawString("IOException 1 : ",0,3);
		} finally {
			LCD.drawString("finally", 0, 3);
			try {
				if(output != null) {
					LCD.drawString("non null", 0, 4);
					output.flush();
					output.close();
				}
			} catch(final IOException ex) {
				LCD.clear();
				LCD.drawString("IOException 2 : ",0,5);
			}
		}
		
	}
	/**
	 * R�cup�re le fichier "memoire_couleur.ser" pour permettre � l'autre robot de charger la liste des couleurs
	 * @return La liste des couleurs m�moris�es
	 */
	public static ArrayList<Color_twister> deserialiserCouleurs() {
		ArrayList<Color_twister> save_couleurs = new ArrayList<Color_twister>();
		ObjectInputStream input = null;
		
		try {
			LCD.clear();
			final FileInputStream fichier_in = new FileInputStream("memoire_couleurs.ser");
			LCD.drawString("fichier in ok", 0, 0);
			input = new ObjectInputStream(fichier_in);
			LCD.drawString("input ok", 0, 1);
			//save_couleurs = (ArrayList) input.readObject();
			//save_couleurs = (ArrayList<Color_twister>) input.readObject();
			//*
			ArrayList<Color_twister> read_object = (ArrayList<Color_twister>) input.readObject();
			for(Color_twister c:read_object) {
				save_couleurs.add(c);
			} //*/
			LCD.drawString("couleurs recup ok", 0, 2);
			LCD.drawString("couleur 1 : ", 0, 3);
			LCD.drawString(save_couleurs.get(0).getName(), 0, 4);
			return save_couleurs;
		} catch(final ClassNotFoundException e) {
			LCD.refresh();
			LCD.drawString("ClassNotFOundException : ",0,5);
		} catch(final IOException e) {
			LCD.refresh();
			//LCD.drawString("IOException 1 : ",0,5);
			LCD.clear();
			System.out.println(e);
			return null;
		} finally {
			LCD.drawString("finally", 0, 5);
			try {
				if(input != null) {
					LCD.drawString("non null", 0, 6);
					input.close();
				}
			} catch(final IOException e) {
				LCD.refresh();
				LCD.drawString("IOException 2 : ",0,7);
			}
		}
		return null;
	}
	
	// M�moire Map
	/**
	 * Permet de sauvegarder la m�moire de la map dans le fichier "memoire_map.ser"
	 * 
	 * @param map Map du jeu twister cartographi�e par le premier robot
	 */
	public static void serialiserMap(Map_twister map) {
		// Cr�ation d'une nouvelle map (copie) 
		Map_twister save_map = new Map_twister();
		save_map.setMap(map.getMap());
		ObjectOutputStream output = null;
		
		try {
			final FileOutputStream fichier_out = new FileOutputStream("memoire_map.ser");
			output = new ObjectOutputStream(fichier_out);
			output.writeObject(save_map);
			output.flush();
		} catch(final IOException e) {
			LCD.clear();
			e.printStackTrace();
		} finally {
			try {
				if(output != null) {
					output.flush();
					output.close();
				}
			} catch(final IOException ex) {
				LCD.clear();
				ex.printStackTrace();
			}
		}
		
	}
	/**
	 * R�cup�re le fichier "memoire_map.ser" pour permettre � l'autre robot de charger la map du jeu
	 * @return La map du jeu cartographi�e
	 */
	public static Map_twister deserialiserMap() {
		ObjectInputStream input = null;
		
		try {
			final FileInputStream fichier_in = new FileInputStream("memoire_map.ser");
			input = new ObjectInputStream(fichier_in);
			final Map_twister save_map = (Map_twister) input.readObject();
			return save_map;
		} catch(final ClassNotFoundException e) {
			LCD.clear();
			e.printStackTrace();
		} catch(final IOException e) {
			LCD.clear();
			e.printStackTrace();
		} finally {
			try {
				if(input != null) {
					input.close();
				}
			} catch(final IOException e) {
				LCD.clear();
				e.printStackTrace();
			}
		}
		return null;
	}

}
