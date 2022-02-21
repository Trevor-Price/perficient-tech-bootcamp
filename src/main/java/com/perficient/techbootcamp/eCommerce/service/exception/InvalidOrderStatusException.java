package com.perficient.techbootcamp.ecommerce.service.exception;

/**
 * Thrown when a null or invalid OrderStatus was provided.
 */
public class InvalidOrderStatusException extends ServiceException{

    public InvalidOrderStatusException(){
        super();
    }

    public InvalidOrderStatusException(String message){
        super(message);
    }

}
