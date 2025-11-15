package com.compritcha.api.dto;

import com.compritcha.api.domain.model.enums.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PurchaseRequest {

    private String description;
    private Status status;
    private List<ItemRequest> items;
    private String total;

    @Data
    public static class ItemRequest {
        private String description;
        private BigDecimal value;
        private Integer itemId;
    }
}
