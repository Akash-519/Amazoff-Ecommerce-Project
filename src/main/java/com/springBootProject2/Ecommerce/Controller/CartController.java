package com.springBootProject2.Ecommerce.Controller;

import com.springBootProject2.Ecommerce.RequestDTO.OrderedRequestDto;
import com.springBootProject2.Ecommerce.ResponseDTO.OrderedResponseDto;
import com.springBootProject2.Ecommerce.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    //we require the inputs same as orderRequestDto
    @PostMapping("/add")
    public String addToCart(@RequestBody()OrderedRequestDto orderedRequestDto)
    {
        String response = "";
        try
        {
            response = cartService.addToCart(orderedRequestDto);
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
        return response;
    }

    @PostMapping("/checkout/{customerId}")
    public ResponseEntity checkoutCart(@PathVariable("customerId") int customerId)
    {
        List<OrderedResponseDto> orderedResponseDtos;
        try
        {
            orderedResponseDtos = cartService.checkoutCart(customerId);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(orderedResponseDtos,HttpStatus.ACCEPTED);
    }

}
