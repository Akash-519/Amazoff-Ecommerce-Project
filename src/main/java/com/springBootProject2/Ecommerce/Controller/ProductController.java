package com.springBootProject2.Ecommerce.Controller;

import com.springBootProject2.Ecommerce.Convertor.ProductConvertor;
import com.springBootProject2.Ecommerce.Enum.ProductCategory;
import com.springBootProject2.Ecommerce.Exception.SellerNotFoundException;
import com.springBootProject2.Ecommerce.Model.Product;
import com.springBootProject2.Ecommerce.Model.Seller;
import com.springBootProject2.Ecommerce.Repository.SellerRepository;
import com.springBootProject2.Ecommerce.RequestDTO.ProductRequestDto;
import com.springBootProject2.Ecommerce.ResponseDTO.ProductResponseDto;
import com.springBootProject2.Ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductService productService;
       @PostMapping("/add")
       public ResponseEntity addProduct(@RequestBody()ProductRequestDto productRequestDto){
           ProductResponseDto productResponseDto;
           try
             {
              productResponseDto = productService.addProduct(productRequestDto);
             }
           catch(Exception e)
           {
               return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
           }

           return new ResponseEntity(productResponseDto,HttpStatus.ACCEPTED);

       }

       @GetMapping("/get/category/{productCategory}")
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable("productCategory") ProductCategory productCategory)
       {
           return productService.getProductsByCategory(productCategory);
       }
}
