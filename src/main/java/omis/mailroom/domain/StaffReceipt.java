package omis.mailroom.domain;

import java.io.Serializable;
import java.util.Date;

import omis.mail.domain.Mail;
import omis.staff.domain.StaffAssignment;

/** Staff mail receipt.
 * @author Ryan Johns
 * @version 0.1.0 (May 11, 2016)
 * @since OMIS 3.0 */
public interface StaffReceipt extends Serializable {
	/** Gets id.
	 * @return id. */
	Long getId();
	
	/** Gets receiver.
	 * @return receiver. */
	StaffAssignment getReceiver();
	
	/** Gets comments.
	 * @return comments. */
	String getComments();
	
	/** Gets mail.
	 * @return mail. */
	Mail getMail();
	
	/** Gets date.
	 * @return date. */
	Date getDate();
	
	/** Sets id.
	 * @param id - id. */
	void setId(Long id);
	
	/** sets receiver.
	 * @param receiver - receiver. */
	void setReceiver(StaffAssignment receiver);
	
	/** Sets comments.
	 * @param comments - comments. */
	void setComments(String comments);
	
	/** Sets mail.
	 * @param mail - mail. */
	void setMail(Mail mail);
	
	/** Sets date.
	 * @param date. */
	void setDate(Date date);
}
