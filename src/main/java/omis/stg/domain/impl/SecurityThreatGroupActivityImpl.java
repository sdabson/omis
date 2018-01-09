package omis.stg.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.person.domain.Person;
import omis.stg.domain.SecurityThreatGroupActivity;

/**
 * Security threat group activity implementation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 29, 2016)
 * @since OMIS 3.0
 */

public class SecurityThreatGroupActivityImpl 
		implements SecurityThreatGroupActivity {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Date date;
	
	private Person reportedBy;
	
	private String summary;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates an implementation of security threat group activity level.
	 */
	public SecurityThreatGroupActivityImpl() {
		// Default constructor
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
	public void setReportDate(final Date date) {
		this.date = date;		
	}

	/** {@inheritDoc} */
	@Override
	public Date getReportDate() {
		return this.date;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/** {@inheritDoc} */
	@Override
	public String getSummary() {
		return this.summary;
	}
	
	/** {@inheritDoc} */
	@Override
	public Person getReportedBy() {
		return this.reportedBy;
	}

	/** {@inheritDoc} */
	@Override
	public void setReportedBy(Person reportedBy) {
		this.reportedBy = reportedBy;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SecurityThreatGroupActivity)) {
			return false;
		}
		SecurityThreatGroupActivity that
			= (SecurityThreatGroupActivity) obj;
		if (this.getSummary() == null) {
			throw new IllegalStateException("Summary required");
		}
		if (!this.getSummary().equals(that.getSummary())) {
			return false;
		}
		if (this.getReportDate() == null) {
			throw new IllegalStateException("Report date required");
		}
		if (!this.getReportDate().equals(that.getReportDate())) {
			return false;
		}		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getSummary() == null) {
			throw new IllegalStateException("Summary required");
		}
		if (this.getReportDate() == null) {
			throw new IllegalStateException("Report date required");
		}
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getSummary().hashCode();
		hashCode = 29 * hashCode + this.getReportDate().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format(
				"Summary: %s, Date: %s, Id: %s",
				this.getSummary(),
				this.getReportDate(),
				this.getId());
	}

}
