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

public class Tecnico extends Usuario implements Serializable {
  public Tecnico(String user, String pass, String name, String level) {
    super(user, pass, name, level);
  }
  

  public static void generarOrden(ArrayList<Servicio> ls, ArrayList<Orden> lo, ArrayList<Cliente> lc, Tecnico t) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Código del cliente: ");
    int cCode = sc.nextInt();
    sc.nextLine();
    System.out.println("Fecha del Servicio: ");
    String sDate = sc.nextLine();
    System.out.println("Tipo de vehículo (1-Automóvil 2-Motocicleta 3-Bus): ");
    String vType = sc.nextLine();
    System.out.println("Placa del vehículo: ");
    String vPlaca = sc.nextLine();

    int num = 0;
    ArrayList<Servicio> sList = new ArrayList<>();
    do {
      System.out.println("Ingrese código del servicio: ");
      int sCode = sc.nextInt();
      Servicio s = new Servicio(sCode);
      if(ls.contains(s) && sCode != -1) {
        int index = ls.indexOf(s);
        Servicio service = ls.get(index);
        sList.add(service);
      }
      num = sCode;
    }while(num != -1);

    Cliente c = new Cliente(cCode);
    if(lc.contains(c)) {
      int index = lc.indexOf(c);
      c = lc.get(index);
    }
    Orden o = new Orden(t, c, sDate, vType, vPlaca, sList);
    lo.add(o);

    System.out.println("El valor total a pagar es de: " + o.valorPagar());
  }
  public static void reporteInsumos() {
    Scanner sc = new Scanner(System.in);
    String mail = "mail@mail.com";
    
    System.out.println("Descripción: ");
    String description = sc.nextLine();
    System.out.println("¿Desea confirmar el envío? (S/N): ");
    String op = sc.nextLine();
    if(op.equals("S") || op.equals("s")) {
      System.out.println("Enviando a " + mail);
    }else {
      System.out.println("Mensaje desechado");
    }
  }

  public static void showMenu(ArrayList<Servicio> listS, ArrayList<Orden> listO, ArrayList<Cliente> listC, Tecnico t) {
    Scanner sc = new Scanner(System.in);

    boolean menuBack = false;
    do {
      System.out.println("Menú nivel Técnico");
      System.out.println("------------------");
      System.out.println("1. Generar orden de servicios");
      System.out.println("2. Reportar falta de insumos");
      System.out.println("3. Salir");
      System.out.println("------------------");
      System.out.println("Escoja opción: ");
      int op = sc.nextInt();
  
      switch(op) {
        case 1:
          generarOrden(listS, listO, listC, t);
          menuBack = true;
          break;
        case 2:
          reporteInsumos();
          menuBack = true;
          break;
        case 3:
          menuBack = false;
          break;
        case 4:
          System.out.println(listO);
          menuBack = true;
      }      
    }while(menuBack);

  }
}