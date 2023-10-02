package com.api.movementsaccount.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CLIENTE")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID_CLI", nullable = false,updatable = false)
    private String idCli;
    @ManyToOne
    @JoinColumn(name = "IDENTIFICACION",nullable = false,foreignKey = @ForeignKey(name = "FK_CLIENTE_PERSONA"))
    private Person person;
    public Customer(String idCli){
        this.idCli = idCli;
    }
}
