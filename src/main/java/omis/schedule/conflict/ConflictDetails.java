package omis.schedule.conflict;

import java.io.Serializable;

/**
 * Describes a conflict between multiple events.
 * 
 * @author Stephen Abson
 * @version 0.1 (Jan 3, 2012)
 * @since OMIS 3.0
 */
public class ConflictDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String reason;
	
	private final Class<?> conflictingPropertyType;
	
	private final String conflictingPropertyName;
	
	/**
	 * Instantiates a description of a conflict between multiple events.
	 * 
	 * @param reason reason for conflict
	 * @param conflictingPropertyType type of conflicting property
	 * @param conflictingPropertyName name of conflicting property
	 */
	public ConflictDetails(final String reason,
			final Class<?> conflictingPropertyType,
			final String conflictingPropertyName) {
		this.reason = reason;
		this.conflictingPropertyType = conflictingPropertyType;
		this.conflictingPropertyName = conflictingPropertyName;
	}
	
	/**
	 * Returns a reason for the conflict.
	 * 
	 * @return reason for conflict 
	 */
	public String getReason() {
		return this.reason;
	}
	
	/**
	 * Returns the type of the conflicting property.
	 * 
	 * @return type of conflicting property
	 */
	public Class<?> getConflictingPropertyType() {
		return this.conflictingPropertyType;
	}
	
	/**
	 * Returns the name of the conflicting property.
	 * 
	 * @return name of conflicting property
	 */
	public String getConflictingPropertyName() {
		return this.conflictingPropertyName;
	}
	
	/**
	 * Compares {@code this} and {@code o} for equality.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ConflictDetails)) {
			return false;
		}
		ConflictDetails that = (ConflictDetails) obj;
		if (!this.reason.equals(that.reason)) {
			return false;
		}
		if (!this.conflictingPropertyType
				.equals(that.conflictingPropertyType)) {
			return false;
		}
		if (!this.conflictingPropertyName
				.equals(that.conflictingPropertyName)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the hash code of the description of a conflict between
	 * multiple events.
	 * 
	 * @return hash code
	 */
	@Override
	public int hashCode() {
		int hashCode = 14;
		hashCode = 29 * hashCode + this.reason.hashCode();
		hashCode = 29 * hashCode + this.conflictingPropertyType.hashCode();
		hashCode = 29 * hashCode + this.conflictingPropertyName.hashCode();
		return hashCode;
	}
	
	/**
	 * Returns a string representation of the description of the conflict
	 * including details of the reason and property name and type.
	 * 
	 * @return string containing details of conflict reason and property
	 */
	@Override
	public String toString() {
		return String.format("%s [%s %s]", this.reason,
				this.conflictingPropertyType.getName(),
				this.conflictingPropertyName);
	}
}