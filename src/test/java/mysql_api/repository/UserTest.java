package mysql_api.repository;

import mysql_api.annotations.test.Plans;

public class UserTest extends Repo<UserTest>{

	private int id;
	private String name;
	private Plans p;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Plans getP() {
		return p;
	}
	public void setP(Plans p) {
		this.p = p;
	}
	
	
}
