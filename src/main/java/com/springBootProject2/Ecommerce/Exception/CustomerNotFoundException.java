package com.springBootProject2.Ecommerce.Exception;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(String message)
    {
        super(message);
    }
}
