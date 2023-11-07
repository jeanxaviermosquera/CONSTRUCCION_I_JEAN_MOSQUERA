
package com.mycompany.club;

import java.util.Date;

/**
 * @author Usuario
 */
public class Membership {
    private String id;
    private String type;
    private Date created_at;
    private Date updated_at;
    private String status;

    public Membership() {
    }

    public Membership(String id, String type, Date created_at, Date updated_at, String status) {
        this.id = id;
        this.type = type;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
    }
    
    
    //Metodos

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
