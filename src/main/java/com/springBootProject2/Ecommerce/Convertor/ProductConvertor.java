package com.springBootProject2.Ecommerce.Convertor;

import com.springBootProject2.Ecommerce.Enum.ProductStatus;
import com.springBootProject2.Ecommerce.Model.Product;
import com.springBootProject2.Ecommerce.RequestDTO.ProductRequestDto;
import com.springBootProject2.Ecommerce.ResponseDTO.ProductResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConvertor {
    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto)
    {
        return Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productCategory(productRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }
    public static ProductResponseDto ProductToProductResponseDto(Product product)
    {
        return ProductResponseDto.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
