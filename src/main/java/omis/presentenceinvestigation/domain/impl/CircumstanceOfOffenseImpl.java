package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.CircumstanceOfOffense;
import omis.presentenceinvestigation.domain.OffenseSectionSummary;
import omis.presentenceinvestigation.domain.component.DefendantsStatement;

/**
 * CircumstanceOfOffenseImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 31, 2017)
 *@since OMIS 3.0
 *
 */
public class CircumstanceOfOffenseImpl implements CircumstanceOfOffense {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private OffenseSectionSummary offenseSectionSummary;
	
	private Document document;
	
	private DefendantsStatement statement;
	
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
	public Document getDocument() {
		return this.document;
	}

	/**{@inheritDoc} */
	@Override
	public void setDocument(final Document document) {
		this.document = document;
	}

	/**{@inheritDoc} */
	@Override
	public OffenseSectionSummary getOffenseSectionSummary() {
		return this.offenseSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public void setOffenseSectionSummary(
			final OffenseSectionSummary offenseSectionSummary) {
		this.offenseSectionSummary = offenseSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public DefendantsStatement getStatement() {
		return this.statement;
	}

	/**{@inheritDoc} */
	@Override
	public void setStatement(final DefendantsStatement statement) {
		this.statement = statement;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof CircumstanceOfOffense)){
			return false;
		}
		
		CircumstanceOfOffense that = (CircumstanceOfOffense) obj;
		
		if(this.getOffenseSectionSummary() == null){
			throw new IllegalStateException("OffenseSectionSummary required.");
		}
		if(this.getStatement().getChargeReason() == null){
			throw new IllegalStateException("ChargeReason required.");
		}
		if(this.getStatement().getInvolvementReason() == null){
			throw new IllegalStateException("InvolvementReason required.");
		}
		if(this.getStatement().getCourtRecommendation() == null){
			throw new IllegalStateException("CourtRecommendation required.");
		}
		
		if(!this.getOffenseSectionSummary().equals(that.getOffenseSectionSummary())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getOffenseSectionSummary() == null){
			throw new IllegalStateException("OffenseSectionSummary required.");
		}
		if(this.getStatement().getChargeReason() == null){
			throw new IllegalStateException("ChargeReason required.");
		}
		if(this.getStatement().getInvolvementReason() == null){
			throw new IllegalStateException("InvolvementReason required.");
		}
		if(this.getStatement().getCourtRecommendation() == null){
			throw new IllegalStateException("CourtRecommendation required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getOffenseSectionSummary().hashCode();
		
		return hashCode;
	}
	
	
}
