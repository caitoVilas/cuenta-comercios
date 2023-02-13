package com.comercios.cuentaComercios.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "personas")
@SQLDelete(sql = "UPDATE personas SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@Data
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String nombre;
    @Column(length = 50)
    private String apellido;
    @Column(length = 70)
    private String email;
    @Column(length = 8)
    private String dni;
    @Column(length = 50)
    private String telefono;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;
    private boolean deleted;
}
