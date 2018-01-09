package omis.questionnaire.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AdministeredQuestionnaireSectionStatus;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AdministeredQuestionnaireSectionStatusImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 16, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireSectionStatusImpl implements AdministeredQuestionnaireSectionStatus {
	
	private static final long serialVersionUID = 1L;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private AdministeredQuestionnaire administeredQuestionnaire;
	
	private QuestionnaireSection questionnaireSection;
	
	private Date date;
	
	private Boolean draft;
	
	
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
	public Boolean getDraft() {
		return this.draft;
	}

	/**{@inheritDoc} */
	@Override
	public void setDraft(Boolean draft) {
		this.draft = draft;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof AdministeredQuestionnaireSectionStatus)){
			return false;
		}
		
		AdministeredQuestionnaireSectionStatus that 
			= (AdministeredQuestionnaireSectionStatus) obj;
		
		if(this.getAdministeredQuestionnaire() == null){
			throw new IllegalStateException(
					"Administered Questionnaire Required");
		}
		if(this.getQuestionnaireSection() == null){
			throw new IllegalStateException("Questionnaire Section Required");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date Required");
		}
		if(this.getDraft() == null){
			throw new IllegalStateException("Draft Required");
		}
		
		
		if(!this.getAdministeredQuestionnaire().equals(
				that.getAdministeredQuestionnaire())){
			return false;
		}
		if(!this.getQuestionnaireSection().equals(
				that.getQuestionnaireSection())){
			return false;
		}
		if(!this.getDraft().equals(that.getDraft())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getAdministeredQuestionnaire() == null){
			throw new IllegalStateException(
					"Administered Questionnaire Required");
		}
		if(this.getQuestionnaireSection() == null){
			throw new IllegalStateException("Questionnaire Section Required");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date Required");
		}
		if(this.getDraft() == null){
			throw new IllegalStateException("Draft Required");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getQuestionnaireSection().hashCode();
		hashCode = 29 * hashCode + this.getAdministeredQuestionnaire().hashCode();
		hashCode = 29 * hashCode + this.getDraft().hashCode();
		
		return hashCode;
	}

}
