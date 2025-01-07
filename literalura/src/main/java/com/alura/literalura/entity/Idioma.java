package com.alura.literalura.entity;

import java.text.Normalizer;

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
        String normalizedText = removerAcento(text);

        for (Idioma idioma : Idioma.values()) {
            String normalizedIdioma = removerAcento(idioma.idiomaPortugues);
            if (normalizedIdioma.equalsIgnoreCase(normalizedText)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Nenhum idioma encontrado para a string fornecida: " + text);
    }

    private static String removerAcento(String text) {
        if (text == null) {
            return null;
        }
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
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
