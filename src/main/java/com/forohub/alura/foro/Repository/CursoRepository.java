package com.forohub.alura.foro.Repository;

import com.forohub.alura.foro.Model.Curso;
import com.forohub.alura.foro.Model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
