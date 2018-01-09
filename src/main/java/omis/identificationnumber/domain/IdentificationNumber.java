package omis.identificationnumber.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.OffenderAssociable;

/**
 * Identification number.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface IdentificationNumber
		extends OffenderAssociable, Creatable, Updatable {

	/**
	 * Sets ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets issuer.
	 * 
	 * @param issuer issuer
	 */
	void setIssuer(IdentificationNumberIssuer issuer);
	
	/**
	 * Returns issuer.
	 * 
	 * @return issuer
	 */
	IdentificationNumberIssuer getIssuer();
	
	/**
	 * Sets category.
	 * 
	 * @param category category
	 */
	void setCategory(IdentificationNumberCategory category);
	
	/**
	 * Returns category.
	 * 
	 * @return category
	 */
	IdentificationNumberCategory getCategory();
	
	/**
	 * Sets value.
	 * 
	 * @param value value
	 */
	void setValue(String value);
	
	/**
	 * Returns value.
	 * 
	 * @return value
	 */
	String getValue();
	
	/**
	 * Sets issue date.
	 * 
	 * @param issueDate issue date
	 */
	void setIssueDate(Date issueDate);
	
	/**
	 * Returns issue date.
	 * 
	 * @return issue date
	 */
	Date getIssueDate();
	
	/**
	 * Sets expire date.
	 * 
	 * @param expireDate expire date
	 */
	void setExpireDate(Date expireDate);
	
	/**
	 * Returns expire date.
	 * 
	 * @return expire date
	 */
	Date getExpireDate();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}