/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package omis.task.domain;

import java.io.Serializable;

/**
 * Value of task parameter.
 *
 * <p>Not complete.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.0.2
 * @since OMIS 3.0
 */
public interface TaskParameterValue
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
	 * Sets task.
	 * 
	 * @param task task
	 */
	void setTask(Task task);
	
	/**
	 * Returns task.
	 * 
	 * @return task
	 */
	Task getTask();
	
	/**
	 * Sets value of instance.
	 * 
	 * @param instanceValue value of instance 
	 */
	void setInstanceValue(String instanceValue);
	
	/**
	 * Returns value of instance.
	 * 
	 * @return value of instance
	 */
	String getInstanceValue();
	
	/**
	 * Sets type name.
	 * 
	 * @param typeName type name
	 */
	void setTypeName(String typeName);
	
	/**
	 * Returns type name.
	 * 
	 * @return type name
	 */
	String getTypeName();
	
	/**
	 * Sets order.
	 * 
	 * @param order
	 */
	void setOrder(Short order);
	
	/**
	 * Returns order.
	 * 
	 * @return order
	 */
	Short getOrder();
	
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