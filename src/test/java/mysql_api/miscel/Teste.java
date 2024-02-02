package mysql_api.miscel;

import java.util.List;

import api.connection.ConnectionManager;
import api.connection.impl.pools.SqliteConnection;
import api.models.Transaction;
import api.models.atributes.PrimaryKey;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ConnectionManager manager = new ConnectionManager("db");
		manager.addConnection(new SqliteConnection("database_5","database_5"));

//		new Transaction().create()
//		.setConnectionManager(manager)
//        .setTable("ProfileBlockLimit")
//        .addColumn("id", "INTEGER", new PrimaryKey().addAutoIncrement())
//        .addColumn("player", "VARCHAR(36)")
//        .commit();
//		
//		new Transaction().create().setConnectionManager(manager)
//		.setTable("BlocksLimit")
//        .addColumn("id", "INTEGER", new PrimaryKey().addAutoIncrement())
//        .addColumn("profileId", "INTEGER")
//        .addColumn("block", "VARCHAR(10)")
//        .addColumn("amount", "INTEGER")
//        .commit();
		
		
		 List<ProfileBlockModel> m=  new Transaction().select()
		 .setConnectionManager(manager)
         .setTable("ProfileBlockLimit")
         //.addInnerjoin("BlocksLimit b2", "b1.id=b2.profileId")
         //.addColumn("b1.id")
         //.addColumn("b1.player")
         .queryList(ProfileBlockModel.class);
		 m.forEach(t->{
			 System.out.println("T: "+t.toString());
		 });
		 
//		 
//		 ProfileBlockModel mo =  new Transaction().select()
//		 .setConnectionManager(manager)
//         .setTable("ProfileBlockLimit")
//         .addColumn("id")
//         .addColumn("player")
//         .filter("player='2e98db5d-0076-3f62-8a3d-8d3ca7d557ec'")
//         .queryResult(ProfileBlockModel.class);
		//manager.getTableFields("ProfileBlockLimit").forEach(t-> System.out.println("T: "+t));
//		 m.forEach(a->{
//			 System.out.println("D: "+a.toString());
		 //});

	}

}
