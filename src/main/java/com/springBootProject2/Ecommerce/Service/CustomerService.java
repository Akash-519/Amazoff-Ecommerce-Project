package com.springBootProject2.Ecommerce.Service;

import com.springBootProject2.Ecommerce.Controller.CustomerController;
import com.springBootProject2.Ecommerce.Convertor.CustomerConvertor;
import com.springBootProject2.Ecommerce.Exception.CustomerNotFoundException;
import com.springBootProject2.Ecommerce.Model.Card;
import com.springBootProject2.Ecommerce.Model.Cart;
import com.springBootProject2.Ecommerce.Model.Customer;
import com.springBootProject2.Ecommerce.Repository.CustomerRepository;
import com.springBootProject2.Ecommerce.RequestDTO.CustomerRequestDto;
import com.springBootProject2.Ecommerce.ResponseDTO.CardResponseDto;
import com.springBootProject2.Ecommerce.ResponseDTO.CustomerCardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    public String addCustomer(CustomerRequestDto customerRequestDto)
    {
        Customer customer = CustomerConvertor.CustomerRequestDtoToCustomer(customerRequestDto);

        //it is a parent and cart is set to customer at the time of registration
        // so we should set cart

        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        //set the cart to customer
        customer.setCart(cart);

        //this will save both customer and cart in database
        customerRepository.save(customer);
        return "Hi "+customer.getName()+". Wellcome to Amazoff! \n  Enjoy your shopping!";
    }

    public List<CustomerCardResponseDto> getCustomerCards(int id) throws CustomerNotFoundException {
        Customer customer;
        try {
            customer = customerRepository.findById(id).get();
        }
        catch(Exception e)
        {
            throw new CustomerNotFoundException("Invalid cutomer id");
        }
        List<CustomerCardResponseDto> customerCards = new ArrayList<>();
        for(Card card : customer.getCards())
        {
            CustomerCardResponseDto cs = new CustomerCardResponseDto();
            cs.setCardNo(card.getCardNo());
            cs.setCardType(card.getCardType());
            customerCards.add(cs);
        }
        return customerCards;
    }
}
