package com.api.personcustomer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSONA")
public class Person {
    @Id
    @Column(name = "IDENTIFICACION", nullable = false,updatable = false,length = 13)
    private String identification;

    @ManyToOne
    @JoinColumn(name = "ID_GEN",nullable = false,foreignKey = @ForeignKey(name = "FK_PERSONA_GENERO"))
    private Gender gender;

    @Column(name = "NOMBRE", nullable = false, length = 64)
    private String name;

    @Column(name = "EDAD")
    private Integer age;

    @Column(name = "DIRECCION",nullable = false,length = 128)
    private String address;

    @Column(name = "TELEFONO", nullable = false,length = 16)
    private String phone;
}
