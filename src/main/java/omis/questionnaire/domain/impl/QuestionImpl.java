package omis.questionnaire.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionCategory;

/**
 * QuestionImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionImpl implements Question {

	private static final long serialVersionUID = 1L;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private QuestionCategory questionCategory;
	
	private String text;
	
	private Boolean statik;
	
	private Boolean valid;
	

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
	public QuestionCategory getQuestionCategory() {
		return this.questionCategory;
	}

	/**{@inheritDoc} */
	@Override
	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
	}

	/**{@inheritDoc} */
	@Override
	public String getText() {
		return this.text;
	}

	/**{@inheritDoc} */
	@Override
	public void setText(String text) {
		this.text = text;
	}

	/**{@inheritDoc} */
	@Override
	public Boolean getStatic() {
		return this.statik;
	}

	/**{@inheritDoc} */
	@Override
	public void setStatic(Boolean statik) {
		this.statik = statik;
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
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof Question)){
			return false;
		}
		
		Question that = (Question) obj;
		
		if(this.getQuestionCategory() == null){
			throw new IllegalStateException("Question Category Required");
		}
		if(this.getText() == null){
			throw new IllegalStateException("Text Required");
		}
		if(this.getStatic() == null){
			throw new IllegalStateException("Static Required");
		}
		if(this.getValid() == null){
			throw new IllegalStateException("Valid Required");
		}
		
		if(!this.getQuestionCategory().equals(that.getQuestionCategory())){
			return false;
		}
		if(!this.getText().equals(that.getText())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getQuestionCategory() == null){
			throw new IllegalStateException("Question Category Required");
		}
		if(this.getText() == null){
			throw new IllegalStateException("Text Required");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getQuestionCategory().hashCode();
		hashCode = 29 * hashCode + this.getText().hashCode();
		
		return hashCode;
	}
	
}
