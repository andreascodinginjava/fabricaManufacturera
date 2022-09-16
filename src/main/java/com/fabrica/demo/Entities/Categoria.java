package com.fabrica.demo.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Categoria")
@Setter
@Getter
@Data
public class Categoria {
    @Id
    @Column(name = "id_categoria")
    private Integer id;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "desc_categoria")
    private String descripcion;
}
