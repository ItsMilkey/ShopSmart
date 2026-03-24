package cl.duoc.shopsmart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Atrapa cualquier error inesperado en nuestro microservicio y lo devuelve en formato JSON
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> manejarExcepcionGlobal(Exception ex) {
        Map<String, String> respuestaError = new HashMap<>();
        respuestaError.put("error", "Ocurrió un problema interno en el servidor.");
        respuestaError.put("detalle", ex.getMessage());
        
        // Devuelve un status 500 pero con un JSON limpio y manejable
        return new ResponseEntity<>(respuestaError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}