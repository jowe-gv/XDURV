package ctrl;

import javax.swing.JOptionPane;
public class Windows implements Constants{
	
	private TypeCtrl ctrl= new TypeCtrl();
	
	
	/**
	 * Genera una ventana de error con el mensaje
	 * pasado por parametro
	 * @param message: mensaje a mostrar
	 */
	public void muestraError(String message) {
		JOptionPane.showMessageDialog(null, message,"COVID",JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Genera una ventana informativa con el mensaje
	 * pasado por parametro
	 * @param message: mensaje a mostrar
	 */
	public void muestraMensaje(String message) {
		JOptionPane.showMessageDialog(null, message,"COVID",JOptionPane.PLAIN_MESSAGE);
	}
	
	
	/**
	 * Dada una lista de opciones genera una ventana
	 * que las muestra y hace seleccionar una
	 * @param message:mensaje a mostrar
	 * @param options: opciones a escoger
	 * @return la opcion escogida o GO_OUT en caso de cancelar
	 */
	public String seleccionaOpcion(String message,String[] options) {
		String s = String.valueOf(JOptionPane.showInputDialog(null,message,"COVID",JOptionPane.QUESTION_MESSAGE,null,options,options[0]));
		if(s!=null)
			return s;
		return GO_OUT;
	}
	
	
	
	/**
	 * Genera una ventana que pide un string
	 * @param message: frase que escribira la ventana
	 * @return string escrito por el usuario
	 */
	public String pideString(String message) {
		String s = JOptionPane.showInputDialog(null,message,"COVID",JOptionPane.QUESTION_MESSAGE);
		if(s==null) 
			return GO_OUT;
		return s;
	}
	
	/**
	 * Genera una ventana que pide un entero y controla
	 * que sea un entero, en el caso contrario devuelve
	 * un INT_ERROR
	 * @param message: frase que escribira la ventana
	 * @return entero o INT_ERROR
	 */
	public String pideEntero(String message) {
		String s = JOptionPane.showInputDialog(message);
		if(s==null) 
			return GO_OUT;
		if(ctrl.isInteger(s)) {
			return s;
		}
		return INT_ERROR;
	}
	
	/**
	 * Genera una ventana que pide un entero y controla
	 * que sea un entero y que esta en el rango formado por
	 * las variables pasadas por parametro. En el caso contrario
	 * devuelve un INT_ERROR
	 * @param message: frase que escribira la ventana
	 * @param min: rango minimo (no inlcuido)
	 * @param max: rango maximo (incluido)
	 * @return el entero o INT_ERROR
	 */
	public String pideEntero(String message,int min, int max) {
		String s = JOptionPane.showInputDialog(null,message,"COVID",JOptionPane.QUESTION_MESSAGE);
		if(s==null) 
			return GO_OUT;
		if(ctrl.isInteger(s)) {
			int num = Integer.valueOf(s);
			if(ctrl.isInRange(num, min, max))
				return s;
		}
		return INT_ERROR;
	}
	
	/**
	 * Genera una ventana que pide una fecha y controla 
	 * que el formato sea correcto y se trate de una fecha
	 * valida.
	 * @param message: frase que escribira la ventana
	 * @return la fecha o DATE_ERROR
	 */
	public String pideFecha(String message) {
		String s = JOptionPane.showInputDialog(null,message,"COVID",JOptionPane.QUESTION_MESSAGE);
		if(s==null) 
			return GO_OUT;
		if(ctrl.isDate(s)) {
			return s;
		}
		return DATE_ERROR;
	}

}
