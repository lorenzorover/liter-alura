package com.alura.literalura.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JsonAutor(@JsonAlias("name") String nome,
                        @JsonAlias("birth_year") Integer anoNascimento,
                        @JsonAlias("death_year") Integer anoFalecimento
) {}