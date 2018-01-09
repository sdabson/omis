package omis.mailroom.domain;

import java.io.Serializable;
import java.util.Date;

import omis.mail.domain.Mail;

/** Mailroom denial.
 * @author Ryan JOhns
 * @version 0.1.0 (May 11, 2016)
 * @since OMIS 3.0 */
public interface Denial extends Serializable {
	/** Gets id.
	 * @return id. */
	Long getId();
	
	/** Gets comments.
	 * @return comments. */
	String getComments();
	
	/** Gets denial date.
	 * @return date. */
	Date getDate();
	
	/** Gets returned.
	 * @return returned to sender. */
	Boolean getReturned();
	
	/** Gets mail.
	 * @return mail. */
	Mail getMail();
	
	/** Sets id.
	 * @param id - id. */
	void setId(Long id);
	
	/** Sets comments.
	 * @param comments - comments. */
	void setComments(String comments);
	
	/** Sets returned.
	 * @param returned - returned. */
	void setReturned(Boolean returned);
	
	/** Sets mail.
	 * @param mail - mail. */
	void setMail(Mail mail);
}
