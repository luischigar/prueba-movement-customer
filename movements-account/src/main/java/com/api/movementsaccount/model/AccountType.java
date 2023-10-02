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
@Table(name = "TIPO_CUENTA")
public class AccountType {
    @Id
    @Column(name = "ID_TCU", nullable = false,updatable = false,length = 1)
    private String idTcu;

    @Column(name = "NOMBRE", nullable = false, length = 16)
    private String name;
    public AccountType(String idTcu){
        this.idTcu = idTcu;
    }
}
