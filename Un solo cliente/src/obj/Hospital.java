package obj;

import java.util.Date;
import java.util.HashMap;

public class Hospital {
	
	private HashMap<Date, Datos> log;

	
	
	
	/**
	 * 
	 */
	public Hospital() {
		this.log = new HashMap<Date, Datos>();
	}

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
	public boolean addLog(Date date, Datos data) {
		if(!log.containsKey(date)) {
			log.put(date, data);
			return true;
		}
		return false;
	}
	
	public Datos getDatos(Date f) {
		return log.get(f);
	}
	
	//TODO HACER FUNCIONES QUE GESTIONEN ESCRITURA Y LECTURA DESDE FICHERO
	
	//TODO 

}
