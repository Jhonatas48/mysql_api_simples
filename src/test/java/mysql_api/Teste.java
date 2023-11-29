package mysql_api;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import api.connection.ConnectionManager;
import api.connection.impl.pools.MysqlConnection;
import api.connection.impl.pools.SqliteConnection;
import api.core.AsyncManager;
import api.interfaces.ICommitAction;
import api.models.CommitActionImpl;
import api.models.Transaction;
import api.models.atributes.PrimaryKey;
import api.models.statements.Result;
import api.models.statements.Row;
import mysql_api.class_test.LminvitesUser;

public class Teste {

    public static void main(String[] args) throws InterruptedException {


        ConnectionManager connectionManager1 = new ConnectionManager("teste1");
        connectionManager1.addConnection(new SqliteConnection("connection1", "teste.db"));
        System.out.println("teste");
        
        new Transaction().select().setTable("teste").queryResultAsync(Db.class,new Consumer<Db>() {

			@Override
			public void accept(Db object) {
				
				//usa o objeto do banco de dados
			}
        	
		});


//		try {
//			PreparedStatement s = connectionManager1.getConnection().prepareStatement("Insert into user(username,password)values(?,?)");
//			Db db = new Db();
//			db.insert(s,"t");
//			db.insert(s, "teste");
//			System.out.println(s.toString());
//			s.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////
//		ConnectionManager connectionManager2 = new ConnectionManager("teste2");
//		connectionManager2.addConnection(new SqliteConnection("connection2", "db2.db"));
//
//		System.out.println(connectionManager1.getConnection()); // Saída: [connection1]
//		System.out.println(connectionManager2.getConnection()); // Saída: [connection2]
//		
//		
//		new Transaction().create().setConnectionManager(connectionManager1).
//		setTable("teste1")
//		.addColumn("id","Integer",new PrimaryKey().addAutoIncrement())
//		.addColumn("teste","varchar(10)")
//		.commit();
//		new Transaction().create().setConnectionManager(connectionManager2).setTable("teste2")
//		.addColumn("id","Integer",new PrimaryKey().addAutoIncrement())
//		.addColumn("teste","varchar(10)")
//		.commit();
//		System.out.println("Insert Teste1");
//		new Transaction().insert().setConnectionManager(connectionManager1).setTable("teste1").addColumn("teste","teste1").commit();
//		System.out.println("Insert Teste1");
//		new Transaction().insert().setConnectionManager(connectionManager2).setTable("teste2").addColumn("teste","teste2").commit();
//		
//		new Transaction().update()
//		.setConnectionManager(connectionManager1)
//		.setTable("teste1")
//		.addColumn("teste","testando1").filter("id =1").commit();
//		
//		new Transaction().update()
//		.setConnectionManager(connectionManager2)
//		.setTable("teste2")
//		.addColumn("teste","testando2").filter("id =1").commit();
//		
//		System.out.println("Insert Teste1");
//		new Transaction().insert().setConnectionManager(connectionManager1).setTable("teste1").addColumn("teste","teste11").commit();
//		System.out.println("Insert Teste2");
//		new Transaction().insert().setConnectionManager(connectionManager2).setTable("teste2").addColumn("teste","teste22").commit();
//		new Transaction().delete().setConnectionManager(connectionManager1).setTable("teste1").filter("id=2").commit();
//		new Transaction().delete().setConnectionManager(connectionManager2).setTable("teste2").filter("id=2").commit();
//		
//		SqlBuilder sqlTransaction;
//		
//		 sqlTransaction = new SqlBuilder("sqlite", "teste", null, null, null, null, null, null);
//	     sqlTransaction.createBuilder().setTable("lminvites_users").addColumn("id", "INTEGER", new PrimaryKey().addAutoIncrement()).addColumn("discordId", "VARCHAR(255)").addColumn("uuid", "VARCHAR(255)").addColumn("invitesReals", "INTEGER").addColumn("invitesFakes", "INTEGER").addColumn("invitesLeaves", "INTEGER").commit();
//	     LminvitesUser u =  sqlTransaction
//	     .selectBuilder()
//	     .setTable("lminvites_users")
//	     .addColumn("discordId")
//	     .addColumn("uuid")
//	     .addColumn("invitesReals")
//	     .addColumn("invitesFakes")
//	     .addColumn("invitesLeaves")
//	     .queryResult(LminvitesUser.class);

        
       List<LminvitesUser>a=  new Transaction().select().setConnectionManager(connectionManager1).setTable("teste").queryList(LminvitesUser.class);
//	     System.out.println("Terminou");
//////		
//	     new Transaction().
//	    		//Define o método de uso como o SELECT
//	    		select()
//	    		//Define o ConnectionManager a ser usado
//	    		.setConnectionManager(connectionManager1)
//	    		//Define a tabela a ser consultada
//	    		.setTable("users")
//	    		//Performa a ação no banco de dados
//	    		.queryListAsync(User.class, new Consumer<List<User>>(){
//
//					@Override
//					public void accept(List<User> u) {
//						// TODO Auto-generated method stub
//						System.out.println("assincrono");
//					}
//	    			
////	    		});

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
        	

        //new Transaction().select().setTable("product p").addInnerjoin("invoice i","i.productId = p.id");
        //.addColumn("username", "11111")
        //.addColumn("password", "password");

//		ConnectionManager connectionManager1 = new ConnectionManager("teste1");
//		connectionManager1.addConnection(new SqliteConnection("connection1", "teste.db"));
//		
//		User user = new Transaction()
//				.select()
//				.setConnectionManager(connectionManager1)
//				.setTable("user u")
//				.addInnerjoin("table t","u.id=t.id")
//				.queryResult(User.class);
//		System.out.println(user.toString());
        // TODO Auto-generated method stub
//     	ConnectionManager.addConnection(new SqliteConnection("sqlite","database"));
//     	ConnectionManager.addConnection(new SqliteConnection("teste","teste"));
//    	//ConnectionManager.addConnection(new SqliteConnection("teste","teste"));
//		List<ExampleEntity>t = new Transaction()
//       .select()
//       .setTable("example_table")
//      
//       .queryList(ExampleEntity.class);
//		
//		if(Checkers.isListEmpty(t)) {
//			System.out.println("Lista nula");
//			
//			return;
//		}
//		System.out.println(ConnectionManager.getConnectionByName("teste"));

//		
//		 Class<?> clazz = Ticket.class;
//
//	        // Obtém todos os campos da classe, incluindo campos herdados
//	        Field[] fields = clazz.getDeclaredFields();
//
//	        // Itera sobre os campos e imprime suas informações
//	        for (Field field : fields) {
//	            String fieldName = field.getName();
//	            String fieldType = field.getType().getSimpleName();
//	            String fieldModifiers = java.lang.reflect.Modifier.toString(field.getModifiers());
//
//	            System.out.println(fieldModifiers + " " + fieldType + " " + fieldName);
//	        }
    }


}
