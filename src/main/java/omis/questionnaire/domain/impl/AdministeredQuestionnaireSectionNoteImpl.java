package omis.questionnaire.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AdministeredQuestionnaireSectionNote;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AdministeredQuestionnaireSectionNoteImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireSectionNoteImpl 
	implements AdministeredQuestionnaireSectionNote {
	
	private static final long serialVersionUID = 1L;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private QuestionnaireSection questionnaireSection;
	
	private String comments;
	
	private AdministeredQuestionnaire administeredQuestionnaire;

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
	public AdministeredQuestionnaire getAdministeredQuestionnaire() {
		return this.administeredQuestionnaire;
	}

	/**{@inheritDoc} */
	@Override
	public void setAdministeredQuestionnaire(
			AdministeredQuestionnaire administeredQuestionnaire) {
		this.administeredQuestionnaire = administeredQuestionnaire;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof AdministeredQuestionnaireSectionNote)){
			return false;
		}
		
		AdministeredQuestionnaireSectionNote that = 
				(AdministeredQuestionnaireSectionNote) obj;
		
		if(this.getComments() == null){
			throw new IllegalStateException("Comments Required");
		}
		if(this.getQuestionnaireSection() == null){
			throw new IllegalStateException("Questionnaire Section Required");
		}
		if(this.getAdministeredQuestionnaire() == null){
			throw new IllegalStateException(
					"Administered Questionnaire Required");
		}
		
		if(!this.getComments().equals(that.getComments())){
			return false;
		}
		if(!this.getQuestionnaireSection().equals(
				that.getQuestionnaireSection())){
			return false;
		}
		if(!this.getAdministeredQuestionnaire().equals(
				that.getAdministeredQuestionnaire())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getComments() == null){
			throw new IllegalStateException("Comments Required");
		}
		if(this.getQuestionnaireSection() == null){
			throw new IllegalStateException("Questionnaire Section Required");
		}
		if(this.getAdministeredQuestionnaire() == null){
			throw new IllegalStateException(
					"Administered Questionnaire Required");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getComments().hashCode();
		hashCode = 29 * hashCode + this.getQuestionnaireSection().hashCode();
		hashCode = 29 * hashCode + 
				this.getAdministeredQuestionnaire().hashCode();
		
		return hashCode;
	}
	
	
}
