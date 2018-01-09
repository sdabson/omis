package omis.courtcasecondition.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.domain.Agreement;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.docket.domain.Docket;

/**
 * 
 * CourtCaseAgreementImpl.java
 *
 *@author unsigned
 *@author Annie Jacques 
 *@version 0.1.1 (Aug 29, 2017)
 *@since OMIS 3.0
 *
 */
public class CourtCaseAgreementImpl implements CourtCaseAgreement {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	private Agreement agreement;
	private Docket docket;
	private Date acceptedDate;
	private CourtCaseAgreementCategory courtCaseAgreementCategory;
	

	private static final String EMPTY_AGREEMENT_MSG = "Agreement Required";
	private static final String EMPTY_AGREEMENT_CATEGORY_MSG =
			"Agreement Category Required";
	private static final String EMPTY_DOCKET_MSG =
			"Docket is Required";
	
	public CourtCaseAgreementImpl()
	{
		//default constructor for hibernate
	}
	
	/**{@inheritDoc}*/
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**{@inheritDoc}*/
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc}*/
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;

	}

	/**{@inheritDoc}*/
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public Agreement getAgreement() {
		return this.agreement;
	}

	/**{@inheritDoc}*/
	@Override
	public void setAgreement(final Agreement agreement) {
		this.agreement = agreement;

	}

	/**{@inheritDoc} */
	@Override
	public Docket getDocket() {
		return this.docket;
	}
	
	/**{@inheritDoc} */
	@Override
	public void setDocket(final Docket docket) {
		this.docket = docket;
	}
	
	/**{@inheritDoc} */
	@Override
	public Date getAcceptedDate() {
		return this.acceptedDate;
	}
	
	/**{@inheritDoc} */
	@Override
	public void setAcceptedDate(final Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
	
	/**{@inheritDoc}*/
	@Override
	public CourtCaseAgreementCategory getCourtCaseAgreementCategory() {
		return this.courtCaseAgreementCategory;
	}

	/**{@inheritDoc}*/
	@Override
	public void setCourtCaseAgreementCategory(final CourtCaseAgreementCategory 
			courtCaseAgreementCategory) {
		this.courtCaseAgreementCategory = courtCaseAgreementCategory;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		} else {
			if (obj instanceof CourtCaseAgreement) {
				this.checkState();
				CourtCaseAgreement that 
					= (CourtCaseAgreement) obj;
				if (this.getDocket().equals(that.getDocket()) 
						&& this.getAgreement().equals(that.getAgreement())
						&& this.getCourtCaseAgreementCategory()
							.equals(that.getCourtCaseAgreementCategory())  ) {
					result = true;
				}
			}
		}
		
		return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		this.checkState();
		int hashCode = 14;

		hashCode = 29 * hashCode + this.getDocket().hashCode();
		hashCode = 29 * hashCode + this.getAgreement().hashCode();
		hashCode = 29 * hashCode + this.getCourtCaseAgreementCategory()
			.hashCode();
		
		return hashCode;
	}
	
	/* Checks state. */
	private void checkState() {

		if (this.getDocket() == null) {
			throw new IllegalStateException(EMPTY_DOCKET_MSG);
		}
		if (this.getAgreement() == null) {
			throw new IllegalStateException(EMPTY_AGREEMENT_MSG);
		}
		if (this.getCourtCaseAgreementCategory() == null) {
			throw new IllegalStateException(EMPTY_AGREEMENT_CATEGORY_MSG);
		}
	}
}
