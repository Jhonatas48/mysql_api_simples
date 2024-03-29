package api.connection.impl.pools;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import api.connection.impl.atributes.ConnectionAtributesFiles;
import api.interfaces.connection.IConnection;
import api.models.enums.ConnectionType;
import api.models.utils.Checkers;

public class SqliteConnection extends ConnectionAtributesFiles implements IConnection<ConnectionAtributesFiles>{

	private String name;
	Connection connection;
	public SqliteConnection(String nameConnection , String nameFile) {
		Checkers.validateStringNotNull(nameConnection, "nameConnection");
		Checkers.validateStringNotNull(nameFile, "nameFile");
		this.name = nameConnection;


		Path path = Paths.get(nameFile);

		if (path.isAbsolute()) {
			this.setNameFile(nameFile);

			// Se o nome do arquivo não contiver extensão, acrescenta ".db"
			if (!path.getFileName().toString().contains(".")) {
				nameFile += ".db";
			}

			return;
		}

		if (nameFile.startsWith("./")) {
			// Obter o diretório de trabalho atual
			Path currentDirectory = Paths.get(System.getProperty("user.dir"));
			// Remover o "./" do caminho do arquivo e concatenar com o diretório atual
			String correctedPath = currentDirectory.resolve(nameFile.substring(2)).toString();
			nameFile = correctedPath;
		}

		String[] nameWiExtension = nameFile.split("\\.");

		if(nameWiExtension.length < 2) {
			this.setNameFile(nameFile+".db");
			return;
		}
		this.setNameFile(nameWiExtension[0]+".db");
	}

	@Override
	public String getAtributesConnection() {

		return "jdbc:sqlite"+getAtributesConnectionFiles()+"?journal_mode=WAL";
	}
	@Override
	public Connection openConnection() {

		Connection connection=null;
		try {
			if(this.connection != null && !connection.isClosed()) {
				return this.connection;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.connection=null;
		}
		try {

			//Class.forName("org.sqlite.JDBC");

			connection = DriverManager.getConnection(getAtributesConnection());

		} catch (SQLException  e) {

			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public void closeConnection(Connection connection) {

		if(connection != null) {

			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	public void closeResultSet(ResultSet resultSet) {

		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("Error closing ResultSet");
				e.printStackTrace();
			}
		}
	}

	@Override
	public ConnectionAtributesFiles getAtributes() {

		return this;
	}

	@Override
	public ConnectionType getConnectionType() {

		return ConnectionType.SQLITE;
	}

	@Override
	public void syncronize() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}