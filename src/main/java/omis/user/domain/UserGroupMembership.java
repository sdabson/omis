package omis.user.domain;

import java.io.Serializable;

/**
 * Membership of user in group.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface UserGroupMembership extends Serializable {

	/**
	 * Sets ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets user account.
	 * 
	 * @param userAccount user account
	 */
	void setUserAccount(UserAccount userAccount);
	
	/**
	 * Returns user account.
	 * 
	 * @return user account
	 */
	UserAccount getUserAccount();
	
	/**
	 * Sets user group.
	 * 
	 * @param userGroup user group
	 */
	void setUserGroup(UserGroup userGroup);
	
	/**
	 * Returns user group.
	 * 
	 * @return user group
	 */
	UserGroup getUserGroup();
	
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
}