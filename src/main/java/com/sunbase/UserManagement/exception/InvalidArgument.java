package com.sunbase.UserManagement.exception;

public class InvalidArgument extends RuntimeException{

    public InvalidArgument(String message){
        super(message);
    }

    public InvalidArgument(String message,Throwable cause){
        super(message, cause);
    }
}
