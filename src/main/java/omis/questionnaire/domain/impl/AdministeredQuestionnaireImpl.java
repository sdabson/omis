package omis.questionnaire.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.person.domain.Person;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.QuestionnaireType;

/**
 * AdministeredQuestionnaireImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireImpl 
	implements AdministeredQuestionnaire {

	private static final long serialVersionUID = 1L;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private Person answerer;
	
	private Boolean draft;
	
	private String comments;
	
	private Person assessor;
	
	private Date date;
	
	private QuestionnaireType questionnaireType;

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
	public Person getAnswerer() {
		return this.answerer;
	}

	/**{@inheritDoc} */
	@Override
	public void setAnswerer(Person answerer) {
		this.answerer = answerer;
	}

	/**{@inheritDoc} */
	@Override
	public Boolean getDraft() {
		return this.draft;
	}

	/**{@inheritDoc} */
	@Override
	public void setDraft(Boolean draft) {
		this.draft = draft;
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
	public Person getAssessor() {
		return this.assessor;
	}

	/**{@inheritDoc} */
	@Override
	public void setAssessor(Person assessor) {
		this.assessor = assessor;
	}

	/**{@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/**{@inheritDoc} */
	@Override
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**{@inheritDoc} */
	@Override
	public QuestionnaireType getQuestionnaireType() {
		return this.questionnaireType;
	}

	/**{@inheritDoc} */
	@Override
	public void setQuestionnaireType(QuestionnaireType questionnaireType) {
		this.questionnaireType = questionnaireType;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof AdministeredQuestionnaire)){
			return false;
		}
		
		AdministeredQuestionnaire that = (AdministeredQuestionnaire) obj;
		
		if(this.getAnswerer() == null){
			throw new IllegalStateException("Answerer required");
		}
		if(this.getDraft() == null){
			throw new IllegalStateException("Draft Required");
		}
		if(this.getAssessor() == null){
			throw new IllegalStateException("Assessor Required");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date Required");
		}
		if(this.getQuestionnaireType() == null){
			throw new IllegalStateException("Questionnaire Type Required");
		}
		
		if(!this.getAnswerer().equals(that.getAnswerer())){
			return false;
		}
		if(!this.getDate().equals(that.getDate())){
			return false;
		}
		if(!this.getQuestionnaireType().equals(that.getQuestionnaireType())){
			return false;
		}
		
		
		return true;
	}
	

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getAnswerer() == null){
			throw new IllegalStateException("Answerer Required");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getAnswerer().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getQuestionnaireType().hashCode();
		
		return hashCode;
	}
}
