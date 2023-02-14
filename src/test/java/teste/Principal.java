package teste;


import api.connection.ConnectionManager;
import api.connection.impl.MysqlConnection;
import api.connection.impl.SqliteConnection;
import api.models.Transaction;
import api.models.atributes.PrimaryKey;


public class Principal {

	public static void main(String[] args) {
	
	
	//	System.out.println(col);
		
		//ConnectionManager.getConnection();
	   MysqlConnection connection = new MysqlConnection();
		connection.setAdress("127.0.0.1");
		connection.setDatabase("test");
		connection.setPort(3306);
		connection.setUsername("root");
		connection.setPassword("");
	    ConnectionManager.addConnection(connection);
		ConnectionManager.addConnection(new SqliteConnection("log3"));
	 
		new Transaction().update().setTable("teste3").addColumn("teste","2").filter("id='1'").commit();
		/*
	   Create c = new Create();
	   c.setTable("table");
	  c.addColumn("p","v");
	   c.addColumn("o","v");
	   c.addColumn("id","PRIMARY KEY AUTO_INCREMENT");
	   c.addColumn("uuid", "VARCHAR (50)");
      c.addColumn("nick", "VARCHAR (50)");
       c.addColumn("nick", "VARCHAR (50)");
       c.addColumn("idnick", "VARCHAR (50)");
	   CreateTableSQLITE sql = new CreateTableSQLITE();
	   sql.buildSQL(c);
	   CreateTableMysql mysql = new CreateTableMysql();
	  */ //System.out.println("MYSQL: "+mysql.buildSQL(c));
	   	
	  // new Transaction().create().setTable("teste3").addColumn("id","INTEGER",new PrimaryKey().addAutoIncrement()).commit();
	   // .addColumn("dateTeste", "datetime").commit();
       /*addColumn("nick", "VARCHAR (50)")
       .addColumn("idnick", "VARCHAR (50)")
       .commit();
	   new Transaction().insert().setTable("teste").addColumn("uuid","teste").commit();
	   Result r  = new Transaction().select().setTable("teste").addColumn("id").queryResult();
	   r.getRows().forEach(row->{
		   
		   System.out.println("id: "+row.get("id"));
		   System.out.println("uuid "+row.get("uuid"));
	   });
	   */
	   /*
		ITransaction a = new Transaction();
		Result r = a.select().setTable("transactions t")
	    .addColumn("t.id")
	    .addColumn("t.sql")
	    .addColumn("tv.parameterOrder")
	    .addColumn("tv.field")
	    .addInnerjoin("transactions_values tv", "t.id=tv.transactionid")
	    .filter("t.id =1")
	    .queryResult();
		r.getRows().forEach(row->{
			System.out.println("--------------------------");
			System.out.println(row.get("id"));
			System.out.println(row.get("parameterOrder"));
			System.out.println(row.get("field"));
			System.out.println("--------------------------");
		});
		
		*/
		//a.filter(null);
		//a.delete().setTable("t").filter(null).commit();
	
	/*	Transaction transaction = new Transaction(true);
		Create create = new Create();
		create.setTable("teste");
		create.addColumn("teste","int");
	
		transaction.createTable(create);
		
		create =null;
		create = new Create();
		
		create.setTable("ticket");
		create.addColumn("id","INTEGER PRIMARY KEY AUTOINCREMENT");
		create.addColumn("guildId","TEXT");
		create.addColumn("userId","TEXT");
		create.addColumn("protocol","TEXT");
		create.addColumn("channelId","TEXT");
		create.addColumn("messageCloseId" ,"TEXT");
		create.addColumn("departamentId","INTEGER");
		
		transaction.createTable(create);
		
		create =null;
		create = new Create();
		
		create.setTable("ticketDepartament");
		create.addColumn("id","INTEGER PRIMARY KEY AUTOINCREMENT");
		create.addColumn("guildId","TEXT");
		create.addColumn("name","TEXT");
		
		transaction.createTable(create);
		
		create =null;
		create = new Create();
		
		create.setTable("ticketOperator");
		create.addColumn("id","INTEGER PRIMARY KEY AUTOINCREMENT");
		create.addColumn("guildId","TEXT");
		create.addColumn("ticketId","INTEGER");
		create.addColumn("departament","INTEGER");
		create.addColumn("FOREIGN","KEY(ticketId) REFERENCES ticket(id),FOREIGN KEY(departament) REFERENCES ticketDepartament(id)");
		transaction.createTable(create);
		
		create =null;
		create = new Create();
		create.setTable("ticketSettings");
		create.addColumn("id","INTEGER PRIMARY KEY AUTOINCREMENT");
		create.addColumn("guildId","TEXT");
		create.addColumn("propertie","TEXT");
		create.addColumn("value","TEXT");
		transaction.createTable(create);
		
		
		
		Insert insert = new Insert();
		insert.setTable("ticketSettings");
		insert.setColumns("guildId,propertie,value");
		
		//List<String>data = new ArrayList<>();
		//String[] data = new String[3];
		//data.add(guilId.toString());
		//data.add(propertie.toString());
		//data.add(value.toString());
	//	data[0]="1";
		//data[1]="1";
		//data[2]="teste";
		
		List<String>data = new ArrayList<>();
		data.add("1");
		data.add("2");
		data.add("teste");
		insert.setData(data);
		
		transaction.tableInsert(insert);
		
		*/
		/*NewTransaction t = new NewTransaction();
		t.update();
		t.setTable("a");
		t.addColumn("a").addColumn("b").addColumn("c");
		t.addColumn("jfdjdhjw,skdjhidq,afhahf,ndjahfj,najfh");
		t.queryResult();
		//t.commit();
		*/
		
	}

}
