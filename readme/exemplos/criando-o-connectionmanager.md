# Criando o ConnectionManager



### Criando a instância do ConnectionManager

```java
ConnectionManager connectionManager = new ConnectionManager("name");
```

### Criando a instância de conexão para usar o SQLITE

```java
SqliteConnection sqlite = new SqliteConnection("connection_name", "yourdirectory/filename.db")
```

### Criando a instância de conexão para usar o MySQL

```java
MysqlConnection mysql = new MysqlConnection("connection_name");

mysql.setAdress("domain/IP"); //Obrigatório
//Opcional
mysql.setPort(3306); //Por padrão a porta será 3306

/*
 *  Opcional, entretando é altamente recomendável definir uma senha
 *  para seu usuário do banco de dados
 */
mysql.setUsername("your_database_user");//Obrigatório
mysql.setPassword("your_database_user_password");
mysql.setDatabase("your_database");//Obrigatório
//Opcional
mysql.setUseSSL(false); // Defino se o banco de dados está usando SSL
```

### Adicionando a instância de conexão no ConnectionManager

{% hint style="info" %}
Os nomes das conexões devem ser único senão irá disparar o erro:\
\
<mark style="color:red;">DuplicateConnectionNameException</mark>:  Ocorre quando o nome dá conexão já existe
{% endhint %}

```java
connectionManager.addConnection(sqlite);
connectionManager.addConnection(sqlite);
```

{% hint style="info" %}
Caso deseje, pode criar a instância do SQLite diretamente no método de adicionar a conexão
{% endhint %}

```java
connectionManager.addConnection(new new SqliteConnection("connection_name", "yourdirectory/filename.db");
```



Como podemos ver anteriormente, podemos registrar quantas conexões forem necessárias, sendo de instâncias diferentes.
