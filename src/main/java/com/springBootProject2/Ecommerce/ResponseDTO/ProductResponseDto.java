package com.springBootProject2.Ecommerce.ResponseDTO;

import com.springBootProject2.Ecommerce.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private String productName;
    private int price;
    private int quantity;
    private ProductStatus productStatus;
}
