package TDALista;

/**
 * Class InvalidPositionException Modela una excepcion por posicion invalida
 * @author fgvol
 *
 */
public class InvalidPositionException extends Exception {

	/**
	 * Crea una excepcion con el mensaje especificado
	 * @param msg Mensaje de la excepcion
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
