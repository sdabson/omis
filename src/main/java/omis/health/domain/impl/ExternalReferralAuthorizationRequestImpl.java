package omis.health.domain.impl;

import java.util.Date;

import omis.facility.domain.Facility;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.ExternalReferralReason;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.offender.domain.Offender;

/**
 * Implementation of request for authorization of external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralAuthorizationRequestImpl
		implements ExternalReferralAuthorizationRequest {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender;
	
	private Facility facility;
	
	private Date date;
	
	private ProviderAssignment providerAssignment;
	
	private MedicalFacility medicalFacility;
	
	private ExternalReferralReason reason;

	private String reasonNotes;

	private ProviderAssignment referredByProviderAssignment;

	private Date referredDate;

	/**
	 * Instantiates an implementation of request for authorization of external
	 * referrals.
	 */
	public ExternalReferralAuthorizationRequestImpl() {
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
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}

	/** {@inheritDoc} */
	@Override
	public Facility getFacility() {
		return this.facility;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setProviderAssignment(
			final ProviderAssignment providerAssignment) {
		this.providerAssignment = providerAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public ProviderAssignment getProviderAssignment() {
		return this.providerAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public void setMedicalFacility(final MedicalFacility medicalFacility) {
		this.medicalFacility = medicalFacility;
	}

	/** {@inheritDoc} */
	@Override
	public MedicalFacility getMedicalFacility() {
		return this.medicalFacility;
	}

	/** {@inheritDoc} */
	@Override
	public void setReason(final ExternalReferralReason reason) {
		this.reason = reason;
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferralReason getReason() {
		return this.reason;
	}

	/** {@inheritDoc} */
	@Override
	public void setReasonNotes(final String reasonNotes) {
		this.reasonNotes = reasonNotes;
	}

	/** {@inheritDoc} */
	@Override
	public String getReasonNotes() {
		return this.reasonNotes;
	}

	/** {@inheritDoc} */
	@Override
	public void setReferredByProviderAssignment(
			final ProviderAssignment referredByProviderAssignment) {
		this.referredByProviderAssignment = referredByProviderAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public ProviderAssignment getReferredByProviderAssignment() {
		return this.referredByProviderAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public void setReferredDate(final Date referredDate) {
		this.referredDate = referredDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getReferredDate() {
		return this.referredDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ExternalReferralAuthorizationRequest)) {
			return false;
		}
		ExternalReferralAuthorizationRequest that
			= (ExternalReferralAuthorizationRequest) obj;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getFacility() == null) {
			throw new IllegalStateException("Facility required");
		}
		if (!this.getFacility().equals(that.getFacility())) {
			return false;
		}
		if (this.getDate() != null) {
			if (!this.getDate().equals(that.getDate())) {
				return false;
			}
		} else if (that.getDate() != null) {
			return false;
		}
		if (this.getMedicalFacility() == null) {
			throw new IllegalStateException("Location required");
		}
		if (!this.getMedicalFacility().equals(that.getMedicalFacility())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getFacility() == null) {
			throw new IllegalStateException("Facility required");
		}
		if (this.getMedicalFacility() == null) {
			throw new IllegalStateException("Location required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getFacility().hashCode();
		if (this.getDate() != null) {
			hashCode = 29 * hashCode + this.getDate().hashCode();
		}
		hashCode = hashCode + this.getMedicalFacility().hashCode();
		return hashCode;
	}
}