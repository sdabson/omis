package omis.mailroom.domain.impl;

import java.math.BigDecimal;

import omis.mail.domain.Mail;
import omis.mailroom.domain.MonetaryContent;
import omis.mailroom.domain.MonetaryContentCategory;

/** Implementation of monetary content.
 * @author Ryan Johns
 * @version 0.1.0 (May 27, 2016)
 * @since OMIS 3.0 */
public class MonetaryContentImpl implements MonetaryContent {
	public static final long serialVersionUID = 1l;
	private static final String AMOUNT_REQUIRED_MSG = "Amount required";
	private static final String CATEGORY_REQUIRED_MSG = "Category required";
	private static final String MAIL_REQUIRED_MSG = "Mail required";
	private static final int[] HASHS = {3, 5, 7};
	
	private Long id;
	private MonetaryContentCategory category;
	private BigDecimal amount;
	private Mail mail;
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public MonetaryContentCategory getCategory() {
		return this.category;
	}
	
	/** {@inheritDoc} */
	@Override
	public BigDecimal getAmount() {
		return this.amount;
	}
	
	/** {@inheritDoc} */
	@Override
	public Mail getMail() {
		return this.mail;
	}
	
	/** @inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCategory(final MonetaryContentCategory category) {
		this.category = category;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setMail(final Mail mail) {
		this.mail = mail;
	}
	
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
	public boolean equals(final Object obj) {
		boolean result = false;
		
		if (this == obj) {
			result = true;
		} else {
			if (obj instanceof MonetaryContent) {
				this.checkState();
				MonetaryContent that = (MonetaryContent) obj;
				if (this.getCategory().equals(that.getCategory())
						&& this.getAmount().equals(that.getAmount())
						&& this.getMail().equals(that.getMail())) {
					result = true;
				}
			}
		}
		return result;
	}
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	public int hashCode() {
		this.checkState();
		return this.getCategory().hashCode() * HASHS[0]
				+ this.getAmount().hashCode() * HASHS[1]
				+ this.getMail().hashCode() * HASHS[2];
	}
	
	/* Checks state. */
	private void checkState() {
		if (this.getCategory() == null) {
			throw new IllegalStateException(CATEGORY_REQUIRED_MSG);
		}
		
		if (this.getAmount() == null) {
			throw new IllegalStateException(AMOUNT_REQUIRED_MSG);
		}
		
		if (this.getMail() == null) {
			throw new IllegalStateException(MAIL_REQUIRED_MSG);
		}
	}
}
