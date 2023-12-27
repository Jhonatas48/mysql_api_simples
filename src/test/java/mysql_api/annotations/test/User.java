package mysql_api.annotations.test;

import java.util.List;

import api.interfaces.annotations.ForeignKey;
import api.interfaces.annotations.Table;

@Table(name="user")
public class User {
	
	private int id;

    @ForeignKey(targetEntity  = Plans.class, referencedColumnName  = "userid",mappedByOrigin = "id")
    private List<Plans> plans;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Plans> getPlans() {
		return plans;
	}

	public void setPlans(List<Plans> plans) {
		this.plans = plans;
	}

    
}
