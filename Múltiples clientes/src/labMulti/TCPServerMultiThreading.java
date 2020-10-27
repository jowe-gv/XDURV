package labMulti;

import java.net.*;
import java.util.HashMap;

class TCPServerMultiThreading {
	public static void main(String argv[]) throws Exception {
		
		ServerSocket welcomeSocket = new ServerSocket(1234);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			new AttendPetition(connectionSocket).start();
		}
	}
}