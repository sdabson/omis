package omis.mailroom.domain;

import java.io.Serializable;

import omis.mail.domain.Mail;

public interface LegalContent extends Serializable {
	/** Gets id.
	 * @return id - id. */
	Long getId();
	
	/** Gets description.
	 * @return description - description. */
	String getDescription();
	
	/** gets mail.
	 * @return mail - mail. */
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
