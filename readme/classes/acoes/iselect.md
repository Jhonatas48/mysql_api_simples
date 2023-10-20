---
description: Representa o comando SELECT a ser executado no banco de dados
---

# ISelect

{% hint style="warning" %}
Campo Obrigatório
{% endhint %}

<details>

<summary>public<a href="iselect.md"> ISelect</a> setConnectionManager(<a href="../../../doc/classes/connectionmanager.md">ConnectionManager</a> connection)</summary>

Define o [ConnectionManager](../../../doc/classes/connectionmanager.md) a ser usado

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando o campo `connection` está nulo ou vazio

</details>

{% hint style="warning" %}
Campo Obrigatório
{% endhint %}

<details>

<summary>public <a href="iselect.md">ISelect</a> setTable(String table)</summary>

Define a tabela a ser consultada no banco de dados

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando o campo `table` está nulo ou vazio

</details>

{% hint style="info" %}
Caso desejar consultar campos especificos,poderá usar esta função.Caso contrário queira listar todos os campos da tabela não informe esse campo
{% endhint %}

<details>

<summary>public <a href="iselect.md">ISelect</a> addColumn(String column);</summary>

Define quais campos serão retornados na consulta no banco de dados

</details>

{% hint style="info" %}
Caso precise realizar um InnerJoin no banco de dados, o método abaixo realiza de forma automática.

A variavel table deve ser passada no seguinte formato:\
tabela \<abreviação da tabela>

**Exemplo**: tabela t

Para mais informações veja um exemplo [aqui](../../exemplos/transacoes/consultando-dados-na-tabela.md#usando-o-innerjoin-na-consulta)
{% endhint %}

<details>

<summary>public <a href="iselect.md">ISelect</a> addInnerjoin(String table,String onClause)</summary>

&#x20;Define a tabela a ser usada no inner join e a clausula on

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando o campo `table` ou quando a `onClause` está nulo ou vazio

</details>

