package com.springBootProject2.Ecommerce.Service;

import com.springBootProject2.Ecommerce.Convertor.CardConvertor;
import com.springBootProject2.Ecommerce.Convertor.CustomerConvertor;
import com.springBootProject2.Ecommerce.Exception.CustomerNotFoundException;
import com.springBootProject2.Ecommerce.Model.Card;
import com.springBootProject2.Ecommerce.Model.Customer;
import com.springBootProject2.Ecommerce.Repository.CardRepository;
import com.springBootProject2.Ecommerce.Repository.CustomerRepository;
import com.springBootProject2.Ecommerce.RequestDTO.CardRequestDto;
import com.springBootProject2.Ecommerce.ResponseDTO.CardDto;
import com.springBootProject2.Ecommerce.ResponseDTO.CardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {
        Customer customer;
        try{
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }
        catch(Exception e)
        {
            throw new CustomerNotFoundException("Invalid Customer Id");
        }

        //make a card convertor from request to card
        Card card = CardConvertor.CardRequestDtoToCard(cardRequestDto);

        //add card to current cardlist of customer
        customer.getCards().add(card);
        card.setCustomer(customer);
        customerRepository.save(customer);

        //prepare response dto
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setName(customer.getName());
        // to set cards, convert every card to cardDto
        List<CardDto> cardDtoList = new ArrayList<>();
        for(Card card1 : customer.getCards())
        {
            CardDto cardDto = new CardDto();
            cardDto.setCardType(card.getCardType());
            cardDto.setCardNo(card.getCardNo());
            cardDtoList.add(cardDto);
        }
        cardResponseDto.setCards(cardDtoList);

        return cardResponseDto;



    }
}
