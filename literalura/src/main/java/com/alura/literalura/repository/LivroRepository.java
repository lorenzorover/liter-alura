package com.alura.literalura.repository;

import com.alura.literalura.entity.Autor;
import com.alura.literalura.entity.Idioma;
import com.alura.literalura.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTituloContainingIgnoreCase(String tituloLivro);

    List<Livro> findByIdioma(Idioma idioma);

    Long countByIdioma(Idioma idioma);
}
