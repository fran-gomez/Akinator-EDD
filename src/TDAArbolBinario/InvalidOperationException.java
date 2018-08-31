package TDAArbolBinario;

/**
 * Class InvalidOperationException Modela una excepcion por operacion invalida
 * @author fgvol
 *
 */
public class InvalidOperationException extends Exception {

	/**
	 * Crea una excepcion con el mensaje especificado
	 * @param msg Mensaje de la excepcion
	 */
	public InvalidOperationException(String msg) {
		super(msg);
	}
}
