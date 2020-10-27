package lab;

import java.io.*;
import java.net.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import dto.Constants;
import dto.Datos;
import dto.Hospital;
import dto.RegionSanitaria;

class TCPServer implements Constants {
	
	private static HashMap<String,RegionSanitaria> sanitaryRegions = new HashMap<String, RegionSanitaria>();
	
	public static String analyzeIni(String clientSentence,String sanitaryRegion,Date fecha) {
		String aux="";
		String[] trozos= clientSentence.split(DELIMITATOR);
		sanitaryRegion = trozos[0];
		fecha= Date.valueOf(trozos[1]);
		aux=MESSAGE_ARRIVED;
		return aux;
	}
	
	public static String analyzeHospital(String clientSentence,String nameSR, Date fecha) {
		String aux="";
		String[] trozos =  clientSentence.split(DELIMITATOR);
		Datos d = new Datos(Float.valueOf(trozos[1]), Float.valueOf(trozos[2]), Float.valueOf(trozos[3]),Float.valueOf(trozos[4]));
		sanitaryRegions.get(nameSR).addHospital(trozos[0], d, fecha);
		aux = MESSAGE_ARRIVED;
		return aux;
	}
	
	public static void addSanitaryRegion(String name) {
		if(!sanitaryRegions.containsKey(name))
			sanitaryRegions.put(name, new RegionSanitaria(null));
	}
	
	public static void main(String argv[]) throws Exception {
		String clientSentence,response;
		Date fecha= Date.valueOf("2020-10-2");
		String sanitaryRegionName="";
		
		ServerSocket welcomeSocket = new ServerSocket(6789);
		HashMap<String, RegionSanitaria> SanitaryRegions = new HashMap<String, RegionSanitaria>();
		
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			//RECIBIMOS NOMBRE REGION SANITARIA Y FECHA
			clientSentence = inFromClient.readLine();
			response=analyzeIni(clientSentence,sanitaryRegionName,fecha);
			addSanitaryRegion(sanitaryRegionName);
			outToClient.writeBytes(response);
			
			//RECIBIMOS INFO DE HOSPITALES
			clientSentence = inFromClient.readLine();
			while(clientSentence!=FINAL_HOSPITALS) {
				response = analyzeHospital(clientSentence,sanitaryRegionName,fecha);
				outToClient.writeBytes(response);
				clientSentence = inFromClient.readLine();
			}
			
			//ENVIAMOS DATOS
			Datos d = sanitaryRegions.get(sanitaryRegionName).calcularMedias(fecha);
			outToClient.writeBytes(d.toString());
		}
		
		
	}
	
	
	
}