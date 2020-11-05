package appImpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import ctrl.Constants;
import obj.RegionSanitaria;

public class TCPServerMTImpl implements Constants {
	
	/**
	 * Guarda en un fichero el hashmap que contiene
	 * toda la informacion de las regiones sanitarias
	 * @param e hasmap a guardar
	 */
	public void saveData(HashMap<String,RegionSanitaria> e) {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(RUTA+FILE_NAME_DATA);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(e);
	         out.close();
	         fileOut.close();
	         System.out.println("Serialized data is saved in" + RUTA+FILE_NAME_DATA);
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	/**
	 * Carga de un fichero un hashmap de forma serializada
	 * @return un hashmap 
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String,RegionSanitaria> loadData(){
		
		HashMap<String,RegionSanitaria> e = new HashMap<String, RegionSanitaria>();
		try {
	         FileInputStream fileIn = new FileInputStream(RUTA+FILE_NAME_DATA);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (HashMap<String,RegionSanitaria>) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         e = new HashMap<String, RegionSanitaria>();
	      } catch (ClassNotFoundException c) {
	         System.out.println("RegionSanitaria class not found");
	      }
		return e;
		
	}

}
