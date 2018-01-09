package omis.identificationnumber.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.IdentificationNumberIssuer;

/**
 * Form for identification numbers.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class IdentificationNumberForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private IdentificationNumberIssuer issuer;
	
	private IdentificationNumberCategory category;
	
	private String value;
	
	private Date issueDate;
	
	private Date expireDate;

	/** Instantiates identification number form. */
	public IdentificationNumberForm() {
		// Default instantiation
	}

	/**
	 * Returns issuer.
	 * 
	 * @return issuer
	 */
	public IdentificationNumberIssuer getIssuer() {
		return this.issuer;
	}

	/**
	 * Sets issuer.
	 * 
	 * @param issuer issuer
	 */
	public void setIssuer(final IdentificationNumberIssuer issuer) {
		this.issuer = issuer;
	}

	/**
	 * Returns category.
	 * 
	 * @return category
	 */
	public IdentificationNumberCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets category.
	 * 
	 * @param category category
	 */
	public void setCategory(final IdentificationNumberCategory category) {
		this.category = category;
	}

	/**
	 * Returns value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets value.
	 * 
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * Returns issue date.
	 * 
	 * @return issue date
	 */
	public Date getIssueDate() {
		return this.issueDate;
	}

	/**
	 * Sets issue date.
	 * 
	 * @param issueDate issue date
	 */
	public void setIssueDate(final Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * Returns expire date.
	 * 
	 * @return expire date
	 */
	public Date getExpireDate() {
		return this.expireDate;
	}

	/**
	 * Sets expire date.
	 * 
	 * @param expireDate expire date
	 */
	public void setExpireDate(final Date expireDate) {
		this.expireDate = expireDate;
	}
}