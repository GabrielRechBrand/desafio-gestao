# Projeto desafio de programação

![](https://img.shields.io/badge/-Java-blue?style&logo=java)
![](https://img.shields.io/badge/-PostgreSQL-white?style&logo=postgresql)

## Motivação

A motivação para a criação desse projeto é de por em prática os estudos teóricos de orientação a objeto, de forma 
simples e inicial.

Não foram abordadas regras de negócio complexas e não possui o objetivo resolver algum problema do nosso dia a dia.

## Escopo

Criar um sistema java onde seja possível realizar cadastro de pessoas contendo <b>Nome</b> e <b>CPF</b> e também 
cadastrar empresas contendo <b>Nome</b>, <b>CNPJ</b> e <b>Presidente</b> onde deve existir um vínculo em presidente e 
pessoa.

## Etapas

Projeto foi divido nas seguintes etapas:

* Criação do CRUD de pessoa armazenando em um HashMap em memória.
* Refatoração para que o CRUD de pessoa seja armazenado em base PostgreSQL.
* Criação do CRUD de empresa realizando o vínculo com a pessoa.

## Algumas telas do sistema

![Menu Principal](https://coffops.com/wp-content/uploads/2021/09/desafio-tela-inicial.jpg)

![Menu Pessoa](https://coffops.com/wp-content/uploads/2021/09/desafio-tela-pessoas.jpg)

![Menu Empresa](https://coffops.com/wp-content/uploads/2021/09/desafio-tela-empresas.jpg)


### Configurações

#### Script criação das tabelas

##### Criação da tabela pessoa
``` 
    CREATE SEQUENCE id;
    CREATE TABLE Pessoas(
        id int default nextval('id'::regclass) PRIMARY KEY,
        nome Varchar(50),
        cpf Varchar(11)
    );
```

##### Criação da tabela empresa
```
    CREATE TABLE public.empresas
    (
        idempresa integer NOT NULL DEFAULT nextval('idempresa'::regclass),
        nome character varying(50) COLLATE pg_catalog."default",
        cnpj character varying(14) COLLATE pg_catalog."default",
        idpresidente integer,
        CONSTRAINT empresas_pkey PRIMARY KEY (idempresa) 
    )
```

##### Criação da chave estrangeira de vínculo de Presidente com pessoa
```
    CONSTRAINT empresas_idpresidente_fkey FOREIGN KEY (idpresidente) REFERENCES public.pessoas (id) 
```

##### Alteração de dados de conexão no código

A classe que contém os dados de conexão com o banco de dados é a classe [Conexao.java] onde deve ser alterados os
dados de conexão caso necessário.

[Conexao.java]: ./src/main/java/br

