package TDAArbolBinario;

import java.util.Iterator;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

/**
 * Class IteradorInorden Implementa un iterador con recorrido inorden
 * @author fgvol
 *
 * @param <E> Tipo de los rotulos del arbol
 */
public class IteradorInorden<E> implements Iterator<E> {

	private BinaryTree<E> BT;
	private PositionList<E> rotulos;
	private Position<E> cursor;
	
	public IteradorInorden(BinaryTree<E> BT) {
		
		try {
		
			if (BT.isEmpty())
				cursor = null;
			else {
				this.BT = BT;
				inorden(rotulos, BT.root());
				cursor = rotulos.first();
			}
		
		} catch (EmptyTreeException | EmptyListException e) {
			e.printStackTrace();
		}
	}
	
	private void inorden(PositionList<E> l, Position<E> p) {
		
		try {
			if (BT.hasLeft(p)) inorden(l, BT.left(p));
			l.addLast(p.element());
			if (BT.hasRight(p)) inorden(l, BT.right(p));
			
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
} // Fin clase IteradorInorden
