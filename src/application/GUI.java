package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import TDALista.Position;
import TDAPila.EmptyStackException;
import TDAPila.PilaEnlazada;
import TDAPila.Stack;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * Class GUI Maneja la interfaz grafica del juego
 * @author fgvol
 */
public class GUI {

	// No tengo idea que son estos atributos, pero si los saco la GUI no anda
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    
    // Componentes de la pantalla inicial
    @FXML
    private TextField textFieldRuta;
    @FXML
    private Label labelNombre;
    @FXML
    private Button botonEmpezar;
    
    // Componentes de la pantalla de juego
    @FXML
    private Label labelPregunta;
    @FXML
    private Button botonSi;
    @FXML
    private Button botonNo;
    @FXML
    private Button botonInformacion;
    @FXML
    private Button botonOlvidar;
    @FXML
    private Button botonReiniciar;
    @FXML
    private Button botonMostrarSuperheroe;
    @FXML
    private Label labelInformacion;
    @FXML
    private Button botonMostrarPreguntas;
    @FXML
    private Button botonGuardar;

    @FXML
    private Button botonEliminar;
    @FXML
    private TextField textFieldEliminar;
    @FXML
    private Button botonVolverJuego;
    
    // Pantalla de informacion
    @FXML
    private ListView<String> lista;
    
    // Componentes de la pantalla de aprendizaje
    @FXML
    private Label labelSuperheroe;
    @FXML
    private TextField textFieldSuperheroe;
    @FXML
    private Button botonOk;
    @FXML
    private Label labelDiferencia;
    @FXML
    private TextField textFieldDiferencia;
    @FXML
    private Button botonAprender;
    
    // Barra menu
    @FXML
    private MenuBar menu;
    @FXML
    private Menu juego;
    @FXML
    private MenuItem cargar;
    @FXML
    private MenuItem reiniciar;
    @FXML
    private MenuItem olvidar;
    @FXML
    private MenuItem salir;
    @FXML
    private Menu informacion;
    @FXML
    private MenuItem conocimiento;
    @FXML
    private MenuItem preguntas;
    @FXML
    private MenuItem superheroes;
    @FXML
    private Menu ayuda;
    @FXML
    private MenuItem manual;
    @FXML
    private MenuItem about;
    
    // Controlador logico del juego
    private JuegoApp logica;
	/**
	 * Creado por JavaFX
	 */
    @FXML
    void initialize() {
        assert botonOk != null : "fx:id=\"botonOk\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert reiniciar != null : "fx:id=\"reiniciar\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert conocimiento != null : "fx:id=\"conocimiento\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert salir != null : "fx:id=\"salir\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert botonAprender != null : "fx:id=\"botonAprender\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert olvidar != null : "fx:id=\"olvidar\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert manual != null : "fx:id=\"manual\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert labelSuperheroe != null : "fx:id=\"labelSuperheroe\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert informacion != null : "fx:id=\"informacion\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert textFieldRuta != null : "fx:id=\"textFieldRuta\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert botonVolverJuego != null : "fx:id=\"botonVolverJuego\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert labelDiferencia != null : "fx:id=\"labelDiferencia\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert lista != null : "fx:id=\"lista\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert botonNo != null : "fx:id=\"botonNo\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert labelPregunta != null : "fx:id=\"labelPregunta\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert textFieldDiferencia != null : "fx:id=\"textFieldDiferencia\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert labelInformacion != null : "fx:id=\"labelInformacion\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert botonEliminar != null : "fx:id=\"botonEliminar\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert juego != null : "fx:id=\"juego\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert botonEmpezar != null : "fx:id=\"botonEmpezar\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert textFieldSuperheroe != null : "fx:id=\"textFieldSuperheroe\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert superheroes != null : "fx:id=\"superheroes\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert ayuda != null : "fx:id=\"ayuda\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert cargar != null : "fx:id=\"cargar\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert preguntas != null : "fx:id=\"preguntas\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert botonGuardar != null : "fx:id=\"botonGuardar\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert textFieldEliminar != null : "fx:id=\"textFieldEliminar\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert labelNombre != null : "fx:id=\"labelNombre\" was not injected: check your FXML file 'Akinator.fxml'.";
        assert botonSi != null : "fx:id=\"botonSi\" was not injected: check your FXML file 'Akinator.fxml'.";
 
    	
        System.out.println("Iniciando akinator...");
        about.setDisable(true);
    }
    
    /**
     * Muestra u oculta la pantalla de inicio
     * @param visible True para mostrar la pantalla, false en caso contrario
     */
    private void pantallaInicio(boolean visible) {
    	
    	labelNombre.setVisible(visible);
    	textFieldRuta.setVisible(visible);
    	botonEmpezar.setVisible(visible);
    	
    	juego.setDisable(visible);
    	informacion.setDisable(visible);
    }
    
    /**
     * Muestra u oculta la pantalla de victoria
     * @param visible True para mostrar la pantalla, false en caso contrario
     */
    private void pantallaGane(boolean visible) {
		botonGuardar.setVisible(visible);
		textFieldRuta.setVisible(visible);
	}

    /**
     * Muestra u oculta la pantalla de aprendizaje
     * @param visible True para mostrar la pantalla, false en caso contrario
     */
    private void pantallaAprendizaje(boolean visible) {
		
    	labelSuperheroe.setVisible(visible);
        textFieldSuperheroe.setVisible(visible);
        botonOk.setVisible(visible);

        // Ocultar la segunda parte de la pantalla. Es mostrada cuando se oprime el botonOk
        labelDiferencia.setVisible(false);
        textFieldDiferencia.setVisible(false);
        botonAprender.setVisible(false);
	}

    /**
     * Muestra u oculta la pantalla del juego
     * @param visible True para mostrar la pantalla, false en caso contrario
     */
	private void pantallaJuego(boolean visible) {
		
		labelPregunta.setVisible(visible);
		botonSi.setVisible(visible);
		botonNo.setVisible(visible);
    }
	
    /**
     * Inicia la logica del juego, y configura la interface
     * @param event Requerido por Java FX
     */
    @FXML
    void botonEmpezar(ActionEvent event) {
    	
    	try {
    		if (textFieldRuta.getText().length() > 0)	
    			logica = new JuegoApp(textFieldRuta.getText());
    		else
    			logica = new JuegoApp();

    		pantallaInicio(false);
    		juego.setDisable(false);
    		informacion.setDisable(false);
    		pantallaJuego(true);
    		textFieldRuta.setText("");
    		
    		labelPregunta.setText(logica.proximaPregunta('a'));
    	} catch (IOException e) {
    		textFieldRuta.setText("");
    		textFieldRuta.setPromptText("Error. Archivo no encontrado.");
    		e.printStackTrace();
    	}
    }
    
    /**
     * Reaccion ante la respuesta afirmativa
     * @param event Requierdo por JavaFX
     */
    @FXML
    void botonSi(ActionEvent event) {

    	if (logica.hayPreguntas())
    		labelPregunta.setText(logica.proximaPregunta('s'));
    	else {
    		labelPregunta.setText("Adivine!");
    		botonSi.setVisible(false);
    		botonNo.setVisible(false);
    		
    		pantallaGane(true);
    	}
    }
    
    /**
     * Reaccion ante la respuesta negativa
     * @param event Requerido por JavaFX
     */
    @FXML
    void botonNo(ActionEvent event) {
    	
    	if (!logica.hayPreguntas()) {
    		pantallaJuego(false);
    		
    		// Desactivar compronentes del menu
    		conocimiento.setDisable(true);
    		preguntas.setDisable(true);
    		superheroes.setDisable(true);
    		cargar.setDisable(true);
    		olvidar.setDisable(true);
    		reiniciar.setDisable(true);
    		
    		pantallaAprendizaje(true);
    	} else
    		labelPregunta.setText(logica.proximaPregunta('n'));
    }
    
    /**
     * Guarda la ultima partida jugada
     * @param event Requerido por JavaFX
     */
    @FXML
    void botonGuardar(ActionEvent event) {
    	
    	String nombre = textFieldRuta.getText();
    	
    	if (nombre.length() > 0) {
    		logica.guardar(nombre);
    		
    		botonGuardar.setVisible(false);
    		textFieldRuta.setText("");
    		textFieldRuta.setVisible(false);
    		
    		pantallaJuego(false);
    		pantallaInicio(true);
    	} else
    		textFieldRuta.setPromptText("Por favor, ingrese el nombre del archivo...");
    }

    /**
     * Registra el nombre de un nuevo superheroe, y se prepara para recibir la diferencia con el ultimo consultado
     * @param event Requerido por Java FX
     */
    @FXML
    void botonOk(ActionEvent event) {
    	
    	String nuevoSuperheroe = textFieldSuperheroe.getText();
    	String ultimoSuperheroe = logica.ultimoSuperHeroe();
    	
    	if (nuevoSuperheroe.length() > 0) {
    		labelDiferencia.setVisible(true);
    		labelDiferencia.setText("¿Cual es la diferencia entre " + nuevoSuperheroe + " y " + ultimoSuperheroe + "?");
    		textFieldDiferencia.setVisible(true);
    		botonAprender.setVisible(true);
    	} else
    		textFieldSuperheroe.setPromptText("Por favor, ingrese el nombre del superheroe");
    }
    
    /**
     * Aprende el nombre y caracteristica de un nuevo superheroe
     * @param event Requerido por Java FX
     */
    @FXML
    void botonAprender(ActionEvent event) {
    	
    	String diferencia = textFieldDiferencia.getText();
    	String nuevoSuperheroe = textFieldSuperheroe.getText();
    	
    	if (diferencia.length() > 0) {
    		logica.aprender(nuevoSuperheroe, diferencia);
    		
    		textFieldDiferencia.setText("");
    		textFieldSuperheroe.setText("");
    		
    		// Activar los componentes del menu
    		conocimiento.setDisable(false);
    		preguntas.setDisable(false);
    		superheroes.setDisable(false);
    		juego.setDisable(false);
    		cargar.setDisable(false);
    		olvidar.setDisable(false);
    		reiniciar.setDisable(false);
    		
    		pantallaInicio(false);
    		pantallaAprendizaje(false);
    		pantallaJuego(true);
    		
    		labelPregunta.setText(logica.proximaPregunta('a'));
    	} else
    		textFieldDiferencia.setPromptText("Por favor, ingrese la diferencia entre los superheroes");
    }

    /**
     * Vuelta a la pantalla del juego
     * @param event Requerido por Java FX
     */
    @FXML
    void botonVolver(ActionEvent event) {
    	
    	botonVolverJuego.setVisible(false);
    	
    	if (labelInformacion.isVisible())
    		labelInformacion.setVisible(false);
    	
    	// Ocultar el panel auxiliar y sus componentes
    	lista.setVisible(false);
    	
    	pantallaJuego(true);
    }
    

    /**
     * Elimina una parte del conocimiento del juego
     * @param event Requerido por Java FX
     */
    @FXML
    void botonEliminar(ActionEvent event) {
    	
    	String rotuloEliminar = textFieldEliminar.getText();
    	
    	if (rotuloEliminar.length() > 0) {
    		logica.eliminar(rotuloEliminar);
    		
    		textFieldEliminar.setVisible(false);
    		textFieldEliminar.setText("");
    		botonEliminar.setVisible(false);
    		
    		pantallaJuego(true);
    	} else
    		textFieldEliminar.setPromptText("Por favor, indique que desea eliminar...");
    }
    
    /**
     * Reinicia el juego, olvidando todo lo aprendido
     * @param event Requerido por Java FX
     */
    @FXML
    void menuReiniciar(ActionEvent event) {
    	logica.reiniciar();
    	
    	pantallaGane(false);
    	pantallaJuego(false);
    	pantallaInicio(true);
    	
    	textFieldRuta.setText("");
    	labelPregunta.setText(logica.proximaPregunta('a'));
    }

    /**
     * Muestra todos los superheroes conocidos por el juego
     * @param event Requerido por Java FX
     */
    @FXML
    void menuSuperheroes(ActionEvent event) {
    	
    	ObservableList<String> items = FXCollections.observableArrayList();
    	Iterable<String> listaSuperHeroes = logica.descripcion();
    	
    	// Armar los strings con cada superheroe y su descripcion
    	for (String s: listaSuperHeroes)
    		items.add(s);
    	
    	lista.setItems(items);
    	lista.setVisible(true);
    	
    	pantallaJuego(false);
    	botonVolverJuego.setVisible(true);
    }
    
    /**
     * Muestra todas las caracteristicas conocidas por el juego
     * @param event Requerido por Java FX
     */
    @FXML
    void menuPreguntas(ActionEvent event) {
    	
    	try {
    		ObservableList<String> items = FXCollections.observableArrayList();
        	Iterable<Position<String>> listaPreguntas = logica.listaNodosInternos();
        	Stack<String> pilaPreguntas = new PilaEnlazada<String>();

    		for (Position<String> pregunta: listaPreguntas)
    			pilaPreguntas.push("¿" + pregunta.element() + "?");
    		
    		
    		while (!pilaPreguntas.isEmpty())
    			items.add(pilaPreguntas.pop());

    		pantallaJuego(false);
    		
    		lista.setItems(items);
    		lista.setVisible(true);
    		botonVolverJuego.setVisible(true);
    		
    	} catch (EmptyStackException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Muestra la informacion del arbol de conocimiento
     * @param event Requerido por Java FX
     */
    @FXML
    void menuConocimiento(ActionEvent event) {
    	
    	String altura = Integer.toString(logica.alturaArbol());
    	String cantSuperheroes = Integer.toString(logica.cantSuperheroes());
    	String cantPreguntas = Integer.toString(logica.cantPreguntas());
    	
    	pantallaJuego(false);
    	
    	if (lista.isVisible())
    		lista.setVisible(false);
    	
    	labelInformacion.setVisible(true);
    	labelInformacion.setText("Altura del arbol: " + altura + "\n" + 
    								"Cantidad de superheroes: " + cantSuperheroes + "\n" +
    								"Cantidad de preguntas: " + cantPreguntas);
    	botonVolverJuego.setVisible(true);
    }
    
    /**
     * Configura la interface para olvida una parte del arbol de conocimiento
     * @param event Requerido por Java FX
     */
    @FXML
    void menuOlvidar(ActionEvent event) {
    	
    	pantallaInicio(false);
    	pantallaJuego(false);
    	pantallaAprendizaje(false);
    	
    	textFieldEliminar.setVisible(true);
		botonEliminar.setVisible(true);

    }
    
    /**
     * Sale del juego
     * @param event Requerido por JavaFX
     */
    @FXML
    void menuSalir(ActionEvent event) {
    	Platform.exit();
    }
    
    /**
     * Abre el manual del juego
     * @param event Requerido por JavaFX
     */
    @FXML
    void menuManual(ActionEvent event) {
    	try {
    		File f = new File("Akinator.pdf");
    		Desktop.getDesktop().open(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Configura la interfaz para cargar una nueva partida
     * @param event Requerido por JavaFX
     */
    @FXML
    void menuCargar(ActionEvent event) {
    	
    	pantallaJuego(false);
    	
    	textFieldRuta.setVisible(true);
    	botonEmpezar.setVisible(true);
    }
    
    /**
     * Muestra la informacion referente al proyecto
     * @param event Requerido por JavaFX
     * TODO No anda. No se muestra nada en pantalla
     */
    @FXML
    void menuAbout(ActionEvent event) {
    	
    	pantallaInicio(false);
    	pantallaJuego(false);
    	pantallaAprendizaje(false);
    	    	
    	Button volver = new Button("Volver");
    	volver.setOnAction(e -> menuReiniciar(event));
    	volver.setLayoutX(252.0);
    	volver.setLayoutY(336.0);
    	volver.setVisible(true);
    	
    	Label info = new Label();
    	info.setText("Akinator v0.1 \n" + 
				"Autores: Matus Bernardo, Gomez Francisco. \n" + 
				"Para la catedra de Estructuras de Datos 2017.");
    	info.setLayoutX(0);
    	info.setLayoutY(0);
    	info.setScaleX(600.0);
    	info.setVisible(true);
    	info.setScaleY(300.0);
    }
}
