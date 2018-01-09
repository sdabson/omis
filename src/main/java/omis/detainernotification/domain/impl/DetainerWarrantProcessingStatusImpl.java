package omis.detainernotification.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.DetainerWarrantProcessingStatus;
import omis.detainernotification.domain.component.DetainerWarrantCancellation;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Unit;

/**
 * Detainer warrant processing status implementation.
 *
 * @author Sheronda Vaughn
 * @author Annie Jacques
 * @version 0.1.1 (Mar 22, 2017)
 * @since OMIS 3.0
 */
public class DetainerWarrantProcessingStatusImpl 
	implements DetainerWarrantProcessingStatus {	

	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private Detainer detainer;
	
	private Date sentToFacilityDate;
	
	private Facility facility;
	
	private Unit unit;
	
	private Complex complex;
	
	private String facilityName;
	
	private Date inmateServedDate;
	
	private Boolean refusedToSign;
	
	private Boolean waiverRequired;
	
	private String refusedToSignComment;
	
	private String waiverRequiredComment;
	
	private DetainerWarrantCancellation cancellation;
	
	/** Instantiates an implementation of person demographics. */
	public DetainerWarrantProcessingStatusImpl() {
		//Default constructor
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
	public void setDetainer(final Detainer detainer) {
		this.detainer = detainer;
	}

	/** {@inheritDoc} */
	@Override
	public Detainer getDetainer() {
		return this.detainer;
	}

	/** {@inheritDoc} */
	@Override
	public void setSentToFacilityDate(final Date sentToFacilityDate) {
		this.sentToFacilityDate = sentToFacilityDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getSentToFacilityDate() {
		return this.sentToFacilityDate;
	}

	/** {@inheritDoc} */
	@Override
	public String getFacilityName() {
		return this.facilityName;
	}

	/** {@inheritDoc} */
	@Override
	public void setFacilityName(final String facilityName) {
		this.facilityName = facilityName;
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
	public void setInmateServedDate(final Date inmateServedDate) {
		this.inmateServedDate = inmateServedDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getInmateServedDate() {
		return this.inmateServedDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setRefusedToSign(final Boolean refusedToSign) {
		this.refusedToSign = refusedToSign;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getRefusedToSign() {
		return this.refusedToSign;
	}

	/** {@inheritDoc} */
	@Override
	public void setWaiverRequired(final Boolean waiverRequired) {
		this.waiverRequired = waiverRequired;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getWaiverRequired() {
		return this.waiverRequired;
	}

	/** {@inheritDoc} */
	@Override
	public Unit getUnit() {
		return this.unit;
	}

	/** {@inheritDoc} */
	@Override
	public void setUnit(final Unit unit) {
		this.unit = unit;
	}

	/** {@inheritDoc} */
	@Override
	public Complex getComplex() {
		return this.complex;
	}

	/** {@inheritDoc} */
	@Override
	public void setComplex(final Complex complex) {
		this.complex = complex;
	}

	/** {@inheritDoc} */
	@Override
	public String getRefusedToSignComment() {
		return this.refusedToSignComment;
	}

	/** {@inheritDoc} */
	@Override
	public void setRefusedToSignComment(final String refusedToSignComment) {
		this.refusedToSignComment = refusedToSignComment;
	}

	/** {@inheritDoc} */
	@Override
	public String getWaiverRequiredComment() {
		return this.waiverRequiredComment;
	}

	/** {@inheritDoc} */
	@Override
	public void setWaiverRequiredComment(final String waiverRequiredComment) {
		this.waiverRequiredComment = waiverRequiredComment;
	}

	/** {@inheritDoc} */
	@Override
	public void setCancellation(
			final DetainerWarrantCancellation cancellation) {
		this.cancellation = cancellation;
	}

	/** {@inheritDoc} */
	@Override
	public DetainerWarrantCancellation getCancellation() {
		return this.cancellation;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof DetainerWarrantProcessingStatus)) {
			return false;
		}
		DetainerWarrantProcessingStatus that 
		= (DetainerWarrantProcessingStatus) obj;
		if (this.getDetainer()  == null) {
			throw new IllegalArgumentException("Detainer required");
		}
		
		if (!this.getDetainer().equals(that.getDetainer())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getDetainer()  == null) {
			throw new IllegalArgumentException("Detainer required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getDetainer().hashCode();
		return hashCode;
	}
}