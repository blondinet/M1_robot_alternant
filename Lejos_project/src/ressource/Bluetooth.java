package ressource;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;

import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;

public class Bluetooth {

	//émetteur
	public static void connectBt(Robot r, String str, String adMac) {	
		if (r.isEmetteur()) {
			BTConnector bt = new BTConnector();
			BTConnection btc= bt.connect(adMac, NXTConnection.PACKET); // adresse MAC du robot que l'on veut connecter
			
			OutputStream os = btc.openOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			try {
				// Convert data to byte
				byte[] data = str.getBytes("UTF-8");
				dos.writeInt(data.length);
				dos.write(data);
				
				dos.flush(); // force l’envoi		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // écrit une valeur intdans le flux, 2 c'est aléatoire
			r.setEmetteur(!r.isEmetteur());
		}
	}
	
	//récepteur
	public static void waitBt(Robot r) {
		if (!r.isEmetteur()) {
			BTConnector bt= new BTConnector();
			System.out.print("En attente");
			NXTConnection btc= bt.waitForConnection(100000, NXTConnection.PACKET);
			
			try {			
				InputStream is= btc.openInputStream();
				ObjectInputStream dis = new ObjectInputStream(is);
				
				int length=dis.readInt();
				byte[] data = new byte[length];
				dis.readFully(data);
				String str = new String(data,"UTF-8");
				
				System.out.print(str);
				//Thread.sleep(30000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		r.setEmetteur(!r.isEmetteur());
	}
	
	public static void emetteur(DataOutputStream dos, String str) {
		try {
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
