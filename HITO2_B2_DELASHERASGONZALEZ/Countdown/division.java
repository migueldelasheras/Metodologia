package Countdown;

public class division implements Operaciones{
	
	/**
	 * 
	 * @author Miguel de las Heras Fuentes, Isaac Gonzalez del Pozo
	 * Class description: clase que representa la division entre dos numeros. contiene el metodo 
	 * operacion que devuelve la división de dos numeros, y el metodo signo que devuelve el signo 
	 * de la operación(/)
	 *
	 */
	
	public int operacion(int x, int y) {
		if (x<y) {
			int t=x;
			x=y;
			y=t;
		}
		if (x/y==x) {
			return 0;
		}
		if (x%y==0) {
			return x/y;
		}else
			return 0;
	}
	
	public String signo() {
		return "/";
	}

}
