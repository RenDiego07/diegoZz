/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.vacaciones;
import java.text.SimpleDateFormat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.*;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import modelo.*;
import javafx.scene.control.ButtonBar.ButtonData;


/**
 * FXML Controller class
 *
 * @author diego
 */
public class MenuAdminController implements Initializable {



    @FXML
    private VBox mainVBox;
    @FXML
    private VBox topVBox;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ComboBox<String> options;
    @FXML
    private VBox midVBox;
    @FXML
    private HBox bottomHBox;
  
    
    
    
    //private ArrayList<Cliente>lClients;
    
    //private ArrayList<Proveedor>lProveedores;
    //private ArrayList<Servicio> lServices;
    
    private ImageView Bk;
    private TableView<Comerciante> TB;
    private ArrayList<Cliente> clientsRemoved;
    private ArrayList<Proveedor> proveedoresRemoved;
    private TableView<Servicio>TB1;
    




    private ArrayList<Servicio> servicesRemoved;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillComboBox();
        date();
        clientsRemoved = new ArrayList<>();
        
        proveedoresRemoved = new ArrayList<>();
        
        servicesRemoved = new ArrayList<>();
 


        //lProveedores = Proveedor.getProveedores();
        //lClients = Cliente.getClients();
        //lServices =Servicio.getServices();
        
        

        // TODO
    }    

    @FXML
    private void comenzar(ActionEvent event) {
        String valor = options.getValue().toString();
        int numeral;
        switch(valor){
            case("Administrar Clientes"):
                numeral = 1;
                administrarComerciante(numeral);
                break;
            case("Administrar Proveedores"):
                
                numeral =0;
                administrarComerciante(numeral);
                
                break;
            case("Administrar Servicios"):
                administrarServicios();
                
                break;
               
                
        }
    
        }
    public TableView createTableView(){
        //---------------------------------------------------------------------------
        TB = new TableView<Comerciante>();
        TB.setEditable(true);
       
        TableColumn colID = new TableColumn<Comerciante,String>("Cédula");
        colID.setCellValueFactory(new PropertyValueFactory<Comerciante,String>("iD"));
        //----------------------------------------------------------------------------
       TableColumn colName = new TableColumn<Comerciante,String>("Name");
       colName.setCellValueFactory(new PropertyValueFactory<Comerciante,String>("name"));
       //--------------------------------------------------------------------------------
       TableColumn colDireccion = new TableColumn<Comerciante,String>("Direccion");
       colDireccion.setCellValueFactory(new PropertyValueFactory<Comerciante,String>("direccion"));
       //-----------------------------------------------------------------------
       TableColumn colTelefono = new TableColumn<Comerciante,String>("Telefono");
       colTelefono.setCellValueFactory(new PropertyValueFactory<Comerciante,String>("telefono"));
       //-----------------------------------------------------------------------

       //-----------------------------------------------------------------------
        TB.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TB.getColumns().add(colID);
        TB.getColumns().add(colName); 
        TB.getColumns().add(colDireccion);        
        TB.getColumns().add(colTelefono);

        
        return TB;
    }
    public void fillComboBox(){
        ArrayList<String> listCm = new ArrayList<>();
        listCm.add("Administrar Clientes");
        listCm.add("Administrar Proveedores");
        listCm.add("Administrar Servicios");
        options.getItems().addAll(listCm);
        
    }
    public void administrarComerciante(int x){
        bottomHBox.getChildren().clear();
        midVBox.getChildren().clear();
        TableView TB = createTableView();
        midVBox.getChildren().add(TB);
        switch (x){
            case 1:
                //-------------------proceso extra por si es Cliente-----------------------------
                TableColumn colType = new TableColumn<Cliente,String>("Tipo");
                colType.setCellValueFactory(new PropertyValueFactory<Cliente,String>("tipoCliente"));
                TB.getColumns().add(colType);
                TB.getItems().addAll(App.getlClients());
                //-------------------------------------------?
                createButtons(x);

                
                
                break;
                
            case 0:
                
                TB.getItems().addAll(App.getlProveedores());
                
                createButtons(x);
                
                break;
        }
       
    }
    public void AgregarComerciante(int op){
        midVBox.getChildren().clear();
        bottomHBox.getChildren().clear();
        

        midVBox.setAlignment(Pos.CENTER);
        
        /*TextField txtCedula = new TextField();
        TextField txtNombre = new TextField();
        TextField txtDireccion = new TextField();
        TextField txttelefono = new TextField();
        TextField txtTipo = new TextField();
         se podia de hacer de esta forma, pero mejor hacerlo con controles de flujo y bucles*/
        
        midVBox.setPadding(new Insets(10,10,10,10));
        if(op ==1){
                    String [] atributos = {"Cedula","Nombre","Direccion","Telefono","tipo"};            
               for(String el : atributos){
                   TextField txt = new TextField();
                   txt.setMaxWidth(200);
                   Label lb = new Label(el);
                   midVBox.getChildren().addAll(lb,txt);

               }
               Button load = new Button("Guardar");
               load.setOnAction((eh)->{
                   ArrayList<String> info = new ArrayList<>();
                   int contador=1;
                   Boolean vbool = true;
                   
                       while(contador<= 9){ 
                       TextField tx = (TextField) midVBox.getChildren().get(contador);
                       info.add(tx.getText());
                       
                       if(tx.getText().isBlank()){
                           vbool=false;
                       }
                       contador+=2;
                       }
                   if(vbool){
                       App.getlClients().add(new Cliente(info.get(0),info.get(1),info.get(2),info.get(3),info.get(4)));
                       Cliente.agregarCliente(App.getlClients());
                       confirmar(Alert.AlertType.CONFIRMATION);
                       info.clear();
                   }else{
                       warning(Alert.AlertType.WARNING);     
                   }    

               });

               midVBox.getChildren().add(load);

           }else if(op ==0){
               String [] atributos = {"Cedula","Nombre","Direccion","Telefono"};   
               
               for(String el : atributos){
                   TextField txt = new TextField();
                   txt.setMaxWidth(200);
                   Label lb = new Label(el);
                   midVBox.getChildren().addAll(lb,txt);
               }
                Button load = new Button("Guardar");
                load.setOnAction((eh)->{
                    ArrayList<String> info = new ArrayList<>();
                    int contador = 1;
                    Boolean vbool2 = true;    
                    while(contador<=7){
                        
                        TextField tx = (TextField) midVBox.getChildren().get(contador);
                        info.add(tx.getText().toString());
                        if(tx.getText().isBlank()){
                            vbool2= false;
                        }
                        contador+=2;
                        
                    }
                    if(vbool2){
                        App.getlProveedores().add(new Proveedor(info.get(0),info.get(1),info.get(2),info.get(3)));                        
                        confirmar(Alert.AlertType.CONFIRMATION);
                        info.clear();
                    }else{
                        warning(Alert.AlertType.WARNING);
                        
                    }
                    
                    
                });
                midVBox.getChildren().add(load);
                
                
               
               
               
        }    
    }
        //--------------------------Apartado Alertas-----------------------------
    public void warning(Alert.AlertType type){
        Alert al = new Alert(type);
        al.setTitle("HOLISS");
        al.setHeaderText("NO HA INGRESADO BIEN LA INFORMACION");
        al.showAndWait();
    }
    public void confirmar(Alert.AlertType type){
        Alert al = new Alert(type);
        al.setTitle("hol");
        al.setHeaderText("SE HA INGRESADO DE FORMA CORRECTA LOS DATOS");
        al.showAndWait();
    }
    public void info(Alert.AlertType tipo,Comerciante c){
        Alert al = new Alert(tipo);
        if(c instanceof Cliente){
            Cliente other = (Cliente) c;
            al.setContentText("SE HA SELECCIONADO AL CLIENTE"+other.getName());
            
        }else if(c instanceof Proveedor){
            Proveedor other1 = (Proveedor) c;
            al.setContentText("SE HA SELECCIONADO AL PROVEEDOR"+other1.getName());
        }
        al.showAndWait();
        
        
    }
    
    public void missingInf(Alert.AlertType tipo){
        Alert al = new Alert(tipo);
        al.setContentText("SE INTENTO EDITAR UN CLIENTE QUE NO EXISTE");
        al.showAndWait();
    }
    //---------------------------fin Apartado Alertas---------------------------
    
    public void createButtons(int x){
                Button bAgregar = new Button("Agregar");
                bAgregar.setOnAction((eh)->{
                    AgregarComerciante(x);
                });

                bottomHBox.setAlignment(Pos.CENTER);
                HBox.setMargin(bAgregar, new Insets(15));
                bottomHBox.getChildren().add(bAgregar);
                

                //------------------------------------------
                Button bEditar = new Button("Editar");
                HBox.setMargin(bEditar, new Insets(15));               
                bottomHBox.getChildren().add(bEditar);
                
                bEditar.setOnAction((v)->{
                    editarComerciante(x);
                    
                });
                
                //--------------------------------------------
                Button bEliminar = new Button("Eliminar");
                bEliminar.setOnAction((v)->{
                    eliminarComerciante(x);
                });
                HBox.setMargin(bEliminar, new Insets(15));
                
                bottomHBox.getChildren().add(bEliminar);
    }
    public boolean ConfRemoveService(Servicio co){
        Boolean vbool = false;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText("Seguo que quiere eliminar a "+co.getName());

        ButtonType cancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancel);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK && result.isPresent()){
            vbool = true;
      }
      return vbool;
  }
    public boolean ConfRemoveComerciante(Comerciante c){
            Boolean vbool = false;
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Aviso");
          alert.setHeaderText("Seguo que quiere eliminar a "+c.getName());

          ButtonType cancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
          alert.getDialogPane().getButtonTypes().add(cancel);
          Optional<ButtonType> result = alert.showAndWait();
          if(result.get() == ButtonType.OK && result.isPresent()){
              vbool = true;
          }
          return vbool;
    }    
    //---------------------------Editar Comerciante("metodos")-----------------------------------------//
    public void editarComerciante(int x){
        
        TB.setOnMouseClicked((eh)->{
            
         
          Comerciante p = TB.getSelectionModel().getSelectedItem();
          
          // -----------IMPORTANTE: indice del combobox ------------- //
          int indice = anchor.getChildren().indexOf(options);

          
          VBox txts = new VBox();
          midVBox.getChildren().add(txts);
          String[] parametros = {"Nombre","Dirección","Telefono"};
          if(!(p==null)){
            midVBox.getChildren().get(0).setVisible(false);              
            bottomHBox.getChildren().clear();              
            anchor.getChildren().get(indice).setVisible(false);              
              if(p instanceof Cliente){
                info(Alert.AlertType.INFORMATION,p);
                
                generarTxts(txts,parametros);
                
                Label lTipo = new Label("Tipo Cliente");
                TextField txtCliente = new TextField();
                txtCliente.setMaxWidth(200);
                txts.getChildren().addAll(lTipo,txtCliente);
                
        
                try{
                    Button but = new Button("Actualizar");
                    but.setOnAction((g)->{
                        ArrayList<String> info = new ArrayList<>();                        
                        Boolean vbool =true;
                        int cont = 1;
                        while(cont<=7){
                            TextField tx = (TextField)txts.getChildren().get(cont);
                            info.add(tx.getText().toString());
                            if(tx.getText().isBlank()){
                                vbool = false;
                            }
                        cont+=2;    
                        }
                        if(vbool){
                            confirmar(Alert.AlertType.INFORMATION);
                            bottomHBox.getChildren().remove(0);
                            midVBox.getChildren().remove(txts);
                            System.out.println();
                            System.out.println(p);
                            Cliente c = (Cliente) p;
                            int ind = App.getlClients().indexOf(c);
                            Cliente.setCliente((Cliente)p, App.getlClients(), info.get(0), info.get(1), info.get(2),info.get(3));            
                            p.setName(info.get(0));
                            p.setDireccion(info.get(1));
                            p.setTelefono(info.get(2));
                            ((Cliente) p).setTipoCliente(info.get(3));
                            System.out.println(App.getlClients().indexOf((Cliente)p));
                            
                            
                              
                            /*for(Cliente c: lClients){
                                System.out.println(c);
                            }*/
                            Cliente.agregarCliente(App.getlClients());
                            TB.refresh();
                            midVBox.getChildren().get(0).setVisible(true);
                            anchor.getChildren().get(0).setVisible(true);
                            info.clear();
                            
                        }else{
                            warning(Alert.AlertType.ERROR);
                                }
                        
                    });
                    bottomHBox.getChildren().add(but);

                

                }catch(RuntimeException e){
                    e.printStackTrace();
                }
              }else if(p instanceof Proveedor){

                  generarTxts(txts,parametros);
                  try{
                      Button but = new Button("Actualizar");
                      but.setOnAction((l)->{
                          int cont = 1;
                          Boolean TF =true;
                          ArrayList<String> componentes = new ArrayList<>();
                          while(cont<= 5){
                              TextField tx = (TextField)txts.getChildren().get(cont);
                              componentes.add(tx.getText().toString());
                              if(tx.getText().isBlank()){
                                  TF = false;
                              }
                              cont+=2;
                          }
                          if(TF){
                              int borrar= midVBox.getChildren().indexOf(txts);
                              midVBox.getChildren().remove(borrar);
                              confirmar(Alert.AlertType.INFORMATION);
                              Proveedor pro= (Proveedor) p;
                              int index = App.getlProveedores().indexOf(pro);
                              App.getlProveedores().get(index).setName(componentes.get(0));
                              App.getlProveedores().get(index).setDireccion(componentes.get(1));
                              App.getlProveedores().get(index).setTelefono(componentes.get(2));
                              TB.refresh();
                              midVBox.getChildren().get(0).setVisible(true);
                              
                              Proveedor.agregarProveedor(App.getlProveedores());
                              anchor.getChildren().get(0).setVisible(true);
                              bottomHBox.getChildren().remove(0);
                              componentes.clear();
                              
                              
                          }else{
                              warning(Alert.AlertType.ERROR);
                          }
                      });
                      bottomHBox.getChildren().add(but);
                  }catch(RuntimeException o){
                      o.printStackTrace();
                  }
              }
          }else if(p == null){
              missingInf(Alert.AlertType.INFORMATION);
          }
            
        });
        
        
        
    }
    public void generarTxts(VBox v, String[] inf){
        for(String parametros: inf){
            v.setAlignment(Pos.CENTER);
            Label l = new Label(parametros);
            TextField txt = new TextField();
            txt.setMaxWidth(200);
            v.getChildren().addAll(l,txt);
            
        }
        
    }  
    //------------------------Fin de Editar Comerciante("metodos")------------------------------------------//

   public void eliminarComerciante(int x){
       // falta poner una condicion con una variable de control que se generará por medio de una alerta
    TB.setOnMouseClicked((k)->{
        Comerciante p = TB.getSelectionModel().getSelectedItem();
        if(ConfRemoveComerciante(p)){
        
            TB.getItems().remove(p);
            TB.refresh();        
            switch(x){
                case(1):

                     Cliente.clientsRemoved(clientsRemoved, (Cliente) p );
                     Cliente.removeClient(App.getlClients(), (Cliente) p);
                     // se usa el agregarCliente a pesar de no estra agregando ningun cliente porque el objetivo de ese metodo cumple con el objetivo a llevar a cabo
                     Cliente.agregarCliente(App.getlClients());


                    break;
                 case(0):
                     for(Proveedor pro: App.getlProveedores()){
                         System.out.println(pro);
                     }
                     
                     Proveedor.ProveedoresBorrados(proveedoresRemoved, (Proveedor) p);
                     Proveedor.removerProveedor(App.getlProveedores(), (Proveedor) p);
                     Proveedor.agregarProveedor(App.getlProveedores());




                     break;

            }
             }
           });
   }
    //-------------------------Eliminar Comerciante ("metodos")---------------------------------------------//
   
   //-------------------------- Administrar Servicios------------------------------------------------//
   public void administrarServicios(){
       midVBox.getChildren().clear();
       bottomHBox.getChildren().clear();
       TB1 = crearTB1();
       
       TB1.getItems().addAll(App.getlServicio());
       midVBox.getChildren().add(TB1);
       makeButtons();
       
       
       
       
       
   }
   
   public TableView<Servicio> crearTB1(){
       TableView<Servicio> TB2 = new TableView<Servicio>();
       TableColumn cCodigo = new TableColumn<Servicio,Integer>("Codigo");
       cCodigo.setCellValueFactory(new PropertyValueFactory<Servicio,Integer>("code"));
       
       TableColumn cNombre = new TableColumn<Servicio, String>("Nombre");
       cNombre.setCellValueFactory(new PropertyValueFactory<Servicio,String>("Name"));
       
       TableColumn cPrecio = new TableColumn<Servicio, Double>("Precio");
       cPrecio.setCellValueFactory(new PropertyValueFactory<Servicio,Double>("price"));
       TB2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       
       
       TB2.getColumns().add(cCodigo);
       TB2.getColumns().add(cNombre);
       TB2.getColumns().add(cPrecio);
       
       return TB2;
   }
   public void makeButtons(){
       bottomHBox.setAlignment(Pos.CENTER);       
       Button addService = new Button("Agregar");

       HBox.setMargin(addService, new Insets(10,10,10,10));
       addService.setOnAction((z)->{
           addService();
       });
       bottomHBox.getChildren().add(addService);
       
       Button editService = new Button("Editar");
       HBox.setMargin(editService, new Insets(10,10,10,10));
       editService.setOnAction((v)->{
           editService();
           
       });
       bottomHBox.getChildren().add(editService);
       
       Button removeService = new Button("Eliminar");
       HBox.setMargin(removeService, new Insets(10,10,10,10));
       removeService.setOnAction((m)->{
           deleteService();
           
       });
       bottomHBox.getChildren().add(removeService);
       
   }
   public void addService(){
       midVBox.getChildren().clear();
       bottomHBox.getChildren().clear();
       
       String[] parametros = {"Nombre","Precio"};
       VBox txts = new VBox();
       for(String veces: parametros){
           Label l= new Label(veces);
           TextField txt = new TextField();
           txt.setMaxWidth(200);
           txts.getChildren().addAll(l,txt);
       }
       
       midVBox.getChildren().add(txts);
       Button save = new Button("Guardar");
       save.setOnAction((o)->{
           
           ArrayList<String> info = new ArrayList<>();
           int c = 1;
           Boolean FT = true;
           try{
           
           while(c<=3){
               TextField txt = (TextField)txts.getChildren().get(c);
               info.add(txt.getText().toString());
               if(txt.getText().isBlank()){
                   FT = false;
               }
               c+=2;
            }
               
               if(FT){
                   confirmar(Alert.AlertType.INFORMATION);
                   Servicio.AgregarServicios(App.getlServicio(), new Servicio(info.get(0),Double.valueOf(info.get(1))));
                   
               }else{
                   warning(Alert.AlertType.ERROR);
               }
           
           }catch(RuntimeException n){
               n.printStackTrace();
           }
       });
       bottomHBox.getChildren().add(save);
      
   }
   public void editService(){
       TB1.setOnMouseClicked((event)->{
           Servicio c = TB1.getSelectionModel().getSelectedItem();
          
           if(!(c == null)){
               bottomHBox.getChildren().clear();               
               midVBox.getChildren().get(0).setVisible(false);
               anchor.getChildren().get(0).setVisible(false);
               VBox txts = new VBox();
               txts.setAlignment(Pos.CENTER);
               TextField txtPrecio = new TextField();
               txtPrecio.setMaxWidth(200);
               Label lPrecio = new Label("Precio");
               txts.getChildren().addAll(lPrecio,txtPrecio);
               midVBox.getChildren().add(txts);
              Button actualizar = new Button("Actualizar");
              actualizar.setOnAction((evento2)->{
                  Boolean control = true;
               try{
                    if(txtPrecio.getText().isBlank()){
                        control = false;
                    }
                    
               }catch(NumberFormatException ex){
                   ex.printStackTrace();
               }
               if(control){
                   anchor.getChildren().get(0).setVisible(true);
                   bottomHBox.getChildren().clear();
                   confirmar(Alert.AlertType.INFORMATION);
                   c.setPrice(Double.valueOf(txtPrecio.getText().toString()));
                   TB1.refresh();
                   midVBox.getChildren().get(0).setVisible(true);
                   midVBox.getChildren().remove(1);
                   
                   
               }
              });
              bottomHBox.getChildren().add(actualizar);
                 
           }else{
               missingInf(Alert.AlertType.ERROR);
           }
       });
   }
   public void deleteService(){
       TB1.setOnMouseClicked((evento3)->{
           Servicio c = TB1.getSelectionModel().getSelectedItem();
           if(!(c==null)&& ConfRemoveService(c)){
                TB1.getItems().remove(c);
                Servicio.removeService(servicesRemoved, c);
                
                TB1.refresh();
                App.getlServicio().remove(c);
                Servicio.refreshServices(App.getlServicio());
                
           
           }else if (c == null){
               warning(Alert.AlertType.ERROR);
           }

       });
       
   }

   //---------------------Fin adminsitrar Servicios----------------------------//
   // falta mostrar la opcion de si de verdad queremos eliminar el proveedor, cliente o Servicio
   // Creando Fecha//
   
   
   public void date(){
       Date fecha = new Date();
       SimpleDateFormat txt = new  SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
       String txtFecha = txt.format(fecha);
       Label portadaFecha = new Label(txtFecha);
       bottomHBox.setAlignment(Pos.BOTTOM_CENTER);
       bottomHBox.getChildren().add(portadaFecha);
       
   }
}
