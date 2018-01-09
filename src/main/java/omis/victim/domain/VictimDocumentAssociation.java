package omis.victim.domain;

import omis.audit.domain.Creatable;
import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.person.domain.Person;

/**
 * Victim document association.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 22, 2017)
 * @since OMIS 3.0
 */
public interface VictimDocumentAssociation extends Creatable {

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the victim {@link Person}.
	 * 
	 * @return victim
	 */
	Person getVictim();
	
	/**
	 * Sets the victim {@link Person}
	 * 
	 * @param victim person
	 */
	void setVictim(Person victim);
	
	/**
	 * Returns the document.
	 * 
	 * @return document
	 */
	Document getDocument();
	
	/**
	 * Sets the document.
	 * 
	 * @param document document
	 */
	void setDocument(Document document);
	
	/**
	 * Returns the docket.
	 * 
	 * @return docket
	 */
	Docket getDocket();
	
	/**
	 * Sets the docket.
	 * 
	 * @param docket docket
	 */
	void setDocket(Docket docket);
	
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
