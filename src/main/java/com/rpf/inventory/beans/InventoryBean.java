package com.rpf.inventory.beans;

import com.rpf.inventory.dao.entity.Inventory;
import lombok.Data;

@Data
public class InventoryBean {
    private Long recordId;

    private String name;

    private Double price;

    private String status;

    private Long userId;
}
