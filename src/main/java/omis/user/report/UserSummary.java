package omis.user.report;

import java.io.Serializable;

import omis.person.domain.Person;
import omis.person.domain.PersonName;
import omis.user.domain.UserAccount;

/**
 * Summary of user.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 7, 2015)
 * @since OMIS 3.0
 */
public class UserSummary
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String lastName;
	
	private final String firstName;
	
	private final String middleName;
	
	private final String suffix;
	
	private final Long accountId;

	private final String username;
	
	/**
	 * Instantiates summary of user.
	 * 
	 * @param user user
	 * @param userName name of user
	 * @param userAccount user account
	 */
	public UserSummary(
			final Person user,
			final PersonName userName,
			final UserAccount userAccount) {
		this.id = user.getId();
		this.lastName = userName.getLastName();
		this.firstName = userName.getFirstName();
		this.middleName = userName.getMiddleName();
		this.suffix = userName.getSuffix();
		this.accountId = userAccount.getId();
		this.username = userAccount.getUsername();
	}
	
	/**
	 * Returns ID of user.
	 * 
	 * @return ID of user
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns last name of user.
	 * 
	 * @return last name of user
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Returns first name of user.
	 * 
	 * @return first name of user
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Returns middle name of user.
	 * 
	 * @return middle name of user
	 */
	public String getMiddleName() {
		return this.middleName;
	}
	
	/**
	 * Returns suffix.
	 * 
	 * @return suffix
	 */
	public String getSuffix() {
		return this.suffix;
	}
	
	/**
	 * Returns ID of user account.
	 * 
	 * @return ID of user account
	 */
	public Long getAccountId() {
		return this.accountId;
	}
	
	/**
	 * Returns username.
	 * 
	 * @return username
	 */
	public String getUsername() {
		return this.username;
	}
}