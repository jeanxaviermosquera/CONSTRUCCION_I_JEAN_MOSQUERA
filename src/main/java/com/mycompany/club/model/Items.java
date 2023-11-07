
package com.mycompany.club.model;

/**
 * @author Usuario
 */
public class Items {
    private String name;
    private String price;
    private String id;

    public Items() {
    }

    public Items(String name, String price, String id) {
        this.name = name;
        this.price = price;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
