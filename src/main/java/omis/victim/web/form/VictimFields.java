package omis.victim.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * Victim fields.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 5, 2015)
 * @since OMIS 3.0
 */
public class VictimFields implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date registerDate;
	
	private Boolean packetSent;
	
	private Date packetSendDate;
	
	private Boolean victim;
	
	private Boolean docRegistered;
	
	private Boolean business;
	
	private Boolean vineRegistered;
	
	/**
	 * Instantiates a default instance of victim fields.
	 */
	public VictimFields() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of victim fields with the specified properties.
	 * 
	 * @param registerDate register date
	 * @param packetSent packet sent
	 * @param packetSendDate packet send date
	 * @param victim victim
	 * @param docRegistered doc registered
	 * @param business business
	 * @param vineRegistered vine registered
	 */
	public VictimFields(
			final Date registerDate, final Boolean packetSent,
			final Date packetSendDate, final Boolean victim,
			final Boolean docRegistered, final Boolean business,
			final Boolean vineRegistered) {
		this.registerDate = registerDate;
		this.packetSent = packetSent;
		this.packetSendDate = packetSendDate;
		this.victim = victim;
		this.docRegistered = docRegistered;
		this.business = business;
		this.vineRegistered = vineRegistered;
	}

	/**
	 * Returns the register date.
	 * 
	 * @return register date
	 */
	public Date getRegisterDate() {
		return this.registerDate;
	}

	/**
	 * Sets the register date.
	 * 
	 * @param registerDate register date
	 */
	public void setRegisterDate(final Date registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * Returns whether packet sent applies.
	 * 
	 * @return packet sent
	 */
	public Boolean getPacketSent() {
		return this.packetSent;
	}

	/**
	 * Sets whether packet sent applies.
	 * 
	 * @param packetSent packet sent
	 */
	public void setPacketSent(final Boolean packetSent) {
		this.packetSent = packetSent;
	}

	/**
	 * Returns the packet send date.
	 * 
	 * @return packet send date
	 */
	public Date getPacketSendDate() {
		return this.packetSendDate;
	}

	/**
	 * Sets the packet send date.
	 * 
	 * @param packetSendDate packet send date
	 */
	public void setPacketSendDate(final Date packetSendDate) {
		this.packetSendDate = packetSendDate;
	}

	/**
	 * Returns whether victim applies.
	 * 
	 * @return victim
	 */
	public Boolean getVictim() {
		return this.victim;
	}

	/**
	 * Sets whether victim applies.
	 * 
	 * @param victim
	 */
	public void setVictim(final Boolean victim) {
		this.victim = victim;
	}

	/**
	 * Returns whether DOC registered applies.
	 * 
	 * @return DOC registered
	 */
	public Boolean getDocRegistered() {
		return this.docRegistered;
	}

	/**
	 * Sets whether DOC registered applies.
	 * 
	 * @param docRegistered DOC registered
	 */
	public void setDocRegistered(final Boolean docRegistered) {
		this.docRegistered = docRegistered;
	}

	/**
	 * Returns whether business applies.
	 * 
	 * @return business
	 */
	public Boolean getBusiness() {
		return this.business;
	}

	/**
	 * Sets whether business applies.
	 * 
	 * @param business business
	 */
	public void setBusiness(final Boolean business) {
		this.business = business;
	}

	/**
	 * Returns whether vine registered applies.
	 * 
	 * @return vine registered
	 */
	public Boolean getVineRegistered() {
		return this.vineRegistered;
	}

	/**
	 * Sets whether vine registered applies.
	 * 
	 * @param vineRegistered vine registered
	 */
	public void setVineRegistered(final Boolean vineRegistered) {
		this.vineRegistered = vineRegistered;
	}
}