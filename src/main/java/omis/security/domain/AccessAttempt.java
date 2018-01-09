package omis.security.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Logs an attempt, successful or otherwise, of a user to access the
 * application. Usually, this means a login via a web app front-end but could
 * also be an attempt to authenticate through a thick client.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Feb 7, 2013)
 * @since OMIS 3.0
 */
public interface AccessAttempt extends Serializable {

	/**
	 * Returns the ID of the access attempt.
	 * 
	 * @return access log entry ID
	 */
	Long getId();

	/**
	 * Sets the ID of the access attempt.
	 * 
	 * @param id access log entry ID
	 */
	void setId(Long id);

	/**
	 * Returns the username with which the access attempt was made.
	 * 
	 * @return username with which access attempt was made
	 */
	String getUsername();

	/**
	 * Sets the username with which the access attempt was made.
	 * 
	 * @param username username with which access attempt was made
	 */
	void setUsername(String username);

	/**
	 * Returns the date at which the access attempt was made.
	 * 
	 * <p>
	 * A deep copy will be returned.
	 * @return date at which access attempt was made
	 */
	Date getDate();

	/**
	 * Sets the date at which the access attempt was made.
	 * 
	 * <p>A deep copy of the specified date will be made.
	 * 
	 * @param date date at which access attempt was made
	 */
	void setDate(Date date);

	/**
	 * Returns the remote address from which the access attempt was made.
	 * 
	 * @return remote address from which access attempt was made
	 */
	String getRemoteAddress();

	/**
	 * Sets the remote address from which the access attempt was made.
	 * 
	 * @param remoteAddress remote address from which the access attempt was
	 * made
	 */
	void setRemoteAddress(String remoteAddress);

	/**
	 * Returns the name of the host from which the access attempt was made.
	 * 
	 * @return name of host from which access attempt was made
	 */
	String getRemoteHost();

	/**
	 * Sets the name of the host from which the access attempt was made.
	 * 
	 * @param remoteHost name of host from which access attempt was made
	 */
	void setRemoteHost(String remoteHost);

	/**
	 * Returns whether the access attempt was successful.
	 * 
	 * @return whether access attempt was successful
	 */
	Boolean getSuccess();

	/**
	 * Sets whether the access attempt was successful.
	 * 
	 * @param success whether access attempt was successful
	 */
	void setSuccess(Boolean success);
	
	/**
	 * Returns user agent.
	 * 
	 * @return user agent
	 */
	String getUserAgent();
	
	/**
	 * Sets user agent.
	 * 
	 * @param userAgent user agent
	 */
	void setUserAgent(String userAgent);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
	
	/**
	 * Returns a string representation of the access attempt including an
	 * indication of the success of the attempt, the attempted username, date
	 * and IP from which the attempt was made.
	 * 
	 * @return string containing success indication, username, date and IP
	 */
	@Override
	String toString();
}