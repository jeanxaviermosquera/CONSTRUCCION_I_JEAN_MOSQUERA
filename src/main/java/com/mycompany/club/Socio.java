
package com.mycompany.club;

/**
 * @author Usuario
 */
public class Socio {
   private String id;
   private String nombre;
   private String apellidos;
   private String cedula; 
   private double fondos;
   private String tipoSubscripcion;

    public Socio() {
    }

    public Socio(String id, String nombre, String apellidos, String cedula, double fondos, String tipoSubscripcion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.fondos = fondos;
        this.tipoSubscripcion = tipoSubscripcion;
    }
  
   //Metodos

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public double getFondos() {
        return fondos;
    }

    public void setFondos(double fondos) {
        this.fondos = fondos;
    }

    public String getTipoSubscripcion() {
        return tipoSubscripcion;
    }

    public void setTipoSubscripcion(String tipoSubscripcion) {
        this.tipoSubscripcion = tipoSubscripcion;
    }
}
