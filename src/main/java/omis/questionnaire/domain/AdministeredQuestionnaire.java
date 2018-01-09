package omis.questionnaire.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.person.domain.Person;

/**
 * AdministeredQuestionnaire.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public interface AdministeredQuestionnaire extends Creatable, Updatable {

	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/** Sets id.
	 * @param id - id. */
	public void setId(Long id);
	
	/**
	 * Returns the answerer
	 * @return answerer - Person
	 */
	public Person getAnswerer();
	
	/**
	 * Sets the answerer
	 * @param answerer - Person
	 */
	public void setAnswerer(Person answerer);
	
	/**
	 * Returns draft
	 * @return draft - Boolean
	 */
	public Boolean getDraft();
	
	/**
	 * Sets draft
	 * @param draft - Boolean
	 */
	public void setDraft(Boolean draft);
	
	/**
	 * Returns the comments
	 * @return comments - String
	 */
	public String getComments();
	
	/**
	 * Sets the comments
	 * @param comments - String
	 */
	public void setComments(String comments);
	
	/**
	 * Returns the assessor
	 * @return assessor - Person
	 */
	public Person getAssessor();
	
	/**
	 * Sets the assessor
	 * @param assessor - Person
	 */
	public void setAssessor(Person assessor);
	
	/**
	 * Returns the date 
	 * @return date
	 */
	public Date getDate();
	
	/**
	 * Returns the questionnaire type
	 * @return questionnaireType
	 */
	public QuestionnaireType getQuestionnaireType();
	
	/**
	 * Sets the questionnaire type
	 * @param questionnaireType
	 */
	public void setQuestionnaireType(QuestionnaireType questionnaireType);
	
	/**
	 * Sets the date
	 * @param date
	 */
	public void setDate(Date date);
	
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
