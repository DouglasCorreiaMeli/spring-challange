# Desafio Spring Boot
O objetivo deste desafio é aplicar os conteúdos dados até o momento durante o Programa de aceleração MeLi (Git, Java e Spring), com a finalidade de poder implementar uma API REST para o SocialMeli, um rede social fictícia onde os usuários são divididos em compradores e vendedores.

- compradores são usuários que tem a função de seguir e visualizar post's.
- vendedores são usuários que podem ser seguidos por compradores, e tem a função de publicar post's de produtos.


## Features implementadas

- US0001: Ser capaz de realizar a ação de “Follow” (seguir) a um determinado
- US002: Obter o resultado do número de usuários que seguem um determinado vendedor
- US0003: Obter uma lista de todos os usuários que seguem um determinado vendedor (quem me segue?)
- US0004: Obter uma lista de todos os vendedores que um determinado usuário segue (quem estou seguindo?)
- US0005: Cadastrar uma nova publicação
- US006 - Obter uma lista das publicações feitas pelos vendedores que um usuário segue nas últimas duas semanas
- US0007: Ser capaz de realizar a ação de “Deixar de seguir” (parar de seguir) um determinado vendedor.
- US0008: Ordenacao alfabética crescente e decrescente no US003
- US 0009: Classificar por data crescente e decrescente no US006
- US 0010: Realizar a publicação de um novo produto promocional
- US 0011: Obtenha o quantidade de produtos promocionais de um vendedor
- US 0012: Obter uma lista de todos os produtos promocionais de um vendedor





## Rodando o projeto

O projeto foi criado utilizando o Spring Data com o H2 com DDL auto create, logo não se faz necessário configurações de banco de dados.
A API está documentada com o SWAGGER para facilitar o acompanhamento junto ao documento de requisitos, os endpoints estão nomeados de acordo com a numeracão do documento:
![documentação da API no Swagger](https://drive.google.com/file/d/1Le3ti9Isq5s3FYl-5Pex32OGsblyj7HN/view?usp=sharing)

| Recurso | URL |
| ------ | ------ |
| Swagger | http://localhost:8080/swagger-ui.html |
| H2 | http://localhost:8080/h2 |


## TODO's
- trocar de LIST para SET os atributos da classe Byuer e Seller.
