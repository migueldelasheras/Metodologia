package Countdown;

import java.util.*;

/**
 * Class name: main
 * @author Miguel de las Heras Fuentes, Isaac Gonzalez del Pozo
 * Class description: Clase en la que haremos la simulacion de la practica. Contiene los m�todos 
 * main, inicGrandes, inicPeque�os, elegirNumeros, backtracking, ordenarImprimir.
 *
 */

public class main {
	
	//creamos las variables globales que usaremos en la simulaci�n
	private static final Operaciones[] OPERATIONS= {new suma(), new resta(), new division(), new multiplicacion()};
	static Scanner teclado= new Scanner(System.in);
	final static int max=6;
	
	/**
	 * Method name: main
	 * Description of the method: M�todo main en el que iremos llamando al resto de m�todos para poder
	 * resolver la pr�ctica. 
	 * @param args
	 */

	public static void main(String[] args) {
		
		ArrayList<Integer>grandes= new ArrayList<>();
		ArrayList<Integer>peque�os= new ArrayList<>();
		grandes=inicGrandes(grandes);
		peque�os=inicPeque�os(peque�os);
		
		int ngrandes=0;
		System.out.println("Seleccione con cu�ntos n�meros grandes quiere jugar(0-4)");
		do {
			ngrandes= teclado.nextInt();
			if (ngrandes<0 || ngrandes>4) {
				System.out.println("N�mero incorrecto. Int�ntelo de nuevo.");
			}
		} while (ngrandes<0 || ngrandes>4);
		
		int[]numeros= new int[max];
		numeros=elegirNumeros(grandes, peque�os, numeros, ngrandes);
		
		System.out.println("Vamos a jugar con:");
		for (int i = 0; i < numeros.length; i++) {
			System.out.print(numeros[i]+" ");
		}
		System.out.println("\nCu�l quieres que sea el objetivo(101-999)");
		int objetivo=0;
		do {
			objetivo=teclado.nextInt();
			if (objetivo<101 || objetivo>999) 
				System.out.println("Numero incorrecto. Int�ntelo de nuevo");
			
		} while (objetivo<101||objetivo>999);
		
		String solucion="";
		ArrayList <String> soluciones= new ArrayList<>();
		backtracking(numeros, numeros.length, objetivo, solucion, soluciones,0);
		ordenarImprimir(soluciones);
		
	}
	
	/**
	 * method name: inicGrandes
	 * Description of the method: M�todo que inicilizar� la lista grandes con todos los n�meros 
	 * grandes que nos pueden tocar en la simulacion
	 * @param grandes: lista de n�meros grandes vacia
	 * @return grandes: lista de numeros grandes llena
	 */
	
	public static ArrayList<Integer> inicGrandes(ArrayList<Integer>grandes){
		for (int i = 25; i <= 100; i+=25) {
			grandes.add(i);
		}
		return grandes;
	}
	
	/**
	 * method name: inicPeque�os
	 * Description of the method: M�todo que inicializar� la lista peque�os con los n�meros peque�os
	 * que nos pueden tocar en la simulaci�n.
	 * @param peque�os: lista de n�meros peque�os vac�a
	 * @return peque�is: lista de n�meros peque�os llena
	 */
	
	public static ArrayList<Integer> inicPeque�os(ArrayList<Integer>peque�os){
		for (int i = 1; i <= 10; i++) {
			peque�os.add(i);
			peque�os.add(i);
		}
		return peque�os;
	}
	
	/**
	 * method name: elegirNumeros
	 * Description of the method: Metodo con el que se elegir�n al azar los n�meros con los que jugaremos
	 * @param grandes: lista de posibles n�meros grandes
	 * @param peque�os: lista de posibles n�meros peque�os que pueden tocar
	 * @param numeros: array en el que guardaremos los n�meros que van tocando. Con este array se jugar�
	 * @param numero: cantidad de numeros grandes con los que se va a jugar
	 * @return in[]numeros: array que contiene todos los n�meros con los que se va a jugar
	 */
	
	public static int[] elegirNumeros(ArrayList<Integer>grandes, ArrayList<Integer>peque�os, 
			int[]numeros, int numero){
		
		int seleccionado;
		for (int i = 0; i < numero; i++) {
			seleccionado=(int)(Math.random()*(grandes.size()-1));
			numeros[i]=grandes.get(seleccionado);
			grandes.remove(seleccionado);
		}
		for (int i = numero; i < max; i++) {
			seleccionado=(int)(Math.random()*(peque�os.size()-1));
			numeros[i]=peque�os.get(seleccionado);
			peque�os.remove(seleccionado);
		}
		return numeros;
	}
	
	/**
	 * method name: backtracking
	 * 
	 * Description of the method: Corresponde al m�todo backtracking. El m�todo se basa en la utilizacion 
	 * de 3 bucles de los cuales los dos primeros ser�n para recorrer el vector que se ha pasado como parametro.
	 * el primer for recorrera las posiciones del vector desde la primera posicion, mientras que el
	 * segundo lo recorrer� desde la siguiente posici�n. De esta manera nos aseguramos que emparejamos 
	 * todos los n�meros con todos. 
	 * Una vez que hemos seleccionado un par de n�emeros, ejecutamos el tercer bucle que recorrer� el 
	 * array operaciones para asignarle a los numeros las 4 operaciones psoibles. De cada operacion 
	 * se guardar� el resultado en la posicion i del vector y se actualizar� la variable operacion con
	 * la solucion calculada hasta el momento, m�s la nueva operaci�n.
	 * Una vez hecho esto, se har� la llamada recursiva al m�todo backtracking.
	 * 
	 * @param numeros: array de n�meros con los que tenemos que probar las distintas combinaciones
	 * de operaciones
	 * @param tama�o: tama�o del array "numeros"
	 * @param objetivo: n�mero objetivo que se debe alcanzar
	 * @param solucion: String que almacena las operaciones llevadas a cabo en esa rama
	 * @param soluciones: lista que almacenas todas las soluciones obtenidas hasta el momento
	 * @param total: resultado obtenido de la �ltima operacion realizada en esa rama
	 */
	
	public static void backtracking(int[]numeros, int tama�o, int objetivo, String solucion, ArrayList<String> soluciones,int total) {
		String operacion;
		for (int i = 0; i < tama�o; i++) {
			if (total==objetivo) 
				soluciones.add(solucion);
			
			for (int j = i+1; j < tama�o; j++) {
				for (int k = 0;  k < OPERATIONS.length; k++) {
					int resultado= OPERATIONS[k].operacion(numeros[i], numeros[j]);
					
					if (resultado!=0) {
						int guardari= numeros[i], guardarj=numeros[j];
						numeros[i]=resultado;
						numeros[j]=numeros[tama�o-1];
						
						operacion=solucion+Math.max(guardari, guardarj)+""+OPERATIONS[k].signo()+
								""+ Math.min(guardari, guardarj)+ "="+ resultado+" ; ";
						
						backtracking(numeros, tama�o-1, objetivo, operacion, soluciones, resultado);
						
						numeros[i]= guardari;
						numeros[j]=guardarj;
					}
				}
			}
		}
	}
	
	/**
	 * method name: ordenarImprimir
	 * Description of the method: M�todo con el que ordenaremos la lista en la que hemos guardado
	 * las soluciones obtenidas en el m�todo backtracking. Ademas imprimiremos cada solucion ordenada
	 * por su longitud. Adem�s, con el primer for, eliminamos las soluciones repetidas para los
	 * casos en los que en el array con el que jugamos se repitan dos n�meros.
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
	 * description of the method: metodo para contar el n�mero de operaciones que se hacen en cada
	 * soluci�n.
	 * @param cadena: solucion de la que queremos contar el numero de operaciones realizadas
	 * @param caracter: caracter ";" que nos permitir� contar el n�mero de operaciones.
	 * @return itn contador: n�mero de veces que se repite el caracter ";" y, por tanto, n�mero
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
