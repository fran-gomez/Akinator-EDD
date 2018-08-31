package TDAPila;

/**
 * Class EmptyStackException Modela una excepcion por pila vacia
 * @author fgvol
 */
public class EmptyStackException extends Exception {

	/**
	 * Crea una excepcion con el mensaje especificado
	 * @param arg0 Mensaje de la excepcion
	 */
	public EmptyStackException(String arg0) {
		super(arg0);
	}
}
