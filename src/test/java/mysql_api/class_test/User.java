package mysql_api.class_test;

import java.time.LocalDateTime;

public class User {
	
	private int id;
	private String username;
	private String password;
	private LocalDateTime updated;
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", updated=" + updated + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getUpdated() {
		return updated;
	}
	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}
	

}
