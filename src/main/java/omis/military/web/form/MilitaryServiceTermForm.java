package omis.military.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import omis.military.domain.MilitaryBranch;
import omis.military.domain.MilitaryDischargeStatus;

/**
 * Military service term form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 15, 2015)
 * @since OMIS 3.0
 */
public class MilitaryServiceTermForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date startDate;
	
	private Date endDate;
	
	private MilitaryBranch branch;
	
	private MilitaryDischargeStatus dischargeStatus;
	
	private List<MilitaryServiceTermNoteItem> serviceTermNoteItems;
	
	/** Instantiates a default instance of military service term form. */
	public MilitaryServiceTermForm() {
		//Default constructor.
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the military branch.
	 * 
	 * @return military branch
	 */
	public MilitaryBranch getBranch() {
		return this.branch;
	}

	/**
	 * Sets the military branch.
	 * 
	 * @param branch military branch
	 */
	public void setBranch(final MilitaryBranch branch) {
		this.branch = branch;
	}

	/**
	 * Returns the military discharge status.
	 * 
	 * @return military discharge status
	 */
	public MilitaryDischargeStatus getDischargeStatus() {
		return this.dischargeStatus;
	}

	/**
	 * Sets the military discharge status.
	 * 
	 * @param dischargeStatus military discharge status
	 */
	public void setDischargeStatus(
			final MilitaryDischargeStatus dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}

	/**
	 * Returns the service term note items.
	 * 
	 * @return list of military service term note items
	 */
	public List<MilitaryServiceTermNoteItem> getServiceTermNoteItems() {
		return this.serviceTermNoteItems;
	}

	/**
	 * Sets the service term note items.
	 * 
	 * @param serviceTermNoteItems military service term note items
	 */
	public void setServiceTermNoteItems(
			final List<MilitaryServiceTermNoteItem> serviceTermNoteItems) {
		this.serviceTermNoteItems = serviceTermNoteItems;
	}
}