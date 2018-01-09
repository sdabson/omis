package omis.mailroom.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.mail.domain.Mail;
import omis.mailroom.domain.PackageContent;

/** Implementation of package content.
 * @author Ryan Johns
 * @version 0.1.0 (May 24, 2016)
 * @since OMIS 3.0 */
public class PackageContentImpl implements PackageContent {
	public static final long serialVersionUID = 1l;
	private static final int[] HASHS = {3, 5};
	private static final String DESCRIPTION_REQUIRED_MSG 
		= "Description required";
	private static final String MAIL_REQUIRED_MSG = "Mail required";
	private Long id;
	private String description;
	private Mail mail;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
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
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
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
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
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
	public boolean equals(Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		} else {
			if (obj instanceof PackageContent) {
				this.checkState();
				PackageContent that = (PackageContent) obj;
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
	
	/* Checks state. */
	private void checkState() {
		if (this.getDescription() == null) {
			throw new IllegalStateException(DESCRIPTION_REQUIRED_MSG);
		}
		if (this.getMail() == null) {
			throw new IllegalStateException(MAIL_REQUIRED_MSG);
		}
	}
}
