package omis.questionnaire.web.form;

import omis.questionnaire.domain.SectionType;

/**
 * Questionnaire Section Form.
 * 
 *@author Annie Wahl
 *@version 0.1.1 (Oct 4, 2018)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireSectionForm {
	
	private String title;
	
	private String sectionNumber;
	
	private SectionType sectionType;
	
	private String sectionHelp;
	
	/**
	 * 
	 */
	public QuestionnaireSectionForm() {
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * @return the sectionNumber
	 */
	public String getSectionNumber() {
		return this.sectionNumber;
	}

	/**
	 * @param sectionNumber the sectionNumber to set
	 */
	public void setSectionNumber(final String sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	/**
	 * @return the sectionType
	 */
	public SectionType getSectionType() {
		return this.sectionType;
	}

	/**
	 * @param sectionType the sectionType to set
	 */
	public void setSectionType(final SectionType sectionType) {
		this.sectionType = sectionType;
	}

	/**
	 * @return the sectionHelp
	 */
	public String getSectionHelp() {
		return this.sectionHelp;
	}

	/**
	 * @param sectionHelp the sectionHelp to set
	 */
	public void setSectionHelp(final String sectionHelp) {
		this.sectionHelp = sectionHelp;
	}
	
	
	
}
