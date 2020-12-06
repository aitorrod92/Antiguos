package calculadoraglobal;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * Clase controlador de la vista
 *
 * @author Setito
 */
public class FXMLDocumentController implements Initializable {

    /**
     * GridPane que ejerce de padre y base del resto de elementos
     */
    @FXML
    private GridPane PanelReja;

    /**
     * Etiqueta que muestra las operaciones y resultados
     */
    private Label EtiquetaResultados;
    
    /**
     * El contenido de texto de EtiquetaResultados
     */
    private String TextoResultados;

    /**
     * Campo de texto sobre el que el usuario introduce los números
     */
    private TextField VisorEscritura;
    
    /**
     * El contenido de texto de VisorEscritura
     */
    private String TextoVE;

    /**
     * El valor numérico del primer operando
     */
    private double PrimeraParte = 0;
    
    /**
     * El valor numérico del segundo operando
     */
    private double SegundaParte = 0;
    
    /**
     * El resultado de la última operación realizada
     */
    private double Resultado = 0;
    
    /**
     * Indica si la introducción del primer operando está por finalizar
     */
    private Boolean PrimerOperando = false;
    
    /**
     * Indica si se ha iniciado la introducción del segundo operando
     */
    private Boolean InicioSegundaParte = false;

    /**
     * RegEx que registra un número positivo
     */
    private final String NUMERO_POSITIVO = "[1-9]+.?\\d*";
    
    /**
     * RegEx que registra un número positivo que empieza por cero
     */
    private final String NUMERO_POSITIVO_EMPIEZA_POR_CERO = "0{1}.{1}\\d*";
    
    /**
     * RegEx que registra un numero negativo
     */
    private final String NUMERO_NEGATIVO = "^-[1-9]+.?\\d*";
    
    /**
     * RegEx que registra un número negativo que empieza por cero
     */
    private final String NUMERO_NEGATIVO_EMPIEZA_POR_CERO = "^-0{1}.{1}\\d*";
    
    /**
     * Regex con los símbolos cuya introducción está permitida
     */
    private final String SIMBOLOS = "[\\+X/*-]";
    private Button BotónIgual;

    /**
     * Método que se ejecuta al iniciar la interfaz
     *
     * @param url La ubicación utilizada para resolver rutas relativas para el objeto
     * raíz, o null si no se conoce la ubicación.
     * @param rb Los recursos utlizados para localizar el objeto raíz, o null si el objeto
     * raíz no se localizó
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CompletarInterfaz();
    }

    /**
     * Define los elementos de la interfaz que se crean programáticamente
     */
    public void CompletarInterfaz() {
        // Modificamos los bordes de las celdas
        PanelReja.setPadding(new Insets(10, 10, 10, 10));

        // Configura y añade el encabezado
        Label Etiqueta = new Label("Calculadora de DI");
        Etiqueta.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold");
        Etiqueta.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        PanelReja.add(Etiqueta, 1, 0, 4, 1);

        // Configura y añade el visor de resultados
        EtiquetaResultados = new Label();
        EtiquetaResultados.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        EtiquetaResultados.setAlignment(Pos.CENTER_RIGHT);
        PanelReja.add(EtiquetaResultados, 0, 1, 4, 1);

        // Configura y añade el visor de escritura y lo selecciona
        VisorEscritura = new TextField("0");
        VisorEscritura.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VisorEscritura.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
        VisorEscritura.setAlignment(Pos.CENTER_RIGHT);
        PanelReja.add(VisorEscritura, 0, 2, 4, 1);
        VisorEscritura.setMinHeight(0);
        ListenerVisorEscritura();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                VisorEscritura.requestFocus();
            }
        });
        ;

        // Configura y añade el botón "="
        BotónIgual = new Button();
        BotónIgual.setText("=");
        BotónIgual.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        PanelReja.add(BotónIgual, 3, 6, 4, 7);
        BotónIgual.setDisable(true);
        BotónIgual.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EscribirBotones(event);
            }
        });
    }

    /**
     * Se invoca si se pulsa una tecla no numérica válida con el teclado,
     * pasando como parámetro su símbolo a "Escribir()", borrándola del visor de
     * escritura y seleccionando el String restante en el mismo.
     *
     * @param tecla Tecla no numérica válida
     */
    public void EscribirTeclado(String tecla) {
        Escribir(tecla);
        String StringSinSigno = VisorEscritura.getText().substring(0,
                VisorEscritura.getText().length());
        VisorEscritura.setText(StringSinSigno);
        if (!tecla.equals(".")) {
            VisorEscritura.requestFocus();
            VisorEscritura.selectAll();
        }
    }

    /**
     * Se invoca si se pulsa un botón y pasa como parámetro su contenido a
     * "Escribir".
     *
     * @param event
     */
    @FXML
    public void EscribirBotones(ActionEvent event) {
        Button Botón = (Button) event.getSource();
        String TextoBotón = Botón.getText();
        Escribir(TextoBotón);
    }

    /**
     * Se encarga de toda la lógica de activación/desactivación de botones,
     * escritura en el visor de escritura/etiqueta de resultados, etc.
     *
     * @param input Tecla o botón pulsado
     */
    public void Escribir(String input) {
        TextoVE = VisorEscritura.getText();
        TextoResultados = EtiquetaResultados.getText();

        if (input.matches("\\d+")) {
            if (TextoVE.matches(NUMERO_POSITIVO) 
                    || TextoVE.matches(NUMERO_POSITIVO_EMPIEZA_POR_CERO)
                    || TextoVE.matches(NUMERO_NEGATIVO) 
                    || TextoVE.matches(NUMERO_NEGATIVO_EMPIEZA_POR_CERO)) {
                if (!PrimerOperando) {
                    VisorEscritura.setText(TextoVE.concat(input));
                } else {
                    if (InicioSegundaParte) {
                        VisorEscritura.setText(input);
                        InicioSegundaParte = false;
                    } else {
                        VisorEscritura.setText(TextoVE.concat(input));
                    }
                }
            } else if (TextoVE.equals("0")) {
                VisorEscritura.setText(input);
            }
        } else if (input.matches(SIMBOLOS) && !PrimerOperando) {
            try {
                PrimeraParte = Double.valueOf(TextoVE);
            } catch (NumberFormatException e) {
                PrimeraParte = 0;
                VisorEscritura.setText(String.valueOf((int) PrimeraParte));
                TextoVE = String.valueOf((int) PrimeraParte);
            }
            VisorEscritura.requestFocus();
            if (!input.equals("X")) {
                EtiquetaResultados.setText(TextoVE.concat(" " + input + " "));
            } else {
                EtiquetaResultados.setText(TextoVE.concat(" * "));
            }
            PrimerOperando = true;
            InicioSegundaParte = true;
            ActivarODesactivarBotones(".", false);
            ConfigurarBotones(false);
        } else if (input.startsWith("C")) {
            if (!input.contains("E")) {
                Reiniciar();
                EtiquetaResultados.setText("");
            } else {
                VisorEscritura.setText("0");
                VisorEscritura.requestFocus();
            }
        } else {
            switch (input) {
                case "=":
                    try {
                        SegundaParte = Double.valueOf(TextoVE);
                    } catch (NumberFormatException e) {
                        SegundaParte = 0;
                        VisorEscritura.setText(String.
                                valueOf((int) SegundaParte));
                        TextoVE = String.valueOf((int) SegundaParte);
                    }
                    RealizarOperación(TextoResultados.
                            charAt(TextoResultados.length() - 2));
                    EtiquetaResultados.setText
                        (TextoResultados.concat(TextoVE + " = " + Resultado));
                    Reiniciar();
                    break;
                case "+/-":
                    if (TextoVE.matches(NUMERO_POSITIVO) 
                            || TextoVE.matches(NUMERO_POSITIVO_EMPIEZA_POR_CERO)) 
                    {
                        VisorEscritura.setText("-" + TextoVE);
                    } else if (TextoVE.matches(NUMERO_NEGATIVO) 
                            || TextoVE.matches(NUMERO_NEGATIVO_EMPIEZA_POR_CERO)) {
                        VisorEscritura.setText(TextoVE.substring(1));
                    }
                    break;
                case ".":
                    if (!TextoVE.contains(".")) {
                        if (!VisorEscritura.isFocused()) {
                            VisorEscritura.setText(TextoVE.concat("."));
                        }
                    } else {
                        VisorEscritura.setText(TextoVE.replace(".", ""));
                    }
                    break;
            }
        }
    }

    /**
     * Dota de sus valores iniciales a todos los elementos de la calculadora,
     * excepto a la etiqueta de resultados
     */
    public void Reiniciar() {
        PrimeraParte = 0;
        SegundaParte = 0;
        PrimerOperando = false;
        Resultado = 0;
        try {
            VisorEscritura.setText("0");
            VisorEscritura.requestFocus();
            ActivarODesactivarBotones(".", false);
            ConfigurarBotones(true);
        } catch (NullPointerException e) {
            System.out.println("Se está ejecutando un test en el método reiniciar");
        }
    }

    /**
     * Adapta la disponibilidad de botones a la situación concreta en el flujo
     * del programa
     *
     * @param Reinicio Especifica si se trata o no de una nueva operación
     */
    public void ConfigurarBotones(Boolean Reinicio) {
        ActivarODesactivarBotones("=", Reinicio);
        ActivarODesactivarBotones("+", !Reinicio);
        ActivarODesactivarBotones("-", !Reinicio);
        ActivarODesactivarBotones("/", !Reinicio);
        ActivarODesactivarBotones("X", !Reinicio);
    }

    /**
     * Activa o desactiva botones concretos
     *
     * @param Botón El botón del que se quiere cambiar el estado
     * @param Desactivar Especifica si se quiere desactivar (true) o activar
     * (false)
     */
    public void ActivarODesactivarBotones(String Botón, Boolean Desactivar) {
        for (Node botón : PanelReja.getChildren()) {
            if (botón instanceof Button) {
                if (((Button) botón).getText().equals(Botón)) {
                    botón.setDisable(Desactivar);
                }
            }
        }
    }

    /**
     * Realiza las operaciones, haciendo uso de los valores almacenados
     *
     * @param Operación Caracter con el símbolo de la operación
     */
    public void RealizarOperación(char Operación) {
        switch (String.valueOf(Operación)) {
            case "+":
                Resultado = PrimeraParte + SegundaParte;
                break;
            case "-":
                Resultado = PrimeraParte - SegundaParte;
                break;
            case "*":
                Resultado = PrimeraParte * SegundaParte;
                break;
            case "/":
                Resultado = PrimeraParte / SegundaParte;
                break;
        }
    }

    /**
     * Determina si el tipo de cambio en el campo está permitido y lo permite o
     * bloquea
     */
    public void ListenerVisorEscritura() {
        UnaryOperator<Change> filtro = new UnaryOperator<Change>() {
            @Override
            public Change apply(Change change) {
                String input = change.getText().toUpperCase();
                if (!input.matches("\\D")) {
                    ListenerEfectoPulsado(input);
                    return change;
                } else if (input.matches("[\\+*/-]") || input.equals("=") 
                        || input.equals(".") || input.equals("C")) {
                    if (input.equals(".")) {
                        if (VisorEscritura.getText().contains(".")) {
                            EscribirTeclado(input);
                            return null;
                        } else {
                            return change;
                        }
                    }
                    EscribirTeclado(input);
                }
                return null;
            }
        };
        VisorEscritura.setTextFormatter(
                new TextFormatter<>(filtro));
    }

    /**
     * Simula una pulsación del botón equivalente a la tecla numérica pulsada
     *
     * @param input La tecla numérica pulsada
     */
    public void ListenerEfectoPulsado(String input) {
        for (Node nodo : PanelReja.getChildren()) {
            if (nodo instanceof Button && ((Button) nodo).getText().equals(input)) {
                ((Button) nodo).arm();
                PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
                pause.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        ((Button) nodo).disarm();
                    }
                });
                pause.play();
            }
        }
    }

    /**
     * Método que define el valor del primer operando
     *
     * @param PrimeraParte El valor que se quiere asignar al primer operando
     */
    public void setPrimeraParte(double PrimeraParte) {
        this.PrimeraParte = PrimeraParte;
    }

    /**
     * Método que define el valor del segundo operando
     *
     * @param SegundaParte El valor que se quiere asignar al segundo operando
     */
    public void setSegundaParte(double SegundaParte) {
        this.SegundaParte = SegundaParte;
    }

    /**
     * Método que devuelve el valor del resultado de la operación
     *
     * @return El valor del resultado de la operación
     */
    public double getResultado() {
        return Resultado;
    }

    /**
     * Método que devuelve el valor del primer operando de la operación
     *
     * @return El valor del primer operando de la operación
     */
    public double getPrimeraParte() {
        return PrimeraParte;
    }

    /**
     * Método que devuelve el valor del segundo operando de la operación
     *
     * @return El valor del segundo operando de la operación
     */
    public double getSegundaParte() {
        return SegundaParte;
    }

    /**
     * Método que especifica si se ha introducido o no el primer operando de la
     * operación
     *
     * @return "true" si el primer operando se ha introducido, "false" si no
     */
    public Boolean getPrimerOperando() {
        return PrimerOperando;
    }

}
