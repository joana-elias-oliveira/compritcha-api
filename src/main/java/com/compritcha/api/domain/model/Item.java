package com.compritcha.api.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    // 🔹 Relação com a compra principal
    @ManyToOne
    @JoinColumn(name = "compra_id")
    @JsonBackReference
    private Compra compra;

    // 🔹 Relação com item pai (para subitens)
    @ManyToOne
    @JoinColumn(name = "item_pai_id")
    private Item itemPai;

    // 🔹 Subitens
    @OneToMany(mappedBy = "itemPai", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Item> subitens;

    // 🔹 Calcula o valor total (item + subitens)
    @Transient
    public double getValorTotal() {
        double total = valor != null ? valor.doubleValue() : 0;
        if (subitens != null) {
            total += subitens.stream().mapToDouble(Item::getValorTotal).sum();
        }
        return total;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Item getItemPai() {
        return itemPai;
    }

    public void setItemPai(Item itemPai) {
        this.itemPai = itemPai;
    }

    public List<Item> getSubitens() {
        return subitens;
    }

    public void setSubitens(List<Item> subitens) {
        this.subitens = subitens;
    }
}
