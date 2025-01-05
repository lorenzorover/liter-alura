# Liter Alura

### Desafio 03 - Curso One-Alura
Programa desenvolvido durante o curso One da plataforma Alura, utilizando uma API de livros.<br>
link da API:<br>
https://gutendex.com <br>
<br>
documentação:<br>
https://github.com/garethbjohnson/gutendex

<br>

## Funcionalidades

Apresentar um sistema de menus no terminal para interação com o usuário, com as seguintes opções:
  - Permitir cadastrar livros no banco de dados com informações como título, idioma (definido por um enum), autor e número de downloads diretamente pela interface de linha de comando.
  - Listar livros, exibindo os autores relacionados.
  - Consultar autores vivos com base em critérios escolhidos.
  - Listar ou mostrar a contagem de livros pelo idioma escolhido.
  - Gerenciar dados em um banco de dados, com suporte a relacionamentos entre entidades (livros e autores), exibindo resultados no terminal.

<br>
<strong>Nota:</strong> O idioma é representado como um enum e armazenado como uma sigla no banco de dados.<br>
<br>
❗ Cuidado: Algumas exceções não foram tratadas, como a possibilidade de o usuário fornecer dados incorretos ou fora do formato esperado. Isso pode causar erros durante a execução do programa.
Recomenda-se que o usuário preencha os dados corretamente para garantir o funcionamento adequado.

<br>

## Estrutura do Projeto

<div> 
  <table>
    <tr>
      <th> Classe/Componente </th>
      <th width="450px"> Funcionalidade(s) </th> 
    </tr> 
    <tr> 
      <th> Livro </th> 
      <td> Entidade principal que representa os livros cadastrados no sistema. Inclui informações como título, idioma (Enum), associação com um autor e número de downloads. </td> 
    </tr> 
    <tr> 
      <th> Autor </th> 
      <td> Entidade que representa os autores, incluindo informações como nome, ano de nascimento e ano de falecimento. </td> 
    </tr> 
    <tr> 
      <th> Idioma (enum) </th> 
      <td> Enumeração que representa os idiomas disponíveis para os livros, com suporte para mapeamento da sigla no banco de dados. </td> 
    </tr> 
    <tr> 
      <th> Controladores </th> 
      <td> Classes responsáveis por expor as operações CRUD para livros e autores por meio de endpoints REST. </td> 
    </tr> 
    <tr> 
      <th> Repositórios </th> 
      <td> Interfaces responsáveis pela interação com o banco de dados, implementadas automaticamente pelo Spring Data JPA. </td> 
    </tr> 
    <tr>
      <th> Classe Principal </th> 
      <td> Responsável por exibir menus e gerenciar a interação com o usuário, incluindo chamadas para os serviços e repositórios configurados. </td> 
    </tr> 
  </table> 
</div> 

<br>








