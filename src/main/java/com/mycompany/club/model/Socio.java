
package com.mycompany.club.model;



/**
 * @author User
 */
public class Socio extends User{ 

    public Socio() {
    }

    public Socio(String id, String first_name, String last_name, String document_number, String document_type, int age) {
        super(id, first_name, last_name, document_number, document_type, age);
    }
    
}