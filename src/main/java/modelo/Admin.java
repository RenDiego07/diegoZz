/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author diego
 */
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Admin extends Usuario implements Serializable{
  public Admin(String user, String pass, String name, String level) {
    super(user, pass, name, level);
  }

 /* public static void agregarCliente(ArrayList<Cliente> lc) {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Nombre del cliente: ");
    String cName = sc.nextLine();
    System.out.println("Dirección: ");
    String cDir = sc.nextLine();
    System.out.println("Número de teléfono: ");
    String cTel = sc.nextLine();
    System.out.println("Tipo de cliente: ");
    String cType = sc.nextLine();

    Cliente c = new Cliente(cName, cDir, cTel, cType);
    lc.add(c);  

    System.out.println("El cliente " + cName + " ha sido agregado\n");
  }
  public static void agregarProveedor(ArrayList<Proveedor> lp) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Nombre del proveedor: ");
    String pName = sc.nextLine();
    System.out.println("Dirección del proveedor: ");
    String pDir = sc.nextLine();
    System.out.println("Teléfono del proveedor: ");
    String pTel = sc.nextLine();

    Proveedor p = new Proveedor(pName, pDir, pTel);
    lp.add(p);

    System.out.println("El proveedor " + pName + " ha sido agregado\n");
  }
  public static void agregarServicio(ArrayList<Servicio> ls) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Nombre del servicio: ");
    String sName = sc.nextLine();
    System.out.println("Precio del servicio: ");
    double sPrice = sc.nextDouble();

    Servicio s = new Servicio(sName, sPrice);
    ls.add(s);

    System.out.println("El servicio " + sName + " ha sido agregado\n");
  }
  public static void editarServicio(ArrayList<Servicio> ls) {
    Scanner sc = new Scanner(System.in);

    System.out.println("El código del servicio a editar: ");
    int id = sc.nextInt();
    Servicio s = new Servicio(id);
    if(ls.contains(s)) {
      int index = ls.indexOf(s);
      Servicio service = ls.get(index);
      System.out.println("Servicio a cambiar: " + service.getName());
      System.out.println("Ingrese nuevo precio: ");
      double newP = sc.nextDouble();
      service.setPrice(newP);
      System.out.println("Se ha cambiado el precio del servicio " + service.getName() + " a " + newP + "\n");
    }
  }
  

  public static void showMenu(ArrayList<Cliente> listC, ArrayList<Servicio> listS, ArrayList<Proveedor> listP) {
    Scanner sc = new Scanner(System.in);
    boolean menuBack = false;
    do {
      System.out.println("Menú nivel Administador");
      System.out.println("-----------------------");
      System.out.println("1. Administrar Clientes");
      System.out.println("2. Administrar Proveedores");
      System.out.println("3. Administrar Servicios");
      System.out.println("4. Salir");
      System.out.println("-----------------------");
      System.out.println("Escoja opción: ");
      int op = sc.nextInt();
      boolean menuBack2 = false;
  
      switch(op) {
        case 1: 
          do {
            System.out.println("1. Agregar cliente");
            System.out.println("2. Regresar al menú principal");
            System.out.println("Escoja opción: ");
            int op2 = sc.nextInt();
            switch(op2) {
              case 1: 
                agregarCliente(listC);
                menuBack2 = true;
                break;
              case 2: 
                menuBack2 = false;
                menuBack = true;
                break;
            }            
          }while(menuBack2);
        break;
        case 2:
          do {
            System.out.println("1. Agregar Proveedor");
            System.out.println("2. Regresar al menú principal");
            System.out.println("Escoja opción: ");
            int op2 = sc.nextInt();
            switch(op2) {
              case 1:
                agregarProveedor(listP);
                menuBack2 = true;
                break;
              case 2: 
                menuBack2 = false;
                menuBack = true;
            }
          }while(menuBack2);
          break;
        case 3:
          do {
            System.out.println("1. Agregar Servicio");
            System.out.println("2. Editar Servicio");
            System.out.println("3. Regresar al menú principal");
            int op2 = sc.nextInt();
            switch(op2) {
              case 1:
                agregarServicio(listS);
                menuBack2 = true;
                break;
              case 2:
                editarServicio(listS);
                menuBack2 = true;
                break;
              case 3:
                menuBack2 = false;
                menuBack = true;
                break;
            }
          }while(menuBack2);
          break;
        case 4:
          menuBack = false;
          break;
        case 5:
          System.out.println(listC);
          System.out.println(listP);
          System.out.println(listS);
          menuBack = true;
          break;
      }
    } while(menuBack);
  } 
*/
}