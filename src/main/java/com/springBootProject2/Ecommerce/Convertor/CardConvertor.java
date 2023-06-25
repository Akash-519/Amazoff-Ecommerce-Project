package com.springBootProject2.Ecommerce.Convertor;

import com.springBootProject2.Ecommerce.Model.Card;
import com.springBootProject2.Ecommerce.RequestDTO.CardRequestDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public  class CardConvertor {
    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto)
    {
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())

                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .build();
    }
}
