package ctrl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class TypeCtrl implements Constants {
	
	
	/**
	 * Dado un string comprueba que se puede transformar a entero
	 * @param s: string a comprobar
	 * @return Cierto si se puede y falso si no
	 */
	public boolean isInteger(String s) {
		try {
			Integer.valueOf(s);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
		
	}
	
	/**
	 * Dado un string comprueba que tanto el formato como
	 * la fecha son correctos.
	 * @param s: string a comprobar
	 * @return Cierto si se puede y falso si no
	 */
	public boolean isDate(String s) {
		try {
			Date.valueOf(s);
			if(	yearValid(s) && monthValid(s) && dayValid(s))
				return true;
		}catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}
	
	/**
	 * Dado un string de numeros separados por delimitadores y un indice
	 * devuelve el numero de la posicion indicada, siendo el primero
	 * la poicion 0
	 * @param s: string con delimitadores
	 * @param i: indice
	 * @param delimiter: delimitador
	 * @return el numero
	 */
	private int obtainPiece(String s, int i, String delimiter) {
		String[] aux = s.split(delimiter);
		return Integer.valueOf(aux[i]);
	}

	/**
	 * Dada la fecha en string comprueba que el dia 
	 * sea valido
	 * @param s: fecha en string
	 * @return cierto si es valido y falso si no
	 */
	private boolean dayValid(String s) {
		int year = obtainPiece(s, 0, "-"),month= obtainPiece(s, 1, "-"),day= obtainPiece(s, 2, "-");
		if(month==2 && year%4!=0 && day>28)
			return false;
		if(day>0 && day <= DAYS_OF_MONTH[month-1])
			return true;
		return false;
	}

	/**
	 * Comprueba que el mes este entre 1 y 12 incluidos
	 * @param s: fecha en string
	 * @return cierto si es valido y falso si no
	 */
	private boolean monthValid(String s) {
		int month = obtainPiece(s, 1, "-");
		if(month>0 && month<=12)
			return true;
		return false;
	}

	/**
	 * Comprueba que el año sea valido, entre 
	 * 2019 y el año actual
	 * @param year: año a comparar
	 * @return
	 */
	private boolean yearValid(String s) {
		int year = obtainPiece(s, 0, "-");
		LocalDate localDate = new java.util.Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if((year>=2019) &&  (localDate.getYear() >= year))
			return true;
		return false;
	}

	/**
	 * Dado un numero devuelve cierto si esta
	 * entre los parametros min y max sin contar el primero
	 * @param num: numero a comparar
	 * @param min: numero minimo (no incluido)
	 * @param max: numero maximo (incluido)
	 * @return Cierto si se encuentra en el rango y falso si no
	 */
	public boolean isInRange(int num,int min,int max) {
		if(num>min && num<=max)
			return true;
		return false;
	}

}
