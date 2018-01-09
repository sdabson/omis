package omis.task.domain;

import java.io.Serializable;

/**
 * Task template.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface TaskTemplate
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
	 * Sets group.
	 * 
	 * @param group
	 */
	void setGroup(TaskTemplateGroup group);
	
	/**
	 * Returns group.
	 * 
	 * @return group
	 */
	TaskTemplateGroup getGroup();
	
	/**
	 * Sets name.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Returns name.
	 * 
	 * @return name
	 */
	String getName();
	
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