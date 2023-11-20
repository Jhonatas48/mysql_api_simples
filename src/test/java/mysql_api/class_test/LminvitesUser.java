package mysql_api.class_test;
public class LminvitesUser {
    private int id;
    private String discordId;
    private String uuid;
    private int invitesReals;
    private int invitesFakes;
    private int invitesLeaves;

    // Construtor vazio
    public LminvitesUser() {
    }

    // Construtor com parâmetros
    public LminvitesUser(int id, String discordId, String uuid, int invitesReals, int invitesFakes, int invitesLeaves) {
        this.id = id;
        this.discordId = discordId;
        this.uuid = uuid;
        this.invitesReals = invitesReals;
        this.invitesFakes = invitesFakes;
        this.invitesLeaves = invitesLeaves;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getInvitesReals() {
        return invitesReals;
    }

    public void setInvitesReals(int invitesReals) {
        this.invitesReals = invitesReals;
    }

    public int getInvitesFakes() {
        return invitesFakes;
    }

    public void setInvitesFakes(int invitesFakes) {
        this.invitesFakes = invitesFakes;
    }

    public int getInvitesLeaves() {
        return invitesLeaves;
    }

    public void setInvitesLeaves(int invitesLeaves) {
        this.invitesLeaves = invitesLeaves;
    }

    @Override
    public String toString() {
        return "LminvitesUser{" +
                "id=" + id +
                ", discordId='" + discordId + '\'' +
                ", uuid='" + uuid + '\'' +
                ", invitesReals=" + invitesReals +
                ", invitesFakes=" + invitesFakes +
                ", invitesLeaves=" + invitesLeaves +
                '}';
    }
}
