package com.springBootProject2.Ecommerce.Convertor;

import com.springBootProject2.Ecommerce.Model.Product;
import com.springBootProject2.Ecommerce.ResponseDTO.ItemResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemConvertor {
    public static ItemResponseDto ProductToItempResponseDto(Product product)
    {
        return ItemResponseDto.builder()
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .productStatus(product.getProductStatus())
                .productName(product.getProductName())
                .build();
    }
}
