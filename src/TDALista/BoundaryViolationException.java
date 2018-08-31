package TDALista;

/**
 * Class BoundaryViolationException Modela una excepcion por violacion de limites
 * @author fgvol
 *
 */
public class BoundaryViolationException extends Exception {

	/**
	 * Crea una excepcion con el mensaje especificado
	 * @param msg Mensaje de la exepcion
	 */
	public BoundaryViolationException(String msg) {
		super(msg);
	}
}
