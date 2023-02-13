package com.comercios.cuentaComerciosBO.exception;

public class Unauthorized extends RuntimeException{
    public Unauthorized(String msg){
        super(msg);
    }
}
