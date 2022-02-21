package com.perficient.techbootcamp.ecommerce.service.exception;

/**
 * Thrown when a Product could not be found.
 */
public class ProductNotFoundException extends ServiceException{

    public ProductNotFoundException(){
        super();
    }

    public ProductNotFoundException(String message){
        super(message);
    }

}
