package obj;

import java.io.Serializable;

public class Datos implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1095185842344130607L;
	
	
	private float lastPositives;
	private float newUCIRegisters;
	private float deaths;
	private float UCIUnsuscribe;
	
	/**
	 * 
	 */
	public Datos() {
		this.lastPositives=0;
		this.deaths=0;
		this.newUCIRegisters=0;
		this.UCIUnsuscribe=0;
	}
	/**
	 * @param lastPositives
	 * @param newUCIRegisters
	 * @param deaths
	 * @param uCIUnsuscribe
	 */
	public Datos(float lastPositives, float newUCIRegisters, float deaths, float uCIUnsuscribe) {
		this.lastPositives = lastPositives;
		this.newUCIRegisters = newUCIRegisters;
		this.deaths = deaths;
		this.UCIUnsuscribe = uCIUnsuscribe;
	}
	/**
	 * @return the lastPositives
	 */
	public float getLastPositives() {
		return lastPositives;
	}
	/**
	 * @param lastPositives the lastPositives to set
	 */
	public void setLastPositives(float lastPositives) {
		this.lastPositives = lastPositives;
	}
	/**
	 * @return the newUCIRegisters
	 */
	public float getNewUCIRegisters() {
		return newUCIRegisters;
	}
	/**
	 * @param newUCIRegisters the newUCIRegisters to set
	 */
	public void setNewUCIRegisters(float newUCIRegisters) {
		this.newUCIRegisters = newUCIRegisters;
	}
	/**
	 * @return the deaths
	 */
	public float getDeaths() {
		return deaths;
	}
	/**
	 * @param deaths the deaths to set
	 */
	public void setDeaths(float deaths) {
		this.deaths = deaths;
	}
	/**
	 * @return the uCIUnsuscribe
	 */
	public float getUCIUnsuscribe() {
		return UCIUnsuscribe;
	}
	/**
	 * @param uCIUnsuscribe the uCIUnsuscribe to set
	 */
	public void setUCIUnsuscribe(float uCIUnsuscribe) {
		UCIUnsuscribe = uCIUnsuscribe;
	}
	@Override
	public String toString() {
		return "Datos [lastPositives=" + lastPositives + ", newUCIRegisters=" + newUCIRegisters + ", deaths=" + deaths
				+ ", UCIUnsuscribe=" + UCIUnsuscribe + "]";
	}
	
	
	
	
	
	
	

}
