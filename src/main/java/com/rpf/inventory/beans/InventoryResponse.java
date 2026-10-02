package com.rpf.inventory.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryResponse extends BaseResponse {
    private InventoryBean inventory;
    private List<InventoryBean> inventoryList;
}
