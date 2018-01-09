package omis.employment.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;

import java.util.Date;

import omis.employment.domain.EmploymentNote;
import omis.employment.domain.EmploymentTerm;

/** Implementation of Job.
 * @author Yidong Li
 * @version 0.1.0 (Feb 2, 2014)
 * @since OMIS 3.0 */
public class EmploymentNoteImpl implements EmploymentNote {
	private Long id;
	private static final long serialVersionUID = 1L;
	private String value;
	private Date date;
	private EmploymentTerm employmentTerm;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;

	/** Constructor. */
	public EmploymentNoteImpl() {
		
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(final String value) {
		this.value = value;
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
	public EmploymentTerm getEmploymentTerm() {
		return this.employmentTerm;
	}

	/** {@inheritDoc} */
	@Override
	public void setEmploymentTerm(final EmploymentTerm employmentTerm) {
		this.employmentTerm = employmentTerm;
	}
	
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EmploymentNote)) {
			return false;
		}
		EmploymentNote that = (EmploymentNote) obj;
		if (this.getEmploymentTerm() == null) {
			throw new IllegalStateException("Employment term required");
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}		
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if(this.getEmploymentTerm().equals(that.getEmploymentTerm())
			&& this.getDate().equals(that.getDate())) {
			return true;
		} else {
			return false;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 7;
		
		if (this.getEmploymentTerm() == null) {
			throw new IllegalStateException("Employment term required");
		}
		if (this.getValue()==null) {
			throw new IllegalStateException("Value required");
		}		
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		hashCode += 29 * this.getEmploymentTerm().hashCode();
		hashCode += 29 * this.getValue().hashCode();
		hashCode += 29 * this.getDate().hashCode();
		return hashCode;
	}
}
