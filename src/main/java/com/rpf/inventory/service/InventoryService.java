package com.rpf.inventory.service;

import com.rpf.inventory.beans.HttpError;
import com.rpf.inventory.beans.InventoryBean;
import com.rpf.inventory.beans.InventoryResponse;
import com.rpf.inventory.dao.InventoryDao;
import com.rpf.inventory.dao.entity.Inventory;
import com.rpf.inventory.dao.repo.InventoryRepo;
import com.rpf.inventory.enums.ResponseCodes;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryDao dao;

    private final InventoryKafkaProducer kafkaProducer;

    public InventoryService(InventoryDao dao, InventoryKafkaProducer kafkaProducer) {
        this.dao = dao;
        this.kafkaProducer = kafkaProducer;
    }


    public ResponseEntity<InventoryResponse> findAll() {
        List<Inventory> data = dao.findAll();
        InventoryResponse response = new InventoryResponse();
        response.setInventoryList(
                data.stream().map(Inventory::populateData)
                        .collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(response);
    }

    public ResponseEntity<InventoryResponse> deleteProduct(Long productId) {
        InventoryResponse response = new InventoryResponse();
        if(dao.deleteProduct(productId)) {
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(response);
        }
        response.setStatus(ResponseCodes.FAILURE);
        response.setError(new HttpError("1000", "Error in product deleteion"));
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(response);
    }

    public ResponseEntity<InventoryResponse> addToCart(Long productId) {
        InventoryResponse response = new InventoryResponse();
        Optional<Inventory> inventory = dao.findById(productId);
        if(inventory.isPresent()) {
            InventoryBean bean = inventory.get().populateData();
            bean.setUserId(1L);
            kafkaProducer.sendInventory(bean);
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(response);
    }
}
