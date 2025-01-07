package com.alura.literalura.principal;

import com.alura.literalura.entity.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;

import java.util.*;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private static String ENDERECO = "https://gutendex.com/books/?search=";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
//    private List<JsonLivro> jsonLivros = new ArrayList<>();

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;
    private Optional<Livro> livroBusca;
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    1 - Busca de livro por título
                    2 - Listagem de todos os livros já buscados
                    3 - Listar por idioma de livros já buscados
                    4 - Exibir a quantidade de livros em um determinado idioma
                    5 - Lista de autores por livros já buscados
                    6 - Listar autores vivos em determinado ano
                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivrosBuscados();
                    break;
                case 3:
                    listarLivrosPorIdioma();
                    break;
                case 4:
                    contarLivrosPorIdioma();
                    break;
                case 5:
                    listarAutoresPorLivrosBuscados();
                    break;
                case 6:
                    listarAutoresVivos();
                    break;
                case 0:
                    System.out.println("Encerrando o programa!");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Deseja buscar autores em que ano?");
        var anoEscolhido = leitura.nextInt();
        leitura.nextLine();

        autores = autorRepository.findByYear(anoEscolhido);

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o ano " + anoEscolhido + ".");
        } else {
            System.out.println("Autores vivos no ano " + anoEscolhido + ":");
            autores.forEach(System.out::println);
        }
        System.out.println();// pula linha
    }

    private void contarLivrosPorIdioma() {
        System.out.println("Deseja buscar livros de que idioma? ");
        var idiomaEscolhido = leitura.nextLine();

        Idioma idioma = Idioma.fromPortugues(idiomaEscolhido);
        Long livrosContados = livroRepository.countByIdioma(idioma);

        System.out.println("Quantidade de livros contados no idioma " + idiomaEscolhido + ": " + livrosContados);
        System.out.println();// pula linha
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                Deseja buscar livros de que idioma?
                Opções:
                Inglês
                Português
                """);
        var idiomaEscolhido = leitura.nextLine();
        Idioma idioma = Idioma.fromPortugues(idiomaEscolhido);
        livros = livroRepository.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma " + idioma);
        } else {
            System.out.println("Livros do idioma " + idiomaEscolhido);
            livros.forEach(System.out::println);
        }
        System.out.println();// pula linha
    }

    private void listarAutoresPorLivrosBuscados() {
        autores = autorRepository.findAll();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(System.out::println);
        System.out.println();// pula linha
    }

    private void listarLivrosBuscados() {
        livros = livroRepository.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
        System.out.println();// pula linha
    }

    private void buscarLivroPorTitulo() {
        JsonLivro jsonLivro = getJsonLivro();
        Livro livro = new Livro(jsonLivro);

        // Busca o livro antes para ver se já existe algum igual, para não haver duplicidades
        livroBusca = livroRepository.findByTituloContainingIgnoreCase(livro.getTitulo());

        if (livroBusca.isPresent()) {
            System.out.println("O livro com o título '" + livro.getTitulo() + "' já está salvo no banco.");
        } else {
            try {
                // Salvar livro e autor
                autorRepository.save(livro.getAutor());
                livroRepository.save(livro);
                System.out.println("Livro '" + livro.getTitulo() + "' salvo com sucesso.");
            } catch (Exception e) {
                System.err.println("Erro ao salvar o livro: " + e.getMessage());
            }
        }

        System.out.println(livro.toString());
        System.out.println();// pula linha
    }

    private JsonLivro getJsonLivro() {
        System.out.println("Digite o título do livro: ");
        var nomeLivro = leitura.nextLine().toLowerCase();
        var json = consumoApi.obterDados(ENDERECO + nomeLivro.replace(" ", "%20").trim());
        ConteinerJson jsonConvertido = converteDados.obterDados(json, ConteinerJson.class);
        JsonLivro jsonLivro = jsonConvertido.resultados().get(0);
        return jsonLivro;
    }
}
