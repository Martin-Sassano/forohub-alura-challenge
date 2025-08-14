package com.forohub.alura.foro.Service;

import com.forohub.alura.foro.Dto.ActualizarTopicoDTO;
import com.forohub.alura.foro.Dto.DetalleTopicoDTO;
import com.forohub.alura.foro.Dto.TopicoRequestDTO;
import com.forohub.alura.foro.Dto.TopicoResponseDTO;
import com.forohub.alura.foro.Model.Curso;
import com.forohub.alura.foro.Model.Topico;
import com.forohub.alura.foro.Model.Usuario;
import com.forohub.alura.foro.Repository.CursoRepository;
import com.forohub.alura.foro.Repository.TopicoRepository;
import com.forohub.alura.foro.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Page<TopicoResponseDTO> listarTopicos(String curso, Integer anio, Pageable pageable) {
        Page<Topico> topicos = topicoRepository.buscarPorCursoYAnio(curso, anio, pageable);
        return topicos.map(TopicoResponseDTO::new);
    }

    public DetalleTopicoDTO obtenerPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
        return new DetalleTopicoDTO(topico);
    }

    public TopicoResponseDTO registrarTopico(TopicoRequestDTO dto) {

        // Validación: existe autor
        Usuario autor = usuarioRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autor no encontrado"));

        // Validación: existe curso
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Curso no encontrado"));

        // Validación: tópico duplicado
        if (topicoRepository.existsByTituloAndMensaje(dto.getTitulo(), dto.getMensaje())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tópico duplicado (título y mensaje ya existen)");
        }

        // Creación y guardado del tópico
        Topico topico = new Topico();
        topico.setTitulo(dto.getTitulo());
        topico.setMensaje(dto.getMensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus("ABIERTO");

        topicoRepository.save(topico);

        return new TopicoResponseDTO(topico);
    }

    public DetalleTopicoDTO actualizarTopico(Long id, ActualizarTopicoDTO dto) {

        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }

        Topico topico = topicoOpt.get();

        // Validar autor y curso
        Usuario autor = usuarioRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autor no encontrado"));

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Curso no encontrado"));

        // Verificar duplicado (pero excluyendo el mismo tópico actual)
        boolean duplicado = topicoRepository.existsByTituloAndMensaje(dto.getTitulo(), dto.getMensaje())
                && !(topico.getTitulo().equals(dto.getTitulo()) && topico.getMensaje().equals(dto.getMensaje()));

        if (duplicado) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tópico duplicado (otro con mismo título y mensaje)");
        }

        // Actualizar campos
        topico.setTitulo(dto.getTitulo());
        topico.setMensaje(dto.getMensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        topicoRepository.save(topico);

        return new DetalleTopicoDTO(topico);
    }

    public void eliminarTopico(Long id) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }

        topicoRepository.deleteById(id);
    }
}