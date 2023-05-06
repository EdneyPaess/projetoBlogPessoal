# projetoBlogPessoal
Este é o repositório do projeto Blog Pessoal em Java.

O projeto foi desenvolvido com o intuito de criar um blog pessoal onde usuários podem criar e acessar postagens de diferentes temas. Para isso, foram utilizados três recursos (conjunto de classes e interfaces responsáveis por mapear um tipo de objeto e persistir no banco de dados relacional) e uma classe auxiliar, descritos abaixo:![68747470733a2f2f692e696d6775722e636f6d2f47373153434a302e706e67](https://user-images.githubusercontent.com/112277192/236642271-b1d5f47f-c9ea-489c-8734-efe0d785711b.png)

Recurso: Postagem: Responsável por definir o objeto Postagem (posts) do Blog Pessoal.

Recurso: Tema: Responsável por classificar as postagens através do objeto Tema.

Recurso: Usuario: Responsável por definir o objeto Usuário, que poderá acessar e criar postagens no Blog Pessoal.

Classe auxiliar: UsuarioLogin: Utilizada para efetuar login no Blog Pessoal e implementação da segurança da aplicação.

Cada recurso gera uma tabela no banco de dados da aplicação, com a classe auxiliar não gerando uma tabela no banco de dados.

O projeto é composto pelos recursos listados acima e foram implementados na mesma sequência descrita. O diagrama de classes e o diagrama de entidade e relacionamentos do projeto estão disponíveis nas imagens abaixo.
![68747470733a2f2f692e696d6775722e636f6d2f7a6d7a656846552e706e67](https://user-images.githubusercontent.com/112277192/236642309-8fffc8d3-3109-4ae2-b98d-cb2834dd8c09.png)
