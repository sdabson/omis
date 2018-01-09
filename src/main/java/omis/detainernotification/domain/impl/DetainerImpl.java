
package omis.detainernotification.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.DetainerAgency;
import omis.detainernotification.domain.DetainerJurisdictionCategory;
import omis.detainernotification.domain.DetainerType;
import omis.offender.domain.Offender;

/**
 * Detainer implementation
 * 
 *@author Annie Jacques 
 *@author Joel Norris
 *@version 0.1.1 (May 25, 2017)
 *@since OMIS 3.0
 *
 */
public class DetainerImpl implements Detainer {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender;
	
	private String alternateOffenderId;
	
	private String offenseDescription;
	
	private String courtCaseNumber;
	
	private DetainerType detainerType;
	
	private DetainerAgency detainerAgency;
	
	private DetainerJurisdictionCategory jurisdiction;
	
	private Date receiveDate;
	
	private Date issueDate;
	
	private String warrantNumber;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/* Constructors */
	
	public DetainerImpl(){
		//Nothing
	}
	
	
	
	/* Getters and Setters */
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
		
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
		
	}
	
	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public String getAlternateOffenderId() {
		return this.alternateOffenderId;
	}

	/** {@inheritDoc} */
	@Override
	public String getOffenseDescription() {
		return this.offenseDescription;
	}

	/** {@inheritDoc} */
	@Override
	public String getCourtCaseNumber() {
		return this.courtCaseNumber;
	}
	
	/** {@inheritDoc} */
	@Override
	public DetainerType getDetainerType(){
		return this.detainerType;
	}

	/** {@inheritDoc} */
	@Override
	public DetainerAgency getDetainerAgency() {
		return this.detainerAgency;
	}

	/** {@inheritDoc} */
	@Override
	public DetainerJurisdictionCategory getJurisdiction() {
		return this.jurisdiction;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;		
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;		
	}

	/** {@inheritDoc} */
	@Override
	public void setAlternateOffenderId(final String alternateOffenderId) {
		this.alternateOffenderId = alternateOffenderId;		
	}

	/** {@inheritDoc} */
	@Override
	public void setOffenseDescription(final String offenseDescription) {
		this.offenseDescription = offenseDescription;		
	}

	/** {@inheritDoc} */
	@Override
	public void setCourtCaseNumber(final String courtCaseNumber) {
		this.courtCaseNumber = courtCaseNumber;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDetainerType(final DetainerType detainerType){
		this.detainerType = detainerType;
	}

	/** {@inheritDoc} */
	@Override
	public void setDetainerAgency(final DetainerAgency detainerAgency) {
		this.detainerAgency = detainerAgency;
	}

	/** {@inheritDoc} */
	@Override
	public void setJurisdiction(final DetainerJurisdictionCategory jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getReceiveDate() {
		return this.receiveDate;
	}


	/** {@inheritDoc} */
	@Override
	public void setReceiveDate(final Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getIssueDate() {
		return this.issueDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setIssueDate(final Date issueDate) {
		this.issueDate = issueDate;
	}

	/** {@inheritDoc} */
	@Override
	public String getWarrantNumber() {
		return this.warrantNumber;
	}

	/** {@inheritDoc} */
	@Override
	public void setWarrantNumber(final String warrantNumber) {
		this.warrantNumber = warrantNumber;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof Detainer)){
			return false;
		}
		
		Detainer that = (Detainer) obj;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getReceiveDate() == null) {
			throw new IllegalStateException("Receive date required");
		}
		if (!this.getReceiveDate().equals(that.getReceiveDate())) {
			return false;
		}
		if (this.getCourtCaseNumber() != null) {
			if (!this.getCourtCaseNumber().equals(that.getCourtCaseNumber())) {
				return false;
			}
		} else if (that.getCourtCaseNumber() != null) {
			return false;
		}
		if (this.getWarrantNumber() != null) {
			if (!this.getWarrantNumber().equals(that.getWarrantNumber())) {
				return false;
			}
		} else if (that.getWarrantNumber() != null) {
			return false;
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (this.getReceiveDate() == null) {
			throw new IllegalStateException("Receive date required");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getReceiveDate().hashCode();
		if (this.getCourtCaseNumber() != null) {
			hashCode = 29 * hashCode + this.getCourtCaseNumber().hashCode();
		}
		if (this.getWarrantNumber() != null) {
			hashCode = 29 * hashCode + this.getWarrantNumber().hashCode();
		}
		return hashCode;
	}
}