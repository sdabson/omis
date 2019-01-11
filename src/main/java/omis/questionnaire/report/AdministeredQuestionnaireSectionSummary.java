package omis.questionnaire.report;

import java.io.Serializable;

/**
 * AdministeredQuestionnaireSectionSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireSectionSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long questionnaireSectionId;
	
	private final Long questionnaireSectionStatusId;
	
	private final String title;
	
	private final String number;
	
	private final String sectionHelpText;
	
	private final String sectionTypeDescription;
	
	private final Boolean draft;

	/**
	 * @param questionnaireSectionId
	 * @param title
	 * @param number
	 * @param sectionHelpText
	 * @param sectionHelpDescription
	 */
	public AdministeredQuestionnaireSectionSummary(final Long questionnaireSectionId, 
			final Long questionnaireSectionStatusId,
			final String title, final String number,
			final String sectionHelpText, final String sectionTypeDescription,
			final Boolean draft) {
		this.questionnaireSectionId = questionnaireSectionId;
		this.questionnaireSectionStatusId = questionnaireSectionStatusId;
		this.title = title;
		this.number = number;
		this.sectionHelpText = sectionHelpText;
		this.sectionTypeDescription = sectionTypeDescription;
		this.draft = draft;
	}

	/**
	 * @return the questionnaireSectionId
	 */
	public Long getQuestionnaireSectionId() {
		return this.questionnaireSectionId;
	}
	
	/**
	 * @return the questionnaireSectionStatusId
	 */
	public Long getQuestionnaireSectionStatusId() {
		return this.questionnaireSectionStatusId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return this.number;
	}

	/**
	 * @return the sectionHelpText
	 */
	public String getSectionHelpText() {
		return this.sectionHelpText;
	}

	/**
	 * @return the sectionHelpDescription
	 */
	public String getSectionTypeDescription() {
		return this.sectionTypeDescription;
	}

	/**
	 * @return the draft
	 */
	public Boolean getDraft() {
		return this.draft;
	}
	
	

}
