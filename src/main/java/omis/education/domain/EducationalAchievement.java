package omis.education.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * EducationalAchievement.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 25, 2016)
 *@since OMIS 3.0
 *
 */
public interface EducationalAchievement extends Creatable, Updatable {
	
	/**
	 * Gets the ID
	 * @return id
	 */
	public Long getId();
	
	/**
	 * @return
	 */
	public Date getDate();
	
	/**
	 * @return
	 */
	public String getDescription();
	
	/**
	 * @return
	 */
	public EducationalAchievementCategory getCategory();
	
	/**
	 * @return
	 */
	public EducationTerm getEducationTerm();
	
	/**
	 * Sets the ID
	 * @param id - id
	 */
	public void setId(Long id);
	
	/**
	 * @param date
	 */
	public void setDate(Date date);
	
	/**
	 * @param description
	 */
	public void setDescription(String description);
	
	/**
	 * @param category
	 */
	public void setCategory(EducationalAchievementCategory category);
	
	/**
	 * @param educationTerm
	 */
	public void setEducationTerm(EducationTerm educationTerm);
	
	
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
