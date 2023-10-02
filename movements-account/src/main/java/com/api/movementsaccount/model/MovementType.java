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
@Table(name = "TIPO_MOVIMIENTO")
public class MovementType {
    @Id
    @Column(name = "ID_TMO", nullable = false,updatable = false,length = 1)
    private String idTmo;

    @Column(name = "NOMBRE", nullable = false, length = 16)
    private String name;
    public MovementType(String idTmo){
        this.idTmo = idTmo;
    }
}
