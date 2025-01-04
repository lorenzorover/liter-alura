package com.alura.literalura.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer anoNascimento;
    private Integer anoFaleciomento;
    private String nome;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {}

    public Autor(JsonAutor jsonAutor) {
        this.anoNascimento = jsonAutor.anoNascimento();
        this.anoFaleciomento = jsonAutor.anoFalecimento();
        this.nome = jsonAutor.nome();
    }

    public void setLivros(List<Livro> livros) {
        livros.forEach(e -> e.setAutor(this));
        this.livros = livros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFaleciomento() {
        return anoFaleciomento;
    }

    public void setAnoFaleciomento(Integer anoFaleciomento) {
        this.anoFaleciomento = anoFaleciomento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    @Override
    public String toString() {
        String tituloLivros = livros.stream()
                .map(Livro::getTitulo)
                .collect(Collectors.joining(", "));
        return
                "nome='" + nome + '\'' +
                ", livros=" + tituloLivros + '\'' +
                ", anoFaleciomento=" + anoFaleciomento + '\'' +
                ", anoNascimento=" + anoNascimento + '\'';
    }
}
