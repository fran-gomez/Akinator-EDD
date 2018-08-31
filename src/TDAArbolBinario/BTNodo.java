package TDAArbolBinario;

import java.io.Serializable;

/**
 * Class BTNodo Nodos enlazados del arbol
 * @author fgvol
 *
 * @param <E> Tipo de los rotulos del arbol
 */
class BTNodo<E> implements BTPosition<E>, Serializable {

	private BTPosition<E> parent;
	private E element;
	private BTPosition<E> left;
	private BTPosition<E> right;
	
	/**
	 * Crea un nodo de rotulo r, sin padre y sin hijos
	 * @param r Rotulo
	 */
	public BTNodo(E r) {
		parent = null;
		element = r;
		left = right = null;
	}

	/**
	 * Crea un nodo con padre p, rotulo r y sin hijos
	 * @param p Padre del nodo creado
	 * @param r Rotulo
	 */
	public BTNodo(BTNodo<E> p, E r) {
		parent = p;
		element = r;
		left = right = null;
	}

	public E element() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public void setParent(BTPosition<E> parent) {
		this.parent = parent;
	}

	public void setLeft(BTPosition<E> left) {
		this.left = left;
	}

	public void setRight(BTPosition<E> right) {
		this.right = right;
	}

	public BTPosition<E> getParent() {
		return parent;
	}

	public BTPosition<E> getLeft() {
		return left;
	}

	public BTPosition<E> getRight() {
		return right;
	}

	public E getElement() {
		return element;
	}
} // Fin clase Nodo
