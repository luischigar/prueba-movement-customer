package com.api.movementsaccount.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUENTA")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID_CUE", nullable = false,updatable = false)
    private String idCue;
    @ManyToOne
    @JoinColumn(name = "ID_CLI",nullable = false,foreignKey = @ForeignKey(name = "FK_CUENTA_CLIENTE"))
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "ID_TCU",nullable = false,foreignKey = @ForeignKey(name = "FK_CUENTA_TIPO_CUENTA"))
    private AccountType accountType;
    @Column(name = "NUMERO_CUENTA",nullable = false,length = 10)
    private String accountNumber;
    @Column(name = "SALDO_INICIAL", nullable = false)
    private Double initialBalance;
    @Column(name = "ESTADO", nullable = false)
    private Boolean state;
    public Account(String idCue){
        this.idCue = idCue;
    }
}
