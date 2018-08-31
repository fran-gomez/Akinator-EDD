package TDAArbolBinario;

import java.util.Iterator;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

/**
 * Class IteradorPreorden Implementa un iterador con recorrido preorden
 * @author fgvol
 *
 * @param <E> Tipo de los rotulos del arbol
 */
public class IteradorPreorden<E> implements Iterator<E> {

	private BinaryTree<E> BT;
	private PositionList<E> rotulos;
	private Position<E> cursor;
	
	public IteradorPreorden(BinaryTree<E> BT) {
		
		try {
			if (BT.isEmpty())
				cursor = null;
			else {
				this.BT = BT;
				rotulos = new ListaDoblementeEnlazada<E>();
				preorden(rotulos, BT.root());
				cursor = rotulos.first();
			}
		
		} catch (EmptyListException | EmptyTreeException e) {
			e.printStackTrace();
		}
	}
	
	private void preorden(PositionList<E> l, Position<E> p) {
		
		try {
			l.addLast(p.element());
			if (BT.hasLeft(p)) preorden(l, BT.left(p));
			if (BT.hasRight(p)) preorden(l, BT.right(p));

		} catch (InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
	}

	public boolean hasNext() {
		return cursor != null;
	}

	public E next() {
		E rotulo = cursor.element();
		
		try {
			cursor = cursor == rotulos.last()? null:rotulos.next(cursor);
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
		return rotulo;
	}
} // Fin clase IteradorPreorden
