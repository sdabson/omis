package omis.document.domain;

import java.io.Serializable;

/** Document associable category.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 9, 2015)
 * @since OMIS 3.0 */
public interface DocumentAssociableCategory extends Serializable {
	/** Gets name.
	 * @return name. */
	String getName();
	
	/** Sets name.
	 * @param name - name. */
	void setName(String name);
	
	/** Gets valid flag.
	 * @return valid. */
	Boolean getValid();
	
	/** Sets valid flag. 
	 * @param valid - valid flag. */
	void setValid(Boolean valid);
}
