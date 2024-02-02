package mysql_api.stress;

import api.connection.ConnectionManager;
import api.connection.impl.pools.SqliteConnection;
import api.models.Transaction;
import api.models.atributes.PrimaryKey;

public class MainStress {

	public static void main(String[] args) {
		   ConnectionManager connectionManager1 = new ConnectionManager("teste1");
	        connectionManager1.addConnection(new SqliteConnection("connection1", "teste.db"));
	       
	      //  System.out.println("teste");
    
        new Transaction()
//  		//Define o método de uso como o Insert
                .create()
//  		//Define o ConnectionManager a ser usado
                .setConnectionManager(connectionManager1)
//  		//Define a tabela a ser consultada
                .setTable("test")
                .addColumn("id", "integer", new PrimaryKey().addAutoIncrement()).addColumn("teste", "varchar(30)")
                
                .commit(
                    //    t -> System.out.println("executou assincrono"),
                        err -> System.out.println("Ocorreu erro: " + err.getMessage())
                );
        System.out.println(connectionManager1.getTableFields("test"));
        long time = System.currentTimeMillis();
        for(int c=0;c<1000;c++) {
        	new Transaction().insert()
        	 .setConnectionManager(connectionManager1)
        	.setTable("test").addColumn("teste","teste "+c).commitAsync(b->{
        		
        	},e->{
          		 e.printStackTrace();
          	 });
        	new Transaction().update().setTable("test")
       	 .setConnectionManager(connectionManager1)
       	 .addColumn("teste","teste").filter("id="+c).commitAsync(a->{},e->{
       		 e.printStackTrace();
       	 });
           
        }
        connectionManager1.shutdown();
      
	}

}
