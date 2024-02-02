package mysql_api.miscel;

import api.interfaces.annotations.Table;

@Table(name = "BlocksLimit")
public class BlocksLimitModel {

    private int id;

    private String block;
    private int amount;
    private int profileId;

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void removeAmount(int amount) {
        if (this.amount - amount < 0) {
            this.amount = 0;
            return;
        }
        this.amount -= amount;
    }

    public void setProfileId(int id) {
        this.profileId = id;
    }

    public String getStringBlock() {
        return block;
    }

	@Override
	public String toString() {
		return "BlocksLimitModel [id=" + id + ", block=" + block + ", amount=" + amount + ", profileId=" + profileId
				+ "]";
	}
    
}
