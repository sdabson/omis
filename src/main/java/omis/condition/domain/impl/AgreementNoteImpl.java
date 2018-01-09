package omis.condition.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementNote;

/**
 * Agreement Note.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @version 0.1.0 (Jul 6, 2016)
 * @since OMIS 3.0
 */
public class AgreementNoteImpl implements AgreementNote{
	
	private static final long serialVersionUID = 1L;

	private static final String EMPTY_DATE_MSG = "Date Required.";

	private static final String EMPTY_DESCRIPTION_MSG = "Description Required.";

	private static final String EMPTY_AGREEMENT_MSG = "Agreement Required.";
	
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	private Long id;
	private Date date;
	private String description;
	private Agreement agreement;

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
	public void setId(final Long id) {
		this.id=id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public Agreement getAgreement() {
		return this.agreement;
	}

	/** {@inheritDoc} */
	@Override
	public void setAgreement(final Agreement agreement) {
		this.agreement=agreement;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		}else if (obj instanceof AgreementNote) {
			this.checkState();
			AgreementNote that 
				= (AgreementNote) obj;
			if (this.getDate().equals(that.getDate())
					&& this.getDescription().equals(that.getDescription())
					&& this.getAgreement().equals(that.getAgreement()))
			{
				result = true;
			}
		}
		return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		this.checkState();
		int hashCode = 14;

		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getAgreement().hashCode();
		
		return hashCode;
	}
		
	/* Checks state. */
	private void checkState() {

		if (this.getDate() == null) {
			throw new IllegalStateException(EMPTY_DATE_MSG);
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException(EMPTY_DESCRIPTION_MSG);
		}
		if (this.getAgreement() == null) {
			throw new IllegalStateException(EMPTY_AGREEMENT_MSG);
		}
	}

}
