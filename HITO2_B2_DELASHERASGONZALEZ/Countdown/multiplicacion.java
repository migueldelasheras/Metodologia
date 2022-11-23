package Countdown;

/**
 * 
 * @author Miguel de las Heras Fuentes, Isaac Gonzalez del Pozo
 * Class description: clase que representa el producto de dos numeros. contiene el metodo 
 * operación que devuelve el producto de dos numeros, y el método signo que devuelve el signo 
 * de la operacion(*). 
 *
 */

public class multiplicacion implements Operaciones{
	
	public int operacion(int x, int y) {
		int resultado= x*y;
		
		if (resultado<=x || resultado<= y) {
			return 0;
		}else
			return resultado;
	}
	
	public String signo() {
		return "*";
	}

}
