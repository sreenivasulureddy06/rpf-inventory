package com.rpf.inventory;

import com.rpf.inventory.dao.InventoryDao;
import com.rpf.inventory.dao.entity.Inventory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryApplication {

	@Autowired
	private InventoryDao dao;

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@PostConstruct
	public void addData() {
		for(int i=1;i<1000;i++) {
			Inventory inventory = new Inventory();
			inventory.setName("Prod "+i);
			inventory.setPrice(10000d+i);
			inventory.setStatus("AV");
			dao.save(inventory);
		}
	}
}
