package com.api.movementsaccount.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "NOMBRE", nullable = false, length = 64)
    private String name;
}
