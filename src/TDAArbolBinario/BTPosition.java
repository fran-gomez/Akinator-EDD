package TDAArbolBinario;

import TDALista.Position;

/**
 * Interface BTPosition Representa la posicion dentro de un arbol binario
 * @author fgvol
 *
 * @param <E> Tipo de los rotulos almacenados en el arbol
 */
public interface BTPosition<E> extends Position<E> {

	/**
	 * Actualiza el elemento almacenado en esta posicion
	 * @param element Nuevo elemento
	 */
	void setElement(E element);
	
	/**
	 * Actualiza la referencia al padre
	 * @param parent Nuevo padre
	 */
	void setParent(BTPosition<E> parent);
	
	/**
	 * Actualiza la referencia al hijo izquierdo
	 * @param left Nuevo hijo izquierdo
	 */
	void setLeft(BTPosition<E> left);
	
	/**
	 * Actualiza la referencia al hijo derecho
	 * @param right Nuevo hijo derecho
	 */
	void setRight(BTPosition<E> right);
	
	/**
	 * Consulta el elemento almacenado en esta posicion
	 * @return Elemento almacenado en esta posicion
	 */
	E getElement();
	
	/**
	 * Consulta la posicion del padre
	 * @return Posicion del padre
	 */
	BTPosition<E> getParent();
	
	/**
	 * Consulta la posicion del hijo izquierdo
	 * @return Posicion del hijo izquierdo
	 */
	BTPosition<E> getLeft();
	
	/**
	 * Consulta la posicion del hijo derecho
	 * @return Posicion del hijo derecho
	 */
	BTPosition<E> getRight();
}
