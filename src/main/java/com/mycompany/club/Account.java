
package com.mycompany.club;

import java.util.Date;

/**
 * @author Usuario
 */
public class Account {
    private String id;
    private String id_socio;
    private double money;
    private Date created_at;
    private Date updated_at;

    public Account() {
    }

    public Account(String id, String id_socio, double money, Date created_at, Date updated_at) {
        this.id = id;
        this.id_socio = id_socio;
        this.money = money;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
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
    
}
