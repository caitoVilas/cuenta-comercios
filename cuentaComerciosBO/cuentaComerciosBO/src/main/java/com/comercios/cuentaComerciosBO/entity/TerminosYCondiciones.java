package com.comercios.cuentaComerciosBO.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "terminos_condiciones")
@SQLDelete(sql = "UPDATE terminos_condiciones SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@Data
public class TerminosYCondiciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;
    private boolean deleted;
    private String archivo;
    private String ruta;
    private LocalDateTime fechaVencimiento;
}
