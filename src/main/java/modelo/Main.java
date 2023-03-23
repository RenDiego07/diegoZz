/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author diego
 */
import java.util.ArrayList;
import java.util.Scanner;
import modelo.*;
public class Main {
  public static ArrayList<Cliente> listClients;
  public static ArrayList<Servicio> listServices;
  public static ArrayList<Proveedor> listProvee;
  public static ArrayList<Orden> listOrdenes;
  public static ArrayList<Usuario> listUsers;

  public static void inicializarSistema() {
    listClients = new ArrayList<Cliente>();
    listServices = new ArrayList<Servicio>();
    listProvee = new ArrayList<Proveedor>();
    listUsers = new ArrayList<Usuario>();
    listOrdenes = new ArrayList<Orden>();
    Usuario admin = new Admin("admin1","12345678","Administrador","admin");
    Usuario tecnico1 = new Tecnico("alopez","al123456","Alvaro Lopez","tecnico");
    Usuario cobranza = new Cobranza("mcastro","mc123456","Maria Castro","cobranzas");
    Usuario tecnico2 = new Tecnico("mbarcos","mb123456","Mario Barcos","tecnico");
    listUsers.add(admin);
    listUsers.add(tecnico1);
    listUsers.add(cobranza);
    listUsers.add(tecnico2);

    // lista Clientes
    //listClients.add(new Cliente("Alex", "abc", "123456789", "Personal"));
    //listClients.add(new Cliente("Canvas", "adc", "125156789", "Empresarial"));
    //listClients.add(new Cliente("Holcim", "csdcds", "5646516", "Empresarial"));
    //listClients.add(new Cliente("Maria", "acds", "121517", "Personal"));
    // lista Servicios
    listServices.add(new Servicio("Alineacion",200.34));
    listServices.add(new Servicio( "Balanceo", 304.23));
    listServices.add(new Servicio( "Cambio de aceite motor", 204.23));
    listServices.add(new Servicio( "Cambio filtro aceite", 104.23));
    listServices.add(new Servicio( "Cambio de llantas", 44.23));
    listServices.add(new Servicio( "Revision de luces", 20.23));
  // lista Proveedores
    listProvee.add(new Proveedor("Juan", "csdcscs", "615616"));
    listProvee.add(new Proveedor("Marcos", "csdcsa", "16513"));
  // lista Ordenes
    ArrayList<Servicio> s1 = new ArrayList<>();
    ArrayList<Servicio> s2 = new ArrayList<>();
    ArrayList<Servicio> s3 = new ArrayList<>();
    ArrayList<Servicio> s4 = new ArrayList<>();

    s1.add(listServices.get(0));
    s1.add(listServices.get(3));
    s2.add(listServices.get(1));
    s2.add(listServices.get(2));
    s2.add(listServices.get(4));
    s3.add(listServices.get(5));
    s3.add(listServices.get(0));
    s4.add(listServices.get(0));
    s4.add(listServices.get(1));
    s4.add(listServices.get(2));
    s4.add(listServices.get(3));
    Tecnico t1 = (Tecnico) tecnico1; 
    Tecnico t2 = (Tecnico) tecnico2; 
    listOrdenes.add(new Orden(t1, listClients.get(0), "02-05-2022", "Automóvil", "GSI-9684", s1));
    listOrdenes.add(new Orden(t1, listClients.get(1), "18-05-2022", "Moto", "GSI-9784", s2));
    listOrdenes.add(new Orden(t2, listClients.get(3), "16-07-2022", "Automóvil", "GSY-1452", s3));
    listOrdenes.add(new Orden(t2, listClients.get(1), "08-02-2022", "Bus", "GST-7583", s4));
  }
  
  
}
 /* public static void main(String[] args) {
    boolean validar = true;
    inicializarSistema();
    do{
      System.out.print("---------.*INICIO DE SECCIÓN*.---------\n");
      Scanner sc = new Scanner(System.in);
      System.out.print("Ingrese su nombre de usuario: \n");
      String usuario = sc.nextLine();
      System.out.print("Ingrese su contraseña: \n");
      String contra = sc.nextLine();
      Usuario buscar = new Admin(usuario,contra,"","");
      if(listUsers.contains(buscar)){
        validar = false;
        int i = listUsers.indexOf(buscar);
        System.out.print(listUsers.get(i));
        if (listUsers.get(i) instanceof Admin){
          Admin.showMenu(listClients, listServices, listProvee);
        }else if(listUsers.get(i) instanceof Tecnico){
          Tecnico t = (Tecnico) listUsers.get(i);
          Tecnico.showMenu(listServices,listOrdenes, listClients,t);
        }else if(listUsers.get(i) instanceof Cobranza){
          Cobranza.showMenu(listOrdenes,listClients,listServices);
        }
      }else{
        System.out.print("ERROR: las credenciales no son válidas\n");
        System.out.println("Ingrese de nuevo los datos");
      }
      
    }while(validar);
    
  }
}
/*
    Admin.showMenu(listClients, listServices, listProvee);
    Tecnico.showMenu(listServices, listOrdenes, listClients, (Tecnico) listUsers.get(1));
    Cobranza.showMenu(listOrdenes, listClients, listServices);
*/