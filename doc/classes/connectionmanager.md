---
description: >-
  O ConnectionManager é  a classe responsável por realizar a conexão com o banco
  de dados, e em caso que umas das Conexões cai, o sistema de FailBack irá
  colocar a próxima conexão disponível
---

# ConnectionManager

Para melhor entendimento segue uma ilustração:

<figure><img src="../../.gitbook/assets/Untitled.png" alt=""><figcaption></figcaption></figure>

Como pode ver acima o Connection Manager é capaz de gerenciar diversas conexões e caso a conexão principal caia ele já aciona outra automáticamente o que é chamado de FailBack

### Construtor

**public ConnectionManager(String name)**

O campo nome é obrigatório porque caso o construir lide com múltiplos servidor a API será capaz de atender

OBS: É fortemente recomendável também ter um **nome único** para cada Connection Manager

<details>

<summary><mark style="color:red;"><strong>Possíveis Erros:</strong></mark></summary>

* <mark style="color:red;">NullPointerException</mark> : Ocorre quando o nome é vazio ou nulo

</details>

### Métodos Da Classe

#### public void addConnection(IConnection\<?> connection)

Método responsável por registrar a conexão.

<mark style="color:red;background-color:red;">**Erros Retornados:**</mark>

* <mark style="color:red;">DuplicateConnectionNameException</mark> : Ocorre quando o nome da conexão já existe
* <mark style="color:red;">Exception</mark> : Ocorre quando qualquer erro aconteçe ao tentar registrar a conexão

#### getConnection()

Método responsável de abrir e obter a conexão

<mark style="color:red;background-color:red;">**Possíveis Erros:**</mark>

* <mark style="color:red;">NullPointerException</mark> : Ocorre quando não há nenhuma conexão registrada
* <mark style="color:red;">ConnectionNotEstablishedException</mark> : Ocorre quando não foi possível estabilizar a conexão com o banco de dados por qualquer motivo que seja

