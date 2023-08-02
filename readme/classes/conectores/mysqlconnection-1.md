---
description: Classe responsável por gerenciar a conexão com o banco de dados SQLITE
---

# SqliteConnection

## Construtor

```java
public SqliteConnection(String name,String fileNamePath)
```

<details>

<summary><mark style="color:red;">Erros</mark></summary>

* <mark style="color:red;">NullPointerException</mark> : Ocorre quando o nome é vazio ou nulo . Ou caso o fileNamePath for vazio

</details>

### Métodos

<details>

<summary>public String getName()</summary>

Obtém o nome da conexão

</details>

<details>

<summary>public Connection openConnection()</summary>

Inicia o processo de abertura de conexão com o banco de dados

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando não tem nenhum banco de dados definido pelo método [setDatabase](mysqlconnection-1.md#public-void-setdatabase-string-database)
* <mark style="color:red;">SQLException</mark>: Ocorre quando não foi possível estabelecer uma conexão com o banco de dados

</details>

<details>

<summary>public void closeConnection(Connection connection)</summary>

Método responsável por fechar a conexão com o banco de dados

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">SQLException</mark>: Ocorre quando há algum erro no fechamento da conexão com o banco de dados

</details>

<details>

<summary>public <a href="../../../doc/enums/connectiontype.md">ConnectionType</a> getConnectionType()</summary>

Retorna o tipo da Conexão sendo [ConnectionType.SQLITE](../../../doc/enums/connectiontype.md)

</details>
