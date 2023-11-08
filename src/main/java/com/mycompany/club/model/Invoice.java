
package com.mycompany.club.model;

import com.mycompany.club.utils.InvoiceStatus;
import java.util.Date;
import java.util.List;

/**
 * @author Usuario
 */
public class Invoice {
   private String id;
   private String id_socio;
   private String name_socio;
   private String concept;
   private InvoiceStatus status;
   private double amount;
   private Date created_at;
   private Date updated_at;
   private List<Items> items;

    public Invoice() {
    }

    public Invoice(String id, String id_socio, String name_socio, String concept, InvoiceStatus status, double amount, Date created_at, Date updated_at, List<Items> items) {
        this.id = id;
        this.id_socio = id_socio;
        this.name_socio = name_socio;
        this.concept = concept;
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

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
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

    public String getName_socio() {
        return name_socio;
    }

    public void setName_socio(String name_socio) {
        this.name_socio = name_socio;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
    
    

   
}
