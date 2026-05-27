package com.gimnasio.clases.controller;

import com.gimnasio.clases.dto.ClaseDTO;
import com.gimnasio.clases.model.Clase;
import com.gimnasio.clases.service.ClaseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    private static final Logger log = LoggerFactory.getLogger(ClaseController.class);

    private final ClaseService service;

    public ClaseController(ClaseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Clase>> listar() {
        log.info("GET /api/clases");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/futuras")
    public ResponseEntity<List<Clase>> listarFuturas() {
        return ResponseEntity.ok(service.listarFuturas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clase> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Clase> crear(@Valid @RequestBody ClaseDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clase> actualizar(@PathVariable Long id, @Valid @RequestBody ClaseDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @PatchMapping("/{id}/decrementar-cupo")
    public ResponseEntity<Clase> decrementarCupo(@PathVariable Long id) {
        return ResponseEntity.ok(service.decrementarCupo(id));
    }

    @PatchMapping("/{id}/incrementar-cupo")
    public ResponseEntity<Clase> incrementarCupo(@PathVariable Long id) {
        return ResponseEntity.ok(service.incrementarCupo(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
