# Este projeto faz parte da avaliação no processo seletivo para oportunidade de desenvolvedor(a) Back-End na **Attornatus**.
 Tem como objetivo analisar:
 * Qualidade de código
 * Java, Spring boot
 * API REST
 * Testes

## Questões:

### Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa para garantia de qualidade de software?

Para garantir a qualidade de software baseio-me no conceito do SCRUM INVEST onde a nova implementação deve seguir os seguintes critérios: Ser independente, negociável, valiosa, estimável, pequena e testável. Os testes estáticos que competem à equipe de engenharia são: testes unitários, de integração e funcionais, e em conjunto com a revisão por pares(Code Review) podem detectar bugs, codes Smells e quebra de padrões de projeto.

### Em qual etapa da implementação você considera a qualidade de software?

Dependerá da cultura da empresa de como é o seu padrão de organização. Para verificarmos a qualidade do software logo no começo desenvolvendo testes seguindo o padrão TDD ou desenvolvendo os testes das aplicações no final seguindo o padrão BDD. Eu costumo seguir o padrão BDD.

## Desafio Java

### Usando Spring boot, crie uma API simples para gerenciar Pessoas. Esta API deve permitir:  

* Criar uma pessoa
* Editar uma pessoa
* Consultar uma pessoa
* Listar pessoas
* Criar endereço para pessoa
* Listar endereços da pessoa
* Poder informar qual endereço é o principal da pessoa  

#### Uma Pessoa deve ter os seguintes campos:  

* Nome
* Data de nascimento
* Endereço:
    * Logradouro
    * CEP
    * Número
    * Cidade

### Requisitos  
* Todas as respostas da API devem ser JSON  
* Banco de dados H2

## Processo de compilação

Para realizar o processo de compilação siga os seguintes passos:

* No terminal digite **./mvnw clean install**

Para executar:

* **java -jar target/personmanager-0.0.1-SNAPSHOT.jar**


