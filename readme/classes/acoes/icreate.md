---
description: Representa o comando CREATE a ser executado no banco de dados
---

# ICreate



<details>

<summary>public ICreate setTable(String table)</summary>

**OBS**:

* Campo Obrigatório

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando o campo `table` está nulo ou vazio

</details>

<details>

<summary>public ICreate addColumn(String column,String value)</summary>

**OBS**:

* Campos `column` e `value` são obrigatórios

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando o campo `column` ou `value` está nulo ou vazio

</details>

<details>

<summary>public ICreate addColumn(String column,String value,ICreateAtributes&#x3C;?> createAtribute)</summary>

**OBS**:

* Campos `column` ,`value` e `createAtribute` são obrigatórios

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando o campo `column,` `value` ou `createAtribute` está nulo ou vazio

</details>

<details>

<summary>public ICreate setConnection(IConnection&#x3C;?> connection)</summary>



</details>

<details>

<summary>public ICreate setConnectionManager(ConnectionManager connectionManager)</summary>



</details>
