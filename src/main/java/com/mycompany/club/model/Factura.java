
package com.mycompany.club.model;

import java.util.Date;
import java.util.List;

/**
 * @author Usuario
 */
public class Factura {
   private String id;
   private String id_socio;
   private String status;
   private double amount;
   private Date created_at;
   private Date updated_at;
   private List<Items> items;

    public Factura() {
    }

    public Factura(String id, String id_socio, String status, double amount, Date created_at, Date updated_at, List<Items> items) {
        this.id = id;
        this.id_socio = id_socio;
        this.status = status;
        this.amount = amount;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.items = items;
    }

    
   
   //Metodos

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_socio() {
        return id_socio;
    }

    public void setId_socio(String id_socio) {
        this.id_socio = id_socio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

   
}
