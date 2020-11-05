package appImpl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.io.*;
import ctrl.Constants;
import obj.Datos;
import obj.RegionSanitaria;

public class AttendPetitionImpl implements Constants {


	/**
	 * Dada la informacion inicial enviada del cliente, separa
	 * el nombre de la region sanitaria de la fecha y los asigna
	 * a las variables pasadas por parametro
	 * @param clientSentence: info recibida del cliente
	 * @return Array de sttings con el nombre y la fecha
	 */
	public  String[] analyzeIni(String clientSentence) {
		String[] trozos= clientSentence.split(DELIMITER);
		return trozos;
	}
	
	/**
	 * Dada la informacion enviada del cliente (info hospital),
	 * el nombre de la region sanitaria y la fecha, genera un objeto
	 * de datos y los introduce en el hospital indicado en la fecha de hoy
	 * siempre y cuando no haya ya una instancia
	 * @param clientSentence: hospital;
	 * @param nameSR
	 * @param fecha
	 * @return
	 */
	public  String analyzeHospital(String clientSentence,String nameSR, Date fecha,HashMap<String,RegionSanitaria> sanitaryRegions) {
		String aux;
		String[] trozos =  clientSentence.split(DELIMITER);
		Datos d = new Datos(Float.valueOf(trozos[1]), Float.valueOf(trozos[2]), Float.valueOf(trozos[3]),Float.valueOf(trozos[4]));
		if(sanitaryRegions.get(nameSR).addHospital(trozos[0], d, fecha))
			aux = MESSAGE_ARRIVED;
		else {
			aux = DAY_INFO_EXISTS;
		}
		return aux;
	}
	
	/** 
	 * Guarda la información de la entrada que ha hecho
	 * el usuario sobre los datos del COVID-19 en un
	 * hospital.
	 * 
	 * @param entryTime: tiempo que se realizo la entrada.
	 * @param sanitaryRegionName: region sanitaria del hospital de la entrada.
	 * @param clientSentence: contiene la información de la entrada realizada.
	 * @param response: indica si la entrada realizada es correcta.
	 * @param output: fichero del log.
	 */
	public void saveLogEntry(Date entryTime, String sanitaryRegionName, String clientSentence, String response)
	{
		try {
			BufferedWriter output= new BufferedWriter(new FileWriter(RUTA+FILE_NAME_LOG, true));
			LocalDateTime ahora= LocalDateTime.now();
			if(response.equals(MESSAGE_ARRIVED)) {
				output.append(ahora+";"+entryTime+";"+sanitaryRegionName+";"+clientSentence+"\n");
			}
			output.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Dado el nombre de una nueva region sanitaria,
	 * crea una nueva instancia
	 * @param name: id de la region sanitaria
	 */
	public  void addSanitaryRegion(String name,HashMap<String,RegionSanitaria> sanitaryRegions) {
		if(!sanitaryRegions.containsKey(name))
			sanitaryRegions.put(name, new RegionSanitaria());
	}

	/**
	 * Devuelve los datos de las medias, la region
	 * sanitaria y la fecha que el cliente mostrara a su gusto
	 * @param d: medias
	 * @param sanitaryRegionName: nombre de la region sanitaria
	 * @param fecha: fecha del dia que se han cogido las medias
	 * @return 
	 */
	public String formalizaMedias(Datos d, String sanitaryRegionName, Date fecha) {
		String aux = sanitaryRegionName + DELIMITER +
					fecha.toString() + DELIMITER + 
					String.valueOf(d.getLastPositives()) + DELIMITER + 
					String.valueOf(d.getNewUCIRegisters()) + DELIMITER + 
					String.valueOf(d.getDeaths()) + DELIMITER + 
					String.valueOf(d.getUCIUnsuscribe());
		return aux;
	}
	
}
