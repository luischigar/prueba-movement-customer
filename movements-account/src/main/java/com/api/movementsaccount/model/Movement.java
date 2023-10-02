package com.api.movementsaccount.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MOVIMIENTO")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID_MOV", nullable = false,updatable = false)
    private String idMov;
    @ManyToOne
    @JoinColumn(name = "ID_CUE",nullable = false,foreignKey = @ForeignKey(name = "FK_MOVIMIENTO_CUENTA"))
    private Account account;
    @ManyToOne
    @JoinColumn(name = "ID_TMO",nullable = false,foreignKey = @ForeignKey(name = "FK_MOVIMIENTO_TIPO_MOVIMIENTO"))
    private MovementType movementType;
    @Column(name = "FECHA", nullable = false)
    private LocalDateTime date;
    @Column(name = "VALOR", nullable = false)
    private Double value;
    @Column(name = "SALDO",nullable = false)
    private Double balance;
}
