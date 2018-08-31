package TDALista;

import java.util.Iterator;

/**
 * Class ElementIterator Iterador con los elementos de la lista
 * @author fgvol
 *
 * @param <E> Tipo de los elementos de la lista
 */
class ElementIterator<E> implements Iterator<E> {

	private PositionList<E> list; // Lista de elementos
	private Position<E> cursor;   // Posicion de lectura en la lista
	
	public ElementIterator(PositionList<E> list) {
		try {
			
			this.list = list;
			cursor = list.isEmpty()? null:list.first();
			
		} catch (EmptyListException e) {
			System.err.println("Esto no tendria que ocurrir.");
		}
	}

	public boolean hasNext() {
		return cursor != null;
	}

	public E next() {
		
		if (cursor != null) {
			E elem = cursor.element();
		
			try {
				cursor = (cursor == list.last())? null:list.next(cursor);
			} catch (BoundaryViolationException | InvalidPositionException | EmptyListException e) {
				System.err.println("Esto no tendria que ocurrir.");
			}
		
			return elem;
		} else
			return null;
	}
} // Fin clase ElementIterator