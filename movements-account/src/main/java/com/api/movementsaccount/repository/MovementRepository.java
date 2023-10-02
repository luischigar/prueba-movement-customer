package com.api.movementsaccount.repository;

import com.api.movementsaccount.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement,String> {
}
