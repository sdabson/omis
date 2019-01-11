package omis.questionnaire.report;

import java.io.Serializable;

/**
 * QuestionnaireSectionSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 21, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireSectionSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long questionnaireSectionId;
	
	private final String title;
	
	private final String number;
	
	private final String sectionHelpText;
	
	private final String sectionTypeDescription;

	/**
	 * @param questionnaireSectionId
	 * @param title
	 * @param number
	 * @param sectionHelpText
	 * @param sectionTypeDescription
	 */
	public QuestionnaireSectionSummary(final Long questionnaireSectionId,
			final String title, final String number,
			final String sectionHelpText,
			final String sectionTypeDescription) {
		this.questionnaireSectionId = questionnaireSectionId;
		this.title = title;
		this.number = number;
		this.sectionHelpText = sectionHelpText;
		this.sectionTypeDescription = sectionTypeDescription;
	}

	/**
	 * @return the questionnaireSectionId
	 */
	public Long getQuestionnaireSectionId() {
		return this.questionnaireSectionId;
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
	 * @return the sectionTypeDescription
	 */
	public String getSectionTypeDescription() {
		return this.sectionTypeDescription;
	}
	
	
	
}
