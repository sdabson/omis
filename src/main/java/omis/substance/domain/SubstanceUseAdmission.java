package omis.substance.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.offender.domain.OffenderAssociable;

/**
 * Substance use admission.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 20, 2013)
 * @since OMIS 3.0
 */
public interface SubstanceUseAdmission extends Creatable, OffenderAssociable {

	/**
	 * Return the id of the substance use admission.
	 * @return the id
	 */
	Long getId();

	/**
	 * Set the id of the substance use admission.
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Return the substance of the substance use admission.
	 * @return the substance
	 */
	Substance getSubstance();

	/**
	 * Set the use of the substance use admission.
	 * @param substance the substance to set
	 */
	void setSubstance(Substance substance);

	/**
	 * Return the admission date of the substance use admission.
	 * @return the admissionDate
	 */
	Date getAdmissionDate();

	/**
	 * Set the admission date of the substance use admission.
	 * @param admissionDate the admissionDate to set
	 */
	void setAdmissionDate(Date admissionDate);
	
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
