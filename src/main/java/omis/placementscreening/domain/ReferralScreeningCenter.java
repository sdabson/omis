package omis.placementscreening.domain;

import omis.audit.domain.Creatable;
import omis.demographics.domain.Sex;
import omis.facility.domain.Facility;

/** Referral screening facility.
 * @author Ryan Johns
 * @version 0.1.0 (Oct 30, 2014)
 * @since OMIS 3.0 */
public interface ReferralScreeningCenter extends Creatable {
	/** Gets id.
	 * @return id id. */
	Long getId();
	
	/** Gets facility.
	 * @return facility facility. */
	Facility getFacility();
	
	/** Gets order.
	 * @return order order. */
	Integer getOrder();
	
	/** Gets sex.
	 * @return sex sex. */
	Sex getSex();

	/** Gets valid.
	 * @return valid valid. */
	Boolean getValid();
	
	/** Gets program category.
	 * @return programCategory program category. */
	ProgramCategory getProgramCategory();
	
	/** Sets id.
	 * @param id id. */
	void setId(Long id);
	
	/** Sets facility.
	 * @param facility facility. */
	void setFacility(Facility facility);
	
	/** Sets order.
	 * @param order order. */
	void setOrder(Integer order);
	
	/** Sets program category.
	 * @param programCategory program category. */
	void setProgramCategory(ProgramCategory programCategory);
	
	/** Sets sex.
	 * @param sex sex. */
	void setSex(Sex sex);
	
	/** Sets valid.
	 * @param valid valid. */
	void setValid(Boolean valid);
	
	 /** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
}
