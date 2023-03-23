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

public abstract class Comerciante implements Serializable {
  protected int code;
  protected String name;
  protected String direccion;
  protected String telefono;
  protected String ID;
  protected static int codigo;

  public Comerciante(String name, String direccion, String telefono) {
    this.name = name;
    this.direccion = direccion;
    this.telefono = telefono;
    
  }
  public Comerciante(String ID, String nombre, String direccion, String telefono){
      this.ID= ID;
      this.name = nombre;      
      this.direccion = direccion;
      this.telefono = telefono;
      this.code= codigo;
  
      
      
  }
  public Comerciante(int code) {
    this.code = code;
  }
  public Comerciante(int code,String name){
      this.code = code;
      this.name = name;
  }



    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getID() {
        return ID;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    

  
}
