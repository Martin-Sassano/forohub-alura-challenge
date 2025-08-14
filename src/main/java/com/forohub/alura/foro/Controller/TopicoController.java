package com.forohub.alura.foro.Controller;

import com.forohub.alura.foro.Dto.ActualizarTopicoDTO;
import com.forohub.alura.foro.Dto.DetalleTopicoDTO;
import com.forohub.alura.foro.Dto.TopicoRequestDTO;
import com.forohub.alura.foro.Dto.TopicoResponseDTO;
import com.forohub.alura.foro.Service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public ResponseEntity<Page<TopicoResponseDTO>> listar(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<TopicoResponseDTO> topicos = topicoService.listarTopicos(curso, anio, pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleTopicoDTO> detalle(@PathVariable Long id) {
        DetalleTopicoDTO dto = topicoService.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TopicoResponseDTO> registrar(@RequestBody @Valid TopicoRequestDTO dto) {
        TopicoResponseDTO response = topicoService.registrarTopico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleTopicoDTO> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid ActualizarTopicoDTO dto) {

        DetalleTopicoDTO actualizado = topicoService.actualizarTopico(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}

