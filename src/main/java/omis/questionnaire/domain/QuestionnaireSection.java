package omis.questionnaire.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Questionnaire Section.
 * 
 *@author Annie Wahl
 *@version 0.1.1 (Oct 4, 2018)
 *@since OMIS 3.0
 *
 */
public interface QuestionnaireSection extends Creatable, Updatable {

	/** Gets id.
	 * @return id. */
	Long getId();
	
	/** Sets id.
	 * @param id - id. */
	void setId(Long id);
	
	/**
	 * Returns the title.
	 * @return title - String
	 */
	String getTitle();
	
	/**
	 * Sets the title.
	 * @param title - String
	 */
	void setTitle(String title);
	
	/**
	 * Returns the sort order.
	 * @return sortOrder - Short
	 */
	Short getSortOrder();
	
	/**
	 * Sets the sort order.
	 * @param sortOrder - Short
	 */
	void setSortOrder(Short sortOrder);
	
	/**
	 * Returns questionnaire type.
	 * @return questionnaireType
	 */
	QuestionnaireType getQuestionnaireType();
	
	/**
	 * Sets the questionnaire type.
	 * @param questionnaireType - Questionnaire Type
	 */
	void setQuestionnaireType(QuestionnaireType questionnaireType);
	
	/**
	 * Returns the section type.
	 * @return sectionType - Section Type
	 */
	SectionType getSectionType();
	
	/**
	 * Sets the section type.
	 * @param sectionType - Section Type
	 */
	void setSectionType(SectionType sectionType);
	
	/**
	 * Returns the section number.
	 * @return section number - String
	 */
	String getSectionNumber();
	
	/**
	 * Sets the section number.
	 * @param sectionNumber - String
	 */
	void setSectionNumber(String sectionNumber);
	
	/**
	 * Returns the section help.
	 * @return sectionHelp - String
	 */
	String getSectionHelp();
	
	/**
	 * Sets the section help.
	 * @param sectionHelp - String
	 */
	void setSectionHelp(String sectionHelp);
	
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
