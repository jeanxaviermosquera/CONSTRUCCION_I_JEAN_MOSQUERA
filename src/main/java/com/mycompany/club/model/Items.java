
package com.mycompany.club.model;

/**
 * @author Usuario
 */
public class Items {
    private String name;
    private String price;

    public Items() {
    }

    public Items(String name, String price) {
        this.name = name;
        this.price = price;
    }

    //Metodos

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
