package com.abee.supervisor.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author xincong yao
 */
@Entity
@Table(name = "organization")
@Data
public class Organization {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "serial_number")
    private String serialNumber;

    public Organization() {}

    public Organization(String username, String password, String serialNumber) {
        this.username = username;
        this.password = password;
        this.serialNumber = serialNumber;
    }

    public Organization(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
