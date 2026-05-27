package com.gimnasio.pagos.controller;

import com.gimnasio.pagos.dto.PagoDTO;
import com.gimnasio.pagos.model.Pago;
import com.gimnasio.pagos.service.PagoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private static final Logger log = LoggerFactory.getLogger(PagoController.class);

    private final PagoService service;

    public PagoController(PagoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pago>> listar() {
        log.info("GET /api/pagos");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/socio/{socioId}")
    public ResponseEntity<List<Pago>> porSocio(@PathVariable Long socioId) {
        return ResponseEntity.ok(service.listarPorSocio(socioId));
    }

    @GetMapping("/membresia/{membresiaId}")
    public ResponseEntity<List<Pago>> porMembresia(@PathVariable Long membresiaId) {
        return ResponseEntity.ok(service.listarPorMembresia(membresiaId));
    }

    @PostMapping
    public ResponseEntity<Pago> registrar(@Valid @RequestBody PagoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }

    @PatchMapping("/{id}/anular")
    public ResponseEntity<Pago> anular(@PathVariable Long id) {
        return ResponseEntity.ok(service.anular(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
