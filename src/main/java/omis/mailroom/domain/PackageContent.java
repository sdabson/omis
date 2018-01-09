package omis.mailroom.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.mail.domain.Mail;

/** Package content.
 * @author Ryan Johns
 * @version 0.1.0 (May 11, 2016)
 * @since OMIS 3.0 */
public interface PackageContent extends Creatable, Updatable {
	/** Gets id. 
	 * @return id - id. */
	Long getId();
	
	/** Gets description.
	 * @return description - description. */
	String getDescription();
	
	/** Gets mail.
	 * @return mail. */
	Mail getMail();
	
	/** Sets id.
	 * @param id - id. */
	void setId(Long id);
	
	/** Sets description.
	 * @param description - description. */
	void setDescription(String description);
	
	/** Sets mail.
	 * @param mail - mail. */
	void setMail(Mail mail);

}
