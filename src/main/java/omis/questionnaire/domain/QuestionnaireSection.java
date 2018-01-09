package omis.questionnaire.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * QuestionnaireSection.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public interface QuestionnaireSection extends Creatable, Updatable {

	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/** Sets id.
	 * @param id - id. */
	public void setId(Long id);
	
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
	 * Returns the sort order
	 * @return sortOrder - Short
	 */
	public Short getSortOrder();
	
	/**
	 * Sets the sort order 
	 * @param sortOrder - Short
	 */
	public void setSortOrder(Short sortOrder);
	
	/**
	 * Returns questionnaire type
	 * @return questionnaireType
	 */
	public QuestionnaireType getQuestionnaireType();
	
	/**
	 * Sets the questionnaire type
	 * @param questionnaireType
	 */
	public void setQuestionnaireType(QuestionnaireType questionnaireType);
	
	/**
	 * Returns the section type
	 * @return sectionType
	 */
	public SectionType getSectionType();
	
	/**
	 * Sets the section type
	 * @param sectionType
	 */
	public void setSectionType(SectionType sectionType);
	
	/**
	 * Returns the section number
	 * @return section number - Integer
	 */
	public Integer getSectionNumber();
	
	/**
	 * Sets the section number
	 * @param sectionNumber - Integer
	 */
	public void setSectionNumber(Integer sectionNumber);
	
	/**
	 * Returns the section help
	 * @return sectionHelp - String
	 */
	public String getSectionHelp();
	
	/**
	 * Sets the section help
	 * @param sectionHelp - String
	 */
	public void setSectionHelp(String sectionHelp);
	
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
