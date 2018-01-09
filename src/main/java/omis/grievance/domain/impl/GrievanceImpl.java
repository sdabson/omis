package omis.grievance.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceComplaintCategory;
import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceSubject;
import omis.grievance.domain.GrievanceUnit;
import omis.offender.domain.Offender;

/**
 * Implementation of grievance.
 *
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.0.2 (Oct 2, 2015)
 * @since OMIS 3.0
 */
public class GrievanceImpl implements Grievance {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Offender offender;

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	private GrievanceLocation location;

	private GrievanceUnit unit;

	private GrievanceSubject subject;

	private GrievanceComplaintCategory complaintCategory;
	
	private Integer grievanceNumber;

	private Date openedDate;

	private Date informalFileDate;

	private String description;
	
	private String initialComment;

	private Date closedDate;
	
	/** Instantiates implementation of grievance. */
	public GrievanceImpl() {
		// Default instantiation
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
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
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
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setLocation(final GrievanceLocation location) {
		this.location = location;
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceLocation getLocation() {
		return this.location;
	}

	/** {@inheritDoc} */
	@Override
	public void setUnit(final GrievanceUnit unit) {
		this.unit = unit;
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceUnit getUnit() {
		return this.unit;
	}

	/** {@inheritDoc} */
	@Override
	public void setSubject(final GrievanceSubject subject) {
		this.subject = subject;
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceSubject getSubject() {
		return this.subject;
	}

	/** {@inheritDoc} */
	@Override
	public void setComplaintCategory(
			final GrievanceComplaintCategory complaintCategory) {
		this.complaintCategory = complaintCategory;
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceComplaintCategory getComplaintCategory() {
		return this.complaintCategory;
	}

	/** {@inheritDoc} */
	@Override
	public void setGrievanceNumber(final Integer grievanceNumber) {
		this.grievanceNumber = grievanceNumber;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getGrievanceNumber() {
		return this.grievanceNumber;
	}

	/** {@inheritDoc} */
	@Override
	public void setOpenedDate(final Date openedDate) {
		this.openedDate = openedDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getOpenedDate() {
		return this.openedDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setInformalFileDate(final Date informalFileDate) {
		this.informalFileDate = informalFileDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getInformalFileDate() {
		return this.informalFileDate;
	}

	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/** {@inheritDoc} */
	@Override
	public void setInitialComment(final String initialComment) {
		this.initialComment = initialComment;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getInitialComment() {
		return this.initialComment;
	}

	/** {@inheritDoc} */
	@Override
	public void setClosedDate(final Date closedDate) {
		this.closedDate = closedDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getClosedDate() {
		return this.closedDate;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Grievance)) {
			return false;
		}
		Grievance that = (Grievance) obj;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getOpenedDate() != null) {
			if (!this.getOpenedDate().equals(that.getOpenedDate())) {
				return false;
			}
		} else if (that.getOpenedDate() != null) {
			return false;
		}
		if (this.getSubject() == null) {
			throw new IllegalStateException("Subject required");
		}
		if (!this.getSubject().equals(that.getSubject())) {
			return false;
		}
		if (this.getUnit() == null) {
			throw new IllegalStateException("Unit required");
		}
		if (!this.getUnit().equals(that.getUnit())) {
			return false;
		}
		if (this.getLocation() == null) {
			throw new IllegalStateException("Location required");
		}
		if (!this.getLocation().equals(that.getLocation())) {
			return false;
		}
		if (this.getComplaintCategory() == null) {
			throw new IllegalStateException("Complaint category required");
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		if (this.getGrievanceNumber() == null) {
			throw new IllegalStateException("Grievance number required");
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getSubject() == null) {
			throw new IllegalStateException("Subject required");
		}
		if (this.getUnit() == null) {
			throw new IllegalStateException("Unit required");
		}
		if (this.getLocation() == null) {
			throw new IllegalStateException("Location required");
		}
		if (this.getComplaintCategory() == null) {
			throw new IllegalStateException("Complaint category required");
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		if (this.getGrievanceNumber() == null) {
			throw new IllegalStateException("Grievance number required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		if (this.getOpenedDate() != null) {
			hashCode = 29 * hashCode + this.getOpenedDate().hashCode();
		}
		hashCode = 29 * hashCode + this.getSubject().hashCode();
		hashCode = 29 * hashCode + this.getUnit().hashCode();
		hashCode = 29 * hashCode + this.getLocation().hashCode();
		hashCode = 29 * hashCode + this.getComplaintCategory().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getGrievanceNumber().hashCode();
		return hashCode;
	}
}