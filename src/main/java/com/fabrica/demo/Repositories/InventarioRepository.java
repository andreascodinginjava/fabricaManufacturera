package com.fabrica.demo.Repositories;

import com.fabrica.demo.Entities.Inventario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends CrudRepository<Inventario, Integer> {
    public List<Inventario> findAll();
    public Optional<Inventario> findById(Integer idProducto);
    public List<Inventario> findAllByEstado(String estado);
    public List<Inventario> findAllByDefectuoso(Boolean defectuoso);
}
