package omis.courtdocument.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.courtcase.domain.CourtCase;
import omis.document.domain.DocumentAssociable;

/** Court document association.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 9, 2015)
 * @since OMIS 3.0 */
public interface CourtCaseDocumentAssociation 
	extends DocumentAssociable, Creatable, Updatable {
	/** Gets id.
	 * @return id. */
	Long getId();
	
	/** Gets court case.
	 * @return court case. */
	CourtCase getCourtCase();
	
	/** Gets document date.
	 * @return document date. */
	Date getDate();
	
	/** Gets court document category.
	 * @return category - category. */
	CourtDocumentCategory getCategory();
	
	/** Sets id.
	 * @param id - id. */
	void setId(Long id);
	
	/** Sets court case.
	 * @param courtCase - court case. */
	void setCourtCase(CourtCase courtCase);

	
	/** Sets document date.
	 * @param date - date. */
	void setDate(Date date);
	
	/** Sets court document category.
	 * @param category - category. */
	void setCategory(CourtDocumentCategory category);
	
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
