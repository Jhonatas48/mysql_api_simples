package mysql_api.teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import api.connection.ConnectionManager;
import api.connection.impl.pools.MysqlConnection;
import api.connection.impl.pools.SqliteConnection;
import api.models.Transaction;
import api.models.atributes.PrimaryKey;

public class Main {

	public static void main(String[] args) {
	  ConnectionManager manager = new ConnectionManager("teste");

	  	manager.addConnection(new SqliteConnection("database","database"));
	  	new Transaction().select().setConnectionManager(manager)
	  	.setTable("ticketDepartament").queryList(TicketDepartament.class);
	  	
	  //	  MysqlConnection conn = new MysqlConnection("teste");
//	  conn.setAdress("181.215.46.186");
//	  conn.setUsername("teste");
//	  conn.setPassword("teste");
//	  conn.setDatabase("teste");
//	  
//	  
//	  manager.addConnection(conn);
//	 // manager.addConnection(new SqliteConnection("teste","teste"));
//	  int numberOfIterations = 1000; // Defina o número de iterações desejado
//
//	  for(int c=0;c<numberOfIterations;c++){
//	  new Transaction()
//	  .create()
//	  .setConnectionManager(manager)
//	  .setTable("teste"+c)
//	  .addColumn("id","INTEGER",new PrimaryKey().addAutoIncrement())
//	  .addColumn("nome","varchar(20)")
//	  .addColumn("idade","int")
//	  .commit();
//	}
//	 
//      long totalElapsedTime = 0;
//     
//      long t1=System.currentTimeMillis();
//      
//      long min=0;
//      long max=0;
//      
//      for (int i = 0; i < numberOfIterations; i++) {
//          System.out.println("Iteração: " + (i + 1));
//
//          long startTime = System.currentTimeMillis();
//
//          // Coloque o código fornecido aqui
//          System.out.println("Iniciou");
//          new Transaction()
//                  .insert()
//                  .setConnectionManager(manager)
//                  .setTable("teste")
//                  .addColumn("nome", "jhonatas")
//                  .addColumn("idade", 99)
//                  .commit();
//        
//          long elapsedTime = System.currentTimeMillis() - startTime;
//          
//          if(elapsedTime < min || i==0 ) {
//        	  min=elapsedTime;
//          }
//          
//          if(elapsedTime > max || i==0 ) {
//        	  max=elapsedTime;
//          }
//          totalElapsedTime += elapsedTime;
//
//          System.out.println("Inseriu em " + elapsedTime + " milissegundos\n");
//      }
//
//      double averageTime = (double) totalElapsedTime / numberOfIterations;
//      System.out.println("Média de tempo de inserção: " + averageTime + " milissegundos");
//	  System.out.println("Tempo total: "+(System.currentTimeMillis()-t1)+"ms");
//	  System.out.println("Min: "+min+" ms, Max: "+max+" ms");
	}


}
