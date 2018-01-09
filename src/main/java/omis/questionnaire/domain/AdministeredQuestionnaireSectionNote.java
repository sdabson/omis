package omis.questionnaire.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * AdministeredQuestionnaireSectionNote.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public interface AdministeredQuestionnaireSectionNote 
	extends Creatable, Updatable {

	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/** Sets id.
	 * @param id - id. */
	public void setId(Long id);
	
	/**
	 * Returns the QuestionnaireSection
	 * @return QuestionnaireSection - Questionnaire Section
	 */
	public QuestionnaireSection getQuestionnaireSection();
	
	/**
	 * Sets the QuestionnaireSection
	 * @param questionnaireSection - questionnaire section 
	 */
	public void setQuestionnaireSection(
			QuestionnaireSection questionnaireSection);
	
	/**
	 * Returns the comments
	 * @return comments - string
	 */
	public String getComments();
	
	/**
	 * Sets the comments
	 * @param comments - string
	 */
	public void setComments(String comments);
	
	/**
	 * Returns the administered questionnaire
	 * @return AdministeredQuestionnaire - administered questionnaire
	 */
	public AdministeredQuestionnaire getAdministeredQuestionnaire();
	
	/**
	 * Sets the administered questionnaire
	 * @param administeredQuestionnaire - administered questionnaire
	 */
	public void setAdministeredQuestionnaire(
			AdministeredQuestionnaire administeredQuestionnaire);
	
	
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
