---
description: >-
  O ConnectionManager é  a classe responsável por realizar a conexão com o banco
  de dados, e em caso que umas das Conexões cai, o sistema de FailBack irá
  colocar a próxima conexão disponível
---

# ConnectionManager

Para melhor entendimento segue uma ilustração:

<figure><img src="../../.gitbook/assets/Untitled.png" alt=""><figcaption></figcaption></figure>

Como pode ver acima o Connection Manager é capaz de gerenciar diversar conexões e caso a conexão principal caia ele já aciona outra automáticamente

### Construtor

**public ConnectionManager(String name)**

O campo nome é obrigatório porque caso o construir lide com múltiplos servidor a API será capaz de atender

OBS: É fortemente recomendável também ter um **nome único** para cada Connection Manager

### Métodos Da Classe

#### public void addConnection(IConnection\<?> connection)

Método responsável por registrar a conexão.

<mark style="background-color:red;">**Erros Retornados**</mark>**:**

* DuplicateConnectionNameException : Retornado quando o nome da conexão já existe
* Exception : Ocorre quando qualquer erro aconteçe ao tentar registrar a conexão

#### getConnection()

Método responsável de abrir e obter a conexão

