package com.abee.supervisor.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Embeddable
@Data
public class UserPK implements Serializable {

    @Column(name = "src_ip")
    private String ip;

    @Column(name = "src_port")
    private Integer port;

    public UserPK(){}

    public UserPK(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public String toString() {
        return ip + ":" + port;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            if (o.toString().equals(this.toString())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
