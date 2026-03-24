package cl.duoc.shopsmart.controller;

import cl.duoc.shopsmart.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private List<Usuario> baseDeDatosUsuarios = new ArrayList<>();
    private AtomicLong counter = new AtomicLong();

    public UsuarioController() {
        // Usuario por defecto simulando un cliente antiguo
        baseDeDatosUsuarios.add(new Usuario(counter.incrementAndGet(), "Ana López", "ana.lopez@email.com", "Electrónica"));
    }

    // Endpoint 1: GET /usuarios -> Obtiene la lista de usuarios (Ideal para el motor de recomendaciones)
    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return baseDeDatosUsuarios;
    }

    // Endpoint 2: POST /usuarios -> Registra un nuevo usuario en la plataforma
    @PostMapping
    public Usuario registrarUsuario(@RequestBody Usuario nuevoUsuario) {
        nuevoUsuario.setId(counter.incrementAndGet());
        baseDeDatosUsuarios.add(nuevoUsuario);
        return nuevoUsuario;
    }
}