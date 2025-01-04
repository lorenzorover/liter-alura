package com.alura.literalura.repository;

import com.alura.literalura.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.dataNascimento <= :anoEscolhido AND (a.dataFalecimento IS NULL OR a.dataFalecimento >= :anoEscolhido)")
    List<Autor> findByYear(@Param("anoEscolhido") int anoEscolhido);
}
