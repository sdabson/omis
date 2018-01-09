package omis.stg.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.offender.domain.Offender;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityInvolvement;

/**
 * Security threat group activity note implementation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 29, 2016)
 * @since OMIS 3.0
 */

public class SecurityThreatGroupActivityInvolvementImpl 
		implements SecurityThreatGroupActivityInvolvement {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private SecurityThreatGroupActivity activity;
	
	private Offender offender;
	
	private String narrative;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates an implementation of security threat group activity level.
	 */
	public SecurityThreatGroupActivityInvolvementImpl() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setActivity(SecurityThreatGroupActivity activity) {
		this.activity = activity;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivity getActivity() {
		return this.activity;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(Offender offender) {
		this.offender = offender;
	}
	
	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	/** {@inheritDoc} */
	@Override
	public String getNarrative() {
		return this.narrative;
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
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof SecurityThreatGroupActivityInvolvement)) {
			return false;
		}
		
		SecurityThreatGroupActivityInvolvement that = (
				SecurityThreatGroupActivityInvolvement) o;
		
		if (this.getActivity() == null) {
			throw new IllegalStateException("Activity required.");
		}
		if (!this.getActivity().equals(that.getActivity())) {
			return false;
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getNarrative() == null) {
			throw new IllegalStateException("Narrative required");
		}
		if (!this.getNarrative().equals(that.getNarrative())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getActivity() == null) {
			throw new IllegalStateException(
					"SecurityThreatGroupActivity required.");
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getNarrative() == null) {
			throw new IllegalStateException("Narrative required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getActivity().hashCode();
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getNarrative().hashCode();
		
		return hashCode;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format(
				"Activity: %s, Offender: #%s, Narrative: %s, ",
				this.getActivity(),
				this.getOffender(),
				this.getNarrative());
	}
	
}
