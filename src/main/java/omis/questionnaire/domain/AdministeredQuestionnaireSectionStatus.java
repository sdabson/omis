package omis.questionnaire.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * AdministeredQuestionnaireSectionStatus.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 16, 2016)
 *@since OMIS 3.0
 *
 */
public interface AdministeredQuestionnaireSectionStatus extends Creatable, Updatable {
	
	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/** Sets id.
	 * @param id - id. */
	public void setId(Long id);
	
	/**
	 * Returns the questionnaireSection
	 * @return questionnaireSection
	 */
	public QuestionnaireSection getQuestionnaireSection();
	
	/**
	 * Sets the questionnaireSection
	 * @param questionnaireSection
	 */
	public void setQuestionnaireSection(
			QuestionnaireSection questionnaireSection);
	
	/**
	 * Returns the administeredQuestionnaire
	 * @return administeredQuestionnaire
	 */
	public AdministeredQuestionnaire getAdministeredQuestionnaire();
	
	/**
	 * Sets the administeredQuestionnaire
	 * @param administeredQuestionnaire
	 */
	public void setAdministeredQuestionnaire(
			AdministeredQuestionnaire administeredQuestionnaire);
	
	/**
	 * Returns the date
	 * @return date
	 */
	public Date getDate();
	
	/**
	 * Sets the date
	 * @param date
	 */
	public void setDate(Date date);
	
	/**
	 * Returns the draft
	 * @return draft - Boolean
	 */
	public Boolean getDraft();
	
	/**
	 * Sets the draft
	 * @param draft - Boolean
	 */
	public void setDraft(Boolean draft);
	
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
