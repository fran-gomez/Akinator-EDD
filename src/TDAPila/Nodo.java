package TDAPila;

/**
 * Class Nodo Nodo simplemente enlazado
 * @author fgvol
 *
 * @param <E> Tipo de los elementos del nodo
 */
class Nodo<E> {
	
	private E element;    // Elemento almacenado en el nodo
	private Nodo<E> next; // Enlace al proximo nodo
	
	/**
	 * Crea un nuevo nodo que contiene el elemento y sucesor pasados por parametro 
	 * @param element Elemento del nodo
	 * @param next Sucesor del nodo
	 */
	public Nodo(E element, Nodo<E> next) {
		this.element = element;
		this.next = next;
	}
	
	/**
	 * Modifica el elemento del nodo
	 * @param element Nuevo elemento
	 */
	public void setElement(E element) {
		this.element = element;
	}
	
	/**
	 * Modifica la referencia al sucesor del nodo
	 * @param next Nuevo sucesor
	 */
	public void setNext(Nodo<E> next) {
		this.next = next;
	}
	
	/**
	 * Obtiene el elemento almacenado en el nodo
	 * @return Elemento
	 */
	public E getElement() {
		return element;
	}
	
	/**
	 * Obtiene la referencia al proximo nodo
	 * @return Nodo sucesor
	 */
	public Nodo<E> getNext() {
		return next;
	}
} // Fin clase nodo