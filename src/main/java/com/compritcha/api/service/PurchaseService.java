package com.compritcha.api.service;

import com.compritcha.api.domain.model.Purchase;
import com.compritcha.api.domain.model.enums.Status;
import com.compritcha.api.dto.PurchaseRequest;
import com.compritcha.api.domain.model.Item;
import com.compritcha.api.dto.PurchaseRequest.ItemRequest;
import com.compritcha.api.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public Purchase createPurchase(PurchaseRequest request) {
        Purchase purchase = new Purchase();
        return mapPurchase(request, purchase);
    }

    public List<Purchase> getPurchasesByStatus(Status status) {
        if (status != null)
            return purchaseRepository.findByStatus(status);

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
        purchase.setTotal(request.getTotal());

        if (purchase.getItems() != null) purchase.getItems().clear();
        else purchase.setItems(new ArrayList<>());

        if (request.getItems() != null) {
            for (ItemRequest itemReq : request.getItems()) {
                Item item = new Item();
                item.setDescription(itemReq.getDescription());
                item.setValue(itemReq.getValue());
                item.setPurchase(purchase);
                purchase.getItems().add(item);
            }
        }

        return purchaseRepository.save(purchase);
    }

    public Purchase updateStatus(Long id, Status status) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        purchase.setStatus(status);
        return purchaseRepository.save(purchase);
    }

}
