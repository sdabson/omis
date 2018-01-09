package omis.security.domain.impl;

import java.util.Date;

import omis.security.domain.AccessAttempt;

/**
 * Implementation of access attempt.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Feb 7, 2013)
 * @since OMIS 3.0
 */
public class AccessAttemptImpl implements AccessAttempt {

	private static final long serialVersionUID = 1L;
	
	private static final String USERNAME_REQUIRED_MSG = "Username required";
	
	private static final String DATE_REQUIRED_MSG = "Date required";
	
	private static final String REMOTE_ADDRESS_REQUIRED_MSG =
			"Remote address required";
	
	private static final String SUCCESS_INDICATION_REQUIRED_MSG =
			"Success indication required";
	
	private Long id;
	
	private String username;
	
	private Long date;
	
	private String remoteAddress;
	
	private String remoteHost;
	
	private Boolean success;
	
	private String userAgent;
	
	/** Instantiates a default access attempt. */
	public AccessAttemptImpl() {
		// Do nothing
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public String getUsername() {
		return this.username;
	}

	/** {@inheritDoc} */
	@Override
	public void setUsername(final String username) {
		this.username = username;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		if (this.date != null) {
			return new Date(this.date);
		} else {
			return null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		if (date != null) {
			this.date = date.getTime();
		} else {
			this.date = null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public String getRemoteAddress() {
		return this.remoteAddress;
	}

	/** {@inheritDoc} */
	@Override
	public void setRemoteAddress(final String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	/** {@inheritDoc} */
	@Override
	public String getRemoteHost() {
		return this.remoteHost;
	}

	/** {@inheritDoc} */
	@Override
	public void setRemoteHost(final String remoteHost) {
		this.remoteHost = remoteHost;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getSuccess() {
		return this.success;
	}

	/** {@inheritDoc} */
	@Override
	public void setSuccess(final Boolean success) {
		this.success = success;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getUserAgent() {
		return this.userAgent;
	}

	/** {@inheritDoc} */
	@Override
	public void setUserAgent(final String userAgent) {
		this.userAgent = userAgent;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AccessAttempt)) {
			return false;
		}
		AccessAttempt that = (AccessAttempt) obj;
		if (this.getUsername() == null) {
			throw new IllegalStateException(USERNAME_REQUIRED_MSG);
		}
		if (!this.getUsername().equals(that.getUsername())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException(DATE_REQUIRED_MSG);
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getRemoteAddress() == null) {
			throw new IllegalStateException(REMOTE_ADDRESS_REQUIRED_MSG);
		}
		if (!this.getRemoteAddress().equals(that.getRemoteAddress())) {
			return false;
		}
		if (this.getSuccess() == null) {
			throw new IllegalStateException(SUCCESS_INDICATION_REQUIRED_MSG);
		}
		if (!this.getSuccess().equals(that.getSuccess())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getUsername() == null) {
			throw new IllegalStateException(USERNAME_REQUIRED_MSG);
		}
		if (this.getDate() == null) {
			throw new IllegalStateException(DATE_REQUIRED_MSG);
		}
		if (this.getRemoteAddress() == null) {
			throw new IllegalStateException(REMOTE_ADDRESS_REQUIRED_MSG);
		}
		if (this.getSuccess() == null) {
			throw new IllegalStateException(SUCCESS_INDICATION_REQUIRED_MSG);
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getUsername().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getRemoteAddress().hashCode();
		hashCode = 29 * hashCode + this.getSuccess().hashCode();
		return hashCode;
	}
}