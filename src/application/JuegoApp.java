package application;

import TDAArbolBinario.*;
import TDALista.*;
import TDAPila.EmptyStackException;
import TDAPila.PilaEnlazada;
import TDAPila.Stack;

import java.io.*;

/**
 * Class JuegoApp Maneja la logica del juego
 * @author bmatus
 *
 */
public class JuegoApp {
	
	//Atributos
	private BinaryTree<String> arbol;
	private Position<String> cursor;
	
	//Constructores
	/**
	 * Inicia la logica del juego
	 */
	public JuegoApp(){
		
		try{
			arbol = new ArbolBinario<String>();
			cursor = arbol.createRoot("Batman"); //Crea el arbol y su raiz
		}
		catch (InvalidOperationException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Carga el archivo referenciado por ruta, e inicia la logica del juego
	 * @param ruta Ruta al archivo
	 * @throws IOException Si el archivo no se encontro
	 */
	public JuegoApp(String ruta) throws IOException{
		
		try {
			File fileName = new File(ruta + ".dat"); //Nombre del archivo
			ObjectInputStream input = new ObjectInputStream( new FileInputStream(fileName) ); //Abrir un manejador de archivo para solo lectura
			arbol = (ArbolBinario<String>) input.readObject();
			cursor = arbol.root();
			input.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Metodos
	/**
	 * Agrega un nuevo superheroe y su caracteristica distintiva
	 * @param superheroe Nuevo superheroe a aprender
	 * @param diferencia Diferencia entre el superheroe nuevo y el que se quiso adivinar 
	 */
	public void aprender(String superheroe, String diferencia){
		try {
			String heroeCursor = arbol.replace(cursor, diferencia); //Convierte el superheroe anterior en una pregunta
			arbol.addLeft(cursor, heroeCursor); //Agrega el superheroe anterior como hijo izquierdo
			arbol.addRight(cursor, superheroe); //Agrega el superheroe nuevo como hijo derecho
			cursor = arbol.root();
		}
		catch (InvalidOperationException e){
			e.printStackTrace();			
		} catch (InvalidPositionException e){
			e.printStackTrace();
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Consulta si quedan preguntas por hacerle al usuario
	 * @return True si quedan preguntas, false en caso contrario
	 */
	public boolean hayPreguntas() {
		try {
			return arbol.isInternal(cursor);
		} catch (InvalidPositionException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Devuelve la proxiam pregunta para hacerle al usuario
	 * @param c Respuesta dada por el usuario (Si, No) o Actual (Requierda por la GUI)
	 * @return Pregunta para el usuario
	 */
	public String proximaPregunta(char c){
		try{
			String respuesta = "";
			
			if (cursor != null) {
				if (c == 's')
					cursor = arbol.right(cursor);
				else if (c == 'n')
					cursor = arbol.left(cursor);
				// Si c == 'a' (Pregunta actual) el cursor no se toca
				
				if (arbol.isInternal(cursor))
					respuesta = "¿" + cursor.element() + "?"; //Si el cursor esta en un nodo interno, hacer la pregunta que contiene
				else
					respuesta = "¿Estas pensando en "+cursor.element()+"?"; //Si el cursor esta en una hoja, pregunta al usuario si esta pensando en el superheroe que almacena				
			}
			
			return respuesta;
		} catch (InvalidPositionException e){
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	/**
	 * Guarda en un achivo la partida terminada
	 * @param ruta Ruta del archivo
	 */
	public void guardar(String ruta){
		try{
			File fileName = new File(ruta + ".dat"); //Crea un objeto de tipo archivo para almacenar el arbol
			ObjectOutputStream output = new ObjectOutputStream( new FileOutputStream( fileName ) );
			output.writeObject(arbol);
			output.flush();
			output.close();
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Consulta la altura del arbol 
	 * @return Altura el arbol 
	 */
	public int alturaArbol(){
		int h = 0;
		try{
			for (Position<String> v : arbol.positions())
				if (arbol.isExternal(v) ) h= Math.max(h, depth(v) );
			return h;
		}
		catch (InvalidPositionException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Consulta cuantos superheroes hay en el arbol
	 * @return Cantidad de superheroes
	 */
	public int cantSuperheroes(){
		int cant=0;
		try{
			for (Position<String> v : arbol.positions())
				if (arbol.isExternal(v))
					cant++;
		}
		catch (InvalidPositionException e){
			e.printStackTrace();;
			return 0;
		}
		
		return cant;
	}
	
	/**
	 * Consulta cuantas preguntas hay en el arbol
	 * @return Cantidad de preguntas
	 */
	public int cantPreguntas(){
		int cant=0;
		
		try{
			for (Position<String> v : arbol.positions())
				if (arbol.isInternal(v)) 
					cant++;
		}
		catch (InvalidPositionException e){
			System.out.println("Esta excepcion no tendria que ocurrir.");
		}
		
		return cant;
	}
	
	/**
	 * Devuelve una lista con los nodos internos (Preguntas) del arbol
	 * @return Lista de posiciones
	 */
	public PositionList<Position<String>> listaNodosInternos(){
		PositionList<Position<String>> lista = new ListaDoblementeEnlazada<Position<String>>();
		try{
			for (Position<String> v : arbol.positions())
				if (arbol.isInternal(v))
					lista.addLast(v);
			return lista;
		}
		catch (InvalidPositionException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Calcula la profundidad de un nodo en el arbol binario
	 * @param v Posicion cuya profundidad se va a calcular
	 * @return Profundidad del nodo
	 */
	private int depth(Position<String> v ) {
		int resp;
		try{
			if (arbol.isRoot(v) ) 
				resp = 0;
			else
				resp = 1 + depth( arbol.parent( v ) );
			return resp;
		}
		catch (InvalidPositionException e){
			e.printStackTrace();;
			return 0;
		}
		catch (BoundaryViolationException m){
			System.out.println(m.getMessage());
			return 0;
		}
	} 
	
	/**
	 * Elimina todo el subarbol con raiz de rotulo r
	 * @param r Rotulo a eliminar
	 */
	public void eliminar(String r) {

		try {
			Position<String> p = buscarPosicion(r, arbol.root());
			
			if (p != null) {
				
				if (p != arbol.root() && arbol.isInternal(p)) {
					Position<String> padre = arbol.parent(p);
					String rotulo = buscarSucesor(p);

					eliminarPostorden(p);

					if (!arbol.hasLeft(padre))
						arbol.addLeft(padre, rotulo);
					else
						arbol.addRight(padre, rotulo);
				} else
					System.out.println("Error. No se puede eliminar la raiz o un nodo externo.");
			} else
				System.out.println("Error. Posicion no encontrada.");
		} catch (EmptyTreeException | InvalidPositionException | BoundaryViolationException | InvalidOperationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Busca el rotulo por el cual reemplazar al nodo que sera eliminado
	 * @param p Posicion del nodo a eliminar
	 * @return Rotulo del futuro sucesor
	 */
	private String buscarSucesor(Position<String> p) {
		try {
			while (arbol.isInternal(p))
				p = arbol.left(p);
			
			return p.element();
		} catch (InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * Busca la posicion de rotulo r
	 * @param r Rotulo a buscar
	 * @param raiz Posicion actual
	 * @return Posicion de rotulo r
	 */
	private Position<String> buscarPosicion(String rotulo, Position<String> raiz) {
		
		Position<String> p = null;
		
		try {
			if (raiz.element().equals(rotulo))
				p = raiz;
			else {
				if (arbol.hasLeft(raiz))
					p = buscarPosicion(rotulo, arbol.left(raiz));
				if (p == null && arbol.hasRight(raiz))
					p = buscarPosicion(rotulo, arbol.right(raiz));
			}
		} catch (InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	/**
	 * Elimina a los hijos de p, y luego a p
	 * @param p Posicion a eliminar
	 */
	private void eliminarPostorden(Position<String> p) {
		
		try {
			if (arbol.hasLeft(p))
				eliminarPostorden(arbol.left(p));
			if (arbol.hasRight(p))
				eliminarPostorden(arbol.right(p));

			arbol.remove(p);
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			e.printStackTrace();
		} catch (InvalidOperationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reinicia el juego
	 */
	public void reiniciar() {
		try {
			
			arbol = new ArbolBinario<String>();
			arbol.createRoot("Batman");
			cursor = arbol.root();
			
		} catch (InvalidOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Consulta el nombre del ultimo superheroe
	 * @return Nombre de superheroe
	 */
	public String ultimoSuperHeroe() {
		
		String superHeroe = "";
		
		try {
			if (arbol.isExternal(cursor))
				superHeroe = cursor.element();
				
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return superHeroe;
	}
	
	/**
	 * Arma la lista de superheroes y sus caracteristicas
	 * @return Coleccion iterable de superheroes
	 */
	public Iterable<String> descripcion() {
		
		PositionList<String> superHeroes = new ListaDoblementeEnlazada<String>();
		String caracteristicas;
		
		try {
			for (Position<String> p: superHeroes()) {
				Stack<String> pilaCaract = armarDescripcion(p);
				
				caracteristicas = p.element() + " es un superheroe que ";
				while (!pilaCaract.isEmpty())
					caracteristicas += pilaCaract.pop();
				
				superHeroes.addLast(caracteristicas);
			}
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
		
		return superHeroes;
	}

	/**
	 * Devuelve una lista conteniendo el nombre de los superheroes conocidos
	 * @return Coleccion iterable con las posiciones de los superheroes
	 */
	private Iterable<Position<String>> superHeroes() {
		
		PositionList<Position<String>> superheroes = new ListaDoblementeEnlazada<Position<String>>();
		
		try {
			for (Position<String> p: arbol.positions())
				if (arbol.isExternal(p))
					superheroes.addLast(p);
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return superheroes;
	}
	
	/**
	 * Devuelve una pila de caracteristicas (De superheroes) 
	 * @return Pila de caracteristicas
	 */
	private Stack<String> armarDescripcion(Position<String> pos) {
		
		Stack<String> pila = new PilaEnlazada<String>();
		String carac = "";
		
		try {
			if (!arbol.isRoot(pos)) {
				
				Position<String> padre = arbol.parent(pos);
				Position<String> p = pos;
				while (padre != null) {
					if (p == arbol.left(padre))
						carac = ", no " + padre.element();
					else
						carac = ", " + padre.element();
					pila.push(carac.toLowerCase());

					p = padre;
					padre = arbol.isRoot(padre)? null:arbol.parent(padre);
				}
			}
		} catch (InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
		return pila;
	}
}
