package mysql_api;

import api.connection.ConnectionManager;
import api.connection.impl.SqliteConnection;
import api.models.Transaction;
import api.models.atributes.PrimaryKey;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionManager.addConnection(new SqliteConnection("teste"));
		new Transaction()
        .create().setTable("minaCreate")
        .addColumn("id", "INTEGER", new PrimaryKey().addAutoIncrement())
        .addColumn("namemina", "VARCHAR (256)")
        .addColumn("icon", "VARCHAR (256)")
        .addColumn("author", "VARCHAR (16)")
        .commit();
        
		
		new Transaction().insert().setTable("minaCreate").addColumn("namemina","teste").addColumn("icon","teste")
		.commit();
	}

}
