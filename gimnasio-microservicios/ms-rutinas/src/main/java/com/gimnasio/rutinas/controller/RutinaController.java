package com.gimnasio.rutinas.controller;

import com.gimnasio.rutinas.dto.RutinaDTO;
import com.gimnasio.rutinas.model.Rutina;
import com.gimnasio.rutinas.service.RutinaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
public class RutinaController {

    private static final Logger log = LoggerFactory.getLogger(RutinaController.class);

    private final RutinaService service;

    public RutinaController(RutinaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Rutina>> listar() {
        log.info("GET /api/rutinas");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rutina> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/socio/{socioId}")
    public ResponseEntity<List<Rutina>> porSocio(@PathVariable Long socioId) {
        return ResponseEntity.ok(service.listarPorSocio(socioId));
    }

    @PostMapping
    public ResponseEntity<Rutina> crear(@Valid @RequestBody RutinaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rutina> actualizar(@PathVariable Long id, @Valid @RequestBody RutinaDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
