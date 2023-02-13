package com.comercios.cuentaComerciosBO.service.contract;

public interface PersonaService {
    boolean existeXDni(String dni);
    boolean existeXEmail(String email);
}
