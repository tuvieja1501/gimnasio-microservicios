package com.gimnasio.membresias.controller;

import com.gimnasio.membresias.dto.MembresiaDTO;
import com.gimnasio.membresias.model.Membresia;
import com.gimnasio.membresias.service.MembresiaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/membresias")
public class MembresiaController {

    private static final Logger log = LoggerFactory.getLogger(MembresiaController.class);

    private final MembresiaService service;

    public MembresiaController(MembresiaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Membresia>> listar() {
        log.info("GET /api/membresias");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membresia> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/socio/{socioId}")
    public ResponseEntity<List<Membresia>> porSocio(@PathVariable Long socioId) {
        return ResponseEntity.ok(service.listarPorSocio(socioId));
    }

    /**
     * Endpoint interno consumido por ms-reservas para saber si el socio
     * tiene membresia vigente antes de reservar una clase.
     */
    @GetMapping("/socio/{socioId}/vigente")
    public ResponseEntity<Map<String, Boolean>> vigente(@PathVariable Long socioId) {
        log.info("Consulta de membresia vigente para socio={}", socioId);
        return ResponseEntity.ok(Map.of("vigente", service.tieneVigente(socioId)));
    }

    @PostMapping
    public ResponseEntity<Membresia> crear(@Valid @RequestBody MembresiaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Membresia> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(service.cancelar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
