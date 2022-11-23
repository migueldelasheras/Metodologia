package Countdown;

/**
 * 
 * @author Miguel de las Heras Fuentes, Isaac Gonzalez del Pozo
 * Class description: clase que representa la suma entre dos numeros. contiene el metodo operacion que 
 * devuelve la suma de dos numeros, y el metodo signo que devuelve el signo de la operacion(+)
 *
 */

public class suma implements Operaciones{
	public int operacion(int x, int y) {
		int resultado= x+y;
		
		if(resultado<=x || resultado<=y) {
			return 0;
		}else
			return resultado;
	}
	
	public String signo() {
		return "+";
	}
}
