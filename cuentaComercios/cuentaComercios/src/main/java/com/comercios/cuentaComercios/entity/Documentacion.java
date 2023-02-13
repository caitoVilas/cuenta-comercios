package com.comercios.cuentaComercios.entity;

import com.comercios.cuentaComercios.enums.DocumentacionTipo;
import com.comercios.cuentaComercios.enums.EstadoDocumentacion;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documentacion")
@Data
public class Documentacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DocumentacionTipo documentacionTipo;
    private String archivo;
    private String ruta;
    @CreationTimestamp
    private LocalDateTime created;
    private LocalDateTime fechaVencimiento;
    @Enumerated(EnumType.STRING)
    private EstadoDocumentacion estado;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "comercio_id")
    private Comercio comercio;
}
