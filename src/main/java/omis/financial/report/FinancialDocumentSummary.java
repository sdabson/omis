package omis.financial.report;

import java.io.Serializable;
import java.util.Date;

/**
 * FinancialDocumentSummary
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (June 2, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialDocumentSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long financialDocumentId;
	
	private final String documentTitle;
	
	private final Date documentDate;
	
	/**
	 * @param financialDocumentId
	 * @param documentTitle
	 * @param documentDate
	 */
	public FinancialDocumentSummary(final Long financialDocumentId,
			final String documentTitle, final Date documentDate) {
		this.financialDocumentId = financialDocumentId;
		this.documentTitle = documentTitle;
		this.documentDate = documentDate;
	}
	
	/**
	 * Returns the financialDocumentId
	 * @return financialDocumentId - Long
	 */
	public Long getFinancialDocumentId() {
		return financialDocumentId;
	}

	/**
	 * Returns the documentTitle
	 * @return documentTitle - String
	 */
	public String getDocumentTitle() {
		return documentTitle;
	}
	
	/**
	 * Returns the documentDate
	 * @return documentDate - Date
	 */
	public Date getDocumentDate() {
		return documentDate;
	}

}
