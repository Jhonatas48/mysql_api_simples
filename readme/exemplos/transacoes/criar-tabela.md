# Criar Tabela



### Definindo o ConnectionManager

{% hint style="info" %}
Para executar qualquer operação no banco de dados deve passar qual o ConnectionManager deverá ser usado
{% endhint %}

```java
ConnectionManager connectionManager = new ConnectionManager("name");

// Estou definindo a Conexão com o SQLITE diretamente
// no métoddo para adicionar a conexão
connectionManager.addConnection(new SqliteConnection("connection2", "db2.db"));

```

### Criando a Transação

```java
//Cria a interface ITransaction
new Transaction()
//Define que irá fazer uma operação de criação de tabela
.create()
//Define a tabela a ser usada
.setTable("yourtable")
//Adiciona as colunas
//Estou definindo o campo como chave primária com autoincremento
.addColumn("id","Integer",new PrimaryKey().addAutoIncrement())
//Adiciono uma coluna com o tipo varchar
.addColumn("teste","varchar(10)")
//Executa a operação no banco de dados
.commit();
```

### Criando a Transação com uma conexão

```java
IConnection connection = new SqliteConnection("name_connection", "db2.db");

//Cria a interface ITransaction
new Transaction()
//Define que irá fazer uma operação de criação de tabela
.create()
//Define a tabela a ser usada
.setTable("yourtable")
//Define a conexão a ser usada
.setConnection(connection)
//Adiciona as colunas
//Estou definindo o campo como chave primária com autoincremento
.addColumn("id","Integer",new PrimaryKey().addAutoIncrement())
//Adiciono uma coluna com o tipo varchar
.addColumn("teste","varchar(10)")
//Executa a operação no banco de dados
.commit();
```
