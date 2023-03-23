/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.vacaciones;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import modelo.*;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author diego
 */
public class TecnicoMenuController implements Initializable {


    @FXML
    private AnchorPane anchor;
    @FXML
    private HBox topHBox;
    @FXML
    private VBox bottomVBox;
    @FXML
    private VBox VBoxTop;
    private Servicio c1;
    private Tecnico tec=InicioController.tec; 
    private static ArrayList<Orden> lOrdersAlopez;
    private static ArrayList<Orden>lOrdersBarcos;
    private ArrayList<Servicio> lServicios;
    private ArrayList<Cliente>lClientes;
    private TableView<Servicio> TB;
    private ArrayList<Servicio> servPedido;
    private boolean vF1;
    private ArrayList<String> parametros;
    private Cliente cliente;
    private String stream;
    private TableView TBorden;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       menuBar();
       //lServicios = MenuAdminController.getlServices();
       //lClientes = MenuAdminController.getlClients();
       Orden.BarcosSerealizar();
       Orden.LopezSerealizar();
       lOrdersAlopez = Orden.getLopezList();
       lOrdersBarcos = Orden.getBarcosList();
            for(Orden o: lOrdersAlopez){
                System.out.println(o);
    }
        
    }


    public void menuBar(){
        MenuBar menuOptions = new MenuBar();
        
        Menu options = new Menu("Menu");
        menuOptions.getMenus().add(options);
        menuOptions.setUseSystemMenuBar(true);
        anchor.getChildren().add(menuOptions);
        anchor.setRightAnchor(menuOptions,10.0);
        MenuItem op1 = new MenuItem("Consultar orden de Servicio");
        op1.setOnAction((e)->{
            topHBox.setVisible(true);
            if(anchor.getChildren().size()>2){
                anchor.getChildren().remove(2);
            }

            topHBox.getChildren().clear();
            bottomVBox.getChildren().clear();
            backgroundStyle();
            HBoxLayoutnEvents();
            consultarServicio();
            
            
            
            
        });
        MenuItem op2 = new MenuItem("Generar orden de Servicio");
        op2.setOnAction((u)->{
            for(int x=0; x<anchor.getChildren().size();x++){
                if(anchor.getChildren().get(x)!= menuOptions){
                    anchor.getChildren().get(x).setVisible(false);
                }
                generarOrden(); //este metodo limpia el contenedor en donde se generarán los labels y los txts y validar la informacion y serializarla
                
            }        
                    });
        MenuItem op3 = new MenuItem("Reportar falta de insumo");
        op3.setOnAction((a)->{
            
        });
        options.getItems().addAll(op1,op2,op3);
        
        
        
    }
    public void alert(){
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("Javafx");
        al.setContentText("Se ha seleccionado una option del menu");
        al.showAndWait();
    }
    //---------------------------Consultar Servicio----------------------//
    public void backgroundStyle(){
        Label lTitle = new Label("Consultar orden de Servicio");
        lTitle.setFont(new Font("Bauhaus 93",30));
        anchor.getChildren().add(lTitle);
        anchor.setLeftAnchor(lTitle,20.0);
        
        
    }
    public void HBoxLayoutnEvents(){
        Label lb = new Label("Filtrar por Codigo:");
        
        Font styleHBox = new Font("Britannic Bold",15.0);
        lb.setFont(styleHBox);
        
        HBox.setMargin(lb, new Insets(10.0));
        
        Label lCodigo = new Label();
        lCodigo.setFont(styleHBox);
        HBox.setMargin(lb, new Insets(10.0));        
        
        TextField txtCod = new TextField();
        txtCod.setMaxWidth(100);
        
        Label lDate = new Label("Fecha");
        lDate.setFont(styleHBox);
        HBox.setMargin(lDate, new Insets(10.0));
        
        TextField txtDate = new TextField();
        txtDate.setMaxWidth(100);
        
        Label lClient = new Label("Cliente");
        lClient.setFont(styleHBox);
        HBox.setMargin(lClient, new Insets(10.0));
        
        TextField txtClient = new TextField();
        txtClient.setMaxWidth(100);
        
        topHBox.getChildren().addAll(lb,lCodigo,txtCod,lDate,txtDate,lClient,txtClient);

    }
    
    
    
    //---------------------------------Fin Consultar Servicios--------------------------------//
    // --------------------------------Inicio de Generar orden de Servicios-------------------//
    public void generarOrden(){
        
        bottomVBox.getChildren().clear();
        String [] etiquetas = {"Code","Service Date","Vehicle Type","license plate","Service's name"};
        ArrayList<String> vehicles = new ArrayList<>();
        vehicles.add("Automovil");
        vehicles.add("Motocicletas");
        vehicles.add("Bus");      
        Font layoutBottom = new Font("Berlin Sans FB Demi",9);
        
        for(String lb : etiquetas){
            Label label = new Label(lb);
            TextField tx = new TextField();
            tx.setMaxWidth(150);
            tx.setPrefHeight(20);
            VBox.setMargin(tx, new Insets(0.50));
            VBox.setMargin(label, new Insets(0.50));
            bottomVBox.setAlignment(Pos.CENTER);
            label.setFont(layoutBottom);
            bottomVBox.getChildren().addAll(label,tx);
        }
        Button verificar = new Button("Guardar");
        
        
        verificar.setOnAction((u)->{
            vF1=true;
            
            parametros = new ArrayList<>();
            servPedido = new ArrayList<>();
            
            TextField txtCodigo = (TextField) bottomVBox.getChildren().get(1);
            
            try{
                Integer.valueOf(txtCodigo.getText().toString());
                
                
            }catch(NumberFormatException e){
                NumberError();
                System.out.println("encontre");                
                
            }
            
            if(vF1){
            
                for(int x =1 ; x<9;x+=2){
                    TextField tx1 = (TextField) bottomVBox.getChildren().get(x);                
                    if(!(tx1.getText().isBlank())&& x!=9){
                        parametros.add(tx1.getText());

                    }else{
                        txtsEmpty();
                        break;
                    }

                }
                TextField txtName = (TextField) bottomVBox.getChildren().get(9);
                c1= new Servicio(txtName.getText());
                
                if(parametros.size()== 4){
                    
                    if(vehicles.contains(parametros.get(2)) && App.getlServicio().contains(c1) && App.getlClients().contains(new Cliente(Integer.valueOf(parametros.get(0)),""))){
                        
                        int indiceServ = App.getlServicio().indexOf(c1);
                        c1 = App.getlServicio().get(indiceServ);
                        servPedido.add(c1);
                        if(continuar()){

                            createTableView();
                            



                        }else{
                            makeOrder();
                            orderDone();
                            

                        }


                    }else{
                        System.out.println("Econtramso el error");
                        NumberError();
                    }
                }
               }
            
        });
        bottomVBox.getChildren().add(verificar);
        
    }

    public void NumberError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Se ha ingresado un caracter NO NUMERICO");
        alert.showAndWait();
        vF1= false;
    }

    public void txtsEmpty(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Falta informacion por llenar");
        alert.showAndWait();        
    }
    public void yaRegistrado(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Se ha repetido un Servicio");
        alert.showAndWait();
    }
    public boolean continuar(){
        boolean vbool = false;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        
        alert.setContentText("DESEA INGRESAR OTRO SERVICIO");
        ButtonType cancelButton = new ButtonType("NO",ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            vbool = true;
        }
        
        return vbool ;
    }
    public void orderDone(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Se Ha Completado la orden");
        alert.showAndWait();
        
    }
    
    public void createTableView(){
        bottomVBox.getChildren().clear();
        TB = new TableView<Servicio>();
        TB.setEditable(true);
        TableColumn cCodigo = new TableColumn<Servicio,Integer>("Codigo");
        cCodigo.setCellValueFactory(new PropertyValueFactory<Servicio,Integer>("code"));
       
        TableColumn clName = new TableColumn<Servicio,String>("Name");
        clName.setCellValueFactory(new PropertyValueFactory<Servicio,String>("name"));
        
        
        TableColumn clPrice = new TableColumn<Servicio,Double>("Precio");
        clPrice.setCellValueFactory(new PropertyValueFactory<Servicio,Double>("price"));
        TB.getColumns().add(cCodigo);
        TB.getColumns().add(clPrice);
        TB.getColumns().add(clName);
        
        
        TB.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        lServicios = App.getlServicio();
        lServicios.remove(c1);
        TB.getItems().addAll(lServicios);
        
        bottomVBox.getChildren().add(TB);
        instructions();
        
        Button done = new Button("Done");
        done.setOnMouseClicked((t)->{
            
            makeOrder();
            orderDone();
            
        });
        
        bottomVBox.getChildren().add(done);
        
        TB.setOnMouseClicked((h)->{
            Servicio c = TB.getSelectionModel().getSelectedItem();

            if(!(c == null)){
                int xServ = App.getlServicio().indexOf(c);
                c = App.getlServicio().get(xServ);
                TB.getItems().remove(c);
                servPedido.add(c);
                TB.refresh();
                
                
            }else{
                noneSelected();
            }
                        
        });
        
        
                
    }
    public void noneSelected(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("NO SE SELECCIONO NINGUN SERVICIO");
        alert.showAndWait();
    }
    public void instructions(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Seleccione los servicios presentes");
        alert.showAndWait();    
    }
    public void makeOrder(){
        String stream ="";
        int indice = App.getlClients().indexOf(new Cliente(Integer.valueOf(parametros.get(0)),""));
        cliente = App.getlClients().get(indice);        
        Orden ord = new Orden(InicioController.tec,cliente,parametros.get(1),parametros.get(2),parametros.get(3),servPedido);
        if((InicioController.tec).equals(new Tecnico("alopez","al123456","",""))){
            stream = Constantes.RUTAORDENESLOPEZ;
            lOrdersAlopez.add(ord);
            Orden.SerealizarOrden(stream, lOrdersAlopez);
        }else{
            stream = Constantes.RUTAORDENESBARCOS;
            lOrdersBarcos.add(ord);
            Orden.SerealizarOrden(stream, lOrdersBarcos);
        }
    }
    //---------------------------------Fin de Generar orden de Servicios----------------------//
    public void consultarServicio(){
        TBorden = new TableView<Orden>();
        TableColumn cedulaCol = new TableColumn<Orden,String>("code");
        cedulaCol.setCellValueFactory(new PropertyValueFactory<Orden,String>("clientCod"));
        
        TableColumn dateCol = new TableColumn<Orden,String>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<Orden,String>("date"));
        
        TableColumn nameCol = new TableColumn<Orden,String>("Client's Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Orden,String>("clientName"));
        
        TableColumn priceCol = new TableColumn<Orden,Double>("Total to pay");
        priceCol.setCellValueFactory(new PropertyValueFactory<Orden,Double>("OrderPrice"));
        
        TBorden.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TBorden.getColumns().add(cedulaCol);
        TBorden.getColumns().add(dateCol);
        TBorden.getColumns().add(nameCol);
        TBorden.getColumns().add(priceCol);
        if(tec.equals(new Tecnico("alopez","al123456","",""))){
            TBorden.getItems().addAll(lOrdersAlopez);
        }else{
            TBorden.getItems().addAll(lOrdersBarcos);
        }
        bottomVBox.getChildren().add(TBorden);
        
    }
    
    
    
}
