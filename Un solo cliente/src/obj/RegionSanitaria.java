package obj;

import java.sql.Date;
import java.util.HashMap;

public class RegionSanitaria {

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
	
	
	public boolean addHospital(String name, Datos d,Date fecha) {
		if(!hospitales.containsKey(name))
			hospitales.put(name, new Hospital());
		if(hospitales.get(name).addLog(fecha, d)) {
				return true;
		}
		return false;
	}
	
	
	public Datos calcularMedias(Date d) {
		Datos data = new Datos();
		hospitales.forEach((k, v) -> {
           data.setDeaths(v.getDatos(d).getDeaths()+data.getDeaths());
           data.setLastPositives(v.getDatos(d).getLastPositives()+data.getLastPositives());
           data.setNewUCIRegisters(v.getDatos(d).getNewUCIRegisters()+data.getNewUCIRegisters());
           data.setUCIUnsuscribe(v.getDatos(d).getUCIUnsuscribe()+data.getUCIUnsuscribe());
        });
		data.setDeaths(data.getDeaths()/hospitales.size());
		data.setLastPositives(data.getLastPositives()/hospitales.size());
		data.setNewUCIRegisters(data.getNewUCIRegisters()/hospitales.size());
		data.setUCIUnsuscribe(data.getUCIUnsuscribe()/hospitales.size());
		return data;
	}
}
