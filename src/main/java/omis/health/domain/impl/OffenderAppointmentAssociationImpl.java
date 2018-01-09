package omis.health.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.health.domain.HealthAppointment;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.offender.domain.Offender;

/**
 * Offender appointment association implementation.
 *
 * @author Joel Norris
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public class OffenderAppointmentAssociationImpl
	implements OffenderAppointmentAssociation {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Offender offender;

	private HealthAppointment appointment;

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	/**
	 * Instantiates a default instance of offender appointment association
	 * implementation.
	 */
	public OffenderAppointmentAssociationImpl() {
		//Default constructor
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
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public HealthAppointment getAppointment() {
		return this.appointment;
	}

	/** {@inheritDoc} */
	@Override
	public void setAppointment(
			final HealthAppointment appointment) {
		this.appointment = appointment;
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
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof OffenderAppointmentAssociation)) {
			return false;
		}

		final OffenderAppointmentAssociation that =
				(OffenderAppointmentAssociation) o;

		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getAppointment() == null) {
			throw new IllegalStateException("Appointment required.");
		}
		if (!this.getAppointment().equals(that.getAppointment())) {
			return false;
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException(" required.");
		}
		if (this.getAppointment() == null) {
			throw new IllegalStateException("Appointment required.");
		}

		int hashCode = 14;

		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getAppointment().hashCode();

		return hashCode;
	}
}