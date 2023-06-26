package com.springBootProject2.Ecommerce.Exception;

public class ProductNotFoundException extends Exception
{
    public ProductNotFoundException(String message)
    {
        super(message);
    }
}
