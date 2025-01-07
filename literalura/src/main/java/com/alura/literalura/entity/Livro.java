package com.alura.literalura.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer downloads;

    @ManyToOne
    private Autor autor;

    public Livro() {}

    public Livro(JsonLivro jsonLivro) {
        this.titulo = jsonLivro.titulo();
        this.idioma = Idioma.fromString(confirmaIdioma(jsonLivro.idiomas()));
        this.downloads = jsonLivro.downloads();
        this.autor = jsonLivro.getPrimeiroAutor();
    }

    // Pega os idiomas e converte em apenas um, confirmando se possui algum idioma
    private String confirmaIdioma(List<String> idiomas) {
        if (idiomas == null || idiomas.isEmpty()) {
            return "Desconhecido";
        } else {
            return idiomas.get(0);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        if (this.autor == null && autor != null) {
            this.autor = autor;
        }
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", autor=" + (autor != null ? autor.getNome() : "Desconhecido") +
                ", idioma=" + idioma +
                ", downloads=" + downloads;
    }

}
