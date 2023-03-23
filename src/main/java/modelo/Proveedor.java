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

public class Proveedor extends Comerciante implements Serializable{
  

  public Proveedor(String name, String direccion, String telefono) {
    super(name, direccion, telefono);
    Comerciante.codigo++;
  }
  public Proveedor(String ID, String name, String direccion, String telefono){
      super(ID,name,direccion,telefono);
      Comerciante.codigo++;
  }

  public String toString() {
    return "Nombre: " + name + " Código: " + code;
  }
  public static void cargarProveedores(){
      ArrayList<Proveedor>listProvee = new ArrayList<>();
      listProvee.add(new Proveedor("3931028983","Juan", "csdcscs", "615616"));
      listProvee.add(new Proveedor("312390123098","Marcos", "csdcsa", "16513"));      
      try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(Constantes.RUTAPROVEEDORES))){
          escritor.writeObject(listProvee);
      }catch(IOException p){
          p.printStackTrace();
      }
  }
  public static ArrayList<Proveedor> getProveedores(){
      ArrayList<Proveedor>lProveedores = new ArrayList<>();
      try(ObjectInputStream lector = new ObjectInputStream(new FileInputStream(Constantes.RUTAPROVEEDORES))){
          lProveedores = (ArrayList<Proveedor>) lector.readObject();
          
      }catch(IOException p){
          p.printStackTrace();
      }catch(Exception e){
          e.printStackTrace();
      }
      return lProveedores;
  }
  public static void agregarProveedor(ArrayList<Proveedor> l){
      try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(Constantes.RUTAPROVEEDORES))){
          escritor.writeObject(l);
      }catch(IOException o){
          o.printStackTrace();
      }
      
  }
  public static void setProveedor(Proveedor pro, ArrayList<Proveedor> lProveedores,String nombre, String direccion,String telefono){
      int indice = lProveedores.indexOf(pro);
      lProveedores.get(indice).setName(nombre);
      lProveedores.get(indice).setDireccion(direccion);
      lProveedores.get(indice).setTelefono(telefono);
      
  }
 public static void ProveedoresBorrados(ArrayList<Proveedor> lProveedores, Proveedor pro){
    lProveedores.add(pro);
    try(ObjectOutputStream escritor =new ObjectOutputStream( new FileOutputStream(Constantes.RUTAPROVEEDORESBORRADOS))){
        escritor.writeObject(lProveedores);
    }catch(IOException e){
        e.printStackTrace();
    }
}
 public static void removerProveedor(ArrayList<Proveedor>lProveedor, Proveedor pro){
     int indice = lProveedor.indexOf(pro);
     lProveedor.remove(indice);
 }
 
  
}