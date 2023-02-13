package com.comercios.cuentaComercios.entity;

import com.comercios.cuentaComercios.enums.EstadoComercio;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comercios")
@SQLDelete(sql = "UPDATE comercios SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@Data
public class Comercio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 70)
    private String razonSocial;
    private String domicilio;
    @Column(length = 30)
    private String localidad;
    @Column(length = 30)
    private String provincia;
    @Column(length = 70)
    private String email;
    @Column(length = 15)
    private String cuit;
    @Column(length = 3)
    private String categoria;
    private String telefono;
    private boolean limite;
    @Enumerated(EnumType.STRING)
    private EstadoComercio estado;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;
    private boolean deleted;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Usuario usuario;
    @OneToMany(mappedBy = "comercio")
    private List<Documentacion> documentacion;
}
