package mysql_api.annotations.test;

import api.interfaces.annotations.Table;

@Table(name="plans")
public class Plans {

	 private int id;

	 private int userid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userid;
	}

	public void setUserId(int userId) {
		this.userid = userId;
	}

	@Override
	public String toString() {
		return "Plans [id=" + id + ", userId=" + userid + "]";
	}
	 
	 
}
