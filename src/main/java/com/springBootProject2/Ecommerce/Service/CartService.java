package com.springBootProject2.Ecommerce.Service;

import com.springBootProject2.Ecommerce.Enum.ProductStatus;
import com.springBootProject2.Ecommerce.Exception.CustomerNotFoundException;
import com.springBootProject2.Ecommerce.Exception.ProductNotFoundException;
import com.springBootProject2.Ecommerce.Model.*;
import com.springBootProject2.Ecommerce.Repository.CustomerRepository;
import com.springBootProject2.Ecommerce.Repository.ProductRepository;
import com.springBootProject2.Ecommerce.RequestDTO.OrderedRequestDto;
import com.springBootProject2.Ecommerce.ResponseDTO.OrderedResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderedService orderedService;
    @Autowired
    JavaMailSender emailSender;

    public String addToCart(OrderedRequestDto orderedRequestDto) throws Exception {
        Customer customer;
        try
        {
            customer = customerRepository.findById(orderedRequestDto.getCustomerId()).get();

        }
        catch(Exception e)
        {
            throw new CustomerNotFoundException("Invalid Customer id");
        }

        Product product;
        try
        {
            product = productRepository.findById(orderedRequestDto.getProductId()).get();
        }
        catch(Exception e )
        {
            throw new ProductNotFoundException("Invalid Product Id");
        }
        if(product.getQuantity() < orderedRequestDto.getRequiredQuantity())
        {
            throw new Exception("Sorry! Required Quantity not available");
        }

        //now get the cart of the current customer
        Cart cart = customer.getCart();
        int newTotalCost = orderedRequestDto.getRequiredQuantity()*product.getPrice()+cart.getCartTotal();
        cart.setCartTotal(newTotalCost);


        //set item
        Item item = new Item();
        item.setRequiredQuantity(orderedRequestDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.getItems().add(item);//cart is all set


        // now check all the objects involved
        customerRepository.save(customer);

        return "Hi "+customer.getName()+"\n Item "+product.getProductName()+" has been added to your Cart!";

    }

    public List<OrderedResponseDto> checkoutCart(int customerId) throws Exception {
        Customer customer;
        try
        {
            customer = customerRepository.findById(customerId).get();

        }
        catch(Exception e)
        {
            throw new CustomerNotFoundException("Invalid Customer id");
        }

        Cart cart = customer.getCart();
        List<OrderedResponseDto> orderedResponseDtos = new ArrayList<>();
        for(Item item : cart.getItems())
        {
            Ordered ordered = new Ordered();

            ordered.setTotalCost(item.getRequiredQuantity()*item.getProduct().getPrice());
            ordered.setDeliveryCharge(0);
            ordered.setCustomer(customer);
            item.setOrder(ordered);
            ordered.getItemsList().add(item);

            Card card = customer.getCards().get(0);
            String cardNo = "";
            int cardNoLen = card.getCardNo().length();
            for(int i=0;i<cardNoLen-4;i++)
                cardNo+='X';
            cardNo += card.getCardNo().substring(cardNoLen-4);
            ordered.setCardUsedForPayment(cardNo);



            int leftQuantity = item.getProduct().getQuantity()- item.getRequiredQuantity();
            if(leftQuantity<=0)
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            item.getProduct().setQuantity(leftQuantity);

            customer.getOrders().add(ordered);

            OrderedResponseDto orderedResponseDto = OrderedResponseDto.builder()
                    .productName(item.getProduct().getProductName())
                    .quantityOrdered(item.getRequiredQuantity())

                    .orderDate(ordered.getOrderDate())
                    .cardUsedForPayment(cardNo)
                    .productPrice(item.getProduct().getPrice())
                    .totalCost(ordered.getTotalCost())
                    .deliveryCharge(ordered.getDeliveryCharge())
                    .build();

            orderedResponseDtos.add(orderedResponseDto);
        }

        //now after checkout cart set cart as empty
        cart.setCartTotal(0);
        cart.setItems(new ArrayList<>());

        customerRepository.save(customer);

        String text = "Congrats !. "+customer.getName()+" . Your Order has been placed ";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("abd.akash630@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Amazoff Shopping ! Order Placed.");
        message.setText(text);
        emailSender.send(message);

        return orderedResponseDtos;


    }
}
