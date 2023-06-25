package com.springBootProject2.Ecommerce.Controller;

import com.springBootProject2.Ecommerce.RequestDTO.CustomerRequestDto;
import com.springBootProject2.Ecommerce.ResponseDTO.CardResponseDto;
import com.springBootProject2.Ecommerce.ResponseDTO.CustomerCardResponseDto;
import com.springBootProject2.Ecommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    //1. post : addCustomer
    //2. get : getCustomerCards
    //3. get : customerById
    //4. get : customers
    //5. delete : deleteCustomerById

     @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public String addCustomer(@RequestBody() CustomerRequestDto customerRequestDto)
    {
        return customerService.addCustomer(customerRequestDto);

    }
    @GetMapping("/get/cards/{id}")
    public ResponseEntity getCustomerCards(@PathVariable("id") int id)
    {
        List<CustomerCardResponseDto> customerCards;
        try
        {
                customerCards = customerService.getCustomerCards(id);
        }
        catch(Exception e)
        {
          return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(customerCards,HttpStatus.ACCEPTED);
    }
}
