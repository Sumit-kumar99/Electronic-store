package com.sumit.electronic.store.ElectronicStore.exceptions;

import lombok.Builder;

@Builder
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource not found !! ");
    }

    public ResourceNotFoundException(String s){
        super(s);
    }

}
