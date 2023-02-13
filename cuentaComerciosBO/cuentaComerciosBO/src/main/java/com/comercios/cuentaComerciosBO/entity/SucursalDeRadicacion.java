package com.comercios.cuentaComerciosBO.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sucursales_radicacion")
@Data
public class SucursalDeRadicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codigoSucursal;
    private String nombre;
}
