package omis.stg.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupActivityLevel;
import omis.stg.domain.SecurityThreatGroupAffiliation;
import omis.stg.domain.SecurityThreatGroupChapter;
import omis.stg.domain.SecurityThreatGroupRank;

/**
 * Implementation of security threat group affiliation.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupAffiliationImpl
		implements SecurityThreatGroupAffiliation {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private DateRange dateRange;
	
	private SecurityThreatGroup group;
	
	private SecurityThreatGroupActivityLevel activityLevel;
	
	private SecurityThreatGroupChapter chapter;
	
	private SecurityThreatGroupRank rank;
	
	private State state;
	
	private City city;
	
	private String moniker;
	
	private String comment;
	
	private VerificationSignature verificationSignature;

	/** Implementation of security threat group affiliation. */
	public SecurityThreatGroupAffiliationImpl() {
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
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setGroup(final SecurityThreatGroup group) {
		this.group = group;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroup getGroup() {
		return this.group;
	}

	/** {@inheritDoc} */
	@Override
	public void setActivityLevel(
			final SecurityThreatGroupActivityLevel activityLevel) {
		this.activityLevel = activityLevel;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivityLevel getActivityLevel() {
		return this.activityLevel;
	}

	/** {@inheritDoc} */
	@Override
	public void setChapter(final SecurityThreatGroupChapter chapter) {
		this.chapter = chapter;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupChapter getChapter() {
		return this.chapter;
	}

	/** {@inheritDoc} */
	@Override
	public void setRank(final SecurityThreatGroupRank rank) {
		this.rank = rank;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupRank getRank() {
		return this.rank;
	}

	/** {@inheritDoc} */
	@Override
	public void setState(final State state) {
		this.state = state;
	}

	/** {@inheritDoc} */
	@Override
	public State getState() {
		return this.state;
	}

	/** {@inheritDoc} */
	@Override
	public void setCity(final City city) {
		this.city = city;
	}
	
	/** {@inheritDoc} */
	@Override
	public City getCity() {
		return this.city;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setMoniker(final String moniker) {
		this.moniker = moniker;
	}

	/** {@inheritDoc} */
	@Override
	public String getMoniker() {
		return this.moniker;
	}

	/** {@inheritDoc} */
	@Override
	public void setComment(final String comment) {
		this.comment = comment;
	}

	/** {@inheritDoc} */
	@Override
	public String getComment() {
		return this.comment;
	}

	/** {@inheritDoc} */
	@Override
	public void setVerificationSignature(
			final VerificationSignature verificationSignature) {
		this.verificationSignature = verificationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public VerificationSignature getVerificationSignature() {
		return this.verificationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SecurityThreatGroupAffiliation)) {
			return false;
		}
		SecurityThreatGroupAffiliation that
			= (SecurityThreatGroupAffiliation) obj;
		if (this.getGroup() == null) {
			throw new IllegalStateException("Group required");
		}
		if (!this.getGroup().equals(that.getGroup())) {
			return false;
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getDateRange() != null) {
			if (that.getDateRange() != null) {
				if (this.getDateRange().getStartDate() != null) {
					if (!this.getDateRange().getStartDate()
							.equals(that.getDateRange().getStartDate())) {
						return false;
					}
				} else if (that.getDateRange().getStartDate() != null) {
					return false;
				}
				if (this.getDateRange().getEndDate() != null) {
					if (!this.getDateRange().getEndDate()
							.equals(that.getDateRange().getEndDate())) {
						return false;
					}
				} else if (that.getDateRange().getEndDate() != null) {
					return false;
				}
			} else {
				return false;
			}
		} else if (that.getDateRange() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getGroup() == null) {
			throw new IllegalStateException("Group required");
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getGroup().hashCode();
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		if (this.getDateRange() != null) {
			if (this.getDateRange().getStartDate() != null) {
				hashCode = 31 * hashCode
						+ this.getDateRange().getStartDate().hashCode();
			}
			if (this.getDateRange().getEndDate() != null) {
				hashCode = 33 * hashCode
						+ this.getDateRange().getEndDate().hashCode();
			}
		}
		return hashCode;
	}
}