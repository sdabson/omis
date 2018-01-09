package omis.mailroom.domain.impl;

import java.util.Date;

import omis.mail.domain.Mail;
import omis.mailroom.domain.StaffReceipt;
import omis.staff.domain.StaffAssignment;

/** Implementation of staff receipt.
 * @author Ryan Johns
 * @version 0.1.0 (May 27, 2016)
 * @since OMIS 3.0 */
public class StaffReceiptImpl implements StaffReceipt {
	public static final long serialVersionUID = 1l;
	private static final String RECEIVER_REQUIRED_MSG = "Receiver requried";
	private static final String MAIL_REQUIRED_MSG = "Mail required";
	private static final String DATE_REQUIRED_MSG = "Date required";
	private static final int[] HASHS = {3,5,7};
	
	private Long id;
	private StaffAssignment receiver;
	private String comments;
	private Mail mail;
	private Date date;
	
	/** Constructor. */
	public StaffReceiptImpl() {
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public StaffAssignment getReceiver() {
		return this.receiver;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getComments() {
		return this.comments;
	}
	
	/** {@inheritDoc} */
	@Override
	public Mail getMail() {
		return this.mail;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setReceiver(final StaffAssignment receiver) {
		this.receiver = receiver;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setComments(final String comments) {
		this.comments = comments;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setMail(final Mail mail) {
		this.mail = mail;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
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
			if (obj instanceof StaffReceipt) {
				this.checkState();
				StaffReceipt that = (StaffReceipt) obj;
				if (this.getReceiver().equals(that.getReceiver())
						&& this.getDate().equals(that.getDate())
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
		return this.getReceiver().hashCode() * HASHS[0]
				+ this.getDate().hashCode() * HASHS[1]
				+ this.getMail().hashCode() * HASHS[2];
	}
	
	/* Check state. */
	private void checkState() {
		if (this.getReceiver() == null) {
			throw new IllegalStateException(RECEIVER_REQUIRED_MSG);
		}
		
		if (this.getDate() == null) {
			throw new IllegalStateException(DATE_REQUIRED_MSG);
		}
		
		if (this.getMail() == null) {
			throw new IllegalStateException(MAIL_REQUIRED_MSG);
		}
	}
}
