package Countdown;

/**
 * 
 * @author Miguel de las Heras Fuentes, Isaac Gonzalez del Pozo
 * Class description: Interfaz que usaremos para crear las clases de las posibles operaciones que 
 * debemos realizar con los numeros. Contiene los metodos abstractos que tendrán las operaciones.
 *
 */

public interface Operaciones {
	
	int operacion(int x, int y);
	
	String signo();

}
