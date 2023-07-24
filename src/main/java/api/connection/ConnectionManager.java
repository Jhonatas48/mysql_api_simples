package api.connection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import api.connection.impl.SqliteConnection;
import api.exception.ConnectionNotEstablishedException;
import api.interfaces.ITransaction;
import api.models.Transaction;
import api.models.atributes.ForeignKey;
import api.models.atributes.PrimaryKey;
import api.models.enums.ConnectionType;

public class ConnectionManager {

	private static boolean firstRun = true;

	private static IConnection<?> primaryConnection = null;

	private static IConnection<?> logConnection = null;

	private static ConnectionType connectionType;

	private static List<IConnection<?>> list = new ArrayList<>();
	private static List<IConnection<?>> listException = new ArrayList<>();

	private static Connection testConnection() throws ConnectionNotEstablishedException {

		if (list.size() == 0) {

			throw new NullPointerException("Not exists database connections register");
		}
		Connection connection = null;
		for (IConnection<?> iConnection : list) {

			connection = iConnection.openConnection();

			if (connection != null) {

				primaryConnection = iConnection;
				connectionType = primaryConnection.geConnectionType();
				firstRun = false;
				break;

			}
			if (listException.contains(iConnection)) {
				continue;
			}
			System.out.println("[API-MSQL]Connection " + iConnection.geConnectionType() + " not estabilized");
			System.out.println("Try next connection");
			listException.add(iConnection);

		}

		if (primaryConnection == null) {

			throw new ConnectionNotEstablishedException();
		}

		if (firstRun) {
			return connection;
		}

		logConnection = new SqliteConnection("log");
		createTablesLogs();
		return connection;

	}

	private static void createTablesLogs() {

		ITransaction transaction = new Transaction();
		transaction.create().setTable("transactions").setConnection(logConnection)
				.addColumn("id", "INTEGER", new PrimaryKey().addAutoIncrement()).addColumn("sql", "TEXT NOT NULL")
				.addColumn("transaction_type",
						"TEXT CHECK(\"transaction_type\" IN ('INSERT', 'SELECT', 'UPDATE', 'DELETE', 'CREATE_TABLE', 'TRUNCATE')) NOT NULL")
				.addColumn("milliseconds", "REAL NOT NULL").commit();

		// transaction.createTable("CREATE TABLE IF NOT EXISTS \"transactions\" (\"id\"
		// INTEGER,\"sql\" TEXT NOT NULL,\"transaction_type\" TEXT
		// CHECK(\"transaction_type\" IN ('INSERT', 'SELECT', 'UPDATE', 'DELETE',
		// 'CREATE_TABLE', 'TRUNCATE')) NOT NULL, \"milliseconds\" REAL NOT NULL,PRIMARY
		// KEY(\"id\" AUTOINCREMENT))",logConnection);
		transaction.create().setTable("transactions_values").setConnection(logConnection)
				.addColumn("id", "INTEGER", new PrimaryKey().addAutoIncrement()).addColumn("field", "text")
				.addColumn("transactionID", "INTEGER",
						new ForeignKey().setTableForeign("transactions").setColumnForeign("id"))
				.addColumn("parameterOrder", "INTEGER").commit();
		// transaction.createTable("CREATE TABLE IF NOT EXISTS \"transactions_values\"
		// (\"id\" INTEGER,\"transactionId\" INTEGER,\"parameterOrder\"
		// INTEGER,\"field\" TEXT, PRIMARY KEY(\"Id\" AUTOINCREMENT),FOREIGN
		// KEY(\"TransactionId\") REFERENCES \"transactions\"(\"id\"))",logConnection);
		transaction.create().setTable("lastest_transaction").setConnection(logConnection)
				.addColumn("id", "INTEGER", new PrimaryKey().addAutoIncrement())
				.addColumn("transactionID", "INTEGER",
						new ForeignKey().setTableForeign("transactions").setColumnForeign("id"))
				.addColumn("milliseconds", "REAL").commit();
		// transaction.createTable("CREATE TABLE IF NOT EXISTS \"lastest_transaction\" (
		// \"id\" INTEGER,\"transactionId\" INTEGER,\"milliseconds\" REAL,PRIMARY
		// KEY(\"id\" AUTOINCREMENT),FOREIGN KEY(\"transactionId\") REFERENCES
		// \"transactions\"(\"id\"))",logConnection);

	}

	public static Connection getConnection() {

		if (primaryConnection != null) {

			Connection connection = primaryConnection.openConnection();
			if (connection != null) {
				return connection;
			}

		}
		try {

			return testConnection();

		} catch (ConnectionNotEstablishedException e) {

			e.printStackTrace();
		}

		return primaryConnection.openConnection();
	}

	public void closeConnection() {

		if (primaryConnection != null) {
			primaryConnection.closeConnection(getConnection());
		}
	}

	public static void addConnection(IConnection<?> connection) {

		list.add(connection);

	}

	public static void removeConnection(String connectionTypeName) {

		if (list.size() == 0) {
			return;
		}

		list.forEach(connection -> {

			String connectionType = connection.geConnectionType().toString().toLowerCase();
			if (connectionType.equals(connectionTypeName.toLowerCase())) {

				if (primaryConnection == connection) {
					primaryConnection = null;
					firstRun = true;
				}
				list.remove(connection);
				return;
			}
		});
	}

	public static ConnectionType getConnectionType() {
		return connectionType;
	}

	public static Connection getLogConnecton() {

		if (logConnection == null) {
			logConnection = new SqliteConnection("log.db");
		}
		try {
			testConnection();
		} catch (ConnectionNotEstablishedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logConnection.openConnection();
	}

}
