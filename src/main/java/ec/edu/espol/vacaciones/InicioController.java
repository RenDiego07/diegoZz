/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.vacaciones;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.image.ImageView;
import java.io.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.*;
import javafx.scene.image.Image;
import java.util.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;



/**
 * FXML Controller class
 *
 * @author diego
 */
public class InicioController implements Initializable {


    @FXML
    private ImageView Portada;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button Iniciar;
    private ArrayList<Usuario> lUsers;
    @FXML
    private VBox fondo;
    @FXML
    private AnchorPane anchor;
    public static Tecnico tec;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Usuario.serealizar();
        lUsers = Usuario.obtenerUsuarios(Constantes.RUTAUSUARIOS);
        
        // TODO
        try{
            Portada.setImage(new Image(new FileInputStream(Constantes.RUTAIMAGENES+"fondo2.jpg")));
            Portada.setPreserveRatio(true);
            Portada.setFitWidth(640);
            Portada.setFitHeight(480);
        }catch(IOException e){
            e.printStackTrace();
        }
    }    

    @FXML
    private void Logear(ActionEvent event) throws Exception {
        String user = txtUser.getText().toString();
        String password = txtPassword.getText().toString();
        Usuario user1 = new Admin(user,password,"","");
        if(lUsers.contains(user1)){
            int indice = lUsers.indexOf(user1);
            if(lUsers.get(indice) instanceof Admin){
                App.setRoot("MenuAdmin");
                
            }else if(lUsers.get(indice) instanceof Tecnico){
                tec = new Tecnico(txtUser.getText(),txtPassword.getText(),"","");
                App.setRoot("tecnicoMenu");
            
            }else if(lUsers.get(indice) instanceof Cobranza){
                
            }
        }else{
            userNotFound(Alert.AlertType.INFORMATION);
        }
        
    }
    public void userNotFound(Alert.AlertType type){
        Alert al = new Alert(type);
        al.setHeaderText("No se ha encontrado el usuario\n"+"ingrese de nuevo la informacion");
        al.showAndWait();
    }

    
}
