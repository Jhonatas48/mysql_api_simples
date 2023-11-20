package mysql_api.class_test;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import api.models.utils.Checkers;

public class Ticket {

	private int id;
	private String channelId;
	//             channelId
	private String protocol;
	private String userOwnerId;
	private String userName;
	private int departamentId;
	private String departamentName;
	private String closeMessageId;
	private String guildId;
	//private TicketPriority priority;
	private String reason;
    private String userclosedId;
    private boolean closed;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channeId) {
		this.channelId = channeId;
	}
	public String getProtocol() {
		if(Checkers.isEmpty(protocol)) {
			
			OffsetDateTime date = OffsetDateTime.now();
		   
			protocol=date.format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"))+"-"+id;
			
		}
		return protocol;
	}
	public void setProtocol(String protol) {
		this.protocol = protol;
	}
	public String getUserOwnerId() {
		return userOwnerId;
	}
	public void setUserOwnerId(String userOwnerId) {
		this.userOwnerId = userOwnerId;
	}
	/*
	public String getCloseMessageId() {
		return closeMessageId;
	}
	public void setCloseMessageId(String closeMessageId) {
		this.closeMessageId = closeMessageId;
	}
	*/
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGuildId() {
		return guildId;
	}
	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}
	
	public int getDepartamentId() {
		return departamentId;
	}
	public void setDepartamentId(int departamentid) {
		this.departamentId = departamentid;
	}
	/*
	public TicketPriority getPriority() {
		return priority;
	}
	public void setPriority(TicketPriority priority) {
		this.priority = priority;
	}
	*/
	public String getDepartamentName() {
		return departamentName;
	}
	
	public void setDepartamentName(String departamentName) {
		this.departamentName = departamentName;
	}
	/**
	 * @return the closeMessageId
	 */
	public String getCloseMessageId() {
		return closeMessageId;
	}
	/**
	 * @param closeMessageId the closeMessageId to set
	 */
	public void setCloseMessageId(String closeMessageId) {
		this.closeMessageId = closeMessageId;
	}
	/**
	 * @return the ticketClosed
	 */
	public boolean isTicketClosed() {
		return closed;
	}
	/**
	 * @param ticketClosed the ticketClosed to set
	 */
	public void setTicketClosed(boolean ticketClosed) {
		this.closed = ticketClosed;
	}
	
	public String getReason() {
		if(Checkers.isNull(reason)) {
			return "Não Informado";
		}
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getUserclosedId() {
		return userclosedId;
	}
	public void setUserclosedId(String userclosedId) {
		this.userclosedId = userclosedId;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", channeId=" + channelId + ", protocol=" + protocol + ", userOwnerId=" + userOwnerId
				+ ", userName=" + userName + ", departamentId=" + departamentId + ", departamentName=" + departamentName
				+ ", closeMessageId=" + closeMessageId + ", guildId=" + guildId + ", reason=" + reason
				+ ", userclosedId=" + userclosedId + ", ticketClosed=" + closed + "]";
	}
	
	
	
	
}
