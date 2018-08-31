package TDAPila;

/**
 * Class PilaEnlazada Pila de elementos genericos
 * @author fgvol
 * 
 * @param <E> Tipo de los elementos almacenados en la pila
 */
public class PilaEnlazada<E> implements Stack<E> {

	// Atributos
	private Nodo<E> top;
	private int size;
	
	// Constructor
	/**
	 * Crea una pila, inicialmente vacia
	 */
	public PilaEnlazada() {
		top = null;
		size = 0;
	}
	
	// Comandos
	public void push(E elemento) {
		top = new Nodo<E>(elemento, top);
		size++;
	}

	public E pop() throws EmptyStackException {
		if (this.isEmpty())
			throw new EmptyStackException("Pila::pop(): Error. Pila vacia.");
		
		E e = top.getElement();
		top = top.getNext();
		size--;
		
		return e;
	}

	// Consultas
	public E top() throws EmptyStackException {
		if (this.isEmpty())
			throw new EmptyStackException("Pila::top(): Error. Pila vacia.");
		
		return top.getElement();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
}
