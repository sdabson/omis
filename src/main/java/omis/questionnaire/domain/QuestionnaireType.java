package omis.questionnaire.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;

/**
 * QuestionnaireType.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public interface QuestionnaireType extends Creatable, Updatable {
	
	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/** Sets id.
	 * @param id - id. */
	public void setId(Long id);
	
	/**
	 * Returns the short title
	 * @return shortTitle - String
	 */
	public String getShortTitle();
	
	/**
	 * Sets the short title
	 * @param shortTitle - String
	 */
	public void setShortTitle(String shortTitle);
	
	/**
	 * Returns the title
	 * @return title - String
	 */
	public String getTitle();
	
	/**
	 * Sets the title
	 * @param title - String
	 */
	public void setTitle(String title);
	
	/**
	 * Returns the cycle
	 * @return cycle - Integer
	 */
	public Integer getCycle();
	
	/**
	 * Sets the cycle
	 * @param cycle - integer
	 */
	public void setCycle(Integer cycle);
	
	/**
	 * Returns the questionnaire category
	 * @return questionnaireCategory
	 */
	public QuestionnaireCategory getQuestionnaireCategory();
	
	/**
	 * Sets the questionnaire category
	 * @param questionnaireCategory
	 */
	public void setQuestionnaireCategory(
			QuestionnaireCategory questionnaireCategory);
	
	/**
	 * Returns valid
	 * @return valid - Boolean
	 */
	public Boolean getValid();
	
	/**
	 * Sets valid
	 * @param valid - Boolean
	 */
	public void setValid(Boolean valid);
	
	/**
	 * Returns the date range
	 * @return dateRange
	 */
	public DateRange getDateRange();
	
	/**
	 * Sets the Date Range
	 * @param dateRange
	 */
	public void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the questionnaire help
	 * @return questionnaireHelp - String
	 */
	public String getQuestionnaireHelp();
	
	/**
	 * Sets the questionnaire help
	 * @param questionnaireHelp - String
	 */
	public void setQuestionnaireHelp(String questionnaireHelp);
	
	
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
