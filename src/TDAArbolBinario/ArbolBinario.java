package TDAArbolBinario;

import java.io.Serializable;
import java.util.Iterator;

import TDALista.BoundaryViolationException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

/**
 * Class ArbolBinario Arbol Binario
 * @author fgvol
 *
 * @param <E> Tipo de los rotulos almacenados en el arbol
 */
public class ArbolBinario<E> implements BinaryTree<E>, Serializable {

	// Atributos
	private BTNodo<E> root;
	private int size;
	private Iterator<E> it;
	
	// Constructores
	/**
	 * Crea un arbol binario, inicialmente vacio y con el iterador preorden por defecto
	 */
	public ArbolBinario() {
		root = null;
		size = 0;
	}
	
	/**
	 * Crea un arbol binario, inicialmente vacio y con el iterador especificado por el usuario
	 * @param it Iterador especificado por el usuario
	 */
	public ArbolBinario(Iterator<E> it) {
		this();
		this.it = it;
	}
	
	// Metodos privados
	/**
	 * Chequea que la posicion p sea valida, y devuelve el nodo que direcciona
	 * @param p Posicion a chequear
	 * @return Nodo correspondiente a la posicion
	 * @throws InvalidPositionException Si la posicion es nula, fue previamente eliminada, o no corresponde a una posicion de arbol binario
	 */
	private BTNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		
		try {
			
			if (p == null)
				throw new InvalidPositionException("ArbolBinario::checkPosition(p): Error. Posicion nula.");
			
			if (p.element() == null)
				throw new InvalidPositionException("ArbolBinario::checkPosition(p): Error. La posicion fue previamente eliminada");
			
			return (BTNodo<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("ArbolBinario::checkPosition(p): Error. La posicion no corresponde a una posicion de arbol binario.");
		}
	}
	
	// Consultas
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Position<E> root() throws EmptyTreeException {
		if (root == null)
			throw new EmptyTreeException("ArbolBinario::root(): Error. Arbol vacio.");
		
		return root;
	}

	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		
		BTNodo<E> n = checkPosition(v);
		
		if (n == root)
			throw new BoundaryViolationException("ArbolBinario::parent(v): Error. La raiz no tiene padre.");
		
		return n.getParent();
	}

	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {

		BTNodo<E> n = checkPosition(v);
		PositionList<Position<E>> children = new ListaDoblementeEnlazada<>();
		
		if (hasLeft(v)) children.addLast(n.getLeft());
		if (hasRight(v)) children.addLast(n.getRight());
		
		return children;
	}

	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		
		if (!hasLeft(v))
			throw new BoundaryViolationException("ArbolBinario::left(v): Error. No existe el hijo izquierdo.");
		
		return checkPosition(v).getLeft();
	}

	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		
		if (!hasRight(v))
			throw new BoundaryViolationException("ArbolBinario::right(v): Error. No existe el hijo derecho.");
		
		return checkPosition(v).getRight();
	}

	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		return (hasLeft(v) || hasRight(v));
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return !hasLeft(v) && !hasRight(v);
	}

	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		return checkPosition(v) == root;
	}

	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		return (checkPosition(v).getLeft() != null);
	}

	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		return (checkPosition(v).getRight() != null);
	}

	// Comandos
	public Position<E> createRoot(E r) throws InvalidOperationException {

		if (root != null)
			throw new InvalidOperationException("ArbolBinario::createRoot(r): Error. La raiz ya fue creada.");
		
		root = new BTNodo<E>(r);
		size = 1;
		
		return root;
	}

	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		
		if (hasLeft(v))
			throw new InvalidOperationException("ArbolBinario::addLeft(v, r): Error. Ya existe el hijo izquierdo.");
		
		BTNodo<E> padre = checkPosition(v);
		BTNodo<E> nuevo = new BTNodo<E>(padre, r);
		
		padre.setLeft(nuevo);
		size++;
		
		return nuevo;
	}

	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		
		if (hasRight(v))
			throw new InvalidOperationException("ArbolBinario::addRight(v, r): Error. Ya existe el hijo derecho.");
		
		BTNodo<E> padre = checkPosition(v);
		BTNodo<E> nuevo = new BTNodo<E>(padre, r);
		
		padre.setRight(nuevo);
		size++;
		
		return nuevo;
	}

	// @author bmatus
	public E remove(Position<E> v) throws InvalidPositionException {
		BTPosition<E> vv = checkPosition(v);
		BTPosition<E> leftPos = vv.getLeft();
		BTPosition<E> rightPos = vv.getRight();
		
		if (leftPos != null && rightPos != null)
			throw new InvalidPositionException("BTree::remove(): No se puede remover nodo con dos hijos");
		
		BTPosition<E> ww;
		if (leftPos != null)
			ww= leftPos;
		else if (rightPos != null)
			ww=rightPos;
		else
			ww=null;
		
		if (vv == root) {
			if (ww != null) ww.setParent(null);
			root = checkPosition(ww);
		} else {
			BTPosition<E> uu = vv.getParent();
			if (vv == uu.getLeft()) uu.setLeft(ww);
			else uu.setRight(ww);
			if (ww != null) ww.setParent(uu);
		}
		size--;
		
		return v.element();
	}

	// TODO Ver como invalidar T1 y T2 para que no puedan ser usados como arboles independientes de nuevo
	public void Attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		
		if (!isExternal(r))
			throw new InvalidPositionException("ArbolBinario::Attach(r, T1, T2): Error. La posicion no corresponde a una hoja.");
		
		try {
			BTNodo<E> raiz = checkPosition(r);
			
			// Agregar el subarbol T1 como hijo izquierdo de r
			if (!T1.isEmpty()) {
				BTNodo<E> izquierdo = checkPosition(T1.root());
				izquierdo.setParent(raiz);
				raiz.setLeft(izquierdo);
			}
			// Agregar el subarbol T2 como hijo derecho de r
			if (!T2.isEmpty()) {
				BTNodo<E> derecho = checkPosition(T2.root());
				derecho.setParent(raiz);
				raiz.setRight(derecho);
			}
			
			// Actualizar el tamaño del arbol
			size += T1.size() + T2.size();
			
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}

	}

	public E replace(Position<E> v, E e) throws InvalidPositionException {
		
		BTNodo<E> n = checkPosition(v);
		E anterior = n.getElement();
		n.setElement(e);
		
		return anterior;
	}
	
	// Iteradores
	public Iterator<E> iterator() {
		return it;
	}

	private void preOrder(PositionList<Position<E>> l, Position<E> p) throws InvalidPositionException {
		try {
			l.addLast(p);
			if (hasLeft(p)) preOrder(l, left(p));
			if (hasRight(p)) preOrder(l, right(p));
		} catch (BoundaryViolationException e) {
			e.printStackTrace();
		}
	}
	
	public Iterable<Position<E>> positions() {
		
		PositionList<Position<E>> positions = new ListaDoblementeEnlazada<Position<E>>();
		
		try {
			if (!this.isEmpty())
				preOrder(positions, root);
			
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		
		return positions;
	}

} // Fin clase ArbolBinario
