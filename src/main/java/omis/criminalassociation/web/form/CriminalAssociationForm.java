package omis.criminalassociation.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.criminalassociation.domain.CriminalAssociationCategory;
import omis.offender.domain.Offender;

/**
 * Form for creating and editing criminal association.
 * 
 * @author Joel Norris 
 * @author Yidong Li
 * @version 0.1.1 (Apr, 14 2015)
 * @since OMIS 3.0
 */
public class CriminalAssociationForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Date startDate;
	
	private Date endDate;
	
	private CriminalAssociationCategory criminalAssociationCategory;
	
	private Offender otherOffender;
	
	private Boolean witness;

	
	/** Instantiates a default criminal association form. */
	public CriminalAssociationForm() {
		//Default constructor.
	}
	
	/**
	 * Returns the start date of the criminal association form.
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date of the criminal association form.
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date of the criminal association form.
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date of the criminal association form.
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the criminal association category.
	 * 
	 * @return the criminal association category
	 */
	public CriminalAssociationCategory getCriminalAssociationCategory() {
		return this.criminalAssociationCategory;
	}

	/**
	 * Sets the criminal association category.
	 * 
	 * @param criminalAssociationCategory criminal association category
	 */
	public void setCriminalAssociationCategory(
			final CriminalAssociationCategory criminalAssociationCategory) {
		this.criminalAssociationCategory = criminalAssociationCategory;
	}
	
	/**
	 * Returns the witness.
	 * 
	 * @return the witness
	 */
	public Boolean getWitness() {
		return this.witness;
	}

	/**
	 * Sets the witness.
	 * 
	 * @param witness witness
	 */
	public void setWitness(final Boolean witness) {
		this.witness = witness;
	}

	/**
	 * Returns the other involved offender.
	 * 
	 * @return the otherOffender
	 */
	public Offender getOtherOffender() {
		return this.otherOffender;
	}

	/**
	 * Sets the other involved offender.
	 * 
	 * @param otherOffender the otherOffender to set
	 */
	public void setOtherOffender(final Offender otherOffender) {
		this.otherOffender = otherOffender;
	}
}