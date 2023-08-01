---
description: Classe responsável por gerenciar a conexão com o banco de dados Mysql
---

# MysqlConnection

## Construtor

```java
public MysqlConnection(String name)
```

<details>

<summary><mark style="color:red;">Erros</mark></summary>

* <mark style="color:red;">NullPointerException</mark> : Ocorre quando o nome é vazio ou nulo

</details>

### Métodos

<details>

<summary>public String getName()</summary>

Obtém o nome da conexão

</details>

<details>

<summary>public void setAdress(String adress)</summary>

Define em qual endereço IP/Domínio a API irá se conectar

</details>

<details>

<summary>public void setPort(int port)</summary>

Define em qual parta a API irá se conectar no banco de dados

</details>

<details>

<summary>public void setUsername(String username)</summary>

Define qual o usuário do banco de dados

</details>

<details>

<summary>public void setPassword(String password)</summary>

Define qual a senha do usuário do banco de dados

</details>

<details>

<summary>public void setDatabase(String database)</summary>

Define qual o banco de dados que será usado

</details>

<details>

<summary>public void setUseSSL(boolean useSSL)</summary>

Define se a API irá se conectar com o banco de dados se forma segura

</details>

<details>

<summary>public Connection openConnection()</summary>

Inicia o processo de abertura de conexão com o banco de dados

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando não tem nenhum banco de dados definido pelo método [setDatabase](mysqlconnection.md#public-void-setdatabase-string-database)
* <mark style="color:red;">SQLException</mark>: Ocorre quando não foi possível estabelecer uma conexão com o banco de dados

</details>

<details>

<summary>public void closeConnection(Connection connection)</summary>

Método responsável por fechar a conexão com o banco de dados

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">SQLException</mark>: Ocorre quando há algum erro no fechamento da conexão com o banco de dados

</details>

<details>

<summary>public <a href="../../enums/connectiontype.md">ConnectionType</a> getConnectionType()</summary>

Retorna o tipo da Conexão sendo [ConnectionType.MYSQL](../../enums/connectiontype.md)

</details>
