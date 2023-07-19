package mysql_api;

import com.github.jhonatas48.api.connection.ConnectionManager;
import com.github.jhonatas48.api.connection.impl.SqliteConnection;
import com.github.jhonatas48.api.models.Transaction;
import com.github.jhonatas48.api.models.atributes.PrimaryKey;

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
