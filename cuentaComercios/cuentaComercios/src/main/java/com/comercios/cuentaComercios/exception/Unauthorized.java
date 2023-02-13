package com.comercios.cuentaComercios.exception;

public class Unauthorized extends RuntimeException{
    public Unauthorized(String msg){
        super(msg);
    }
}
