package dto;

import java.util.Date;
import java.util.HashMap;

public class Hospital {
	
	private HashMap<Date, Datos> log;

	/**
	 * @param log
	 */
	public Hospital(HashMap<Date, Datos> log) {
		this.log = log;
	}

	/**
	 * @return the log
	 */
	public HashMap<Date, Datos> getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(HashMap<Date, Datos> log) {
		this.log = log;
	}
	
	/**
	 * 
	 * @param date
	 * @param data
	 */
	public void addLog(Date date, Datos data) {
		if(!log.containsKey(date))
			log.put(date, data);
	}
	
	public Datos getDatos(Date f) {
		return log.get(f);
	}
	
	//TODO HACER FUNCIONES QUE GESTIONEN ESCRITURA Y LECTURA DESDE FICHERO
	
	//TODO 

}
