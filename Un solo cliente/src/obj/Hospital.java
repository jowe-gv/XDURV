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
	
	/**
	 * Dada una fecha devuelve sus datos
	 * @param f: fecha mirar
	 * @return la datos del hospital a esa fecha
	 */
	public Datos getDatos(Date f) {
		return log.get(f);
	}
	
	/**
	 * Dada una fecha dice si existen datos ese dia o no
	 * @param d: fecha a mirar
	 * @return cierto si tiene datos y falso si no
	 */
	public boolean dayHasData(Date d) {
		if(log.containsKey(d))
			return true;
		return false;
	}
	

}
