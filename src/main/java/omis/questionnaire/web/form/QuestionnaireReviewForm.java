package omis.questionnaire.web.form;

import java.util.List;
import omis.questionnaire.web.form.SectionReviewItem;


/**
 * QuestionnaireReviewForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 6, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireReviewForm {
	
	private List<SectionReviewItem> sectionReviewItems;
	
	/**
	 * Default Constructor for QuestionnaireReviewForm
	 */
	public QuestionnaireReviewForm() {
	}

	/**
	 * @return the sectionReviewItems
	 */
	public List<SectionReviewItem> getSectionReviewItems() {
		return this.sectionReviewItems;
	}

	/**
	 * @param sectionReviewItems the sectionReviewItems to set
	 */
	public void setSectionReviewItems(List<SectionReviewItem> 
		sectionReviewItems) {
			this.sectionReviewItems = sectionReviewItems;
	}
}
