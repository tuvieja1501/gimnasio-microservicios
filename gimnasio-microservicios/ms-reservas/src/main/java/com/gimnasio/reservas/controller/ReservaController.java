package com.gimnasio.reservas.controller;

import com.gimnasio.reservas.dto.ReservaDTO;
import com.gimnasio.reservas.model.Reserva;
import com.gimnasio.reservas.service.ReservaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private static final Logger log = LoggerFactory.getLogger(ReservaController.class);

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listar() {
        log.info("GET /api/reservas");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/socio/{socioId}")
    public ResponseEntity<List<Reserva>> porSocio(@PathVariable Long socioId) {
        return ResponseEntity.ok(service.listarPorSocio(socioId));
    }

    @GetMapping("/clase/{claseId}")
    public ResponseEntity<List<Reserva>> porClase(@PathVariable Long claseId) {
        return ResponseEntity.ok(service.listarPorClase(claseId));
    }

    @PostMapping
    public ResponseEntity<Reserva> reservar(@Valid @RequestBody ReservaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.reservar(dto));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Reserva> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(service.cancelar(id));
    }

    @PatchMapping("/{id}/asistida")
    public ResponseEntity<Reserva> marcarAsistida(@PathVariable Long id) {
        return ResponseEntity.ok(service.marcarAsistida(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
