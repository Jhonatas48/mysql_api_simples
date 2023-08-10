---
description: Representa a interface de uma conexão, que verá na próxima seção
---

# IConnection



{% hint style="info" %}
Você não precisa se preocupar de passar o parâmetros e métodos para essa interface já que a API já cuida de fazer isso, já que isso quem se encarrega de gerenciar é o [ConnectionManager](../../doc/classes/connectionmanager.md)
{% endhint %}

### Métodos

<details>

<summary>public Connection openConnection()</summary>

Método responsável por abrir a conexão com o [conector do banco de dados](conectores/)

<mark style="color:red;">Erros</mark>:

* <mark style="color:red;">SQLException</mark>: Ocorre por algum erro ao tenta abrir a conexão com o banco de dados
* <mark style="color:red;">ClassNotFoundException</mark>: Ocorre quando o driver do banco de dados não foi encontrado

</details>

<details>

<summary>public void closeConnection(Connection connection)</summary>

Método responsável por fechar a conexão com o [conector do banco de dados](conectores/)\
\
<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">SQLException</mark> : Ocorre quando há um problema durante o encerramento da conexão com o banco de dados

</details>

<details>

<summary>public <a href="../../doc/enums/connectiontype.md">ConnectionType</a> getConnectionType()</summary>

Retorna o [ConnectionType](iconnection.md#public-connectiontype-getconnectiontype) correspondente ao [conector de banco de dados](conectores/) usado

</details>
