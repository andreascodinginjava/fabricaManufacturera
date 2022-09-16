package com.fabrica.demo.Services.Impl;

import com.fabrica.demo.Entities.Categoria;
import com.fabrica.demo.Repositories.CategoriaRepository;
import com.fabrica.demo.Services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> getCategoria() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria createCategoria(Categoria categoria) {
        Optional<Categoria> resultCategoria = categoriaRepository.findById(categoria.getId());

        if (!resultCategoria.isPresent()) {
            return categoriaRepository.save(categoria);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Categoria %s ya existe", categoria.getCategoria()));
        }
    }

    @Override
    public Categoria getCategoriaById(Integer idCategoria) {
        Optional<Categoria> resultCategoria = categoriaRepository.findById(idCategoria);

        if (resultCategoria.isPresent()) {
            return resultCategoria.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("La categoria con id %s no existe", idCategoria));
        }
    }
}
