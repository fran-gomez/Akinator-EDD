package TDALista;

import java.util.Iterator;

/**
 * Class ListaDoblementeEnlazada Lista doblemente enlazada de elementos
 * @author fgvol
 * 
 * @param <E> Tipo de los elementos almacenados en la lista
 */
public class ListaDoblementeEnlazada<E> implements PositionList<E> {

	// Atributos
	private Nodo<E> header;  // Primer nodo centinela
	private Nodo<E> trailer; // Ultimo nodo centinela
	private int size;	     // Cantidad de elementos
	
	// Constructor
	/**
	 * Crea una lista doblemente enlazada, inicialmente vacia
	 */
	public ListaDoblementeEnlazada() {
		header = new Nodo<E>(null, null, null);
		trailer = new Nodo<E>(header, null, null);
		header.setNext(trailer);
		size = 0;
	}
	
	// Metodos privados
	/**
	 * Testea si la posicion p es valida, y devuelve el nodo asociado
	 * @param p Posicion a testear
	 * @return Nodo asociado a la posicion p
	 * @throws InvalidPositionException Si la posicion es nula, fue invalidada o pertenece a otra estructura de datos
	 */
	private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		
		try {
			
			if (p == null)
				throw new InvalidPositionException("Lista::checkPosition(p): Error. Posicion nula.");
			
			if (p.element() == null)
				throw new InvalidPositionException("Lista::checkPosition(p): Error. Posicion previamente eliminada.");
			
			return (Nodo<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Lista::checkPosition(p): Error. La posicion no corresponde a una posicion de lista doblemente enlazada.");
		}
	}
	
	/**
	 * Agrega un nuevo nodo entre los dos pasados por parametro
	 * @param prev Nodo anterior al nuevo
	 * @param element Elemento a almacenar en el nuevo nodo
	 * @param next Nodo sucesor al nuevo
	 * @return Posicion del nuevo nodo en la lista
	 */
	private Position<E> addBetween(Nodo<E> prev, E element, Nodo<E> next) {
		
		// Crear el nuevo nodo
		Nodo<E> n = new Nodo<E>(prev, element, next);
		
		// Engancharlo en la lista
		prev.setNext(n);
		next.setPrev(n);
		// Actualizar el tamaño de la lista
		size++;
		
		return n;
	}
	
	// Comandos
	public void addFirst(E element) {
		addBetween(header, element, header.getNext());
	}

	public void addLast(E element) {
		addBetween(trailer.getPrev(), element, trailer);
	}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		
		Nodo<E> n = checkPosition(p);
		addBetween(n.getPrev(), element, n);
	}

	public void addAfter(Position<E> p, E element) throws InvalidPositionException {

		Nodo<E> n = checkPosition(p);
		addBetween(n, element, n.getNext());
	}
	
	public E remove(Position<E> p) throws InvalidPositionException {
		
		Nodo<E> n = checkPosition(p);
		E element = p.element();
		Nodo<E> prev = n.getPrev();
		Nodo<E> next = n.getNext();
		
		// Desenganchar el nodo de la lista
		prev.setNext(next);
		next.setPrev(prev);
		size--;
		
		// Invalidacion del nodo, para evitar posibles errores
		n.setElement(null);
		n.setNext(null);
		n.setPrev(null);
		
		return element;
	}

	public E set(Position<E> p, E element) throws InvalidPositionException {
		
		Nodo<E> n = checkPosition(p);
		E e = p.element();
		n.setElement(element);
		
		return e;
	}

	// Consultas
	public Position<E> first() throws EmptyListException {
		if (this.isEmpty())
			throw new EmptyListException("Lista::first(): Error. Lista vacia.");
		
		return header.getNext();
	}

	public Position<E> last() throws EmptyListException {
		if (this.isEmpty())
			throw new EmptyListException("Lista::last(): Error. Lista vacia.");
		
		return trailer.getPrev();
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		Nodo<E> n = checkPosition(p);
		
		if (n == header.getNext())
			throw new BoundaryViolationException("Lista::prev(p): Error. Posicion fuera de rango.");
		
		return n.getPrev();
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		Nodo<E> n = checkPosition(p);
		
		if (n == trailer.getPrev())
			throw new BoundaryViolationException("Lista::next(p): Error. Posicion fuera de rango.");
		
		return n.getNext();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// Iteradores
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	public Iterable<Position<E>> positions() {
		
		PositionList<Position<E>> iterable = new ListaDoblementeEnlazada<>();
			
		if (this.size() > 0) {
			Nodo<E> n = header.getNext();
			while (n != trailer) {
				iterable.addLast(n);
				n = n.getNext();
			}
		}
		
		return iterable;
	}
} // Fin clase ListaDoblementeEnlazada
