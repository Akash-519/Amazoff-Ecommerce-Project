package com.springBootProject2.Ecommerce.Service;

import com.springBootProject2.Ecommerce.Convertor.SellerConvertor;
import com.springBootProject2.Ecommerce.Exception.SellerNotFoundException;
import com.springBootProject2.Ecommerce.Model.Seller;
import com.springBootProject2.Ecommerce.Repository.SellerRepository;
import com.springBootProject2.Ecommerce.RequestDTO.SellerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;
    public String addSeller(SellerRequestDto sellerRequestDto)
    {
        //even though sellerRequestDto has all parameters in seller,
        //sellerRepository won't accept it , it only accepts the seller object
        Seller seller = SellerConvertor.SellerRequestDtoToSeller(sellerRequestDto);

        sellerRepository.save(seller);
        return "Congrats ! "+seller.getName()+" You can now sell your products on Market!!!";
    }

    public String deleteSeller(int id) throws SellerNotFoundException {
        try
        {
            sellerRepository.deleteById(id);
        }
        catch (Exception e)
        {
            throw new SellerNotFoundException("Invalid seller id");
        }
        return "Seller with "+id+" has been deleted";
    }
}
