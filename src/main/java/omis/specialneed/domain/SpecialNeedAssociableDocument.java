package omis.specialneed.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.document.domain.Document;

/**
 * Special need associable document.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 1, 2017)
 * @since OMIS 3.0
 */
public interface SpecialNeedAssociableDocument extends Creatable, Updatable {
	
	/**
	 * Sets the ID of the special need associable document.
	 *
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Return the ID of the special need associable document.
	 *
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the special need associated with this special need associable 
	 * document.
	 *
	 * @param specialNeed special need
	 */
	void setSpecialNeed(SpecialNeed specialNeed);
	
	/**
	 * Returns the special need associated with the special need associable 
	 * document.
	 *
	 * @return special need
	 */
	SpecialNeed getSpecialNeed();
	
	/**
	 * Sets the category of the special need associable document.
	 *
	 * @param category category
	 */
	void setCategory(SpecialNeedAssociableDocumentCategory category);
	
	/**
	 * Returns the category of the special need associable document.
	 *
	 * @return category
	 */
	SpecialNeedAssociableDocumentCategory getCategory();
	
	/**
	 * Sets the document of the special need associable document.
	 *
	 * @param document document
	 */
	void setDocument(Document document);
	
	/**
	 * Return the document of the special need associable document.
	 *
	 * @return document
	 */
	Document getDocument();
	
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