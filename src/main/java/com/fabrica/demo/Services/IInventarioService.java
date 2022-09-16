package com.fabrica.demo.Services;

import com.fabrica.demo.Entities.Inventario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface IInventarioService {
    public List<Inventario> getInventario();
    public Inventario getInventarioById(Integer idProducto);
    public List<Inventario> getInventarioByEstado(String estado);
    public List<Inventario> getInventarioByDefectuoso(Boolean defectuoso);
    public Inventario createInventario(Inventario inventario);
    public void createInventarioMasivo(ArrayList<Inventario> inventario);
    public Inventario updateInventarioSalida(Integer idProducto);
    public Inventario updateInventarioDefectuoso(Integer idProducto);
    public void deleteInventario(Integer idProducto);
}
