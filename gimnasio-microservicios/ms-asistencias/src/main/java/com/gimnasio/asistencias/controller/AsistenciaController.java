package com.gimnasio.asistencias.controller;

import com.gimnasio.asistencias.dto.AsistenciaDTO;
import com.gimnasio.asistencias.model.Asistencia;
import com.gimnasio.asistencias.service.AsistenciaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    private static final Logger log = LoggerFactory.getLogger(AsistenciaController.class);

    private final AsistenciaService service;

    public AsistenciaController(AsistenciaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Asistencia>> listar() {
        log.info("GET /api/asistencias");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/socio/{socioId}")
    public ResponseEntity<List<Asistencia>> porSocio(@PathVariable Long socioId) {
        return ResponseEntity.ok(service.listarPorSocio(socioId));
    }

    @PostMapping("/ingreso")
    public ResponseEntity<Asistencia> registrarIngreso(@Valid @RequestBody AsistenciaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarIngreso(dto));
    }

    @PatchMapping("/salida/socio/{socioId}")
    public ResponseEntity<Asistencia> registrarSalida(@PathVariable Long socioId) {
        return ResponseEntity.ok(service.registrarSalida(socioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
