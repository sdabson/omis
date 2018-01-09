package omis.health.domain.impl;

import omis.health.domain.LabWork;
import omis.health.domain.LabWorkRequirement;
import omis.health.domain.OffenderAppointmentAssociation;

/**
 * Lab Work Requirement Implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 1, 2014)
 * @since OMIS 3.0
 */
public class LabWorkRequirementImpl implements LabWorkRequirement {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private OffenderAppointmentAssociation offenderAppointmentAssociation;
	
	private LabWork labWork;
	
	/**
	 * Instantiates a default implementation of lab work requirement.
	 */
	public LabWorkRequirementImpl() {
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
	public OffenderAppointmentAssociation getOffenderAppointmentAssociation() {
		return this.offenderAppointmentAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffenderAppointmentAssociation(
			final OffenderAppointmentAssociation 
			offenderAppointmentAssociation) {
		this.offenderAppointmentAssociation = offenderAppointmentAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public LabWork getLabWork() {
		return this.labWork;
	}

	/** {@inheritDoc} */
	@Override
	public void setLabWork(final LabWork labWork) {
		this.labWork = labWork;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof LabWorkRequirement)) {
			return false;
		}
		
		LabWorkRequirement that = (LabWorkRequirement) o;
		
		if (this.getOffenderAppointmentAssociation() == null) {
			throw new IllegalStateException("Offender Appointment Association" 
					+ " required.");
		}
		if (!this.getOffenderAppointmentAssociation().equals(
				that.getOffenderAppointmentAssociation())) {
			return false;
		}
		if (this.getLabWork() == null) {
			throw new IllegalStateException("Lab work required.");
		}
		if (!this.getLabWork().equals(that.getLabWork())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffenderAppointmentAssociation() == null) {
			throw new IllegalStateException("Offender Appointment Association" 
					+ " required.");
		}
		if (this.getLabWork() == null) {
			throw new IllegalStateException("Lab work required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOffenderAppointmentAssociation()
				.hashCode();
		hashCode = 29 * hashCode + this.getLabWork().hashCode();
		
		return hashCode;
	}
}