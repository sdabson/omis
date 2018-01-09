package omis.mail.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.mail.domain.Mail;
import omis.mail.domain.component.Correspondent;

/** Implementation of mail.
 * @author Ryan Johns
 * @version 0.1.0 (May 24, 2016)
 * @since OMIS 3.0 */
public class MailImpl implements Mail {
	public static final long serialVersionUID = 1l;
	private static final String TO_REQUIRED_MSG 
		= "To Correspondent required";
	private static final String FROM_REQUIRED_MSG 
		= "From Correspondent required";
	private static final String PROCESS_DATE_REQUIRED_MSG
		= "Process date required";
	private static final int[] HASHS = {3, 5, 7};
	private Long id;
	private Correspondent to;
	private Correspondent from;
	private Date processDate;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	

	/** {@inheritDoc} */
	@Override
	public Correspondent getTo() {
		return this.to;
	}
	
	/** {@inheritDoc} */
	@Override
	public Correspondent getFrom() {
		return this.from;
	}
	
	/** {@inhertiDoc} */
	@Override
	public Date getProcessDate() {
		return this.processDate; 
	}
	
	/** {@inhertiDoc} */
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
	public void setTo(final Correspondent to) {
		this.to = to;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setFrom(final Correspondent from) {
		this.from = from;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setProcessDate(final Date processDate) {
		this.processDate = processDate;
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
		}
		if (obj instanceof Mail) {
			this.checkState();
			Mail that = (Mail) obj;
			if (this.getTo().equals(that.getTo())
					&& this.getFrom().equals(that.getFrom())
					&& this.getProcessDate().equals(that.getProcessDate())) {
				result = true;
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
		return this.getTo().hashCode() * HASHS[0]
				+ this.getFrom().hashCode() * HASHS[1]
				+ this.getProcessDate().hashCode() * HASHS[2];
	}
	
	/* Checks state. */
	private void checkState() {
		if (this.to == null) {
			throw new IllegalStateException(TO_REQUIRED_MSG);
		}
		if (this.from == null) {
			throw new IllegalStateException(FROM_REQUIRED_MSG);
		}
		if (this.processDate == null) {
			throw new IllegalStateException(PROCESS_DATE_REQUIRED_MSG);
		}
	}
}
