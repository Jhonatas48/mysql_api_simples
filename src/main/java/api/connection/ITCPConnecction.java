package api.connection;

public interface ITCPConnecction extends IConnectionAtributes {

	public void setUsername(String username);

	public String getUsername();

	public void setPassword(String password);

	public void setAdress(String Adress);

	public String getAdress();

	public void setPort(int port);

	public int getPort();

	public void setDatabase(String databaseName);

	public String getDatabase();

	public void setUseSSL(boolean useSSL);

	public boolean isUseSSL();
}
