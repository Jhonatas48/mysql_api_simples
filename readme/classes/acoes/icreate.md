---
description: Representa o comando CREATE a ser executado no banco de dados
---

# ICreate



{% hint style="info" %}
Campo Obrigatório
{% endhint %}

<details>

<summary>public ICreate setTable(String table)</summary>

Define qual o nome da tabela a ser criado

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando o campo `table` está nulo ou vazio

</details>

{% hint style="info" %}
Campos `column` e `value` são obrigatórios
{% endhint %}

<details>

<summary>public ICreate addColumn(String column,String value)</summary>

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando o campo `column` ou `value` está nulo ou vazio

</details>

{% hint style="info" %}
Campos `column` e `value` são obrigatórios
{% endhint %}

<details>

<summary>public ICreate addColumn(String column,String value,ICreateAtributes&#x3C;?> createAtribute)</summary>

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando o campo `column,` `value` ou `createAtribute` está nulo ou vazio

</details>

{% hint style="info" %}
Os campo [`setConnection`](icreate.md#public-icreate-setconnection-iconnection-less-than-greater-than-connection) ou [`setConnectionManager`](icreate.md#public-icreate-setconnectionmanager-connectionmanager-connectionmanager)deve ser usado para que a transação seja efetuada no banco de dados caso não for definido um dos campos irá resultar em erro,pois a API não deixará a transação ser executada no banco de dados
{% endhint %}

<details>

<summary>public ICreate setConnection(IConnection&#x3C;?> connection)</summary>



</details>

<details>

<summary>public ICreate setConnectionManager(ConnectionManager connectionManager)</summary>



</details>
