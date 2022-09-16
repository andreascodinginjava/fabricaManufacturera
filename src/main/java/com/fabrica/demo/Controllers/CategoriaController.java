package com.fabrica.demo.Controllers;

import com.fabrica.demo.Entities.Categoria;
import com.fabrica.demo.Services.Impl.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fabrica")
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @GetMapping("/categoria")
    public ResponseEntity<List<Categoria>> getCategoria() {
        return new ResponseEntity<List<Categoria>>(service.getCategoria(), HttpStatus.OK);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable("id") Integer idCategoria) {
        return new ResponseEntity<Categoria>(service.getCategoriaById(idCategoria), HttpStatus.OK);
    }

    @PostMapping("/categoria/create")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        return new ResponseEntity<Categoria>(service.createCategoria(categoria), HttpStatus.CREATED);
    }
}
