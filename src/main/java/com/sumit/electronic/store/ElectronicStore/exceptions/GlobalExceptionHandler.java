package com.sumit.electronic.store.ElectronicStore.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException){
        logger.info("Exception Handler invoked !!");
        return new ResponseEntity("Resource Not Found!!!!!", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        logger.info("Method Argument Not Found Invoke!!");
        List<ObjectError> allError = ex.getBindingResult().getAllErrors();
        Map<String,Object> response = new HashMap<>();
        allError.stream().forEach(objectError ->{
            String message = objectError.getDefaultMessage();
            String field = ((FieldError)objectError).getField();
            response.put(field,message);
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    //handle BAD API Exception
    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<String> resourceNotFoundExceptionHandler(BadApiRequest badApiRequest){
        logger.info("Exception Handler invoked !!");
        return new ResponseEntity("Bad Api request", HttpStatus.BAD_REQUEST);
    }

}
