package com.alura.literalura.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JsonLivro(@JsonAlias("title") String titulo,
                        @JsonAlias("authors") List<JsonAutor> autores,
                        @JsonAlias("languages") List<String> idiomas,
                        @JsonAlias("download_count") Integer downloads
) {
    public String getPrimeiroIdioma() {
        return (this.idiomas != null && !this.idiomas.isEmpty()) ? this.idiomas.get(0) : null;
    }
    public Autor getPrimeiroAutor() {
        return (this.autores != null && !this.autores.isEmpty()) ? new Autor(this.autores.get(0)) : null;
    }
}

