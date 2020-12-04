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
	public static void connectBt(Robot r, String str) {	
		if (r.isEmetteur()) {
			try {
				BTConnector bt = new BTConnector();
				BTConnection btc= bt.connect(Variables.AdMAC, NXTConnection.PACKET); // adresse MAC du robot que l'on veut connecter
				
				OutputStream os = btc.openOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				
				// Convert data to byte
				byte[] data = str.getBytes("UTF-8");
				dos.writeInt(data.length);
				dos.write(data);
				
				dos.flush(); // force l’envoi		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // écrit une valeur intdans le flux, 2 c'est aléatoire
		}
	}
	
	//récepteur
	public static void waitBt(Robot r) {
		if (!r.isEmetteur()) {
			try {
				BTConnector bt= new BTConnector();
				System.out.print("En attente");
				NXTConnection btc= bt.waitForConnection(100000, NXTConnection.PACKET);
				
				InputStream is= btc.openInputStream();
				DataInputStream dis = new DataInputStream(is);
			
				System.out.print("J'ai la valeur");
				
				int length=dis.readInt();
				byte[] data = new byte[length];
				dis.readFully(data);
				String str = new String(data,"UTF-8");
				
				System.out.print(str);
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // récupère la valeur dans le flux
		}
	}

}
