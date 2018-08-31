package TDALista;

/**
 * Class EmptyListException Modela una excepcion por lista vacia
 * @author fgvol
 *
 */
public class EmptyListException extends Exception {

	/**
	 * Crea una excepcion con el mensaje especificado
	 * @param arg0 Mensaje de la excepcion
	 */
	public EmptyListException(String arg0) {
		super(arg0);
	}
}
