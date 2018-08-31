package TDAArbolBinario;

import java.util.Iterator;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

/**
 * Class IteradorPostorden Implementa un iterador con recorrido postorden
 * @author fgvol
 *
 * @param <E> Tipo de los rotulos del arbol
 */
public class IteradorPostorden<E> implements Iterator<E> {

	private BinaryTree<E> BT;
	private PositionList<E> rotulos;
	private Position<E> cursor;
	
	public IteradorPostorden(BinaryTree<E> BT) {
		
		try {
			if (BT.isEmpty())
				cursor = null;
			else {
				this.BT = BT;
				rotulos = new ListaDoblementeEnlazada<E>();
				postorden(rotulos, BT.root());
				cursor = rotulos.first();	
			}
			
		} catch (EmptyListException | EmptyTreeException e) {
			e.printStackTrace();
		}
	}
	
	private void postorden(PositionList<E> l, Position<E> p) {
		
		try {
			if (BT.hasLeft(p)) postorden(l, BT.left(p));
			if (BT.hasRight(p)) postorden(l, BT.right(p));
			l.addLast(p.element());
		
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
} // Fin clase Iterador Postorden
