package omis.education.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.education.domain.component.Institute;
import omis.offender.domain.Offender;

/**
 * EducationTerm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 25, 2016)
 *@since OMIS 3.0
 *
 */
public interface EducationTerm extends Creatable, Updatable {

	/**
	 * Gets the ID
	 * @return id
	 */
	public Long getId();
	
	/**
	 * Gets the offender
	 * @return offender
	 */
	public Offender getOffender();
	
	/**
	 * Gets the institute
	 * @return institute
	 */
	public Institute getInstitute();
	
	/**
	 * Gets the description
	 * @return description
	 */
	public String getDescription();
	
	/**
	 * Gets the achievement 
	 * @return educational achievement
	 */
	public EducationalAchievement getAchievement();
	
	/**
	 * Gets special education
	 * @return special education
	 */
	public Boolean getSpecialEducation();
	
	/**
	 * Gets attended date range
	 * @return attended date range
	 */
	public DateRange getAttendedDateRange();
	
	/**
	 * Gets the verification signature
	 * @return verification signature
	 */
	public VerificationSignature getVerificationSignature();
	
	/**
	 * Sets the ID
	 * @param id - id
	 */
	public void setId(Long id);
	
	/**
	 * Sets the offender
	 * @param offender - offender
	 */
	public void setOffender(Offender offender);
	
	/**
	 * Sets the institute
	 * @param institute - institute
	 */
	public void setInstitute(Institute institute);
	
	/**
	 * Sets the description
	 * @param description - description
	 */
	public void setDescription(String description);
	
	/**
	 * Sets the achievement
	 * @param achievement - educational achievement
	 */
	public void setAchievement(EducationalAchievement achievement);
	
	/**
	 * Sets special education
	 * @param specialEducation - special education
	 */
	public void setSpecialEducation(Boolean specialEducation);
	
	/**
	 * Sets the attended date range
	 * @param attendedDateRange - attended date range
	 */
	public void setAttendedDateRange(DateRange attendedDateRange);
	
	/**
	 * Sets the verification signature
	 * @param verificationSignature - verification signature
	 */
	public void setVerificationSignature(VerificationSignature verificationSignature);
	
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
