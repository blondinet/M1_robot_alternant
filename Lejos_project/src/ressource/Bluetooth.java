package ressource;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;

public class Bluetooth {

	/** �metteur
	 * Fonction qui g�re la connexion du bluetooth vers une adresse MAC
	 * 
	 * @param r : celui qui ce connecte
	 * @param str : la position du robot
	 * @param adMac : adresse MAC du second robot � connecter
	 */
	public static void connectBt(Robot r, Position str, String adMac) {	
		if (r.isEmetteur()) {
			BTConnector bt = new BTConnector();
			BTConnection btc= bt.connect(adMac, NXTConnection.PACKET); // adresse MAC du robot que l'on veut connecter
			
			OutputStream os = btc.openOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			try {
				// Convert data to byte
				byte[] data = str.toString().getBytes("UTF-8");
				dos.writeInt(data.length);
				dos.write(data);
				
				dos.flush(); // force l�envoi		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // �crit une valeur intdans le flux, 2 c'est al�atoire
			r.setEmetteur(!r.isEmetteur());
		}
	}
	
	/** r�cepteur
	 * Fonction qui g�re la connexion du bluetooth vers une adresse MAC
	 * 
	 * 
	 * @param r : celui qui attend la connxion (second robot)
	 */
	public static void waitBt(Robot r) {
		if (!r.isEmetteur()) {
			BTConnector bt= new BTConnector();
			System.out.print("En attente...");
			NXTConnection btc= bt.waitForConnection(100000, NXTConnection.PACKET);
			
			InputStream is= btc.openInputStream();
			DataInputStream dis = new DataInputStream(is);
			try {			
				int length=dis.readInt();
				byte[] data = new byte[length];
				dis.readFully(data);
				String str = new String(data,"UTF-8");
				
				System.out.print(str);
				Thread.sleep(5000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		r.setEmetteur(!r.isEmetteur());
	}
	
	/** 
	 * Protoype d'un syst�me pour l'envoie de donn�es
	 * 
	 * @param dos : stream pour envoyer les donn�es
	 * @param str : position � atteindre
	 */
	public static void emetteur(DataOutputStream dos, String str) {
		try {
			// Convert data to byte
			byte[] data = str.getBytes("UTF-8");
			dos.writeInt(data.length);
			dos.write(data);
			
			dos.flush(); // force l�envoi		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // �crit une valeur intdans le flux, 2 c'est al�atoire
	}
	
	/** 
	 * Protoype d'un syst�me pour l'envoie de donn�es
	 * 
	 * @param dis : stream pour recevoir les donn�esss
	 */
	public static void recepteur(DataInputStream dis) {
		try {			
			int length=dis.readInt();
			byte[] data = new byte[length];
			dis.readFully(data);
			String str = new String(data,"UTF-8");
			
			System.out.print(str);
			Thread.sleep(30000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
