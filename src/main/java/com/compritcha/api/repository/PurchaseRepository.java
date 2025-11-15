package com.compritcha.api.repository;

import com.compritcha.api.domain.model.Purchase;
import com.compritcha.api.domain.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("SELECT p FROM Purchase p WHERE p.status = :status")
    List<Purchase> findByStatus(Status status);
}