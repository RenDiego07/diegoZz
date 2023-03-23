/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.*;
/**
 *
 * @author diego
 */
import java.io.*;
public class Servicio implements Serializable{
  private int code;
  private String name;
  private double price;
  private static int cont = 0;

  public Servicio(String name, double price) {
    this.name = name;
    this.price = price;
    this.code = cont + 1;
    cont += 1;
  }
  public Servicio(String name){
      this.name= name;
  }
  public Servicio(int code) {
    this.code = code;
  }

  public String toString() {
    return "Nombre: " + name + " Precio: " + price + " Código: " + code;
  }

  public String getName() {
    return name;
  }
  public double getPrice() {
    return price;
  }
  public void setPrice(double price) {
    this.price = price;
  }

  public boolean equals(Object o) {
    if(o == this) {
      return true;
    }
    if(o != null && o instanceof Servicio) {
      Servicio other = (Servicio) o;
      return other.getName().equals(this.getName());
    }
    return false;
  }

    public int getCode() {
        return code;
    }
    public static void serealizarServicios(){
        ArrayList<Servicio>lServices = new ArrayList<>();
        lServices.add(new Servicio("Alineacion",200.34));
        lServices.add(new Servicio( "Balanceo", 304.23));
        lServices.add(new Servicio( "Cambio de aceite motor", 204.23));
        lServices.add(new Servicio( "Cambio filtro aceite", 104.23));
        lServices.add(new Servicio( "Cambio de llantas", 44.23));
        lServices.add(new Servicio( "Revision de luces", 20.23));
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(Constantes.RUTASERVICIOS))){
        
            escritor.writeObject(lServices);
        }catch(IOException t){
            t.printStackTrace();
        }
    }
    public static ArrayList<Servicio> getServices(){
        ArrayList<Servicio>lServices = new ArrayList<>();
        try(ObjectInputStream lector = new ObjectInputStream(new FileInputStream(Constantes.RUTASERVICIOS))){
            lServices = (ArrayList<Servicio>) lector.readObject();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception io){
            io.printStackTrace();
        }
        return lServices;
    }
    public static void AgregarServicios(ArrayList<Servicio> lServices,Servicio c){
        lServices.add(c);
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(Constantes.RUTASERVICIOS))){
            escritor.writeObject(lServices);
        }catch(IOException t){
            t.printStackTrace();
        }
        
        
    }
    public static void removeService(ArrayList<Servicio>lServices, Servicio s){
        lServices.add(s);
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(Constantes.RUTASERVICIOSDELETED))){
            escritor.writeObject(lServices);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void refreshServices(ArrayList<Servicio> lServices){
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(Constantes.RUTASERVICIOS))){
            escritor.writeObject(lServices);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    

}