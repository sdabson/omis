package omis.disciplinaryCode.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * SupervisoryOrganizationCode.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 9, 2016)
 *@since OMIS 3.0
 *
 */
public interface SupervisoryOrganizationCode extends Creatable, Updatable {
	
	/**
	 * Gets the ID
	 * @return id
	 */
	public Long getId();
	
	/**
	 * Returns the supervisory organization
	 * @return
	 */
	public SupervisoryOrganization getSupervisoryOrganization();
	
	/**
	 * Returns the date range
	 * @return
	 */
	public DateRange getDateRange();
	
	/**
	 * Returns the disciplinary code
	 * @return disciplinary code
	 */
	public DisciplinaryCode getCode();
	
	/**
	 * Sets the ID
	 * @param id - id
	 */
	public void setId(Long id);
	
	/**
	 * Sets the supervisory organization
	 * @param supervisoryOrganization
	 */
	public void setSupervisoryOrganization(SupervisoryOrganization supervisoryOrganization);
	
	/**
	 * Sets the date range
	 * @param dateRange
	 */
	public void setDateRange(DateRange dateRange);
	
	/**
	 * Sets the disciplinary code
	 * @param code - disciplinary code
	 */
	public void setCode(DisciplinaryCode code);
	
	
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
	public boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	public int hashCode();
}
