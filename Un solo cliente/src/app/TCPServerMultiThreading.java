package app;

import java.net.*;
import java.util.HashMap;

import obj.RegionSanitaria;

class TCPServerMultiThreading {
	public static void main(String argv[]) throws Exception {
		HashMap<String, RegionSanitaria> sanitaryRegions = new HashMap<String, RegionSanitaria>();
		ServerSocket welcomeSocket = new ServerSocket(6789);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			new AttendPetition(connectionSocket,sanitaryRegions).start();
		}
	}
}