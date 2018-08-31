package TDAArbolBinario;

/**
 * Class EmptyTreeException Modela una excepcion por lista vacia
 * @author fgvol
 *
 */
public class EmptyTreeException extends Exception {
	
	/**
	 * Crea una excepcion con el mensaje especificado
	 * @param msg Mensaje de la excepcion
	 */
	public EmptyTreeException(String msg) {
		super(msg);
	}
}
