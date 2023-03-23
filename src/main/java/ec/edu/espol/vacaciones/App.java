package ec.edu.espol.vacaciones;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.*;
import java.io.IOException;
import java.util.*;
/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ArrayList<Cliente> lClients;
    private static ArrayList<Servicio> lServicio;
    private static ArrayList<Proveedor>lProveedores;

    public static ArrayList<Cliente> getlClients() {
        return lClients;
    }

    public static ArrayList<Servicio> getlServicio() {
        return lServicio;
    }

    public static ArrayList<Proveedor> getlProveedores() {
        return lProveedores;
    }
    

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Inicio"), 640, 400);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Vacaciones");
        Cliente.Serealizar();
        Servicio.serealizarServicios();
        Proveedor.cargarProveedores();
        lClients = Cliente.getClients();
        lServicio =Servicio.getServices();
        lProveedores =Proveedor.getProveedores();

        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}