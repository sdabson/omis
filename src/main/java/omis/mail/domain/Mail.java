package omis.mail.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.mail.domain.component.Correspondent;

/** Mail.
 * @author Ryan Johns
 * @version 0.1.0 (May 2, 2016)
 * @since OMIS 3.0 */
public interface Mail extends Creatable, Updatable {
	/** Gets Id.
	 * @return id. */
	Long getId();
	
	/** Gets to.
	 * @return to line. */
	Correspondent getTo();
	
	/** Gets from.
	 * @return from line. */
	Correspondent getFrom();
	
	
	
	/** Gets process date.
	 * @return process date. */
	Date getProcessDate();
	
	/** Sets id.
	 * @param id - id. */
	void setId(Long id);
	
	/** Sets to.
	 * @param to - to. */
	void setTo(Correspondent to);
	
	/** Sets from.
	 * @param from - from. */
	void setFrom(Correspondent from);
	
	
	
	/** Sets process date.
	 * @param processDate - process date. */
	void setProcessDate(Date processDate);
}
