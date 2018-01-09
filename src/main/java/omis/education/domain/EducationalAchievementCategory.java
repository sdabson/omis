package omis.education.domain;

import omis.audit.domain.Creatable;

/**
 * EducationalAchievementCategory.java
 * 
 *@author Annie Jacques
 *@author Ryan Johns 
 *@version 0.1.1 (Aug 30, 2016)
 *@since OMIS 3.0
 *
 */
public interface EducationalAchievementCategory extends Creatable {
	
	/**
	 * Gets the ID
	 * @return id
	 */
	public Long getId();
	
	/**
	 * Gets the name of the category
	 * @return name
	 */
	public String getName();
	
	/**
	 * Gets the sort order
	 * @return sort order
	 */
	public Short getSortOrder();
	
	/**
	 * Gets valid
	 * @return valid
	 */
	public Boolean getValid();
	
	/** Gets level.
	 * @return level. */
	public AchievementCategoryLevel getLevel();
	
	/**
	 * Sets the ID
	 * @param id - id
	 */
	public void setId(Long id);
	
	/**
	 * Sets the name of the category
	 * @param name - name
	 */
	public void setName(String name);
	
	/**
	 * Sets the sort order
	 * @param sortOrder - sort order
	 */
	public void setSortOrder(Short sortOrder);
	
	/**
	 * Sets valid
	 * @param valid - valid
	 */
	public void setValid(Boolean valid);
	
	/** Sets level.
	 * @param level - level. */
	public void setLevel(AchievementCategoryLevel level);
	
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
