package com.api.personcustomer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GENERO")
public class Gender {
    @Id
    @Column(name = "ID_GEN", nullable = false,updatable = false,length = 1)
    private String idGen;

    @Column(name = "NOMBRE", nullable = false, length = 16)
    private String name;

    public Gender(String idGen){
        this.idGen = idGen;
    }
}
