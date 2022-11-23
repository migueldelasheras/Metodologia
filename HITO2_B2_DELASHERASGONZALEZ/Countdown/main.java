package Countdown;

import java.util.*;

/**
 * Class name: main
 * @author Miguel de las Heras Fuentes, Isaac Gonzalez del Pozo
 * Class description: Clase en la que haremos la simulacion de la practica. Contiene los métodos 
 * main, inicGrandes, inicPequeños, elegirNumeros, backtracking, ordenarImprimir.
 *
 */

public class main {
	
	//creamos las variables globales que usaremos en la simulación
	private static final Operaciones[] OPERATIONS= {new suma(), new resta(), new division(), new multiplicacion()};
	static Scanner teclado= new Scanner(System.in);
	final static int max=6;
	
	/**
	 * Method name: main
	 * Description of the method: Método main en el que iremos llamando al resto de métodos para poder
	 * resolver la práctica. 
	 * @param args
	 */

	public static void main(String[] args) {
		
		ArrayList<Integer>grandes= new ArrayList<>();
		ArrayList<Integer>pequeños= new ArrayList<>();
		grandes=inicGrandes(grandes);
		pequeños=inicPequeños(pequeños);
		
		int ngrandes=0;
		System.out.println("Seleccione con cuántos números grandes quiere jugar(0-4)");
		do {
			ngrandes= teclado.nextInt();
			if (ngrandes<0 || ngrandes>4) {
				System.out.println("Número incorrecto. Inténtelo de nuevo.");
			}
		} while (ngrandes<0 || ngrandes>4);
		
		int[]numeros= new int[max];
		numeros=elegirNumeros(grandes, pequeños, numeros, ngrandes);
		
		System.out.println("Vamos a jugar con:");
		for (int i = 0; i < numeros.length; i++) {
			System.out.print(numeros[i]+" ");
		}
		System.out.println("\nCuál quieres que sea el objetivo(101-999)");
		int objetivo=0;
		do {
			objetivo=teclado.nextInt();
			if (objetivo<101 || objetivo>999) 
				System.out.println("Numero incorrecto. Inténtelo de nuevo");
			
		} while (objetivo<101||objetivo>999);
		
		String solucion="";
		ArrayList <String> soluciones= new ArrayList<>();
		backtracking(numeros, numeros.length, objetivo, solucion, soluciones,0);
		ordenarImprimir(soluciones);
		
	}
	
	/**
	 * method name: inicGrandes
	 * Description of the method: Método que inicilizará la lista grandes con todos los números 
	 * grandes que nos pueden tocar en la simulacion
	 * @param grandes: lista de números grandes vacia
	 * @return grandes: lista de numeros grandes llena
	 */
	
	public static ArrayList<Integer> inicGrandes(ArrayList<Integer>grandes){
		for (int i = 25; i <= 100; i+=25) {
			grandes.add(i);
		}
		return grandes;
	}
	
	/**
	 * method name: inicPequeños
	 * Description of the method: Método que inicializará la lista pequeños con los números pequeños
	 * que nos pueden tocar en la simulación.
	 * @param pequeños: lista de números pequeños vacía
	 * @return pequeñis: lista de números pequeños llena
	 */
	
	public static ArrayList<Integer> inicPequeños(ArrayList<Integer>pequeños){
		for (int i = 1; i <= 10; i++) {
			pequeños.add(i);
			pequeños.add(i);
		}
		return pequeños;
	}
	
	/**
	 * method name: elegirNumeros
	 * Description of the method: Metodo con el que se elegirán al azar los números con los que jugaremos
	 * @param grandes: lista de posibles números grandes
	 * @param pequeños: lista de posibles números pequeños que pueden tocar
	 * @param numeros: array en el que guardaremos los números que van tocando. Con este array se jugará
	 * @param numero: cantidad de numeros grandes con los que se va a jugar
	 * @return in[]numeros: array que contiene todos los números con los que se va a jugar
	 */
	
	public static int[] elegirNumeros(ArrayList<Integer>grandes, ArrayList<Integer>pequeños, 
			int[]numeros, int numero){
		
		int seleccionado;
		for (int i = 0; i < numero; i++) {
			seleccionado=(int)(Math.random()*(grandes.size()-1));
			numeros[i]=grandes.get(seleccionado);
			grandes.remove(seleccionado);
		}
		for (int i = numero; i < max; i++) {
			seleccionado=(int)(Math.random()*(pequeños.size()-1));
			numeros[i]=pequeños.get(seleccionado);
			pequeños.remove(seleccionado);
		}
		return numeros;
	}
	
	/**
	 * method name: backtracking
	 * 
	 * Description of the method: Corresponde al método backtracking. El método se basa en la utilizacion 
	 * de 3 bucles de los cuales los dos primeros serán para recorrer el vector que se ha pasado como parametro.
	 * el primer for recorrera las posiciones del vector desde la primera posicion, mientras que el
	 * segundo lo recorrerá desde la siguiente posición. De esta manera nos aseguramos que emparejamos 
	 * todos los números con todos. 
	 * Una vez que hemos seleccionado un par de núemeros, ejecutamos el tercer bucle que recorrerá el 
	 * array operaciones para asignarle a los numeros las 4 operaciones psoibles. De cada operacion 
	 * se guardará el resultado en la posicion i del vector y se actualizará la variable operacion con
	 * la solucion calculada hasta el momento, más la nueva operación.
	 * Una vez hecho esto, se hará la llamada recursiva al método backtracking.
	 * 
	 * @param numeros: array de números con los que tenemos que probar las distintas combinaciones
	 * de operaciones
	 * @param tamaño: tamaño del array "numeros"
	 * @param objetivo: número objetivo que se debe alcanzar
	 * @param solucion: String que almacena las operaciones llevadas a cabo en esa rama
	 * @param soluciones: lista que almacenas todas las soluciones obtenidas hasta el momento
	 * @param total: resultado obtenido de la última operacion realizada en esa rama
	 */
	
	public static void backtracking(int[]numeros, int tamaño, int objetivo, String solucion, ArrayList<String> soluciones,int total) {
		String operacion;
		for (int i = 0; i < tamaño; i++) {
			if (total==objetivo) 
				soluciones.add(solucion);
			
			for (int j = i+1; j < tamaño; j++) {
				for (int k = 0;  k < OPERATIONS.length; k++) {
					int resultado= OPERATIONS[k].operacion(numeros[i], numeros[j]);
					
					if (resultado!=0) {
						int guardari= numeros[i], guardarj=numeros[j];
						numeros[i]=resultado;
						numeros[j]=numeros[tamaño-1];
						
						operacion=solucion+Math.max(guardari, guardarj)+""+OPERATIONS[k].signo()+
								""+ Math.min(guardari, guardarj)+ "="+ resultado+" ; ";
						
						backtracking(numeros, tamaño-1, objetivo, operacion, soluciones, resultado);
						
						numeros[i]= guardari;
						numeros[j]=guardarj;
					}
				}
			}
		}
	}
	
	/**
	 * method name: ordenarImprimir
	 * Description of the method: Método con el que ordenaremos la lista en la que hemos guardado
	 * las soluciones obtenidas en el método backtracking. Ademas imprimiremos cada solucion ordenada
	 * por su longitud. Además, con el primer for, eliminamos las soluciones repetidas para los
	 * casos en los que en el array con el que jugamos se repitan dos números.
	 * @param soluciones: lista que contine todas las soluciones obtenidas del backtracking
	 */
	
	public static void ordenarImprimir(ArrayList<String> soluciones) {
		soluciones.sort(Comparator.comparingInt(String::length));
		
		for (int i = 1; i < soluciones.size(); i++) {
			if (soluciones.get(i).equals(soluciones.get(i-1))) {
				soluciones.remove(i);
				i--;
			}
		}
		
		char caracter=';';
		
		for (int i = 0; i < soluciones.size(); i++) {
			int operaciones=contarOperaciones(soluciones.get(i).toString(),caracter);
			System.out.println(soluciones.get(i).toString() + "--> "+ operaciones+" operaciones");
		}
	}
	
	/**
	 * method name: contarOperaciones
	 * description of the method: metodo para contar el número de operaciones que se hacen en cada
	 * solución.
	 * @param cadena: solucion de la que queremos contar el numero de operaciones realizadas
	 * @param caracter: caracter ";" que nos permitirá contar el número de operaciones.
	 * @return itn contador: número de veces que se repite el caracter ";" y, por tanto, número
	 * de operaciones realizadas.
	 */
	
	public static int contarOperaciones(String cadena, char caracter) {
		int posicion, contador = 0;
        posicion = cadena.indexOf(caracter);
        while (posicion != -1) { 
            contador++;                                            
            posicion = cadena.indexOf(caracter, posicion + 1);
        }
        return contador;
	}
	
}
