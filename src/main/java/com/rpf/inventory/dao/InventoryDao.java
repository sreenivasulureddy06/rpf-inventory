package com.rpf.inventory.dao;

import com.rpf.inventory.dao.entity.Inventory;
import com.rpf.inventory.dao.repo.InventoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InventoryDao {

    private final InventoryRepo repo;

    public InventoryDao(InventoryRepo repo) {
        this.repo = repo;
    }

    public List<Inventory> findAll() {
        return repo.findAll();
    }

    public Inventory save(Inventory inventory) {
        return repo.save(inventory);
    }

    public boolean deleteProduct(Long productId) {
        Optional<Inventory> obj = repo.findById(productId);
        if(obj.isPresent()) {
            repo.delete(obj.get());
            return true;
        }
        return false;
    }

    public Optional<Inventory> findById(Long productId) {
        return repo.findById(productId);
    }
}
