package com.application.finances.dto;

import com.application.finances.enums.TransactionPaymentTypeEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class TransactionRequestDto {
    private String purchasePlace;
    private TransactionPaymentTypeEnum paymentType;
    private Timestamp dateCreated;
    private Long purposeId;
    private BigDecimal price;
    private String description;
}
