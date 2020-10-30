package app;


import java.io.*;
import java.net.*;

import appImpl.TCPClientImpl;
import ctrl.Constants;
import ctrl.Windows;

class TCPClient implements Constants {
	
	public static void main(String argv[]) throws Exception {
		String sentence="";
		String response="";
		Windows w = new Windows();
		TCPClientImpl impl = new TCPClientImpl();
		
		//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket("localhost", 6789);
		
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		//ENVIAR INCIALIZACION
		sentence = impl.pideDatosIni();
		if(!sentence.equals(GO_OUT)) {
			outToServer.writeBytes(sentence + "\n");
			response = inFromServer.readLine();
			System.out.println("FROM SERVER: " + response);
			
			//ENVIAR HOSPITALES
			sentence = w.seleccionaOpcion("Seleccione una opcion", new String[]{ADD_HOSPITAL,FINAL_HOSPITALS});
			while((!sentence.equals(GO_OUT)) && (!sentence.equals(FINAL_HOSPITALS))) {
				sentence = impl.pideHospital();
				if(!sentence.equals(GO_OUT)) {
					outToServer.writeBytes(sentence + "\n");
					response = inFromServer.readLine();
					System.out.println("FROM SERVER: " + response);
					sentence = w.seleccionaOpcion("Seleccione una opcion", new String[]{ADD_HOSPITAL,FINAL_HOSPITALS});
				}
			}
			
			//CONTROL FINAL HOSPITALES
			if(sentence.equals(FINAL_HOSPITALS) || sentence.equals(GO_OUT)) 
				outToServer.writeBytes(FINAL_HOSPITALS + "\n");
			
			//RECIBIR DATOS FINALES
			response = inFromServer.readLine();
			if(!response.equals(GO_OUT)) 
				w.muestraMensaje(impl.estadisticas(response));
			
		}
		
		clientSocket.close();
		
		
		
	}
	
	
	
}