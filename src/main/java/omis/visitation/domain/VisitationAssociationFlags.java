package omis.visitation.domain;

import java.io.Serializable;

/**
 * Visitation association flags.
 * 
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.1 (Aug 29, 2017)
 * @since OMIS 3.0
 */
public class VisitationAssociationFlags implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean money;
	
	private Boolean nonContact;
	
	private Boolean courtOrder;
	
	private Boolean specialVisit;
	
	/**
	 * Instantiates a default instance of visitation association flags.
	 */
	public VisitationAssociationFlags() {
		//Default constructor.
	}
	
	/**
	 * Instantiates visitation association flags with the specified flags.
	 * 
	 * @param money money
	 * @param nonContact non contact
	 * @param courtOrder court order
	 * @param specialVisit special visit
	 */
	public VisitationAssociationFlags(final Boolean money, 
			final Boolean nonContact, final Boolean courtOrder,
			final Boolean specialVisit) {
		this.money = money;
		this.nonContact = nonContact;
		this.courtOrder = courtOrder;
		this.specialVisit = specialVisit;
	}

	/**
	 * Returns money.
	 * 
	 * @return money
	 */
	public Boolean getMoney() {
		return this.money;
	}

	/**
	 * Sets money.
	 * 
	 * @param money money
	 */
	public void setMoney(final Boolean money) {
		this.money = money;
	}

	/**
	 * Returns whether non contact applies.
	 * 
	 * @return non contact
	 */
	public Boolean getNonContact() {
		return this.nonContact;
	}

	/**
	 * Sets whether non contact applies.
	 * 
	 * @param nonContact non contact
	 */
	public void setNonContact(final Boolean nonContact) {
		this.nonContact = nonContact;
	}

	/**
	 * Returns whether court order applies.
	 * 
	 * @return court order
	 */
	public Boolean getCourtOrder() {
		return this.courtOrder;
	}

	/**
	 * Sets whether court order applies.
	 * 
	 * @param courtOrder court order
	 */
	public void setCourtOrder(final Boolean courtOrder) {
		this.courtOrder = courtOrder;
	}

	/**
	 * Returns whether special visit applies.
	 * 
	 * @return special visit
	 */
	public Boolean getSpecialVisit() {
		return this.specialVisit;
	}

	/**
	 * Sets whether special visit applies.
	 * 
	 * @param specialVisit special visit
	 */
	public void setSpecialVisit(final Boolean specialVisit) {
		this.specialVisit = specialVisit;
	}
}