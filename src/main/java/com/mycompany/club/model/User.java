
package com.mycompany.club.model;

/**
 * @author User
 */
public abstract class User {
    //definicion de atributos
    private String id;
    private String first_name;
    private String last_name;
    private String document_number; 
    private String document_type;
    private int age;

    //constructor sin parametros
    public User() {}

    //constructores con parametros

    public User(String id, String first_name, String last_name, String document_number, String document_type, int age) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.document_number = document_number;
        this.document_type = document_type;
        this.age = age;
    }

    //metodos getter y setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

   
}

