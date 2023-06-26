package com.springBootProject2.Ecommerce.Controller;

import com.springBootProject2.Ecommerce.RequestDTO.SellerRequestDto;
import com.springBootProject2.Ecommerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {
    //1. post : addSeller
    //get all sellers
    // get a seller by pancard
    // find sellers of a particular age
    //delete a seller by id

    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public String addSeller(@RequestBody()SellerRequestDto sellerRequestDto)
    {
       return sellerService.addSeller(sellerRequestDto);

    }

    @DeleteMapping("/delete/{id}")
    public String deleteSeller(@PathVariable("id") int id)
    {
        String response;
        try
        {
            response = sellerService.deleteSeller(id);
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
        return response;
    }



}
