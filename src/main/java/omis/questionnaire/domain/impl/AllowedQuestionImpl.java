package omis.questionnaire.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.AnswerCardinality;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionConditionality;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AllowedQuestionImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public class AllowedQuestionImpl implements AllowedQuestion {
	
	private static final long serialVersionUID = 1L;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private QuestionnaireSection questionnaireSection;
	
	private Question question;
	
	private Boolean valid;
	
	private Short sortOrder;
	
	private String questionNumber;
	
	private QuestionConditionality questionConditionality;
	
	private AnswerCardinality answerCardinality;
	
	private String questionhelp;

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
	public QuestionnaireSection getQuestionnaireSection() {
		return this.questionnaireSection;
	}

	/**{@inheritDoc} */
	@Override
	public void setQuestionnaireSection(
			QuestionnaireSection questionnaireSection) {
		this.questionnaireSection = questionnaireSection;
	}

	/**{@inheritDoc} */
	@Override
	public Question getQuestion() {
		return this.question;
	}

	/**{@inheritDoc} */
	@Override
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**{@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/**{@inheritDoc} */
	@Override
	public void setValid(Boolean valid) {
		this.valid = valid;
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
	public String getQuestionNumber() {
		return this.questionNumber;
	}

	/**{@inheritDoc} */
	@Override
	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}

	/**{@inheritDoc} */
	@Override
	public QuestionConditionality getQuestionConditionality() {
		return this.questionConditionality;
	}

	/**{@inheritDoc} */
	@Override
	public void setQuestionConditionality(
			QuestionConditionality questionConditionality) {
		this.questionConditionality = questionConditionality;
	}

	/**{@inheritDoc} */
	@Override
	public AnswerCardinality getAnswerCardinality() {
		return this.answerCardinality;
	}

	/**{@inheritDoc} */
	@Override
	public void setAnswerCardinality(AnswerCardinality answerCardinality) {
		this.answerCardinality = answerCardinality;
	}
	
	/**{@inheritDoc} */
	@Override
	public String getQuestionHelp() {
		return this.questionhelp;
	}

	/**{@inheritDoc} */
	@Override
	public void setQuestionHelp(String questionHelp) {
		this.questionhelp = questionHelp;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof AllowedQuestion)){
			return false;
		}
		
		AllowedQuestion that = (AllowedQuestion) obj;
		
		if(this.getQuestionnaireSection() == null){
			throw new IllegalStateException("Questionnaire Section Required");
		}
		if(this.getValid() == null){
			throw new IllegalStateException("Valid Required");
		}
		if(this.getAnswerCardinality() == null){
			throw new IllegalStateException("Answer Cardinality Required");
		}
		if(this.getQuestion() == null){
			throw new IllegalStateException("Question Required");
		}
		if(this.getQuestionConditionality() == null){
			throw new IllegalStateException("Question Conditionality Required");
		}
		if(this.getSortOrder() == null){
			throw new IllegalStateException("Sort Order Required");
		}
		if(this.getQuestionNumber() == null){
			throw new IllegalStateException("Question Number Required");
		}
		
		if(!this.getQuestionnaireSection().equals(
				that.getQuestionnaireSection())){
			return false;
		}
		if(!this.getQuestion().equals(that.getQuestion())){
			return false;
		}
		if(!this.getQuestionNumber().equals(that.getQuestionNumber())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getQuestionnaireSection() == null){
			throw new IllegalStateException("Questionnaire Section Required");
		}
		if(this.getQuestion() == null){
			throw new IllegalStateException("Question Required");
		}
		if(this.getQuestionNumber() == null){
			throw new IllegalStateException("Question Number Required");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getQuestionnaireSection().hashCode();
		hashCode = 29 * hashCode + this.getQuestion().hashCode();
		hashCode = 29 * hashCode + this.getQuestionNumber().hashCode();
		
		return hashCode;
	}

}
