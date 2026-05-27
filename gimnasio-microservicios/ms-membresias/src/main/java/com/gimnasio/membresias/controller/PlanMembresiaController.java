package com.gimnasio.membresias.controller;

import com.gimnasio.membresias.dto.PlanMembresiaDTO;
import com.gimnasio.membresias.model.PlanMembresia;
import com.gimnasio.membresias.service.PlanMembresiaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
public class PlanMembresiaController {

    private static final Logger log = LoggerFactory.getLogger(PlanMembresiaController.class);

    private final PlanMembresiaService service;

    public PlanMembresiaController(PlanMembresiaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PlanMembresia>> listar() {
        log.info("GET /api/planes");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<PlanMembresia>> listarActivos() {
        return ResponseEntity.ok(service.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanMembresia> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PlanMembresia> crear(@Valid @RequestBody PlanMembresiaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanMembresia> actualizar(@PathVariable Long id,
                                                    @Valid @RequestBody PlanMembresiaDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
