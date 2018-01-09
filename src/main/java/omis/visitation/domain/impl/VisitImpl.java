package omis.visitation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.location.domain.Location;
import omis.visitation.domain.Visit;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.component.VisitFlags;

/**
 * Visit Impl.
 * @author Joel Norris
 * @version 0.1.0 (Jul 17, 2013)
 * @since OMIS 3.0
 */
public class VisitImpl implements Visit {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private VisitationAssociation visitationAssociation;

	private DateRange dateRange;
	
	private String badgeNumber;
	
	private VisitFlags flags;
	
	private String notes;
	
	private Location location;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;

	/**
	 * Instantiates a default instance of visit.
	 */
	public VisitImpl() {
		//Default constructor.
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
	public VisitationAssociation getVisitationAssociation() {
		return this.visitationAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public void setVisitationAssociation(
			final VisitationAssociation visitationAssociation) {
		this.visitationAssociation = visitationAssociation;
	}
	
	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public String getBadgeNumber() {
		return this.badgeNumber;
	}

	/** {@inheritDoc} */
	@Override
	public void setBadgeNumber(final String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}

	/** {@inheritDoc} */
	@Override
	public VisitFlags getFlags() {
		return this.flags;
	}

	/** {@inheritDoc} */
	@Override
	public void setFlags(final VisitFlags flags) {
		this.flags = flags;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getNotes() {
		return this.notes;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setNotes(final String notes) {
		this.notes = notes;
	}

	/** {@inheritDoc} */
	@Override
	public Location getLocation() {
		return location;
	}

	/** {@inheritDoc} */
	@Override
	public void setLocation(final Location location) {
		this.location = location;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Visit)) {
			return false;
		}
		
		Visit that = (Visit) obj;
		
		if (this.getVisitationAssociation() == null) {
			throw new IllegalStateException("VisitationAssociation required");
		}
		if (!this.getVisitationAssociation().equals(that
				.getVisitationAssociation())) {
			return false;
		}
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required");
		}
		if (!this.getDateRange().equals(that.getDateRange())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;
		
		if (this.getVisitationAssociation() == null) {
			throw new IllegalStateException("VisitationAssociation required");
		}
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required");
		}
		
		hashCode = 29 * hashCode + this.getVisitationAssociation().hashCode();
		hashCode = 29 * hashCode + this.getDateRange().hashCode();
		
		return hashCode;
	}
}