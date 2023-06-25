package com.springBootProject2.Ecommerce.Controller;

import com.springBootProject2.Ecommerce.Exception.CustomerNotFoundException;
import com.springBootProject2.Ecommerce.RequestDTO.CardRequestDto;
import com.springBootProject2.Ecommerce.ResponseDTO.CardResponseDto;
import com.springBootProject2.Ecommerce.Service.CardService;
import com.springBootProject2.Ecommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    //1. post : addCard
    //2. delete : removeCard
    //3. delete : removeAllCardsByCustomerId

    @Autowired
    CardService cardService;
    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody() CardRequestDto cardRequestDto)
    {
        CardResponseDto cardResponseDto;
        try{
            cardResponseDto = cardService.addCard(cardRequestDto);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cardResponseDto,HttpStatus.ACCEPTED);


    }
}
