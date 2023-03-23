/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.*;
import java.util.*;

/**
 *
 * @author diego
 */

import java.util.ArrayList;
import java.io.*;


public abstract class Usuario implements Serializable{
  protected String user;
  protected String pass;
  protected String name;
  protected String level;

  public Usuario(String user, String pass, String name, String level) {
    this.user = user;
    this.pass = pass;
    this.name = name;
    this.level = level;
  }
  @Override 
public boolean equals(Object obj){
    if (this == obj){
        return true;
    }else if (obj != null && obj instanceof Usuario){
        Usuario other = (Usuario) obj;
        return (this.user.equals(other.user) && this.pass.equals(other.pass) );
    }
    return false;
  }
public static void serealizar(){
    try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(Constantes.RUTAUSUARIOS))){
        ArrayList<Usuario>lUsers = new ArrayList<>();
        lUsers.add(new Admin("admin1","12345678","Administrador","admin"));
        lUsers.add(new Tecnico("alopez","al123456","Alvaro Lopez","tecnico"));
        lUsers.add(new Cobranza("mcastro","mc123456","Maria Castro","cobranzas"));
        lUsers.add(new Tecnico("mbarcos","mb123456","Mario Barcos","tecnico"));
        escritor.writeObject(lUsers);
    
    }catch(IOException io){
        io.printStackTrace();
    }
   
}
public static ArrayList<Usuario> obtenerUsuarios(String ruta){
    ArrayList<Usuario> lUsers = new ArrayList<>();
    try(ObjectInputStream lector = new ObjectInputStream(new FileInputStream(ruta))){
        lUsers = (ArrayList<Usuario>) lector.readObject();
        
    }catch(IOException e){
        e.printStackTrace();
    }catch(Exception e){
        e.printStackTrace();
    }
    return lUsers;
    
}

  // metodo para cargar la lista de usuarios

  


  // public abstract void showMenu();

    public String getName() {
        return name;
    }
}