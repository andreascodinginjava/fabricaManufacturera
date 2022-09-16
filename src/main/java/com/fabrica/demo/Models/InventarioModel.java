package com.fabrica.demo.Models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class InventarioModel {
    @SerializedName("id_registro")
    @Expose
    private Integer idRegistro;
    @SerializedName("nombre_producto")
    @Expose
    private String nombreProducto;
    @SerializedName("estado_producto")
    @Expose
    private String estadoProducto;
    @SerializedName("tipo_elaborado")
    @Expose
    private String tipoElaborado;
    @SerializedName("defectuoso")
    @Expose
    private Boolean defectuoso;
    @SerializedName("comentario")
    @Expose
    private String comentario;
    @SerializedName("id_categoria_FK")
    @Expose
    private Integer idCategoriaFK;

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(String estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public String getTipoElaborado() {
        return tipoElaborado;
    }

    public void setTipoElaborado(String tipoElaborado) {
        this.tipoElaborado = tipoElaborado;
    }

    public Boolean getDefectuoso() {
        return defectuoso;
    }

    public void setDefectuoso(Boolean defectuoso) {
        this.defectuoso = defectuoso;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getIdCategoriaFK() {
        return idCategoriaFK;
    }

    public void setIdCategoriaFK(Integer idCategoriaFK) {
        this.idCategoriaFK = idCategoriaFK;
    }
}
