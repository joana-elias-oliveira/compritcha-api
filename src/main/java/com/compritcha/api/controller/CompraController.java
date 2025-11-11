package com.compritcha.api.controller;

import com.compritcha.api.dto.CompraRequest;
import com.compritcha.api.domain.model.Compra;
import com.compritcha.api.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<Compra> criarCompra(@RequestBody CompraRequest request) {
        Compra compra = compraService.criarCompra(request);
        return ResponseEntity.ok(compra);
    }

    @GetMapping
    public ResponseEntity<List<Compra>> listarTodas() {
        List<Compra> compras = compraService.listarTodas();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> buscarPorId(@PathVariable Long id) {
        Compra compra = compraService.buscarPorId(id);
        if (compra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(compra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> atualizarCompra(
            @PathVariable Long id,
            @RequestBody CompraRequest request) {
        Compra compraAtualizada = compraService.atualizarCompra(id, request);
        if (compraAtualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(compraAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCompra(@PathVariable Long id) {
        boolean removida = compraService.deletarCompra(id);
        if (!removida) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
