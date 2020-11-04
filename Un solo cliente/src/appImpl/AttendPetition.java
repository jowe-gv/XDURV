package appImpl;

import java.io.*;
import java.net.*;
import java.sql.Date;
import java.util.HashMap;

import ctrl.Constants;
import obj.Datos;
import obj.RegionSanitaria;

public class AttendPetition extends Thread implements Constants {	
	private Socket connectionSocket;
	private HashMap<String, RegionSanitaria> sanitaryRegions; 
	
	
	public AttendPetition(Socket s,HashMap<String, RegionSanitaria> sanitaryRegions){
		this.connectionSocket=s;
		try {
			this.connectionSocket.setSoTimeout(120*1000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.sanitaryRegions=sanitaryRegions;
	}
	
	public void run() {
		AttendPetitionImpl impl= new AttendPetitionImpl();
		String clientSentence,response;
		Date fecha;
		String sanitaryRegionName="";
		
		
		//HashMap<String, RegionSanitaria> sanitaryRegions = new HashMap<String, RegionSanitaria>();
		 
		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			//RECIBIMOS NOMBRE REGION SANITARIA Y FECHA
			clientSentence = inFromClient.readLine();
			String[] aux = impl.analyzeIni(clientSentence);
			sanitaryRegionName=aux[0];fecha=Date.valueOf(aux[1]);
			impl.addSanitaryRegion(sanitaryRegionName,sanitaryRegions);
			outToClient.writeBytes(MESSAGE_ARRIVED+"\n");
			
			//RECIBIMOS INFO DE HOSPITALES
			clientSentence = inFromClient.readLine();
			while(!clientSentence.equals(FINAL_HOSPITALS)) {
				response = impl.analyzeHospital(clientSentence,sanitaryRegionName,fecha,sanitaryRegions);
				impl.saveLogEntry(fecha, sanitaryRegionName, clientSentence, response);
				outToClient.writeBytes(response+"\n");
				clientSentence = inFromClient.readLine();
			}
			
			
			//ENVIAMOS DATOS
			Datos d = sanitaryRegions.get(sanitaryRegionName).calcularMedias(fecha);
			outToClient.writeBytes(impl.formalizaMedias(d,sanitaryRegionName,fecha)+"\n");
		}catch (SocketTimeoutException e ) {
			System.out.println("EL TIEMPO DE ESPERA SE HA SUPERADO");
		}catch (IOException e) {
			System.out.println("ALGO HA FALLADO CON LA INICIALIZACION.");
		} 
	}
	
	
	
}