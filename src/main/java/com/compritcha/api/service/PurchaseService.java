package com.compritcha.api.service;

import com.compritcha.api.domain.model.Purchase;
import com.compritcha.api.dto.PurchaseRequest;
import com.compritcha.api.domain.model.Item;
import com.compritcha.api.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public Purchase createPurchase(PurchaseRequest request) {
        Purchase purchase = new Purchase();
        return mapPurchase(request, purchase);
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public Purchase getById(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public Purchase updatePurchase(Long id, PurchaseRequest request) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        return mapPurchase(request, purchase);
    }

    public void deletePurchase(Long id) {
        if (!purchaseRepository.existsById(id)) throw new RuntimeException();
        purchaseRepository.deleteById(id);
    }

    private Purchase mapPurchase(PurchaseRequest request, Purchase purchase) {
        purchase.setDescription(request.getDescription());
        purchase.setStatus(request.getStatus());

        List<Item> items = new ArrayList<>();
        if (request.getItems() != null) {
            request.getItems().forEach(itemReq -> {
                Item item = new Item();
                item.setDescription(itemReq.getDescription());
                item.setValue(itemReq.getValue());
                item.setPurchase(purchase);
                items.add(item);
            });
        }

        purchase.setItems(items);
        return purchaseRepository.save(purchase);
    }
}
