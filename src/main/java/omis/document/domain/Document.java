package omis.document.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/** Document.
 * @author Ryan Johns
 * @version 0.1.0 (May 18, 2015)
 * @since OMIS 3.0 */
public interface Document extends Creatable, Updatable {
	/** Gets id.
	 * @return id. */
	Long getId();
	
	/** Gets date.
	 * @return date. */
	Date getDate();
	
	/** Gets title.
	 * @return title. */
	String getTitle();
	
	/** Gets file name.
	 * @return file name. */
	String getFilename();
	
	/** Gets file extension.
	 * @return file extension. */
	String getFileExtension();
	
	/** Sets id.
	 * @param id - id. */
	void setId(Long id);
	
	/** Sets date.
	 * @param date - date. */
	void setDate(Date date);
	
	/** Sets title.
	 * @param title - title. */
	void setTitle(String title);
	
	/** Sets file name.
	 * @param filename - file name. */
	void setFilename(String filename);
	
	/** Sets file extension.
	 * @param fileExtension - file extension. */
	void setFileExtension(String fileExtension);
	
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
