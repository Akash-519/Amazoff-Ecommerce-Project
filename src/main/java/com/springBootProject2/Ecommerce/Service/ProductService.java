package com.springBootProject2.Ecommerce.Service;

import com.springBootProject2.Ecommerce.Convertor.ProductConvertor;
import com.springBootProject2.Ecommerce.Enum.ProductCategory;
import com.springBootProject2.Ecommerce.Exception.SellerNotFoundException;
import com.springBootProject2.Ecommerce.Model.Product;
import com.springBootProject2.Ecommerce.Model.Seller;
import com.springBootProject2.Ecommerce.Repository.ProductRepository;
import com.springBootProject2.Ecommerce.Repository.SellerRepository;
import com.springBootProject2.Ecommerce.RequestDTO.ProductRequestDto;
import com.springBootProject2.Ecommerce.ResponseDTO.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
            private ProductRepository productRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
        Seller seller;
        try {
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();

        } catch (Exception e) {
            throw new SellerNotFoundException("Invalid seller id");
        }
        Product product = ProductConvertor.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);

        //saved the seller and product - parent and child
        sellerRepository.save(seller);

        //prepare and returning productResponseDto
        ProductResponseDto productResponseDto= ProductConvertor.ProductToProductResponseDto(product);
        return productResponseDto;
    }

    public List<ProductResponseDto> getProductsByCategory(ProductCategory productCategory)
    {
            List<Product> products= productRepository.findAllByProductCategory(productCategory);
            List<ProductResponseDto> productResponseDtos = new ArrayList<>();
            for(Product product : products) {
                ProductResponseDto productResponseDto = ProductConvertor.ProductToProductResponseDto(product);
                productResponseDtos.add(productResponseDto);
            }

          return productResponseDtos;


    }
}
