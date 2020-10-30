package appImpl;

import ctrl.Constants;
import ctrl.Windows;

public class TCPClientImpl implements Constants {

	public static Windows w = new Windows();
	/**
	 * Dado el numero de region sanitaria y la fecha de los datos
	 * genera el string a enviar al servidor
	 * @param num: numero de region sanitaria
	 * @param d: fecha de los datos a enviar
	 * @return el strigna enviar al servidor
	 */
	public String formalizaIni(String[] numAndDate) {
		return String.valueOf(REGION_SANITARIA)+numAndDate[0]+DELIMITER+numAndDate[1];
	}
	
	/**
	 *Pide el id de la region sanitaria y la fecha de los datos
	 *que se van a introducir
	 * @return El string a enviar al servidor o GO_OUT en caso de salir
	 */
	public  String pideDatosIni() {
		String[] numAndDate= new String[2];
		String[] messages = {DEMAND_RS,DEMAND_DATE};
		String response=INT_ERROR;
		for (int i = 0; i < numAndDate.length && response!=GO_OUT; i++) {
			while((response==INT_ERROR || response==DATE_ERROR) && response!=GO_OUT ) {
				switch(i) {
					case(0):response = w.pideEntero(messages[i], 0, NUM_RS);break;
					case(1):response = w.pideFecha(messages[i]);break;
				}
				switch(response) {
					case(INT_ERROR):w.muestraError(INVALID_RS_MESSAGE);break;
					case(DATE_ERROR):w.muestraError(INVALID_DATE_MESSAGE);break;
				}
			}
			if(response!=GO_OUT) {
				numAndDate[i] = response;
				response = DATE_ERROR;
			}
		}
		if(response!=GO_OUT)
			return formalizaIni(numAndDate);
		return GO_OUT;
	}
	
	/**
	 * Dado un array de strings donde los strings son:
	 * hospital,ultimos positivos,altas UCI, muertes, bajas UCI
	 * @param nameAndData: array con los strings
	 * @return el mensaje preparado para devolverlo al servidor
	 */
	public String formalizaHospital(String[] nameAndData) {
		String aux="";
		for (String string : nameAndData) {
			aux+=string+DELIMITER;
		}
		return aux;
	}
	
	/**
	 * Pide la informacion al usuario sobre un hospital
	 * @return el mensaje para enviar al servidor o GO_OUT
	 */
	public String pideHospital() {
		String[] nameAndData= new String[5];
		String[] messages = {DEMAND_HOSPITAL,DEMAND_NEW_POSITIVES,DEMAND_NEW_UCI_REGISTERS,DEMAND_DEATHS,DEMAND_UCI_UNREGISTERS};
		String response=INT_ERROR;
		for (int i = 0; i < nameAndData.length && response!=GO_OUT; i++) {
			while(response==INT_ERROR && response!=GO_OUT ) {
				response = w.pideEntero(messages[i],0,100000000);
				if(response==INT_ERROR) 
					w.muestraError(INT_ERROR);
			}
			if(response!=GO_OUT) {
				nameAndData[i] = response;
				response = INT_ERROR;
			}
		}
		if(response!=GO_OUT)
			return formalizaHospital(nameAndData);
		return GO_OUT;
	}

	/**
	 * Genera el string que muestra los datos de las estadisticas
	 * @param response: datos a mostrar
	 * @return string a imprimir
	 */
	public String estadisticas(String response) {
		String[] aux = response.split(DELIMITER);
		String[] fecha = aux[1].split("-");
		String stats = "ESTADISTICAS DEL DIA " + fecha[2] + "/" + fecha[1] + "/" + fecha[0] +
				"\n\nREGION SANITARIA : "+ aux[0]+
				"\nMEDIA DE POSITIVOS EN LAS ULTIMAS 24H : "+ aux[2] +
				"\nMEDIA DE ALTAS EN LAS UCI EN LAS ULTIMAS 24H : "+ aux[3] +
				"\nMEDIA DE MUERTES EN LAS ULTIMAS 24H : "+ aux[4] +
				"\nMEDIA DE BAJAS EN LAS UCI EN LAS ULTIMAS 24H : "+ aux[5];
		return stats;
	}
}
