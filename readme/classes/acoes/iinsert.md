---
description: Representa o comando INSERT a ser executado no banco de dados
---

# IInsert



{% hint style="warning" %}
Campo Obrigatório
{% endhint %}

<details>

<summary>public <a href="iinsert.md">IInsert</a> setTable(String table)</summary>

Define em qual tabela que o valor será inserido

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando o campo `table` está nulo ou vazio.

</details>

{% hint style="warning" %}
&#x20;Campo Obrigatório
{% endhint %}

<details>

<summary>public <a href="iinsert.md">IInsert </a>setConnectionManager(<a href="../../../doc/classes/connectionmanager.md">ConnectionManager</a> connection)</summary>

Define qual o [ConnectionManager](../../../doc/classes/connectionmanager.md) irá ser usado para inserir o dado

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando o campo `connection` está nulo ou vazio.

</details>

{% hint style="warning" %}
Campo Obrigatório
{% endhint %}

<details>

<summary>public <a href="iinsert.md">IInsert</a> addColumn(String column, String value)</summary>

Define a coluna e o valor do campo na tabela do banco de dados onde o dado será inserido

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando o campo `column` ou `value` está nulo ou vazio

</details>

{% hint style="info" %}
Método responsável por performar a ação no banco de dados
{% endhint %}

<details>

<summary>public boolean commit()</summary>

Retorna `true` caso o registro for inserido com sucesso,caso não retorna `false`.

</details>

{% hint style="info" %}
Método responsável por performar a ação no banco de dados e pode retornar um erro caso aconteça algo.\
\
Veja o exemplo [aqui](../../exemplos/transacoes/inserir-dados-na-tabela.md#inserindo-dados-aplicando-o-consumer)
{% endhint %}

<details>

<summary>public boolean commit(Consumer&#x3C;? super Throwable> failure)</summary>

Retorna `true` caso o registro for inserido com sucesso,caso não retorna `false`. Retornando juntamente o Cconsumer caso der erro,caso contrario retorna um Consumer vazio

</details>
