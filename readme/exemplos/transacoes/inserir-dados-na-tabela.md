# Inserir Dados Na Tabela

## Inicializando o ConnectionManager

{% hint style="info" %}
Para executar qualquer operação no banco de dados deve passar qual o ConnectionManager deverá ser usado
{% endhint %}

```java
ConnectionManager connectionManager = new ConnectionManager("name");

// Estou definindo a Conexão com o SQLITE diretamente
// no métoddo para adicionar a conexão
connectionManager.addConnection(new SqliteConnection("connection_name", "db2.db"));
```

### Inserindo dados com o ConnectionManager

```java
new Transaction()
.insert()
.setConnectionManager(connectionManager)
//Define em qual tabela deverá ser inserido
.setTable("teste1")
//Define o nome da tabela e o dado a ser inserido
.addColumn("teste","teste1")
//Executa o comando no banco de dados
.commit();
```

### Inserindo dados com o IConnection

```java
IConnection connection = new SqliteConnection("name_connection", "db2.db");

new Transaction()
.insert()
//Define a conexão a ser usada
.setConnection(connection)
//Define em qual tabela deverá ser inserido
.setTable("teste1")
//Define o nome da tabela e o dado a ser inserido
.addColumn("teste","teste1")
//Executa o comando no banco de dados
.commit();
```

### Inserindo dados aplicando o Consumer

```java
IConnection connection = new SqliteConnection("name_connection", "db2.db");

new Transaction()
.insert()
//Define a conexão a ser usada
.setConnection(connection)
//Define em qual tabela deverá ser inserido
.setTable("teste1")
//Define o nome da tabela e o dado a ser inserido
.addColumn("teste","teste1")

//Executa o comando no banco de dados e obtém o erro
.commit(new Consumer<Throwable>(){
    @Override
     public void accept(Throwable error) {
     
       //Checagem obrigatória para quando usa o método consumer
        if(error != null){
           .printStackTrace();
        }
				
    }
});
```
