package omis.caseload.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caseload.domain.Caseload;
import omis.caseload.domain.CaseworkCategory;

/**
 * Caseload implementation.
 *
 * @author Sheronda Vaughn
 * @author Ryan Johns
 * @version 0.1.0 (May 18, 2017)
 * @since OMIS 3.0
 */
public class CaseloadImpl implements Caseload {
	
	private static final long serialVersionUID = 1L;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private CaseworkCategory category;
	
	private String name;
	
	private Caseload caseload;

	/** Instantiates an implementation of CaseloadImpl. */
	public CaseloadImpl() {
		// Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
					final CreationSignature creationSignature) {
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
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final CaseworkCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public CaseworkCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setCaseload(final Caseload caseload) {
		this.caseload = caseload;
	}

	/** {@inheritDoc} */
	@Override
	public Caseload getCaseload() {
		return this.caseload;
	}	
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Caseload)) {
			return false;
		}
		
		Caseload that = (Caseload) obj;
		if (this.getCategory() == null) {
			throw new IllegalStateException("Case work category required.");
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getCategory() == null) {
			throw new IllegalStateException("Case work category required.");
		}
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;
	}
}