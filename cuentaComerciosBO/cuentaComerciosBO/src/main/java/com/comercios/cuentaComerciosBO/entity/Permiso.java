package com.comercios.cuentaComerciosBO.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "permisos")
@SQLDelete(sql = "UPDATE permisos SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@Data
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 70)
    private String descripcion;
    private boolean deleted;
}
