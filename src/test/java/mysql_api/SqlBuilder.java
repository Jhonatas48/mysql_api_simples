package mysql_api;

import api.connection.ConnectionManager;
import api.connection.impl.pools.MysqlConnection;
import api.connection.impl.pools.SqliteConnection;
import api.interfaces.*;
import api.interfaces.actions.ICreate;
import api.interfaces.actions.IDelete;
import api.interfaces.actions.IInsert;
import api.interfaces.actions.ISelect;
import api.interfaces.actions.IUpdate;
import api.models.Transaction;
public class SqlBuilder {
	
	private final Transaction transaction;
    private ConnectionManager connectionManager;

    public SqlBuilder(String type, String pluginName, String host, String user, String database, String password, Integer port, Boolean useSSL) {
        ConnectionManager manager = new ConnectionManager(pluginName);
        if (type.equalsIgnoreCase("sqlite")) {
            manager.addConnection(new SqliteConnection(pluginName, "plugins/" + pluginName + "/database.db"));
        } else if (type.equalsIgnoreCase("mysql")) {
            MysqlConnection connector = new MysqlConnection(pluginName);
            connector.setAdress(host);
            connector.setUsername(user);
            connector.setDatabase(database);
            connector.setPassword(password);
            connector.setPort(port);
            connector.setUseSSL(useSSL);
            manager.addConnection(connector);
        }
        transaction = new Transaction();
        connectionManager = manager;
    }

    public ICreate createBuilder() {
        return transaction.create().setConnectionManager(connectionManager);
    }

    public ISelect selectBuilder() {
        return transaction.select().setConnectionManager(connectionManager);
    }

    public IUpdate updateBuilder() {
        return transaction.update().setConnectionManager(connectionManager);
    }

    public IInsert insertBuilder() {
        return transaction.insert().setConnectionManager(connectionManager);
    }

    public IDelete delete() {
        return transaction.delete().setConnectionManager(connectionManager);
    }

}
