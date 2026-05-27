package com.gimnasio.sucursales.controller;

import com.gimnasio.sucursales.dto.SucursalDTO;
import com.gimnasio.sucursales.model.Sucursal;
import com.gimnasio.sucursales.service.SucursalService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private static final Logger log = LoggerFactory.getLogger(SucursalController.class);

    private final SucursalService service;

    public SucursalController(SucursalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Sucursal>> listar() {
        log.info("GET /api/sucursales");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Sucursal>> listarActivas() {
        return ResponseEntity.ok(service.listarActivas());
    }

    @GetMapping("/comuna/{comuna}")
    public ResponseEntity<List<Sucursal>> porComuna(@PathVariable String comuna) {
        return ResponseEntity.ok(service.listarPorComuna(comuna));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Sucursal> crear(@Valid @RequestBody SucursalDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizar(@PathVariable Long id, @Valid @RequestBody SucursalDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Sucursal> cambiarEstado(@PathVariable Long id, @RequestParam Boolean activa) {
        return ResponseEntity.ok(service.cambiarEstado(id, activa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
