/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author diego
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Cobranza extends Usuario implements Serializable {
  public Cobranza(String user, String pass, String name, String level) {
    super(user, pass, name, level);
    Comerciante.codigo++;
  }

  public static void generarFactura(ArrayList<Orden> lo, ArrayList<Cliente> lc) {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Ingrese código de la empresa: ");
    int id = sc.nextInt();
    sc.nextLine();
    System.out.println("Ingrese mes y año en este formato (mm-yy): ");
    String monYe = "xx-" + sc.nextLine();
    Cliente c = new Cliente(id);
    Orden o1 = new Orden(c, monYe);
    System.out.println(o1);
    if(lc.contains(c)) {
      int index = lc.indexOf(c);
      c = lc.get(index);
    }
    double vp = 0;

    System.out.println("Empresa: " + c.name);
    System.out.println("Periodo de facturación: " + monYe);
    System.out.println("Detalles de servicios: ");
    System.out.println("#Placa     Fecha     Tipo     Servicio      Cantidad   Total");
    for(Orden o: lo) {
      if(o1.equals(o)) {
        ArrayList<Servicio> listS = o.getServicesList();
        for(int i = 0; i < listS.size(); i++) {
          System.out.println(o.getPlaca() + "    " + o.getDate() + "    " + o.getVehType() + "     " + listS.get(i).getName() + "    1    " + listS.get(i).getPrice());
        }
        vp += o.valorPagar();
      }
    } 
    System.out.println("Total a Pagar = " + vp);
  }
  public static void repIngresosServicios(ArrayList<Servicio> ls, ArrayList<Orden> lo) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Ingrese mes y año en el formato (mm-yy): ");
    String date = "xx-" + sc.nextLine();
    Orden o1 = new Orden(new Cliente(0), date);
  
    for(int i = 0; i < ls.size(); i++) {
      double total = 0;
      for(Orden o: lo) {
        if(o1.equals2(o)) {
          ArrayList<Servicio> ser = o.getServicesList();
          for(Servicio s: ser) {
            if(s.equals(ls.get(i))) {
              total += s.getPrice();
            }
          }
        }
      }
      System.out.println(ls.get(i).getName() + ": " + total);
    }
  }
  public static void repAtencionesTecnico(ArrayList<Orden> lo) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Ingrese mes y año en el formato (mm-yy): ");
    String date = "xx-" + sc.nextLine();
    Orden o1 = new Orden(new Cliente(0), date);

    for(Orden o: lo) {
      if(o1.equals2(o)) {
        Tecnico t = o.getTec();
      }
    }
    
  }

  public static void showMenu(ArrayList<Orden> listO, ArrayList<Cliente> listC, ArrayList<Servicio> listS) {
    Scanner sc = new Scanner(System.in);
    boolean menuBack = false;
    
    do {
      System.out.println("Menú nivel cobranzas");
      System.out.println("--------------------");
      System.out.println("1. Generar facturas a empresas");
      System.out.println("2. Reporte de ingresos por servicio");
      System.out.println("3. Reporte de atenciones por técnico");
      System.out.println("--------------------");
      System.out.println("Escoja opción: ");
      int op = sc.nextInt();

      switch(op) {
        case 1:
          generarFactura(listO, listC);
          menuBack = true;
          break;
        case 2:
          repIngresosServicios(listS, listO);
          menuBack = true;
          break;
      }
    }while(menuBack);
  }
}