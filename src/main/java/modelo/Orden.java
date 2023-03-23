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
import java.util.ArrayList;

public class Orden implements Serializable {
  private Tecnico tec; 
  private Cliente client;
  private String date;
  private String vehType;
  private String placa;


  private ArrayList<Servicio> servicesList;

  public Orden(Tecnico tec, Cliente client, String date, String vehType, String placa, ArrayList<Servicio>servicesList) {
    this.tec = tec;
    this.client = client;
    this.date = date;
    this.vehType = vehType;
    this.placa = placa;
    this.servicesList = servicesList;
  }
  public Orden(Cliente client, String date) {
    this.client = client;
    this.date = date;
  }

  public String getDate() {
    return date;
}
  public String getPlaca() {
    return placa;
}
  public String getVehType() {
    return vehType;
}
  public ArrayList<Servicio> getServicesList() {
    return servicesList;
}
  public Tecnico getTec() {
    return tec;
  }

  public String toString() {
    return "Cliente: " + client + " | Fecha: " + date + " | Servicios: " + servicesList;
  }
    public Cliente getClient() {
        return client;
    }
  public double valorPagar() {
    double vp = 0;
    for(Servicio s: servicesList) {
      vp += s.getPrice();
    }
    return vp;
  }

  public boolean equals(Object o) {
    if(o == this) {
      return true;
    }
    if(o != null && o instanceof Orden) {
      Orden other = (Orden) o;
      Cliente cO = other.client;
      Cliente cT = this.client;
      String[] partsT = this.date.split("-");
      String[] partsO = other.date.split("-");
      if(cO.getTipoCliente().equals("Empresarial")) {
        return (cO.equals(cT)) && (partsO[1].equals(partsT[1])) && (partsO[2].equals(partsT[2]));
      }
    }
    return false;
  }
  public boolean equals2(Object o) {
    if(o != null && o instanceof Orden) {
      Orden other = (Orden) o;
      String[] partsO = other.date.split("-");
      String[] partsT = this.date.split("-");

      return (partsO[1].equals(partsT[1])) && (partsO[2].equals(partsT[2]));
    }
    return false;
  }
  public static void SerealizarOrden(String stream, ArrayList<Orden> lOrden){
      try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(stream)) ){
          escritor.writeObject(lOrden);
          
      }catch(IOException e){
          e.printStackTrace();
      }
  }
  public static ArrayList<Orden> getLopezList(){
      ArrayList<Orden> lOrder = new ArrayList<>();
      try(ObjectInputStream lector = new ObjectInputStream(new FileInputStream(Constantes.RUTAORDENESLOPEZ))){
          lOrder = (ArrayList<Orden>) lector.readObject();
      }catch(IOException e){
      e.printStackTrace();
      }catch(Exception w){
          w.printStackTrace();
      }
      return lOrder;
  }
  public static ArrayList<Orden> getBarcosList(){
      ArrayList<Orden> lOrder = new ArrayList<>();
      try(ObjectInputStream lector = new ObjectInputStream(new FileInputStream(Constantes.RUTAORDENESBARCOS))){
          lOrder = (ArrayList<Orden>) lector.readObject();
      }catch(IOException e){
      e.printStackTrace();
      }catch(Exception w){
          w.printStackTrace();
      }
      return lOrder;
  }
    public static void LopezSerealizar(){
        ArrayList<Orden> lOrder = new ArrayList<>();
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(Constantes.RUTAORDENESLOPEZ))){
            escritor.writeObject(lOrder);
        }catch(IOException  y){
            y.printStackTrace();
        }
    }
    public static void BarcosSerealizar(){
        ArrayList<Orden> lOrder = new ArrayList<>();
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(Constantes.RUTAORDENESBARCOS))){
            escritor.writeObject(lOrder);
        }catch(IOException  y){
            y.printStackTrace();
        }
    }
    public String getClientName(){
        return this.getClient().getName();
    }
    public String getClientCod(){
        return String.valueOf(this.getClient().getCode());
    }
    
    public double getOrderPrice(){
        double totalPrice=0;
        for(Servicio serv: this.getServicesList()){
            totalPrice+=serv.getPrice();
        }
        return totalPrice;
    }
  
  
}