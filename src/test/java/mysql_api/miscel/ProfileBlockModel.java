package mysql_api.miscel;

import java.util.List;

import api.interfaces.annotations.ForeignKey;

public class ProfileBlockModel {
	
	private int id;
    private String player;

    @ForeignKey(
            targetEntity = BlocksLimitModel.class,
            referencedColumnName = "profileId",
            mappedByOrigin = "id"
    )
    private List<BlocksLimitModel> blocks;

    public int getId() {
        return id;
    }

    public List<BlocksLimitModel> getBlocks() {
        if (blocks == null) {
            blocks = new java.util.ArrayList<>();
        }
        return blocks;
    }

    public void addBlock(BlocksLimitModel block) {
        if (blocks == null) {
            blocks = new java.util.ArrayList<>();
        }
        blocks.add(block);
    }

    public void removeBlock(BlocksLimitModel block) {
        if (blocks == null) {
            blocks = new java.util.ArrayList<>();
        }
        blocks.remove(block);
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "ProfileBlockModel [id=" + id + ", player=" + player + ", blocks=" + blocks + "]";
	}
	
	
}
