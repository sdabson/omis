package omis.education.report;

import java.io.Serializable;
import java.util.Date;

/**
 * EducationDocumentSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 13, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationDocumentSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long educationDocumentId;
	
	private final String documentTitle;
	
	private final String categoryName;
	
	private final Date documentDate;

	/**
	 * @param educationDocumentId
	 * @param documentTitle
	 * @param categoryName
	 * @param documentDate
	 */
	public EducationDocumentSummary(final Long educationDocumentId,
			final String documentTitle,final  String categoryName,
			final Date documentDate) {
		this.educationDocumentId = educationDocumentId;
		this.documentTitle = documentTitle;
		this.categoryName = categoryName;
		this.documentDate = documentDate;
	}

	/**
	 * Returns the educationDocumentId
	 * @return educationDocumentId - Long
	 */
	public Long getEducationDocumentId() {
		return educationDocumentId;
	}

	/**
	 * Returns the documentTitle
	 * @return documentTitle - String
	 */
	public String getDocumentTitle() {
		return documentTitle;
	}

	/**
	 * Returns the categoryName
	 * @return categoryName - String
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Returns the documentDate
	 * @return documentDate - Date
	 */
	public Date getDocumentDate() {
		return documentDate;
	}
	
	
}
