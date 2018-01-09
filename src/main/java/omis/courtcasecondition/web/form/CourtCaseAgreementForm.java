package omis.courtcasecondition.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.docket.domain.Docket;

/** Form for court case condition.
 * @author Jonny Santy
 * @author Annie Wahl
 * @version 0.1.2 (Nov 29, 2017)
 * @since OMIS 3.0 */ 
public class CourtCaseAgreementForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date startDate;
	
	private Date endDate;
	
	private Docket docket;
	
	private Date acceptedDate;
	
	private String description;
	
	private List<AgreementAssociableDocumentItem>
		agreementAssociableDocumentItems =
			new ArrayList<AgreementAssociableDocumentItem>();;
	
	/**
	 * Court Case Agreement Form.
	 */
	public CourtCaseAgreementForm() {
	}
	
	/**
	 * @return the start Date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * @param startDate the start Date to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the end Date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * @param endDate the end Date to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the docket
	 */
	public Docket getDocket() {
		return this.docket;
	}

	/**
	 * @param docket - Docket to set
	 */
	public void setDocket(final Docket docket) {
		this.docket = docket;
	}
	
	/**
	 * Returns the accepted Date.
	 * @return acceptedDate - Date
	 */
	public Date getAcceptedDate() {
		return acceptedDate;
	}

	/**
	 * Sets the accepted Date.
	 * @param acceptedDate - Date
	 */
	public void setAcceptedDate(final Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	/**
	 * Returns the description.
	 * @return description - String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * @param description - String
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Returns the agreementAssociableDocumentItems.
	 * @return agreementAssociableDocumentItems -
	 * List<AgreementAssociableDocumentItem>
	 */
	public List<AgreementAssociableDocumentItem>
		getAgreementAssociableDocumentItems() {
		return agreementAssociableDocumentItems;
	}

	/**
	 * Sets the agreementAssociableDocumentItems.
	 * @param agreementAssociableDocumentItems -
	 * List<AgreementAssociableDocumentItem>
	 */
	public void setAgreementAssociableDocumentItems(
			final List<AgreementAssociableDocumentItem>
				agreementAssociableDocumentItems) {
		this.agreementAssociableDocumentItems =
				agreementAssociableDocumentItems;
	}
	
	

}
