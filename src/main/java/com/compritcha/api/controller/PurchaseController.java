package com.compritcha.api.controller;

import com.compritcha.api.domain.model.Purchase;
import com.compritcha.api.dto.PurchaseRequest;
import com.compritcha.api.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseRequest request) {
        Purchase purchase = purchaseService.createPurchase(request);
        return ResponseEntity.ok(purchase);
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getPurchases() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getById(@PathVariable Long id) {
        try {
            Purchase purchase = purchaseService.getById(id);
            return ResponseEntity.ok(purchase);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(
            @PathVariable Long id,
            @RequestBody PurchaseRequest request) {
        try {
            Purchase updatedPurchase = purchaseService.updatePurchase(id, request);
            return ResponseEntity.ok(updatedPurchase);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
        try {
            purchaseService.deletePurchase(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
