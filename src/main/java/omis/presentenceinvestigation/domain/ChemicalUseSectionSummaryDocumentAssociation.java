package omis.presentenceinvestigation.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.document.domain.Document;

/**
 * ChemicalUseSectionSummaryDocumentAssociation.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 5, 2017)
 *@since OMIS 3.0
 *
 */
public interface ChemicalUseSectionSummaryDocumentAssociation
	extends Creatable, Updatable{
	
	/**
	 * Returns the ID of the ChemicalUseSectionSummaryDocumentAssociation
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the ChemicalUseSectionSummaryDocumentAssociation
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the ChemicalUseSectionSummary for the
	 * ChemicalUseSectionSummaryDocumentAssociation
	 * @return chemicalUseSectionSummary - ChemicalUseSectionSummary
	 */
	public ChemicalUseSectionSummary getChemicalUseSectionSummary();
	
	/**
	 * Sets the ChemicalUseSectionSummary for the
	 * ChemicalUseSectionSummaryDocumentAssociation
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 */
	public void setChemicalUseSectionSummary(
			ChemicalUseSectionSummary chemicalUseSectionSummary);
	
	/**
	 * Returns the Document for the ChemicalUseSectionSummaryDocumentAssocation
	 * @return document - Document
	 */
	public Document getDocument();
	
	/**
	 * Sets the Document for the ChemicalUseSectionSummaryDocumentAssocation
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
