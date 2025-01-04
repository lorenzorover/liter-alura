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
        this.idioma =Idioma.fromString(jsonLivro.getPrimeiroIdioma().split(",")[0].trim());
        this.downloads = jsonLivro.downloads();
        this.autor = jsonLivro.getPrimeiroAutor();
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
        this.autor = autor;
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
        return
                "titulo='" + titulo + '\'' +
                ", autor=" + autor.getNome() + '\'' +
                ", idioma=" + idioma + '\'' +
                ", downloads=" + downloads + '\'';
    }
}
