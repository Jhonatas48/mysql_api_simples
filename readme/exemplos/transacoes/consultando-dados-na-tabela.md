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

## Consultando os dados,retornando todos os dados

<pre class="language-java"><code class="lang-java"><strong>//Recebe o resultado da consulta realizada e salva os resultado na classe Result
</strong><strong>Result result = 
</strong><strong>//Instancia a Classe Transaction
</strong>new Transaction().
//Define o método de uso como o SELECT
select()
//Define o ConnectionManager a ser usado
.setConnectionManager(connectionManager)
//Define a tabela a ser consultada
.setTable("youtable")
//Performa a ação no banco de dados
.queryResult();

</code></pre>

{% hint style="danger" %}
**Atenção**

Para usar esta função os campos da classe devem conter o mesmo nome e tipo do dado da tabela
{% endhint %}

## Mapeando Do Banco de Dados Para Classe

Para os Próximos exemplos de usos irei definir uma Classe User e irei fazer o mapeamento do banco de dados para a Classe User

**Exemplo De Classe**:

```java
import java.time.LocalDateTime;

public class User {
	
	private int id;
	private String username;
	private String password;
	private LocalDateTime updated;
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", updated=" + updated + "]";
	}
	

}

```

#### Exemplo De Dado No Sqlite

<figure><img src="../../../.gitbook/assets/banco de dados.JPG" alt=""><figcaption></figcaption></figure>

### Métodos

<details>

<summary>Retornando os dados em uma List&#x3C;T></summary>

```java
List<User> users =
//Instancia a Classe Transaction 
new Transaction()
//Define o método de uso como o SELECT
.select()
//Define o ConnectionManager a ser usado
.setConnectionManager(connectionManager)
//Define a tabela a ser consultada para a tabela user
.setTable("user")
//Performa a ação no banco de dados e retorna uma lista
//Mapeando os dados para uma List da classe User
.queryList(User.class);
```

</details>

<details>

<summary>Retornando o dado na classe mapeada</summary>

```java
User user =
//Instancia a Classe Transaction 
new Transaction()
//Define o método de uso como o SELECT
.select()
//Define o ConnectionManager a ser usado
.setConnectionManager(connectionManager)
//Define a tabela a ser consultada para a tabela user
.setTable("user")
//Performa a ação no banco de dados e retorna uma lista
//Mapeando os dados para a Classe User
.queryResult(User.class);
```

</details>



### Usando o filtro na consulta

Há casos em que devemos usar algum sistema de filtro para podermos obter um determinado registro,para isso temos a função filter na API.

Suponhamos que queremos retornar um dado da classe **User** cujo o usuário e a senha seja iguais a dado banco de dados.

**OBS:** O filter ele funciona com base do WHERE do SQL

```java
User user =
//Instancia a Classe Transaction 
new Transaction()
//Define o método de uso como o SELECT
.select()
//Define o ConnectionManager a ser usado
.setConnectionManager(connectionManager)
//Define a tabela a ser consultada para a tabela user
.setTable("user")
//Cria um filtro para ser processado no Select do banco de dados
.filter("username='youusername' and password ='your_passsword'")
//Performa a ação no banco de dados e retorna uma lista
//Mapeando os dados para a Classe User
.queryResult(User.class);
```
