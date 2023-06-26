package com.springBootProject2.Ecommerce.Service;

import com.springBootProject2.Ecommerce.Enum.ProductStatus;
import com.springBootProject2.Ecommerce.Exception.CustomerNotFoundException;
import com.springBootProject2.Ecommerce.Exception.ProductNotFoundException;
import com.springBootProject2.Ecommerce.Model.*;
import com.springBootProject2.Ecommerce.Repository.CustomerRepository;
import com.springBootProject2.Ecommerce.Repository.ProductRepository;
import com.springBootProject2.Ecommerce.RequestDTO.OrderedRequestDto;
import com.springBootProject2.Ecommerce.ResponseDTO.ItemResponseDto;
import com.springBootProject2.Ecommerce.ResponseDTO.OrderedResponseDto;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OrderedService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    JavaMailSender emailSender;

    public OrderedResponseDto placeOrder(OrderedRequestDto orderedRequestDto) throws Exception {
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


        //set all the entity from child wise to parent and save parent at last
        //Ordered
        Ordered ordered = new Ordered();
        ordered.setTotalCost(orderedRequestDto.getRequiredQuantity()*product.getPrice());
        ordered.setDeliveryCharge(50);
        //to set cardUsed // we assume that customer uses first card
        Card card = customer.getCards().get(0);
        String cardNo = "";
        int cardNoLen = card.getCardNo().length();
        for(int i=0;i<cardNoLen-4;i++)
            cardNo+='X';
        cardNo += card.getCardNo().substring(cardNoLen-4);
        ordered.setCardUsedForPayment(cardNo);

        //to set items to the order, create and set item
        Item item = new Item();
        item.setRequiredQuantity(orderedRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrder(ordered);
        ordered.getItemsList().add(item);
        ordered.setCustomer(customer);

        int leftQuantity = product.getQuantity()- orderedRequestDto.getRequiredQuantity();
        if(leftQuantity<=0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);
        //no need to save product again as the changes are inplace

        customer.getOrders().add(ordered);
        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrders().get(savedCustomer.getOrders().size()-1);



        //lastly prepare response dto
            OrderedResponseDto orderedResponseDto = OrderedResponseDto.builder()
                    .productName(product.getProductName())
                    .quantityOrdered(orderedRequestDto.getRequiredQuantity())

                    .orderDate(savedOrder.getOrderDate())
                    .cardUsedForPayment(cardNo)
                    .productPrice(product.getPrice())
                    .totalCost(savedOrder.getTotalCost())
                    .deliveryCharge(savedOrder.getDeliveryCharge())
                    .build();

        String text = "Congrats !. "+customer.getName()+" . Your Order has been placed ";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("abd.akash630@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Amazoff Shopping! Order Placed.");
        message.setText(text);
        emailSender.send(message);


        return orderedResponseDto;







    }
}
