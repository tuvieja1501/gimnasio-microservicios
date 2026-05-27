package com.gimnasio.equipos.controller;

import com.gimnasio.equipos.dto.EquipoDTO;
import com.gimnasio.equipos.model.Equipo;
import com.gimnasio.equipos.model.EstadoEquipo;
import com.gimnasio.equipos.service.EquipoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    private static final Logger log = LoggerFactory.getLogger(EquipoController.class);

    private final EquipoService service;

    public EquipoController(EquipoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Equipo>> listar() {
        log.info("GET /api/equipos");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<Equipo>> porSucursal(@PathVariable Long sucursalId) {
        return ResponseEntity.ok(service.listarPorSucursal(sucursalId));
    }

    @PostMapping
    public ResponseEntity<Equipo> crear(@Valid @RequestBody EquipoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizar(@PathVariable Long id, @Valid @RequestBody EquipoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Equipo> cambiarEstado(@PathVariable Long id, @RequestParam EstadoEquipo estado) {
        return ResponseEntity.ok(service.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
