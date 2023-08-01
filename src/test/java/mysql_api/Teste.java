package mysql_api;

import api.connection.ConnectionManager;
import api.connection.impl.SqliteConnection;
import api.models.Transaction;
import api.models.atributes.PrimaryKey;
import api.models.statements.Result;
import api.models.statements.Row;

public class Teste {

	public static void main(String[] args) {
		
//		ConnectionManager connectionManager1 = new ConnectionManager("teste1");
//		connectionManager1.addConnection(new SqliteConnection("connection1", "db1.db"));
//
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
		SqlBuilder sqlTransaction;
		
		 sqlTransaction = new SqlBuilder("sqlite", "teste", null, null, null, null, null, null);
	     sqlTransaction.createBuilder().setTable("lminvites_users").addColumn("id", "INTEGER", new PrimaryKey().addAutoIncrement()).addColumn("discordId", "VARCHAR(255)").addColumn("uuid", "VARCHAR(255)").addColumn("invitesReals", "INTEGER").addColumn("invitesFakes", "INTEGER").addColumn("invitesLeaves", "INTEGER").commit();
	     LminvitesUser u =  sqlTransaction
	     .selectBuilder()
	     .setTable("lminvites_users")
	     .addColumn("discordId")
	     .addColumn("uuid")
	     .addColumn("invitesReals")
	     .addColumn("invitesFakes")
	     .addColumn("invitesLeaves")
	     .queryResult(LminvitesUser.class);
	    
	     System.out.println("Terminou");
//		
		
		
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
