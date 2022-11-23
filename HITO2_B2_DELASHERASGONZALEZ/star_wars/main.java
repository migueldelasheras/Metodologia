package star_wars;

import java.util.*;

/**
 * Class name: main
 * @author Miguel de las Heras Fuentes, Isaac Gonzalez del Pozo
 * Class description: Esta clase corresponde al main del programa donde realizaremos la simulacion
 * de las 7 noches. Contará con los métodos "main", "inicEopies", "inicContenedores", "ordEopies",
 * "ordContenedores", "voraz" y "simulacion".
 *
 */

public class main {
	
	static Scanner teclado= new Scanner(System.in);
	
	/**
	 * Method name: main
	 * Description of the method: Este corresponde al método main de la simulación, desde el que 
	 * iremos llamando a los otros métodos que nos permitirán llevar a cabo la simulación.
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		double litros=0; /*cantidad de litros transportados en la semana*/
		double litrosInic=0; /*cantidad de litros inicial*/
		int noches=7;
		int numEopies;
		int numContenedores;
		
		System.out.println("Seleccione el número de eopies");
		numEopies=teclado.nextInt();
		System.out.printf("Seleccione el número de contenedores. Debe ser mayor de %s\n", numEopies);
		do {
			numContenedores= teclado.nextInt();
			if (numContenedores<=numEopies) {
				System.out.printf("Error. El número de contenedores debe ser mayor que el de eopies"
						+ "(%s)\n", numEopies);
			}
		} while (numContenedores<=numEopies);
		
		System.out.printf("El numero total de eopies es %s y el número total de contenedores es %s\n"
				,numEopies, numContenedores);
		
		
		ArrayList <Eopie>eopies= new ArrayList<Eopie>();
		ArrayList<Contenedor> contenedores=new ArrayList<Contenedor>();
		
		
		litrosInic=inicContenedores(contenedores, numContenedores);
		ordContenedores(contenedores);
		System.out.printf("La cantidad total de litros que se debe transportar es: %.2f\n",litrosInic);
		
		
		for (int i = 0; i < noches; i++) {
			System.out.printf("%sº noche: \n", i+1);
			litros+=simulacion(eopies, contenedores, numEopies);
		}
		
		System.out.printf("El numero total de litros transportados en las 7 noches es %.2f \n"
				,litros);
		
		

	}
	
	/**
	 * Mehtod name: inicEopies
	 * Description of the method: Este método sirve para inicializar la lista de Eopies que utilizaremos
	 * para la simulacion de una noche. Este método se llamará cada vez que se inicie la simulación
	 * de una noche ya que los eopies cambian su capacidad cada noche.
	 * @param eopies: lista de eopies que inicializaremos
	 * @param numEopies: numero de eopies que tendrá la lista "eopies"
	 */
	
	public static void inicEopies(ArrayList<Eopie>eopies,int numEopies) {
		double capacidad;
		Eopie e;
		System.out.println("Lista de Eopies:");
		for (int i = 0; i < numEopies; i++) {
			capacidad=Math.random()*50;
			e= new Eopie(capacidad);
			eopies.add(e);
			System.out.printf("%sº Eopie: capacidad= %.2f litros\n",i+1,capacidad);
		}

	}
	
	/**
	 * Method name: inicContenedores
	 * Description of the method: Este métod sirve para inicializar la lista de contenedores que 
	 * utilizaremos para la simulacion de las 7 noches. Este metodo se llamará una única vez antes
	 * de comenzar la simulacion ya que se elige de una sola vez todos los contenedores de la semana.
	 * @param contenedores: lista de contenedores
	 * @param numContenedores: número de contendores que tendrá la lista "contenedores"
	 * @return litrosInic: variable que se irá actualizando con el volumen de cada contenedor para obtener
	 * el número total de litros que se deben transportar en la simulación.
	 */
	
	public static double inicContenedores(ArrayList<Contenedor> contenedores, int numContenedores) {
		double capacidad;
		double litrosInic=0;
		Contenedor c;
		System.out.println("Lista de contenedores:");
		for (int i = 0; i < numContenedores; i++) {
			capacidad= Math.random()*50;
			litrosInic+=capacidad;
			c= new Contenedor(capacidad);
			contenedores.add(c);
			System.out.printf("%s. volumen= %.2f litros\n",i+1, capacidad);
		}
		return litrosInic;
	}
	
	/**
	 * method name: ordEopies
	 * Description of the method: Método para ordenar la lista de eopies de mayor a menor capacidad para 
	 * poder hacer el algorítmo voraz.
	 * @param eopies: lista de eopies que hay que ordenar
	 */
	
	public static void ordEopies( ArrayList<Eopie> eopies) {
		
		eopies.sort(Comparator.comparing(Eopie::getCapacidad).reversed());
		
	}
	
	/**
	 * Method name: ordContenedores
	 * Description of the method: Método para ordenar la lista de contenedores de mayor a menor
	 * volumen para poder hacer el algoritmo voraz.
	 * @param contenedores: lista de contenedores que hay que ordenar
	 */
	
	public static void ordContenedores( ArrayList<Contenedor> contenedores) {
		
		contenedores.sort(Comparator.comparing(Contenedor::getVolumen).reversed());
		
	}
	
	/**
	 * Method name: voraz
	 * Description of the method: Metodo voraz que utilizaremos para resolver la practica. Le pasaremos como
	 * parametros un eopie y una lista de contenedores disponibles ordenados de mayor a menor capacidad.
	 * La funcion del método es recorrer la lista de contenedores y retornar el contenedor de mayor
	 * capacidad que puede soportar el eopie seleccionado.
	 * @param e: eopie al que hay que asignarle un contenedor
	 * @param contenedores: lista de contenedores disponibles ordenada de mayor a menor volumen
	 * @return S: contenedor que le asignaremos al eopie.
	 */
	
	public static Contenedor voraz(Eopie e, ArrayList <Contenedor> contenedores) {
		Contenedor S=null;
		Contenedor aux;
		
		Iterator <Contenedor> it= contenedores.iterator();
		
		while(S==null && it.hasNext()) {
			aux= it.next();
			if (aux.getVolumen()<=e.getCapacidad()) {
				S=aux;
				contenedores.remove(aux);
		}
		}
		return S;
	}
	
	/**
	 * Mehod name: simulacion
	 * Description of the method: Metodo con el que haremos la simulación de una de las noches. 
	 * Para ello, primero inicializaremos la lista de eopies que usaremos. A continuación, iremos
	 * recorriendo dicha lista y en cada iteracion llamaremos al metodo voraz para asignarle el
	 * contendor de mayor capacidad que pueda soportar.
	 * @param eopies: lista de eopies que participarán esa noche
	 * @param contenedores: lista de contenedores que hay que transportar
	 * @param numEopies: numero de eopies que tendrá la lista de eopies
	 * @return double litros: corresponde al número de litros transportados esa noche
	 */
	
	public static double simulacion(ArrayList<Eopie> eopies, ArrayList<Contenedor> contenedores, 
			int numEopies) {
		
		double litros=0;
		eopies= new ArrayList<Eopie>();
		
		inicEopies(eopies, numEopies);
		ordEopies(eopies);
		Iterator <Eopie>it= eopies.iterator();
		Eopie e;
		System.out.println("Lista de eopies que no han transportado ningun litro esta noche:");
		while (it.hasNext()) {
			e= it.next();
			e.setContenedor(voraz(e, contenedores));
			
			if (e.getContenedor()!=null) {
				litros+=e.getContenedor().getVolumen();
			}else {
				System.out.printf("Eopie: capacidad= %.2f litros\n", e.getCapacidad());
			}
		}
		
		return litros;
	}

}
