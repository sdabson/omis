package omis.financial.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.document.domain.Document;
import omis.offender.domain.Offender;

/**
 * FinancialDocumentAssociation
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (June 2, 2017)
 *@since OMIS 3.0
 *
 */
public interface FinancialDocumentAssociation extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the FinancialDocumentAssociation
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the FinancialDocumentAssociation
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the Offender for the FinancialDocumentAssociation
	 * @return offender - Offender
	 */
	public Offender getOffender();
	
	/**
	 * Sets the Offender for the FinancialDocumentAssociation
	 * @param offender - Offender
	 */
	public void setOffender(Offender offender);
	
	/**
	 * Returns the Document for the FinancialDocumentAssociation
	 * @return document - Document
	 */
	public Document getDocument();
	
	/**
	 * Sets the Document for the FinancialDocumentAssociation
	 * @param document - Document
	 */
	public void setDocument(Document document);
	
	/** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();

}
