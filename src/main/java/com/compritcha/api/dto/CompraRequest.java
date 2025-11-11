package com.compritcha.api.dto;

import java.math.BigDecimal;
import java.util.List;


public class CompraRequest {

    private String descricao;
    private String status;
    private List<ItemRequest> itens;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemRequest> getItens() {
        return itens;
    }

    public void setItens(List<ItemRequest> itens) {
        this.itens = itens;
    }


    public static class ItemRequest {
        private String descricao;
        private BigDecimal valor;
        private Integer idItem;

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }

        public Integer getIdItem() {
            return idItem;
        }

        public void setIdItem(Integer idItem) {
            this.idItem = idItem;
        }
    }
}
