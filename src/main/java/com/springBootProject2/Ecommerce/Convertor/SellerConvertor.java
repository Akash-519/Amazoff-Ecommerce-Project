package com.springBootProject2.Ecommerce.Convertor;

import com.springBootProject2.Ecommerce.Model.Seller;
import com.springBootProject2.Ecommerce.RequestDTO.SellerRequestDto;

public class SellerConvertor {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto)
    {
        return  Seller.builder()
                .panNo(sellerRequestDto.getPanNo())
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }
}
