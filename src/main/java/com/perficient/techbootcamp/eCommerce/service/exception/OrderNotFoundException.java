package com.perficient.techbootcamp.ecommerce.service.exception;

/**
 * Thrown when an Order could not be found.
 */
public class OrderNotFoundException extends ServiceException{

    public OrderNotFoundException(){
        super();
    }

    public OrderNotFoundException(String message){
        super(message);
    }

}
