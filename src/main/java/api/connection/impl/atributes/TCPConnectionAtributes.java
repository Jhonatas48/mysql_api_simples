package api.connection.impl.atributes;

import api.connection.connectionatributes.ITCPConnecction;

public abstract class  TCPConnectionAtributes implements ITCPConnecction {

	private String username;
	private String password;
	private String adress;
	private int port;
	private String database;
	private boolean useSSL;
	
	
	public String getAtributesConnectionTCP() {
		String atributes = "://"+getAdress()+":"+getPort()+"/"+getDatabase(); 
		return isUseSSL()?atributes:atributes+"?useSSL=true";
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getAdress() {
		return adress;
	}
	
	@Override
	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String getDatabase() {
		return database;
	}

	@Override
	public void setDatabase(String database) {
		this.database = database;
	}

	@Override
	public boolean isUseSSL() {
		return useSSL;
	}

	@Override
	public void setUseSSL(boolean useSSL) {
		this.useSSL = useSSL;
	}
	
	

}
