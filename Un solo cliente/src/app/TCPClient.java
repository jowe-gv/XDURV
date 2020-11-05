package app;


import java.io.*;
import java.net.*;
import java.sql.Date;

import appImpl.TCPClientImpl;
import ctrl.Constants;
import ctrl.Windows;
import exceptions.FinishProgramException;

class TCPClient implements Constants {
	
	public static void main(String argv[]) throws Exception {
		String sentence="";
		String response="";
		String regSan=String.valueOf(REGION_SANITARIA),hosp=HOSPITAL;
		Date d;
		Windows w = new Windows();
		TCPClientImpl impl = new TCPClientImpl();
		
		//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket("localhost", 6789);
		clientSocket.setSoTimeout(10*1000);
		
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		try {
			//ENVIAR INCIALIZACION
			outToServer.writeBytes(INI + "\n");
			response = inFromServer.readLine();
			
			//ENVIAR REGION SANITARIA Y FECHA
			sentence = impl.pideDatosIni();
			outToServer.writeBytes(sentence + "\n");
			response = inFromServer.readLine();
			System.out.println("FROM SERVER: " + response);
				
			//ENVIAR HOSPITALES
			sentence = w.seleccionaOpcion("Seleccione una opcion", new String[]{ADD_HOSPITAL,FINAL_HOSPITALS});
			while(!sentence.equals(FINAL_HOSPITALS)) {
				try {
				sentence = impl.pideHospital();
				outToServer.writeBytes(sentence + "\n");
				response = inFromServer.readLine();
				if(response.equals(DAY_INFO_EXISTS))
					w.muestraError(response);
				else
					System.out.println("FROM SERVER: " + response);
				}catch(FinishProgramException e) {
					
				}
				sentence = w.seleccionaOpcion("Seleccione una opcion", new String[]{ADD_HOSPITAL,FINAL_HOSPITALS});
			}
			
			//CONTROL FINAL HOSPITALES
			if(sentence.equals(FINAL_HOSPITALS))
				outToServer.writeBytes(FINAL_HOSPITALS + "\n");
			
			//RECIBIR DATOS FINALES
			response = inFromServer.readLine();
			w.muestraMensaje(impl.estadisticas(response));
				
			
		}catch (SocketTimeoutException e ) {
			System.out.println("EL TIEMPO DE ESPERA SE HA SUPERADO");
		}catch (FinishProgramException e) {
			outToServer.writeBytes(EXIT + "\n");
			w.muestraMensaje("Hasta la proxima");
		}
		
		clientSocket.close();
		
		
		
	}
	
	
	
}