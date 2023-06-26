package com.springBootProject2.Ecommerce.Controller;

import com.springBootProject2.Ecommerce.RequestDTO.OrderedRequestDto;
import com.springBootProject2.Ecommerce.ResponseDTO.OrderedResponseDto;
import com.springBootProject2.Ecommerce.Service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordered")
public class OrderedController {
    @Autowired
    OrderedService orderedService;


    @PostMapping("/place")
public ResponseEntity placeOrder(@RequestBody()OrderedRequestDto orderedRequestDto)
    {
        OrderedResponseDto orderedResponseDto;
        try
        {
            orderedResponseDto = orderedService.placeOrder(orderedRequestDto);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(orderedResponseDto,HttpStatus.ACCEPTED);
    }

}
