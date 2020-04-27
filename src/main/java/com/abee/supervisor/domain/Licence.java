package com.abee.supervisor.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Random;
import java.util.UUID;

/**
 * @author xincong yao
 */
@Entity
@Table(name = "licence")
@Data
public class Licence {

    @Id
    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "type")
    private Integer type;

    @Column(name = "current_number")
    private int currentNumber;

    public Licence(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        this.serialNumber = sb.toString();
        this.currentNumber = 0;
    }

    public Licence(Integer type) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        this.serialNumber = sb.toString();
        this.type = type;
        this.currentNumber = 0;
    }
}
