package omis.caseload.domain.impl;

import java.io.Serializable;
import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caseload.domain.CaseloadContact;
import omis.caseload.domain.ContactCategory;
import omis.caseload.domain.OffenderCaseAssignment;
import omis.person.domain.Person;

/**
 * Caseload contact implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 27, 2016)
 * @since OMIS 3.0
 */
public class CaseloadContactImpl implements Serializable, CaseloadContact {

	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private Date contactDate;
	
	private Person contactBy;
	
	private ContactCategory category;
	
	private OffenderCaseAssignment caseAssignment;
	
	private String caseNote;

	/** Instantiates an implementation of CaseloadContactImpl */
	public CaseloadContactImpl() {
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
	public void setContactDate(final Date contactDate) {
		this.contactDate = contactDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getContactDate() {
		return this.contactDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setContactBy(final Person contactBy) {
		this.contactBy = contactBy;
	}

	/** {@inheritDoc} */
	@Override
	public Person getContactBy() {
		return this.contactBy;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final ContactCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public ContactCategory getCategory() {
		return this.category;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCaseAssignment(final OffenderCaseAssignment caseAssignment) {
		this.caseAssignment = caseAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderCaseAssignment getCaseAssignment() {
		return this.caseAssignment;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCaseNote(final String caseNote) {
		this.caseNote = caseNote;
	}

	/** {@inheritDoc} */
	@Override
	public String getCaseNote() {
		return this.caseNote;
	}


	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CaseloadContact)) {
			return false;
		}
		
		CaseloadContact that = (CaseloadContact) obj;
		if (this.getContactDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (!this.getContactDate().equals(that.getContactDate())) {
			return false;
		}
		if (this.getContactBy() == null) {
			throw new IllegalStateException(
					"Caseload contact by person required.");
		}
		if (!this.getContactBy().equals(that.getContactBy())) {
			return false;
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Case work category required.");
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		if (this.getCaseAssignment() == null) {
			throw new IllegalStateException(
					"Offender case assignment required.");
		}
		if (!this.getCaseAssignment().equals(that.getCaseAssignment())) {
			return false;
		}
		if (this.getCaseNote() == null) {
			throw new IllegalStateException("Case note required.");
		}
		if (!this.getCaseNote().equals(that.getCaseNote())) {
			return false;
		}
		return true;		
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getContactDate() == null) {
			throw new IllegalStateException("Contact date required.");
		}
		if (this.getContactBy() == null) {
			throw new IllegalStateException(
					"Caseload contact by person required.");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Case work category required.");
		}
		if (this.getCaseAssignment() == null) {
			throw new IllegalStateException(
					"Offender case assignment required.");
		}
		if (this.getCaseNote() == null) {
			throw new IllegalStateException("Case note required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getContactDate().hashCode();
		hashCode = 29 * hashCode + this.getContactBy().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		hashCode = 29 * hashCode + this.getCaseAssignment().hashCode();
		hashCode = 29 * hashCode + this.getCaseNote().hashCode();
		
		return hashCode;
	}
}