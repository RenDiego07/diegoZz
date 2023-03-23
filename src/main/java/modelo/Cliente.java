/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author diego
 */
import java.util.*;
import java.io.*;

public class Cliente extends Comerciante implements Serializable {
  private String tipoCliente;
  
  


  public Cliente(int code) {
    super(code);
  }
  public Cliente(int code, String name){
      super(code,name);
  }
  public Cliente(String cedula,String name,String direccion, String telefono,String tipoCliente){
      super(cedula,name,direccion,telefono);
      this.tipoCliente= tipoCliente;
      Comerciante.codigo++;
  }

  public String getTipoCliente() {
    return tipoCliente;
  }

  // toString
  public String toString() {
    String result ="cedula"+ID+ " Nombre:" + name + " Dirección: " + direccion + " Teléfono: " + telefono + " Tipo de cliente:   " + tipoCliente + " Código: " + code;

    return result;
  }
  
  public static void Serealizar(){
      ArrayList<Cliente> lClients = new ArrayList<>();
          lClients.add(new Cliente("099843928","Alex", "abc", "123456789", "Personal"));
          lClients.add(new Cliente("0983429837","Canvas", "adc", "125156789", "Empresarial"));
          lClients.add(new Cliente("09394802932","Holcim", "csdcds", "5646516", "Empresarial"));
          lClients.add(new Cliente("09829384732","Maria", "acds", "121517", "Personal"));   
          try(ObjectOutputStream escritor = new ObjectOutputStream( new FileOutputStream(Constantes.RUTACLIENTES))){
              escritor.writeObject(lClients);
          }catch(IOException e){
              e.printStackTrace();
          }
  }
  public static ArrayList<Cliente> getClients(){
      ArrayList<Cliente> lClients = new ArrayList<>();
      try(ObjectInputStream lector = new ObjectInputStream(new FileInputStream(Constantes.RUTACLIENTES))){
          lClients=(ArrayList<Cliente>)lector.readObject();
          
      }catch(IOException io){
          io.printStackTrace();
      }catch(Exception e){
          e.printStackTrace();
      }
    return lClients;  
  }

 
    public static void agregarCliente(ArrayList<Cliente> l){
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(Constantes.RUTACLIENTES))){
        escritor.writeObject(l);
        }catch(IOException oi){
            oi.printStackTrace();
        }
       
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    public static void setCliente(Cliente c, ArrayList<Cliente> lClients, String nombre,String direccion,String telefono,String tipoCliente){
        int indice= lClients.indexOf(c);
        lClients.get(indice).setName(nombre);
        lClients.get(indice).setDireccion(direccion);
        lClients.get(indice).setTelefono(telefono);
        lClients.get(indice).setTipoCliente(tipoCliente);
        
    }
    public static void clientsRemoved(ArrayList<Cliente> lRemoved, Cliente c){
        lRemoved.add(c);
        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(Constantes.RUTACLIENTESBORRADOS))){
            escritor.writeObject(lRemoved);
        }catch(IOException r){
            r.printStackTrace();
        }
    }
        public static void removeClient(ArrayList<Cliente>lClients, Cliente c){
            int index = lClients.indexOf(c);
            lClients.remove(index);
            try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(Constantes.RUTASERVICIOS))){
                escritor.writeObject(lClients);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    public boolean equals(Object o){
        if (this == o){
            return true;
        }else if(o!= null && o instanceof Cliente){
            Cliente other = (Cliente) o;
            return (this.name).equals(other.name) || this.code == other.code;
        }
        
        
        return false;
    }
    

    
   
}