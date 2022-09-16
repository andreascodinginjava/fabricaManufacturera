package com.fabrica.demo.Services;

import com.fabrica.demo.Entities.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoriaService {
    public List<Categoria> getCategoria();
    public Categoria createCategoria(Categoria categoria);
    public Categoria getCategoriaById(Integer idCategoria);
}
