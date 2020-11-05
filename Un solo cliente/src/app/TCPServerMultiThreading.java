package app;

import java.net.*;
import java.util.HashMap;

import appImpl.AttendPetition;
import appImpl.TCPServerMTImpl;
import obj.RegionSanitaria;

class TCPServerMultiThreading {
	public static void main(String argv[]) throws Exception {
		TCPServerMTImpl i = new TCPServerMTImpl();
		HashMap<String, RegionSanitaria> sanitaryRegions = i.loadData();
		
		ServerSocket welcomeSocket = new ServerSocket(6789);
		
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			new AttendPetition(connectionSocket,sanitaryRegions).start();
		}
	}
}