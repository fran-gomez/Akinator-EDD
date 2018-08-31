package TDALista;

/**
 * Class Nodo Nodos doblemente enlazados
 * @author fgvol
 *
 * @param <E> Tipo de los elementos de la lista
 */
class Nodo<E> implements Position<E> {
	
	private Nodo<E> prev; // Enlace al nodo predecesor
	private E element;    // Elemento del nodo
	private Nodo<E> next; // Enlace al nodo sucesor
	
	/**
	 * Crea un nuevo nodo situado entre prev y next, conteniendo element
	 * @param prev Nodo previo al nuevo
	 * @param element Elemento a almacenar
	 * @param next Nodo sucesor al nuevo
	 */
	public Nodo(Nodo<E> prev, E element, Nodo<E> next) {
		this.prev = prev;
		this.element = element;
		this.next = next;
	}

	/**
	 * Obtiene la referencia al nodo predecesor
	 * @return Nodo predecesor
	 */
	public Nodo<E> getPrev() {
		return prev;
	}
	
	public E element() {
		return element;
	}
	
	/**
	 * Obtiene la referencia al nodo sucesor
	 * @return Nodo sucesor
	 */
	public Nodo<E> getNext() {
		return next;
	}
	
	/**
	 * Establece la referencia al nodo predecesor
	 * @param prev Nodo predecesor
	 */
	public void setPrev(Nodo<E> prev) {
		this.prev = prev;
	}
	
	/**
	 * Establece el elemento del nodo
	 * @param element Elemento
	 */
	public void setElement(E element) {
		this.element = element;
	}
	
	/**
	 * Establece la referencia al nodo sucesor
	 * @param next Nodo sucesor
	 */
	public void setNext(Nodo<E> next) {
		this.next = next;
	}
} // Fin clase nodo
