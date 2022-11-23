package star_wars;

/**
 * 
 * Class name: Contenedor
 * @author Miguel de las Heras Fuentes, Isaac Gonzalez del Pozo
 * Class description: Esta clase representa los contenedores que habrá que asignarle a los eopies en
 * la clase main. Su único atributo será el volumen del contenedor.
 *
 */

public class Contenedor{
	private double volumen;

	public Contenedor(double volumen) {
		this.volumen=volumen;
	}
	
	public double getVolumen() {
		return this.volumen;
	}
	public void setVolumen(double volumen) {
		this.volumen=volumen;
	}

	@Override
	public String toString() {
		return "Contenedor [volumen=" + volumen + "]";
	}
	
	
	
}
