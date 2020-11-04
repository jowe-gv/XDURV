package ctrl;

import java.util.ArrayList;

public interface Constants {

	// FICHEROS
	public final String FILE_NAME_LOG="log.txt";
	public final String FILE_NAME_DATA="data.txt";	
	//PARAMETROS ARBITRARIOS
	public final int NUM_RS=100;
	
	//MENSAJES COMPARTIDOS
	public final CharSequence REGION_SANITARIA="RS";
	public final String HOSPITAL="HO";
	public final String DELIMITER=";";
	public final String MESSAGE_ARRIVED = "RECEIVED";
	public final String FINAL_HOSPITALS = "Acaba";
	public final String DAY_INFO_EXISTS = "En el hospital de la region sanitaria seleccionada ya existe una instancia para el dia introducido.";
	public final String REG_SAN_INV = "ERR03";
	
	
	//PEDIR DATOS Y CONTROL DE ERRORES
	public final String INT_ERROR="isNotAnInt";
	public final String STRING_ERROR= "isNotAString";
	public final String DATE_ERROR="isNotADate";
	public final String DEMAND_RS="Introduzca el numero de la region sanitaria.";
	public final String DEMAND_DATE="Introduzca la fecha de los datos que vaya a introducir con el formato \"yyyy-[m]m-[d]d\".";
	public final String DEMAND_HOSPITAL="Introduzca el numero del hospital.";
	public final String DEMAND_NEW_POSITIVES="Introduzca el numero de nuevos positivos.";
	public final String DEMAND_NEW_UCI_REGISTERS="Introduzca el numero de nuevos registros en la UCI.";
	public final String DEMAND_DEATHS="Introduzca el numero de nuevas defunciones.";
	public final String DEMAND_UCI_UNREGISTERS="Introduzca el numero de bajas de la UCI.";
	public final String INVALID_RS_MESSAGE = "El numero de regiones sanitarias va de 1 a " + NUM_RS + ", incluyendo ambos.";
	public final String INVALID_DATE_MESSAGE = "Recuerde que el fomato de fecha es \"yyyy-[m]m-[d]d\" y los datos han de ser correctos ";
	public final Integer[] DAYS_OF_MONTH = {31,29,31,30,31,30,31,31,30,31,30,31};
	public final String ADD_HOSPITAL="Añade la info de un nuevo hospital.";
	public final String INI="IniConexion";
	public final String EXIT="FinConexion";
	
	
	
	public final String RUTA="C:/Users/jimmy/Desktop/datos/";
	// FORMATO INI NOMBRE;FECHA
	// FORMATO HOSPITAL NOMBRE;NEWPOSITIVES;NEWUCIREGISTER;DEFUNCIONES;UCIUNREGISTER
	
	
	
}
