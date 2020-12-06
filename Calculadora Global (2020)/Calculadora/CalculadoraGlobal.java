package calculadoraglobal;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Clase definitoria de las características generales de la ventana 
 * e inicializadora de la interfaz
 *
 * @author Setito
 */
public class CalculadoraGlobal extends Application {
    /**
     * Carga el fxml, define la ventana y la muestra
     * @param stage La ventana en cuestión
     */
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().
                    getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Calculadora");
            stage.getIcons().add(new Image(getClass().
                    getResourceAsStream("descarga.png")));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println("Recurso no encontrado");
        }
    }

    /**
     * Método main, punto de inicio de la aplicación
     * @param args Los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }
}
