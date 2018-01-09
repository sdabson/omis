package omis.presentenceinvestigation.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummary;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummaryNote;

/**
 * PleaAgreementSectionSummaryNote.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public class PleaAgreementSectionSummaryNoteImpl
		implements PleaAgreementSectionSummaryNote {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private PleaAgreementSectionSummary pleaAgreementSectionSummary;
	
	private String description;
	
	private Date date;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**{@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/**{@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/**{@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummary getPleaAgreementSectionSummary() {
		return this.pleaAgreementSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public void setPleaAgreementSectionSummary(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary) {
		this.pleaAgreementSectionSummary = pleaAgreementSectionSummary;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof PleaAgreementSectionSummaryNote)){
			return false;
		}
		
		PleaAgreementSectionSummaryNote that =
				(PleaAgreementSectionSummaryNote) obj;
		
		if(this.getPleaAgreementSectionSummary() == null){
			throw new IllegalStateException(
					"PleaAgreementSectionSummary required.");
		}
		if(this.getDescription() == null){
			throw new IllegalStateException("Description required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		
		if(!this.getPleaAgreementSectionSummary().equals
				(that.getPleaAgreementSectionSummary())){
			return false;
		}
		if(!this.getDescription().equals(that.getDescription())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getPleaAgreementSectionSummary() == null){
			throw new IllegalStateException(
					"PleaAgreementSectionSummary required.");
		}
		if(this.getDescription() == null){
			throw new IllegalStateException("Description required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode +
				this.getPleaAgreementSectionSummary().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		
		return hashCode;
	}
	
}
