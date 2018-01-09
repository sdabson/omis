package omis.document.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/** Document Tag.
 * @author Ryan JOhns
 * @version 0.1.0 (May 18, 2015)
 * @since OMIS 3.0 */
public interface DocumentTag extends Creatable, Updatable {
	/** Gets id.
	 * @return id. */
	Long getId();
	
	/** Gets name.
	 * @return name. */
	String getName();
	
	/** Gets document.
	 * @return document. */
	Document getDocument();
	
	/** Sets id.
	 * @param id id. */
	void setId(Long id);
	
	/** Sets name.
	 * @param name name. */
	void setName(String name);
	
	/** Sets document.
	 * @param document document. */
	void setDocument(Document document);
	
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
