package com.rpf.inventory.controller;

import com.rpf.inventory.beans.InventoryResponse;
import com.rpf.inventory.dao.entity.Inventory;
import com.rpf.inventory.service.InventoryService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/find/all")
    public ResponseEntity<InventoryResponse> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<InventoryResponse> deleteProduct(@PathVariable @PathParam("productId") Long productId) {
        return service.deleteProduct(productId);
    }

    @PostMapping("/add/cart/{productId}")
    public ResponseEntity<InventoryResponse> addToCart(@PathVariable @PathParam("productId") Long productId) {
        return service.addToCart(productId);
    }
}
