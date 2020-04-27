package com.abee.supervisor.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author xincong yao
 */
@Entity
@Table(name = "user")
@Data
public class User {

    @EmbeddedId
    private UserPK userPK;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "state")
    private Integer state;

    public User(){}

    public User(String ip, Integer port, String serialNumber, Integer state) {
        this.userPK = new UserPK(ip, port);
        this.serialNumber = serialNumber;
        this.state = state;
    }
}
