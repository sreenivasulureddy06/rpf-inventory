package com.rpf.inventory.dao.entity;

import com.rpf.inventory.beans.InventoryBean;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "RPF_INVENTORY")
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORD_ID")
    private Long recordId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "STATUS")
    private String status;

    public InventoryBean populateData() {
        InventoryBean bean = new InventoryBean();
        bean.setRecordId(this.recordId);
        bean.setName(this.name);
        bean.setPrice(this.price);
        bean.setStatus(this.status);
        return bean;
    }
}
