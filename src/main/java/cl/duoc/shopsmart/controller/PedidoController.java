package cl.duoc.shopsmart.controller;

import cl.duoc.shopsmart.model.Pedido;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private List<Pedido> baseDeDatosPedidos = new ArrayList<>();
    private AtomicLong counter = new AtomicLong();

    public PedidoController() {
        // Añadimos un pedido por defecto de prueba (Ej: El usuario 101 compró 2 unidades del producto 1)
        baseDeDatosPedidos.add(new Pedido(counter.incrementAndGet(), 101L, 1L, 2, 91.98, "PAGADO"));
    }

    // Endpoint 1: GET /pedidos -> Ver todos los pedidos (Ideal para el dashboard de administración)
    @GetMapping
    public List<Pedido> obtenerPedidos() {
        return baseDeDatosPedidos;
    }

    // Endpoint 2: POST /pedidos -> Ingresar un nuevo pedido (Cuando el cliente le da a "Comprar")
    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido nuevoPedido) {
        nuevoPedido.setId(counter.incrementAndGet());
        // Forzamos el estado inicial a PENDIENTE para que luego pase por un procesador de pagos
        nuevoPedido.setEstado("PENDIENTE"); 
        baseDeDatosPedidos.add(nuevoPedido);
        return nuevoPedido;
    }
}