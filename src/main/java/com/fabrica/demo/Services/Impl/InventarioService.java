package com.fabrica.demo.Services.Impl;

import com.fabrica.demo.Entities.Categoria;
import com.fabrica.demo.Entities.Inventario;
import com.fabrica.demo.Repositories.CategoriaRepository;
import com.fabrica.demo.Repositories.InventarioRepository;
import com.fabrica.demo.Services.IInventarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioService implements IInventarioService {
    private static final Logger log = LoggerFactory.getLogger(InventarioService.class);
    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    //Lista del inventario
    @Override
    public List<Inventario> getInventario() {
        return inventarioRepository.findAll();
    }

    @Override
    public Inventario getInventarioById(Integer idProducto) {
        Optional<Inventario> resultInventario = inventarioRepository.findById(idProducto);

        if (resultInventario.isPresent()) {
            return resultInventario.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El producto con id %s no existe", idProducto));
        }
    }

    //Lista del inventario dependiendiendo el estado es ACTIVO o INACTIVO.
    @Override
    public List<Inventario> getInventarioByEstado(String estado) {
        return inventarioRepository.findAllByEstado(estado);
    }

    //Lista del inventario defectuoso o no defectuoso
    @Override
    public List<Inventario> getInventarioByDefectuoso(Boolean defectuoso) {
        return inventarioRepository.findAllByDefectuoso(defectuoso);
    }

    //Crear inventario
    @Override
    public Inventario createInventario(Inventario inventario) {
        Optional<Categoria> resultCategoria = categoriaRepository.findById(inventario.getCategoria().getId());
        Optional<Inventario> resultInventario = inventarioRepository.findById(inventario.getId());

        if (!resultCategoria.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("La categoria con id %s no existe", inventario.getCategoria().getId()));
        } else if (!inventario.getEstado().equals("ACTIVO") && !inventario.getEstado().equals("INACTIVO")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Los valores para el estado del producto pueden ser ACTIVO o INACTIVO"));
        } else if (!inventario.getTipo().equals("MAQUINA") && !inventario.getTipo().equals("MANO")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Los valores para el tipo de elaboracion del producto pueden ser MANO o MAQUINA"));
        } else if (!resultInventario.isPresent() && resultCategoria.isPresent()) {
            return inventarioRepository.save(inventario);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Producto %s,  ya existe", inventario.getProducto()));
        }
    }

    @Override
    public void createInventarioMasivo(ArrayList<Inventario> inventario) {
        for (Inventario inventarioAux : inventario) {
            Optional<Categoria> resultCategoria = categoriaRepository.findById(inventarioAux.getCategoria().getId());
            Optional<Inventario> resultInventario = inventarioRepository.findById(inventarioAux.getId());

            if (!resultCategoria.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("La categoria con id %s no existe", inventarioAux.getCategoria().getId()));
            }else if (!inventarioAux.getEstado().equals("ACTIVO") && !inventarioAux.getEstado().equals("INACTIVO")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Los valores para el estado del producto pueden ser ACTIVO o INACTIVO"));
            } else if (!inventarioAux.getTipo().equals("MAQUINA") && !inventarioAux.getTipo().equals("MANO")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Los valores para el tipo de elaboracion del producto pueden ser MANO o MAQUINA"));
            } else if (!resultInventario.isPresent() && resultCategoria.isPresent()) {
                inventarioRepository.save(inventarioAux);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Producto %s,  ya existe", inventarioAux.getProducto()));
            }
        }
    }

    //Actualizar salida de un inventario
    @Override
    public Inventario updateInventarioSalida(Integer idProducto) {
        Optional<Inventario> resultInventario = inventarioRepository.findById(idProducto);

        if (resultInventario.isPresent()) {
            Inventario inventarioAux = resultInventario.get();
            inventarioAux.setSalida(new Date());
            return inventarioRepository.save(inventarioAux);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Producto con id %s no existe", idProducto));
        }
    }

    //Actualizar si un producto es defectuoso o no
    @Override
    public Inventario updateInventarioDefectuoso(Integer idProducto) {
        Optional<Inventario> resultInventario = inventarioRepository.findById(idProducto);

        if (resultInventario.isPresent()) {
            Inventario inventarioAux = resultInventario.get();
            inventarioAux.setDefectuoso(true);
            return inventarioRepository.save(inventarioAux);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Producto con id %s no existe", idProducto));
        }
    }

    //Eliminar un producto del inventario - Cambia su estado a inactivo
    @Override
    public void deleteInventario(Integer idProducto) {
        Optional<Inventario> resultInventario = inventarioRepository.findById(idProducto);

        if (resultInventario.isPresent()) {
            resultInventario.get().setEstado("INACTIVO");
            inventarioRepository.save(resultInventario.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Producto con id %s no existe", idProducto));
        }
    }
}
