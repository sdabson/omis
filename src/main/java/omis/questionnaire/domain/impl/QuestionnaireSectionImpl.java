package omis.questionnaire.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;
import omis.questionnaire.domain.SectionType;

/**
 * QuestionnaireSectionImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireSectionImpl implements QuestionnaireSection {

	private static final long serialVersionUID = 1L;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private String title;
	
	private Short sortOrder;
	
	private QuestionnaireType questionnaireType;
	
	private SectionType sectionType;
	
	private Integer sectionNumber;
	
	private String sectionHelp;

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
	public QuestionnaireType getQuestionnaireType() {
		return this.questionnaireType;
	}

	/**{@inheritDoc} */
	@Override
	public void setQuestionnaireType(QuestionnaireType questionnaireType) {
		this.questionnaireType = questionnaireType;
	}

	/**{@inheritDoc} */
	@Override
	public SectionType getSectionType() {
		return this.sectionType;
	}

	/**{@inheritDoc} */
	@Override
	public void setSectionType(SectionType sectionType) {
		this.sectionType = sectionType;
	}

	/**{@inheritDoc} */
	@Override
	public Integer getSectionNumber() {
		return this.sectionNumber;
	}

	/**{@inheritDoc} */
	@Override
	public void setSectionNumber(Integer sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	
	/**{@inheritDoc} */
	@Override
	public String getSectionHelp() {
		return this.sectionHelp;
	}

	/**{@inheritDoc} */
	@Override
	public void setSectionHelp(String sectionHelp) {
		this.sectionHelp = sectionHelp; 
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof QuestionnaireSection)){
			return false;
		}
		
		QuestionnaireSection that = (QuestionnaireSection) obj;
		
		if(this.getTitle() == null){
			throw new IllegalStateException("Title Required");
		}
		if(this.getSortOrder() == null){
			throw new IllegalStateException("Sort Order Required");
		}
		if(this.getQuestionnaireType() == null){
			throw new IllegalStateException("Questionnaire Type Required");
		}
		if(this.getSectionType() == null){
			throw new IllegalStateException("Section Type Required");
		}
		if(this.getSectionNumber() == null){
			throw new IllegalStateException("Section Number Required");
		}
		
		if(!this.getTitle().equals(that.getTitle())){
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
		if(this.getTitle() == null){
			throw new IllegalStateException("Title Required");
		}
		if(this.getQuestionnaireType() == null){
			throw new IllegalStateException("Questionnaire Type Required");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getTitle().hashCode();
		hashCode = 29 * hashCode + this.getQuestionnaireType().hashCode();
		
		return hashCode;
	}
}
