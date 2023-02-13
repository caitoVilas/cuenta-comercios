package com.comercios.cuentaComercios.service.contract;

public interface PersonaService {
    boolean existePorDni(String dni);
    boolean existePorMail(String email);
}
