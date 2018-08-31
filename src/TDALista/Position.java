package TDALista;

/**
 * Interface Position Posicion de un elemento en una coleccion
 * @author fgvol
 *
 * @param <E> Tipo de los elementos
 */
public interface Position<E> {

	/**
	 * Consulta el elemento almacenado en esta posicion 
	 * @return Elemento
	 */
	E element();
}
