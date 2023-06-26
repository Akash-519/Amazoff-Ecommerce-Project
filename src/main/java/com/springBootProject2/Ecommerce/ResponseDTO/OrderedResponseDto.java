package com.springBootProject2.Ecommerce.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OrderedResponseDto {
    private String productName;
    private Date orderDate;
    private int productPrice;
    private int quantityOrdered;
    private int totalCost;
    private int deliveryCharge;
    private String cardUsedForPayment;

}
