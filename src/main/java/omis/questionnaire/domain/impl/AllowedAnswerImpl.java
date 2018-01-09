package omis.questionnaire.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.questionnaire.domain.AllowedAnswer;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.AnswerValue;

/**
 * AllowedAnswerImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public class AllowedAnswerImpl implements AllowedAnswer {
	
	private static final long serialVersionUID = 1L;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private AllowedQuestion allowedQuestion;
	
	private Short sortOrder;
	
	private AnswerValue answerValue;
	
	
	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public AllowedQuestion getAllowedQuestion() {
		return this.allowedQuestion;
	}

	/**{@inheritDoc} */
	@Override
	public void setAllowedQuestion(AllowedQuestion allowedQuestion) {
		this.allowedQuestion = allowedQuestion;
	}

	/**{@inheritDoc} */
	@Override
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/**{@inheritDoc} */
	@Override
	public void setSortOrder(Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**{@inheritDoc} */
	@Override
	public AnswerValue getAnswerValue() {
		return this.answerValue;
	}

	/**{@inheritDoc} */
	@Override
	public void setAnswerValue(AnswerValue answerValue) {
		this.answerValue = answerValue;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof AllowedAnswer)){
			return false;
		}
		
		AllowedAnswer that = (AllowedAnswer) obj;
		
		if(this.getAllowedQuestion() == null){
			throw new IllegalStateException("Allowed Question Required.");
		}
		if(this.getAnswerValue() == null){
			throw new IllegalStateException("Answer Value Required.");
		}
		if(this.getSortOrder() == null){
			throw new IllegalStateException("Sort Order Required.");
		}
		
		if(!(this.getAllowedQuestion().equals(that.getAllowedQuestion()))){
			return false;
		}
		if(!(this.getAnswerValue().equals(that.getAnswerValue()))){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getAllowedQuestion() == null){
			throw new IllegalStateException("Allowed Question Required.");
		}
		if(this.getAnswerValue() == null){
			throw new IllegalStateException("Answer Value Required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getAllowedQuestion().hashCode();
		hashCode = 29 * hashCode + this.getAnswerValue().hashCode();
		
		return hashCode;
	}
	
}
