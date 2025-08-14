package com.forohub.alura.foro.Controller;

import com.forohub.alura.foro.Model.Respuesta;
import com.forohub.alura.foro.Repository.RespuestaRepository;
import com.forohub.alura.foro.Repository.TopicoRepository;
import com.forohub.alura.foro.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping
    public List<Respuesta> listarRespuestas() {
        return respuestaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Respuesta> crearRespuesta(@RequestBody Respuesta respuesta) {
        // Validación opcional aquí (e.g. autor y tópico deben existir)
        return ResponseEntity.status(HttpStatus.CREATED).body(respuestaRepository.save(respuesta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> obtenerRespuesta(@PathVariable Long id) {
        return respuestaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Respuesta> actualizarRespuesta(@PathVariable Long id, @RequestBody Respuesta datos) {
        return respuestaRepository.findById(id)
                .map(respuesta -> {
                    respuesta.setMensaje(datos.getMensaje());
                    respuesta.setSolucion(datos.getSolucion());
                    return ResponseEntity.ok(respuestaRepository.save(respuesta));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id) {
        return respuestaRepository.findById(id)
                .map(respuesta -> {
                    respuestaRepository.delete(respuesta);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
