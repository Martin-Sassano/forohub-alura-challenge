package com.forohub.alura.foro.Repository;

import com.forohub.alura.foro.Model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    // Buscar por nombre de curso y año
    @Query("SELECT t FROM Topico t WHERE (:curso IS NULL OR t.curso.nombre = :curso) AND (:anio IS NULL OR YEAR(t.fechaCreacion) = :anio)")
    Page<Topico> buscarPorCursoYAnio(@Param("curso") String curso, @Param("anio") Integer anio, Pageable pageable);
}
