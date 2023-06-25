package com.springBootProject2.Ecommerce.Convertor;

import com.springBootProject2.Ecommerce.Model.Customer;
import com.springBootProject2.Ecommerce.RequestDTO.CustomerRequestDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConvertor {
    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto)
    {
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .email(customerRequestDto.getEmail())
                .mobNo(customerRequestDto.getMobNo())
                .build();
    }
}
