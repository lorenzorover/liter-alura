package com.alura.literalura.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JsonLivro(@JsonAlias("Title") String titulo,
                        @JsonAlias("Authors") List<JsonAutor> autores,
                        @JsonAlias("Languages") List<String> idiomas,
                        @JsonAlias("downloadCount") Integer downloads
) {
//    public String getPrimeiroAutor() {
//        return (this.autores != null && !this.autores.isEmpty()) ? this.autores.get(0).nome() : null;
//    }

    public String getPrimeiroIdioma() {
        return (this.idiomas != null && !this.idiomas.isEmpty()) ? this.idiomas.get(0) : null;
    }
}

