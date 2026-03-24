package cl.duoc.shopsmart.controller;

import cl.duoc.shopsmart.model.Producto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private List<Producto> inventario = new ArrayList<>();
    private AtomicLong counter = new AtomicLong();

    public ProductoController() {
        inventario.add(new Producto(counter.incrementAndGet(), "Aurculares Inalámbricos", 45.99, 100));
    }
    @GetMapping
    public List<Producto> obtenerProductos() {
        return inventario;
    }
    @PostMapping
    public Producto agregarProducto(@RequestBody Producto nuevoProducto) {
        nuevoProducto.setId(counter.incrementAndGet());
        inventario.add(nuevoProducto);
        return nuevoProducto;
    }
}