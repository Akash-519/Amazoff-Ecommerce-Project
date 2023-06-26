package com.springBootProject2.Ecommerce.ResponseDTO;

import com.springBootProject2.Ecommerce.Enum.ProductCategory;
import com.springBootProject2.Ecommerce.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDto {
    private String productName;
    private int price;
    private ProductCategory productCategory;
    private ProductStatus productStatus;
}
