package com.rpf.inventory.beans;

import com.rpf.inventory.enums.ResponseCodes;
import lombok.Data;

@Data
public class BaseResponse {
    private ResponseCodes status =  ResponseCodes.SUCCESS;
    private HttpError error;
}
