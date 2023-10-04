---
description: Mostra exemplos de como a API realiza a consulta no banco de dados
---

# Consultando Dados na Tabela

{% hint style="info" %}
Para executar qualquer operação no banco de dados deve passar qual o ConnectionManager deverá ser usado
{% endhint %}

## Inicializando e Declarando o ConnectionManager

```java
ConnectionManager connectionManager = new ConnectionManager("name");

// Estou definindo a Conexão com o SQLITE diretamente
// no métoddo para adicionar a conexão
connectionManager.addConnection(new SqliteConnection("connection_name", "db2.db"));
```



### Consultando os dados,retornando todos os dados

<pre class="language-java"><code class="lang-java"><strong>//Recebe o resultado da consulta realizada e salva os resultado na classe Result
</strong><strong>Result result = 
</strong>//Instancia a Classe Transaction
new Transaction().
//Define o método de uso como o SELECT
select()
//Define o ConnectionManager a ser usado
.setConnectionManager(connectionManager)
//Define a tabela a ser consultada
.setTable("youtable")
//Performa a ação no banco de dados
.queryResult();

</code></pre>
