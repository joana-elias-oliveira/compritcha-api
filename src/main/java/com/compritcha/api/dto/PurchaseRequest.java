package com.compritcha.api.dto;

import com.compritcha.api.domain.model.enums.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseRequest {

    private String description;
    private Status status;
    private List<ItemRequest> items;
    private String total;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Data
    public static class ItemRequest {
        private String description;
        private BigDecimal value;
        private Integer itemId;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }

        public Integer getItemId() {
            return itemId;
        }

        public void setItemId(Integer itemId) {
            this.itemId = itemId;
        }
    }
}
