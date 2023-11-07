
package com.mycompany.club.model;

import com.mycompany.club.utils.MembershipStatus;
import com.mycompany.club.utils.MembershipType;
import java.util.Date;

/**
 * @author Usuario
 */
public class Membership {
    private String id;
    private String id_socio;
    private MembershipType type;
    private Date created_at;
    private Date updated_at;
    private MembershipStatus status;

    public Membership() {}

    public Membership(String id, String id_socio, MembershipType type, Date created_at, Date updated_at, MembershipStatus status) {
        this.id = id;
        this.id_socio = id_socio;
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

    public String getId_socio() {
        return id_socio;
    }

    public void setId_socio(String id_socio) {
        this.id_socio = id_socio;
    }

    public MembershipType getType() {
        return type;
    }

    public void setType(MembershipType type) {
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

    public MembershipStatus getStatus() {
        return status;
    }

    public void setStatus(MembershipStatus status) {
        this.status = status;
    }

   
}
