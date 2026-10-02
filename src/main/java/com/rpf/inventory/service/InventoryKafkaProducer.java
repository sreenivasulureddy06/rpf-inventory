package com.rpf.inventory.service;

import com.rpf.inventory.beans.InventoryBean;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class InventoryKafkaProducer {

    @Value("${inventory.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, InventoryBean> kafkaTemplate;

    public InventoryKafkaProducer(
            KafkaTemplate<String, InventoryBean> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInventory(InventoryBean inventory) {
        log.info("Inventory topic name '{}' and  product id '{}'", topicName, inventory.getRecordId());
        kafkaTemplate.send(
                topicName,
                inventory.getRecordId().toString(),
                inventory
        );
    }
}
