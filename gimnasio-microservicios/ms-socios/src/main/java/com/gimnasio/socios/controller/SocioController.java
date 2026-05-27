package com.gimnasio.socios.controller;

import com.gimnasio.socios.dto.SocioDTO;
import com.gimnasio.socios.model.EstadoSocio;
import com.gimnasio.socios.model.Socio;
import com.gimnasio.socios.service.SocioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para Socios.
 * Expone endpoints CRUD y operaciones de dominio.
 * Solo orquesta: delega la logica al SocioService.
 */
@RestController
@RequestMapping("/api/socios")
public class SocioController {

    private static final Logger log = LoggerFactory.getLogger(SocioController.class);

    private final SocioService service;

    public SocioController(SocioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Socio>> listar() {
        log.info("GET /api/socios");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Socio> obtener(@PathVariable Long id) {
        log.info("GET /api/socios/{}", id);
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<Socio> obtenerPorRut(@PathVariable String rut) {
        log.info("GET /api/socios/rut/{}", rut);
        return ResponseEntity.ok(service.buscarPorRut(rut));
    }

    @PostMapping
    public ResponseEntity<Socio> crear(@Valid @RequestBody SocioDTO dto) {
        log.info("POST /api/socios rut={}", dto.getRut());
        Socio creado = service.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Socio> actualizar(@PathVariable Long id,
                                            @Valid @RequestBody SocioDTO dto) {
        log.info("PUT /api/socios/{}", id);
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Socio> cambiarEstado(@PathVariable Long id,
                                               @RequestParam EstadoSocio estado) {
        log.info("PATCH /api/socios/{}/estado -> {}", id, estado);
        return ResponseEntity.ok(service.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/socios/{}", id);
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
