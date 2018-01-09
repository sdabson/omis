package omis.task.domain;

import java.io.Serializable;
import java.util.Date;

import omis.user.domain.UserAccount;

/**
 * Task.
 * 
 * <p>Not complete.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface Task
		extends Serializable {

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
	 * Sets controller name.
	 * 
	 * @param controllerName controller name
	 */
	void setControllerName(String controllerName);
	
	/**
	 * Returns controller name.
	 * 
	 * @return controller name
	 */
	String getControllerName();
	
	/**
	 * Sets method name.
	 * 
	 * @param methodName method name
	 */
	void setMethodName(String methodName);
	
	/**
	 * Returns method name.
	 * 
	 * @return method name
	 */
	String getMethodName();
	
	/**
	 * Sets description.
	 * 
	 * @param description description
	 */
	void setDescription(String description);
	
	/**
	 * Returns description.
	 * 
	 * @return description
	 */
	String getDescription();
	
	/**
	 * Sets source user account.
	 * 
	 * @param sourceAccount source user account
	 */
	void setSourceAccount(UserAccount sourceAccount);
	
	/**
	 * Returns source user account.
	 * 
	 * @return source user account
	 */
	UserAccount getSourceAccount();
	
	/**
	 * Sets origination date.
	 * 
	 * @param originationDate origination date
	 */
	void setOriginationDate(Date originationDate);
	
	/**
	 * Returns origination date.
	 * 
	 * @return origination date
	 */
	Date getOriginationDate();
	
	/**
	 * Sets completion date.
	 * 
	 * @param completionDate completion date
	 */
	void setCompletionDate(Date completionDate);
	
	/**
	 * Returns completion date.
	 * 
	 * @return completion date
	 */
	Date getCompletionDate();
	
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