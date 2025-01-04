package com.alura.literalura.principal;

import com.alura.literalura.entity.Autor;
import com.alura.literalura.entity.Idioma;
import com.alura.literalura.entity.JsonLivro;
import com.alura.literalura.entity.Livro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.*;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "https://gutendex.com/books";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
    private List<JsonLivro> jsonLivros = new ArrayList<>();

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;
    private Optional<Livro> livroBusca;
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();



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
//            numero 3, 11 e 6 precisa de derived query no repository, por pelo menos 2 idiomas

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
        }

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

    private void listarAutoresVivos() {
        System.out.println("Deseja buscar autores em que ano?");
        var anoEscolhido = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autoresVivos = autorRepository.findByYear(anoEscolhido);

        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o ano " + anoEscolhido + ".");
        } else {
            System.out.println("Autores vivos no ano " + anoEscolhido + ":");
            autoresVivos.forEach(System.out::println);
        }

        autoresVivos.forEach(System.out::println);
    }

    private void contarLivrosPorIdioma() {
        System.out.println("Deseja buscar livros de que idioma? ");
        var idiomaEscolhido = leitura.nextLine();

        Idioma idioma = Idioma.fromPortugues(idiomaEscolhido);
        Long livrosContados = livroRepository.countByIdioma(idioma);

        System.out.println("Quantidade de livros contados no idioma " + idiomaEscolhido + ": " + livrosContados);
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Deseja buscar livros de que idioma? ");
        var idiomaEscolhido = leitura.nextLine();
        Idioma idioma = Idioma.fromPortugues(idiomaEscolhido);
        List<Livro> livrosPorIdioma = livroRepository.findByIdioma(idioma);

        System.out.println("Livros do idioma " + idiomaEscolhido);
        livrosPorIdioma.forEach(System.out::println);
    }

    private void listarAutoresPorLivrosBuscados() {
        autores = autorRepository.findAll();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(System.out::println);
    }

    private void listarLivrosBuscados() {
        livros = livroRepository.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
    }

    private void buscarLivroPorTitulo() {
        JsonLivro jsonLivro = getDadosLivro();
        Livro livro = new Livro(jsonLivro);

        Optional<Livro> livroExistente = livroRepository.findByTituloContainingIgnoreCase(livro.getTitulo());

        if (livroExistente.isPresent()) {
            System.out.println("O livro com o título '" + livro.getTitulo() + "' já está salvo no banco.");
        } else {
            livroRepository.save(livro);
            System.out.println("Livro '" + livro.getTitulo() + "' salvo com sucesso.");
        }

        System.out.println(livro.toString());
    }
    private JsonLivro getDadosLivro() {
        System.out.println("Digite o título do livro: ");
        var nomeLivro = leitura.nextLine().toLowerCase();
        var json = consumoApi.obterDados(ENDERECO + "?search=" + nomeLivro.replace(" ", "%20"));
        JsonLivro jsonLivro = converteDados.obterDados(json, JsonLivro.class);
        return jsonLivro;
    }
}
