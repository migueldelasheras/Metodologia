package Countdown;
/**
 * 
 * @author Miguel de las Heras Fuentes, Isaac Gonzalez del Pozo
 * Class description: clase que representa la resta entre dos numeros. contiene el metodo operacion que 
 * devuelve la resta de dos numeros, y el metodo signo que devuelve el signo de la operacion(-)
 *
 */

public class resta implements Operaciones{
	
	public int operacion(int x, int y) {
		if (x<y) {
			return y-x;
		}else 
			return x-y;
	}
	
	public String signo() {
		return "-";
	}
	
}
