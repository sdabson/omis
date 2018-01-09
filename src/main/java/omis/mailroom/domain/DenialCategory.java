package omis.mailroom.domain;

import java.io.Serializable;

/** Mail Denial Category.
 * @author Ryan Johns.
 * @version 0.1.0 (May 11, 2016)
 * @since OMIS 3.0 */
public interface DenialCategory extends Serializable {
	/** Gets id. 
	 * @return id. */
	Long getId();
	
	/** Gets description.
	 * @return description. */
	String getDescription();
	
	/** Gets valid.
	 * @return valid. */
	Boolean getValid();
	
	/** Gets outgoing denial category.
	 * @return outgoing. */
	Boolean getOutgoing();
	
	/** Gets incoming denial category.
	 * @return incoming. */
	Boolean getIncoming();
	
	/** Sets id.
	 * @param id - id. */
	void setId(Long id);
	
	/** Sets description.
	 * @param description -  description. */
	void setDescription(String description);
	
	/** Sets valid.
	 * @param valid - valid. */
	void setValid(Boolean valid);
	
	/** Sets outgoing denial category.
	 * @param outgoing - outgoing. */
	void setOutgoing(Boolean outgoing);
	
	/** Sets incoming denial category.
	 * @param incoming - incoming. */
	void setIncoming(Boolean incoming);
}
