package mysql_api.teste;

import java.util.Objects;


public class TicketDepartament {

	private int id;
	private String name;
	private String guildId;
	private String categoryId;
	private boolean requiredlinkwhmcs;
	private boolean requiredreason;
	private boolean active;
	
	public TicketDepartament() {
		
	}
	
	public TicketDepartament(int id, String name) {
		
		this.id = id;
		this.name = name;
	}
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

	public String getGuildId() {
		return guildId;
	}

	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, guildId, id, name);
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		TicketDepartament other = (TicketDepartament) obj;
//		return Checkers.isStringEquals(categoryId, other.categoryId) && Checkers.isStringEquals(guildId, other.guildId) && id == other.id
//				&& Checkers.isStringEquals(name, other.name);
//	}

	public boolean isRequiredlinkwhmcs() {
		return requiredlinkwhmcs;
	}

	public void setRequiredlinkwhmcs(boolean requiredlinkwhmcs) {
		this.requiredlinkwhmcs = requiredlinkwhmcs;
	}

	public boolean isRequiredreason() {
		return requiredreason;
	}

	public void setRequiredreason(boolean requiredreason) {
		this.requiredreason = requiredreason;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
