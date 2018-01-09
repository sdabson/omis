package omis.questionnaire.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.questionnaire.domain.QuestionnaireCategory;
import omis.questionnaire.domain.QuestionnaireType;

/**
 * QuestionnaireTypeImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireTypeImpl implements QuestionnaireType {

	private static final long serialVersionUID = 1L;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private String shortTitle;
	
	private String title;
	
	private Integer cycle;
	
	private QuestionnaireCategory questionnaireCategory;
	
	private Boolean valid;
	
	private DateRange dateRange;
	
	private String questionnaireHelp;

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
	public String getShortTitle() {
		return this.shortTitle;
	}

	/**{@inheritDoc} */
	@Override
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	/**{@inheritDoc} */
	@Override
	public String getTitle() {
		return this.title;
	}

	/**{@inheritDoc} */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	/**{@inheritDoc} */
	@Override
	public Integer getCycle() {
		return this.cycle;
	}

	/**{@inheritDoc} */
	@Override
	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	/**{@inheritDoc} */
	@Override
	public QuestionnaireCategory getQuestionnaireCategory() {
		return this.questionnaireCategory;
	}

	/**{@inheritDoc} */
	@Override
	public void setQuestionnaireCategory(
			QuestionnaireCategory questionnaireCategory) {
		this.questionnaireCategory = questionnaireCategory;
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
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/**{@inheritDoc} */
	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}
	
	/**{@inheritDoc} */
	@Override
	public String getQuestionnaireHelp() {
		return this.questionnaireHelp;
	}

	/**{@inheritDoc} */
	@Override
	public void setQuestionnaireHelp(String questionnaireHelp) {
		this.questionnaireHelp = questionnaireHelp;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof QuestionnaireType)){
			return false;
		}
		
		QuestionnaireType that = (QuestionnaireType) obj;
	
		if(this.getTitle() == null){
			throw new IllegalStateException("Title Required");
		}
		if(this.getCycle() == null){
			throw new IllegalStateException("Cycle Required");
		}
		if(this.getQuestionnaireCategory() == null){
			throw new IllegalStateException("Questionnaire Category Required");
		}
		if(this.getValid() == null){
			throw new IllegalStateException("Valid Required");
		}
		if(this.getDateRange().getStartDate() == null){
			throw new IllegalStateException("Start Date Required");
		}
		
		if(!this.getTitle().equals(that.getTitle())){
			return false;
		}
		if(!this.getCycle().equals(that.getCycle())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getTitle() == null){
			throw new IllegalStateException("Title Required");
		}
		if(this.getCycle() == null){
			throw new IllegalStateException("Cycle Required");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getTitle().hashCode();
		hashCode = 29 * hashCode + this.getCycle().hashCode();
		
		return hashCode;
	}
	
	
}
