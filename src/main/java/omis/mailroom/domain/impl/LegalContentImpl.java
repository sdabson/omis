package omis.mailroom.domain.impl;

import omis.mail.domain.Mail;
import omis.mailroom.domain.LegalContent;

/** Implementation of legal content.
 * @author Ryan Johns
 * @version 0.1.0 (May 27, 2016)
 * @since OMIS 3.0 */
public class LegalContentImpl implements LegalContent {
	public static final long serialVersionUID = 1l;
	private static final String DESCRIPTION_REQUIRED_MSG
		= "Description required";
	private static final String MAIL_REQUIRED_MSG = "Mail required";
	private static final int[] HASHS = {3, 5};
	
	private Long id;
	private String description;
	private Mail mail;
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}
	
	/** {@inheritDoc} */
	@Override
	public Mail getMail() {
		return this.mail;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
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
			if (obj instanceof LegalContent) {
				this.checkState();
				LegalContent that = (LegalContent) obj;
				if (this.getDescription().equals(that.getDescription())
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
		return this.getDescription().hashCode() * HASHS[0]
				+ this.getMail().hashCode() * HASHS[1];
	}
	
	/* checks state. */
	private void checkState() {
		if (this.getMail() == null) {
			throw new IllegalStateException(MAIL_REQUIRED_MSG);
		}
		
		if (this.getDescription() == null) {
			throw new IllegalStateException(DESCRIPTION_REQUIRED_MSG);
		}
	}
}
