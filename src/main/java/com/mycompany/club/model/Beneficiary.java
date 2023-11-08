
package com.mycompany.club.model;

/**
 * @author Usuario
 */
public class Beneficiary extends User {
    private String id_socio;

    public Beneficiary() {
    }

    public Beneficiary(String id_socio) {
        this.id_socio = id_socio;
    }

    public Beneficiary(String id_socio, String id, String first_name, String last_name, int document_number, String document_type, int age) {
        super(id, first_name, last_name, document_number, document_type, age);
        this.id_socio = id_socio;
    }

    public String getId_socio() {
        return id_socio;
    }

    public void setId_socio(String id_socio) {
        this.id_socio = id_socio;
    }
    
    
}
  