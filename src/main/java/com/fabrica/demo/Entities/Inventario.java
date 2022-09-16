package com.fabrica.demo.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Inventario")
@Setter
@Getter
@Data
public class Inventario {
    @Id
    @Column(name = "id_registro")
    private Integer id;

    @Column(name = "nombre_producto")
    private String producto;

    @Column(name = "estado_producto")
    private String estado;

    @Column(name = "tipo_elaborado")
    private String tipo;

    @Column(name = "defectuoso")
    private Boolean defectuoso;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fecha_entrada")
    private Date entrada;

    @Column(name = "fecha_salida")
    private Date salida;

    @OneToOne
    @JoinColumn(name = "id_categoria_fk")
    private Categoria categoria;
}
