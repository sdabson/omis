package omis.questionnaire.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Question.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public interface Question extends Creatable, Updatable {
	
	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/** Sets id.
	 * @param id - id. */
	public void setId(Long id);
	
	/**
	 * Returns the question category
	 * @return questionCategory
	 */
	public QuestionCategory getQuestionCategory();
	
	/**
	 * Sets the  question category
	 * @param questionCategory
	 */
	public void setQuestionCategory(QuestionCategory questionCategory);
	
	/**
	 * Returns the text
	 * @return text - String
	 */
	public String getText();
	
	/**
	 * Sets the text
	 * @param text - String
	 */
	public void setText(String text);
	
	/**
	 * Returns static
	 * @return static - Boolean
	 */
	public Boolean getStatic();
	
	
	/**
	 * Sets static
	 * @param statik(static) - Boolean
	 */
	public void setStatic(Boolean statik);
	
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
