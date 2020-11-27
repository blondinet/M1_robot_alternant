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

	//émetteur
	public static void connectBt() throws IOException {
		BTConnector bt = new BTConnector();
		BTConnection btc= bt.connect(Variables.AdMAC, NXTConnection.PACKET); // adresse MAC du robot que l'on veut connecter
		
		OutputStream os = btc.openOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		
		dos.write(2); // écrit une valeur intdans le flux, 2 c'est aléatoire
		dos.flush(); // force l’envoi		
	}
	
	//récepteur
	public static void waitBt() throws IOException {
		BTConnector bt= new BTConnector();
		NXTConnection btc= bt.waitForConnection(100000, NXTConnection.PACKET);
		
		InputStream is= btc.openInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		int valeur = dis.read(); // récupère la valeur dans le flux
	}

}
