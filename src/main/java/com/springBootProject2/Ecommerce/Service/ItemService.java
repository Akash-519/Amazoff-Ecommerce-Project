package com.springBootProject2.Ecommerce.Service;

import com.springBootProject2.Ecommerce.Convertor.ItemConvertor;
import com.springBootProject2.Ecommerce.Exception.ProductNotFoundException;
import com.springBootProject2.Ecommerce.Model.Item;
import com.springBootProject2.Ecommerce.Model.Product;
import com.springBootProject2.Ecommerce.Repository.ProductRepository;
import com.springBootProject2.Ecommerce.ResponseDTO.ItemResponseDto;
import com.springBootProject2.Ecommerce.ResponseDTO.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ProductRepository productRepository;
    public ItemResponseDto viewItem(int productId) throws ProductNotFoundException {
        Product product1;
        try
        {
            product1 = productRepository.findById(productId).get();
        }
        catch(Exception e)
        {
            throw new ProductNotFoundException("Sorry! Invalid Product id");
        }

      Item item = Item.builder()
              .requiredQuantity(0)
              .product(product1)
              .build();

        //and also map this item to the product, as this product is also an item now
        product1.setItem(item);
        //save both
        productRepository.save(product1);

        //prapre the Item response dto
        ItemResponseDto itemResponseDto = ItemConvertor.ProductToItempResponseDto(product1);

        return itemResponseDto;




    }
}
