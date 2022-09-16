package com.fabrica.demo.Repositories;

import com.fabrica.demo.Entities.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
    public List<Categoria> findAll();
    public Optional<Categoria> findById(Integer idCategoria);
}
