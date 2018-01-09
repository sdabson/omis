package omis.courtcasecondition.domain.impl;

import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
/**
 * 
 * CourtCaseAgreementCategoryImpl.java
 * 
 *@author unsigned
 *@author Annie Jacques 
 *@version 0.1.1 (May 22, 2017)
 *@since OMIS 3.0
 *
 */
public class CourtCaseAgreementCategoryImpl implements CourtCaseAgreementCategory{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;

	private static final String EMPTY_NAME_MSG
	="Name required.";
	
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
	public String getName() {
		return this.name;
	}

	/**{@inheritDoc}*/
	@Override
	public void setName(final String name) {
		this.name = name;
		
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		}else if (obj instanceof CourtCaseAgreementCategory) {
			this.checkState();
			CourtCaseAgreementCategory that 
				= (CourtCaseAgreementCategory) obj;
			if (this.getName().equals(that.getName()))
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

		hashCode = 29 * hashCode + this.getName().hashCode();
		return hashCode;
	}
		
	/* Checks state. */
	private void checkState() {

		if (this.getName() == null) {
			throw new IllegalStateException(EMPTY_NAME_MSG);
		}
	}
}