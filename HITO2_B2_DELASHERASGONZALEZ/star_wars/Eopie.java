package star_wars;

/**
 * 
 * Class name: Eopie
 * @author Miguel de las Heras Fuentes, Isaac Gonzalez del Pozo
 * Class description: Esta clase corresponde a los eopies que utilizaremos en el método main. 
 * A cada eopie habrá que asignarle un contenedor con un volumen menor que la capacidad que puede
 * sorportar. Por ello lo atributos de esta clase son capacidad y Contenedor.
 *
 */

public class Eopie {
	private double capacidad;
	private Contenedor contenedor;
	
	public Eopie(double capacidad) {
		this.capacidad=capacidad;
		
	}
	
	public double getCapacidad() {
		return this.capacidad;
	}
	public void setCapacidad(double capacidad) {
		this.capacidad=capacidad;
	}

	public Contenedor getContenedor() {
		return contenedor;
	}

	public void setContenedor(Contenedor contenedor) {
		this.contenedor = contenedor;
	}

	
	public String toString() {
		return "Eopie [capacidad=" + capacidad + ", contenedor=" + contenedor + "]";
	}

	
	
	
}
