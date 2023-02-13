package com.comercios.cuentaComercios.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@SQLDelete(sql = "UPDATE roles SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String rolName;
    private boolean deleted;
}
