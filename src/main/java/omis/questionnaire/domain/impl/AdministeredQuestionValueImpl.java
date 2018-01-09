package omis.questionnaire.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.questionnaire.domain.AdministeredQuestionValue;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AnswerValue;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AdministeredQuestionValueImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionValueImpl implements AdministeredQuestionValue {

	private static final long serialVersionUID = 1L;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private Question question;
	
	private String comments;
	
	private AnswerValue answerValue;
	
	private String answerValueText;
	
	private AdministeredQuestionnaire administeredQuestionnaire;
	
	private QuestionnaireSection questionnaireSection;

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
	public String getComments() {
		return this.comments;
	}

	/**{@inheritDoc} */
	@Override
	public void setComments(String comments) {
		this.comments = comments;
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

	/**{@inheritDoc} */
	@Override
	public String getAnswerValueText() {
		return this.answerValueText;
	}

	/**{@inheritDoc} */
	@Override
	public void setAnswerValueText(String answerValueText) {
		this.answerValueText = answerValueText;
	}
	
	/**{@inheritDoc} */
	@Override
	public AdministeredQuestionnaire getAdministeredQuestionnaire() {
		return this.administeredQuestionnaire;
	}

	/**{@inheritDoc} */
	@Override
	public void setAdministeredQuestionnaire(
			AdministeredQuestionnaire administeredQuestionnaire) {
		this.administeredQuestionnaire = administeredQuestionnaire;
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
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof AdministeredQuestionValue)){
			return false;
		}
		
		AdministeredQuestionValue that = (AdministeredQuestionValue) obj;
		
		if(this.getQuestion() == null){
			throw new IllegalStateException("Question Required");
		}
		if(this.getAdministeredQuestionnaire() == null){
			throw new IllegalStateException(
					"Administered Questionnaire Required");
		}
		if(this.getQuestionnaireSection() == null){
			throw new IllegalStateException("Questionnaire Section Required");
		}
		
		if(!this.getQuestion().equals(that.getQuestion())){
			return false;
		}
		if(!this.getAnswerValue().equals(that.getAnswerValue())){
			return false;
		}
		if(!this.getAnswerValueText().equals(that.getAnswerValueText())){
			return false;
		}
		if(!this.getAdministeredQuestionnaire().equals(
				that.getAdministeredQuestionnaire())){
			return false;
		}
		if(!this.getQuestionnaireSection().equals(
				that.getQuestionnaireSection())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getQuestion() == null){
			throw new IllegalStateException("Question Required");
		}
		if(this.getAdministeredQuestionnaire() == null){
			throw new IllegalStateException(
					"Administered Questionnaire Required");
		}
		if(this.getQuestionnaireSection() == null){
			throw new IllegalStateException("Questionnaire Section Required");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getQuestion().hashCode();
		hashCode = 29 * hashCode + 
				this.getAdministeredQuestionnaire().hashCode();
		hashCode = 29 * hashCode + this.getQuestionnaireSection().hashCode();
		
		return hashCode;
	}
	
}
