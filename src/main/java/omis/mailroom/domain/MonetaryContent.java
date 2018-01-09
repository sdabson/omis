package omis.mailroom.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import omis.mail.domain.Mail;

/** Monetary mail content.
 * @author Ryan Johns
 * @version 0.1.0 (May 11, 2016)
 * @since OMIS 3.0 */
public interface MonetaryContent extends Serializable {
	/** Gets id.
	 * @return id. */
	Long getId();
	
	/** Gets amount.
	 * @return amount. */
	BigDecimal getAmount();
	
	/** Gets monetary category.
	 * @return category. */
	MonetaryContentCategory getCategory();
	
	/** Gets mail.
	 * @return mail. */
	Mail getMail();
	
	/** Sets id.
	 * @param id - id. */
	void setId(Long id);
	
	/** Sets amount.
	 * @param amount - amount. */
	void setAmount(BigDecimal amount);
	
	/** Sets monetary category.
	 * @param category - category. */
	void setCategory(MonetaryContentCategory category);
	
	/** Sets mail.
	 * @param mail - mail. */
	void setMail(Mail mail);
}
