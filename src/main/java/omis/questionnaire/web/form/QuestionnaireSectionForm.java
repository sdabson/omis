package omis.questionnaire.web.form;

import omis.questionnaire.domain.SectionType;

/**
 * QuestionnaireSectionForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 17, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireSectionForm {
	
	private String title;
	
	private Integer sectionNumber;
	
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
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the sectionNumber
	 */
	public Integer getSectionNumber() {
		return this.sectionNumber;
	}

	/**
	 * @param sectionNumber the sectionNumber to set
	 */
	public void setSectionNumber(Integer sectionNumber) {
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
	public void setSectionType(SectionType sectionType) {
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
	public void setSectionHelp(String sectionHelp) {
		this.sectionHelp = sectionHelp;
	}
	
	
	
}
