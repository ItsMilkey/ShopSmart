package cl.duoc.shopsmart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private Long id;
    private Long usuarioId;
    private Long productoId;
    private Integer cantidad;
    private Double total;
    private String estado; // Ej: "PENDIENTE", "PAGADO", "ENVIADO"
}