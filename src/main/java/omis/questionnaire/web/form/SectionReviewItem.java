package omis.questionnaire.web.form;

import java.util.List;

import omis.questionnaire.domain.AdministeredQuestionnaireSectionStatus;

/**
 * SectionReviewItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 13, 2016)
 *@since OMIS 3.0
 *
 */
public class SectionReviewItem {
	
	private List<QuestionAnswerItem> questionAnswerItems;
	
	private String sectionNotes;
	
	private AdministeredQuestionnaireSectionStatus sectionStatus;
	
	/**
	 * 
	 */
	public SectionReviewItem() {
	}

	/**
	 * @return the questionAnswerItems
	 */
	public List<QuestionAnswerItem> getQuestionAnswerItems() {
		return this.questionAnswerItems;
	}

	/**
	 * @param questionAnswerItems the questionAnswerItems to set
	 */
	public void setQuestionAnswerItems(List<QuestionAnswerItem> questionAnswerItems) {
		this.questionAnswerItems = questionAnswerItems;
	}

	/**
	 * @return the sectionNotes
	 */
	public String getSectionNotes() {
		return this.sectionNotes;
	}

	/**
	 * @param sectionNotes the sectionNotes to set
	 */
	public void setSectionNotes(String sectionNotes) {
		this.sectionNotes = sectionNotes;
	}

	/**
	 * @return the sectionStatus
	 */
	public AdministeredQuestionnaireSectionStatus getSectionStatus() {
		return this.sectionStatus;
	}

	/**
	 * @param sectionStatus the sectionStatus to set
	 */
	public void setSectionStatus(AdministeredQuestionnaireSectionStatus sectionStatus) {
		this.sectionStatus = sectionStatus;
	}


	
	

}
