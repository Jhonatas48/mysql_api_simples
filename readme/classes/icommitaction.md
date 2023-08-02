---
description: >-
  Representa a interface da classe que executa de fatos as Ações no banco de
  daodos
---

# ICommitAction

### Métodos

<details>

<summary>public boolean commit()</summary>

Executa a ação no banco de dados e em caso de sucesso retorna `True`

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando algum campo obrigatório não foi preenchido
* <mark style="color:red;">TransactionInvalidException</mark>: Ocorre quando algum tipo inválido de Transaction foi passado
* <mark style="color:red;">ColumnsIsNullException</mark>: Ocorre quando uma das colunas foi passado o valor nulo
* <mark style="color:red;">FilterIsNullException</mark>: Ocorre quando o filtro é requerido para uma determinada Transaction ser executada. Por padrão Transaction do tipo [Update](acoes/iupdate.md) ou [Delete](acoes/idelete.md) requer o filter

</details>

<details>

<summary>public boolean commit(Consumer&#x3C;? super Throwable> failure)</summary>

Executa a ação no banco de dados e em caso de sucesso retorna `True`

<mark style="color:red;">**Erros**</mark>:

* <mark style="color:red;">NullPointerException</mark>: Ocorre quando algum campo obrigatório não foi preenchido
* <mark style="color:red;">TransactionInvalidException</mark>: Ocorre quando algum tipo inválido de Transaction foi passado
* <mark style="color:red;">ColumnsIsNullException</mark>: Ocorre quando uma das colunas foi passado o valor nulo
* <mark style="color:red;">FilterIsNullException</mark>: Ocorre quando o filtro é requerido para uma determinada [Transaction](transaction.md) ser executada. Por padrão [Transaction](transaction.md) do tipo [Update](acoes/iupdate.md) ou [Delete](acoes/idelete.md) requer o filter

**OBS**: Caso ocorra um dos erros acima será passado ao Consumer que tratará o erro conforme a lógica que o usuário implementar, caso não dê nenhum erro certifique de verificar se o Objeto é nulo

**Retorno:**

* Caso a [Transaction](transaction.md) for executado com sucessp será retornado o valor `True` e o objeto Consumer será **nulo**
* Caso ocorra algum erro será retornado `False` e  objeto Consumer receberá o erro

</details>
