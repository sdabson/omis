package omis.presentenceinvestigation.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.document.domain.Document;

/**
 * Health section summary documentation association.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 3, 2017)
 * @since OMIS 3.0
 */
public interface HealthSectionSummaryDocumentAssociation 
extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the health section summary documentation association.
	 *
	 *
	 * @return health section summary documentation association
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the health section summary documentation association.
	 *
	 *
	 * @param id ID
	 */
	public void setId(Long id);
	
	/**
	 * Returns the document of the health section summary document
	 * association.
	 *
	 *
	 * @return document
	 */
	public Document getDocument();
	
	/**
	 * Sets the document of the health section summary document association.
	 *
	 *
	 * @param document document
	 */
	public void setDocument(Document document);
	
	/**
	 * Returns the health section summary of the health section summary 
	 * document association.
	 *
	 *
	 * @return health section summary
	 */
	public HealthSectionSummary getHealthSectionSummary();
	
	/**
	 * Sets the health section summary of the health section summary document 
	 * association.
	 *
	 *
	 * @param healthSectionSummary health section summary
	 */
	public void setHealthSectionSummary (
			HealthSectionSummary healthSectionSummary);

	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
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
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}