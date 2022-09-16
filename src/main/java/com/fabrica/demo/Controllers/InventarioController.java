package com.fabrica.demo.Controllers;

import com.fabrica.demo.Entities.Inventario;
import com.fabrica.demo.Services.Impl.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fabrica")
public class InventarioController {
    @Autowired
    private InventarioService service;

    @GetMapping("/inventario")
    public ResponseEntity<List<Inventario>> getInventario() {
        return new ResponseEntity<List<Inventario>>(service.getInventario(), HttpStatus.OK);
    }

    @GetMapping("/inventario/{id}")
    public ResponseEntity<Inventario> getInventarioById(@PathVariable("id") Integer idProducto) {
        return new ResponseEntity<Inventario>(service.getInventarioById(idProducto), HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Inventario>> getInventarioByEstado(@PathVariable("estado") String estado) {
        return new ResponseEntity<List<Inventario>>(service.getInventarioByEstado(estado), HttpStatus.OK);
    }

    @GetMapping("/defectuoso/{defectuoso}")
    public ResponseEntity<List<Inventario>> getInventarioByDefectuoso(@PathVariable("defectuoso") Boolean defectuoso) {
        return new ResponseEntity<List<Inventario>>(service.getInventarioByDefectuoso(defectuoso), HttpStatus.OK);
    }

    @PostMapping("/inventario/create")
    public ResponseEntity<Inventario> createInventario(@RequestBody Inventario inventario) {
        return new ResponseEntity<Inventario>(service.createInventario(inventario), HttpStatus.CREATED);
    }

    @PostMapping("/inventario/create/masivo")
    public ResponseEntity<Void> createInventarioMasivo(@RequestBody ArrayList<Inventario> inventario) {
        service.createInventarioMasivo(inventario);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/update/defectuoso/{id}")
    public ResponseEntity<Inventario> updateInventarioDefectuoso(@PathVariable("id") Integer idProducto) {
        return new ResponseEntity<Inventario>(service.updateInventarioDefectuoso(idProducto), HttpStatus.CREATED);
    }

    @PutMapping("/update/salida/{id}")
    public ResponseEntity<Inventario> updateInventarioSalida(@PathVariable("id") Integer idProducto) {
        return new ResponseEntity<Inventario>(service.updateInventarioSalida(idProducto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable("id") Integer idProducto) {
        service.deleteInventario(idProducto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
