package com.springBootProject2.Ecommerce.RequestDTO;

import com.springBootProject2.Ecommerce.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {

    private int sellerId;
    private String productName;
    private int price;
    private int quantity;
    private ProductCategory productCategory;

}
