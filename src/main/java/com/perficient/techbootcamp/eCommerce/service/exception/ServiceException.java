package com.perficient.techbootcamp.ecommerce.service.exception;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
public abstract class ServiceException extends RuntimeException{
    private @NonNull String message;
}
