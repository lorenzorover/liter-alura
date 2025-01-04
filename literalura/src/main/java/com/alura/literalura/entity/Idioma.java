package com.alura.literalura.entity;

public enum Idioma {
    INGLES("en", "Inglês"),
    PORTUGUES("pt", "Português");

    private String idiomaOmdb;
    private String idiomaPortugues;

    Idioma(String idiomaOmdb, String idiomaPortugues){
        this.idiomaOmdb = idiomaOmdb;
        this.idiomaPortugues = idiomaPortugues;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaOmdb.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Nenhum idioma encontrado para a string fornecida: " + text);
    }

    public static Idioma fromPortugues(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaPortugues.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Nenhum idioma encontrado para a string fornecida: " + text);
    }

    public String getIdiomaOmdb() {
        return idiomaOmdb;
    }

    public void setIdiomaOmdb(String idiomaOmdb) {
        this.idiomaOmdb = idiomaOmdb;
    }

    public String getIdiomaPortugues() {
        return idiomaPortugues;
    }

    public void setIdiomaPortugues(String idiomaPortugues) {
        this.idiomaPortugues = idiomaPortugues;
    }
}
