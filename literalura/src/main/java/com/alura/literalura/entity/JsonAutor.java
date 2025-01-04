package com.alura.literalura.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JsonAutor(@JsonAlias("birthYear") Integer anoNascimento,
                        @JsonAlias("deathYear") Integer anoFalecimento,
                        @JsonAlias("Name") String nome
) {}