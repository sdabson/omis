package omis.health.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.OffenderAssociable;

/**
 * Offender AppointmentAssociation.
 *
 * @author Joel Norris
 * @author Ryan Johns
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public interface OffenderAppointmentAssociation
	extends OffenderAssociable, Creatable, Updatable {

	/**
	 * Returns the id of the offender appointment association.
	 *
	 * @return id
	 */
	Long getId();

	/**
	 * Sets the id of the offender appointment association.
	 * @param id id
	 */
	void setId(Long id);

	/**
	 * Returns the appointment of the offender appointment association.
	 *
	 * @return health appointment
	 */
	HealthAppointment getAppointment();

	/**
	 * Sets the appointment of the offender appointment association.
	 *
	 * @param appointment health appointment
	 */
	void setAppointment(HealthAppointment appointment);

	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null}
	 */
	@Override
	boolean equals(Object obj);

	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}