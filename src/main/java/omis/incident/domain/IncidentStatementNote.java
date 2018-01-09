package omis.incident.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Incident Note
 * 
 * @author: Yidong Li
 * @author: Joel Norris
 * @version: 0.1.0 (June 20, 2015)
 * @since: OMIS 3.0
 */
public interface IncidentStatementNote extends Creatable, Updatable{
	/**
	 * Returns the incident note id
	 * @return incident report id
	 */
	Long getId();
		
	/** 
	 * Sets the incident note id
	 * @param incident id
	 */
	void setId(Long id);
	
	/**
	 * Returns the date
	 * @return the date
	 */
	Date getDate();
		
	/** 
	 * Sets the date
	 * @param date
	 */
	void setDate(Date date);
	
	/**
	 * Returns the note
	 * @return the note
	 */
	String getNote();
		
	/** 
	 * Sets the note
	 * @param note
	 */
	void setNote(String note);
	
	/**
	 * Returns the statement.
	 * 
	 * @return the statement
	 */
	IncidentStatement getStatement();
	
	/** 
	 * Sets the report.
	 * 
	 * @param statement statement
	 */
	void setStatement(IncidentStatement statement);
	
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