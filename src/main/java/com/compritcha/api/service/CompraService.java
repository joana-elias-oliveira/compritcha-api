package com.compritcha.api.service;

import com.compritcha.api.dto.CompraRequest;
import com.compritcha.api.domain.model.Compra;
import com.compritcha.api.domain.model.Item;
import com.compritcha.api.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public Compra criarCompra(CompraRequest request) {
        Compra compra = new Compra();
        compra.setDescricao(request.getDescricao());
        compra.setStatus(request.getStatus());

        List<Item> itens = new ArrayList<>();
        if (request.getItens() != null) {
            request.getItens().forEach(itemReq -> {
                Item item = new Item();
                item.setDescricao(itemReq.getDescricao());
                item.setValor(itemReq.getValor());
                item.setCompra(compra);
                itens.add(item);
            });
        }

        compra.setItens(itens);
        return compraRepository.save(compra);
    }

    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }

    public Compra buscarPorId(Long id) {
        Optional<Compra> compra = compraRepository.findById(id);
        return compra.orElse(null);
    }

    public Compra atualizarCompra(Long id, CompraRequest request) {
        Optional<Compra> compraOpt = compraRepository.findById(id);
        if (compraOpt.isEmpty()) return null;

        Compra compra = compraOpt.get();
        compra.setDescricao(request.getDescricao());
        compra.setStatus(request.getStatus());

        List<Item> novosItens = new ArrayList<>();
        if (request.getItens() != null) {
            request.getItens().forEach(itemReq -> {
                Item item = new Item();
                item.setDescricao(itemReq.getDescricao());
                item.setValor(itemReq.getValor());
                item.setCompra(compra);
                novosItens.add(item);
            });
        }

        compra.setItens(novosItens);
        return compraRepository.save(compra);
    }

    public boolean deletarCompra(Long id) {
        if (!compraRepository.existsById(id)) return false;
        compraRepository.deleteById(id);
        return true;
    }
}
