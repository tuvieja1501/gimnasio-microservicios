package com.gimnasio.instructores.controller;

import com.gimnasio.instructores.dto.InstructorDTO;
import com.gimnasio.instructores.model.Instructor;
import com.gimnasio.instructores.service.InstructorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructores")
public class InstructorController {

    private static final Logger log = LoggerFactory.getLogger(InstructorController.class);

    private final InstructorService service;

    public InstructorController(InstructorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> listar() {
        log.info("GET /api/instructores");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Instructor>> listarActivos() {
        return ResponseEntity.ok(service.listarActivos());
    }

    @GetMapping("/especialidad/{esp}")
    public ResponseEntity<List<Instructor>> porEspecialidad(@PathVariable String esp) {
        return ResponseEntity.ok(service.buscarPorEspecialidad(esp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Instructor> crear(@Valid @RequestBody InstructorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> actualizar(@PathVariable Long id, @Valid @RequestBody InstructorDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
