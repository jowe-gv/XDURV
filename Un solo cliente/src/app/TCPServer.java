package app;

import java.io.*;
import java.net.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import appImpl.TCPServerImpl;
import ctrl.Constants;
import obj.Datos;
import obj.Hospital;
import obj.RegionSanitaria;

class TCPServer implements Constants {
	
	//private static HashMap<String,RegionSanitaria> sanitaryRegions = new HashMap<String, RegionSanitaria>();
	
	
	
	public static void main(String argv[]) throws Exception {
		TCPServerImpl impl= new TCPServerImpl();
		String clientSentence,response;
		Date fecha;
		String sanitaryRegionName="";
		
		ServerSocket welcomeSocket = new ServerSocket(6789);
		HashMap<String, RegionSanitaria> sanitaryRegions = new HashMap<String, RegionSanitaria>();
		
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
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
				outToClient.writeBytes(response+"\n");
				clientSentence = inFromClient.readLine();
			}
			
			//ENVIAMOS DATOS
			Datos d = sanitaryRegions.get(sanitaryRegionName).calcularMedias(fecha);
			outToClient.writeBytes(impl.formalizaMedias(d,sanitaryRegionName,fecha)+"\n");
		}
		
		
	}
	
	
	
}