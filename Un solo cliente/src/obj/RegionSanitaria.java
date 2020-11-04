package obj;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class RegionSanitaria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2632175748538478159L;
	
	
	private HashMap<String, Hospital> hospitales;
	
	

	/**
	 * 
	 */
	public RegionSanitaria() {
		this.hospitales = new HashMap<String, Hospital>();
	}

	/**
	 * @param hospitales
	 */
	public RegionSanitaria(HashMap<String, Hospital> hospitales) {
		this.hospitales = hospitales;
	}

	/**
	 * @return the hospitales
	 */
	public HashMap<String, Hospital> getHospitales() {
		return hospitales;
	}

	/**
	 * @param hospitales the hospitales to set
	 */
	public void setHospitales(HashMap<String, Hospital> hospitales) {
		this.hospitales = hospitales;
	}
	
	/**
	 * Si existe el hospital intenta añadir los datos sino
	 * crea una instancia y añade los datos
	 * @param name: nombre del hospital
	 * @param d: datos a introducir
	 * @param fecha: fecha de los datos
	 * @return
	 */
	public boolean addHospital(String name, Datos d,Date fecha) {
		if(!hospitales.containsKey(name))
			hospitales.put(name, new Hospital());
		if(hospitales.get(name).addLog(fecha, d)) {
				return true;
		}
		return false;
	}
	
	/**
	 * Dada una fecha calcula las medias de los parametros de datos
	 * de todos los hospitales
	 * @param d fecha a observar
	 * @return medias calculadas y dentro de un objeto Datos
	 */
	public Datos calcularMedias(Date d) {
		Datos data = new Datos();
		List<String> hospitals = hospitalesWithDatosAtDate(d);
		if(hospitals.size()>0) {
			for(String name: hospitals) {
		       data.setDeaths(hospitales.get(name).getDatos(d).getDeaths()+data.getDeaths());
		       data.setLastPositives(hospitales.get(name).getDatos(d).getLastPositives()+data.getLastPositives());
		       data.setNewUCIRegisters(hospitales.get(name).getDatos(d).getNewUCIRegisters()+data.getNewUCIRegisters());
		       data.setUCIUnsuscribe(hospitales.get(name).getDatos(d).getUCIUnsuscribe()+data.getUCIUnsuscribe());
			}
			data.setDeaths(data.getDeaths()/hospitales.size());
			data.setLastPositives(data.getLastPositives()/hospitales.size());
			data.setNewUCIRegisters(data.getNewUCIRegisters()/hospitales.size());
			data.setUCIUnsuscribe(data.getUCIUnsuscribe()/hospitales.size());
		}
		return data;
	}
	
	/**
	 * Comprueba que hospitales tienen datos el dia indicado por
	 * parametro y devuelve una lista con los nombres
	 * @param d: fecha a mirar
	 * @return arraylist de nombres de hospitales
	 */
	public List<String> hospitalesWithDatosAtDate(Date d) {
		List<String> hospitals = new ArrayList<String>();
		for (Map.Entry<String, Hospital> entry : hospitales.entrySet()) {
			if(entry.getValue().dayHasData(d))
				hospitals.add(entry.getKey());
		}
		return hospitals;
	}
}
