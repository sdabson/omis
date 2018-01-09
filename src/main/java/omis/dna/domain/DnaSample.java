package omis.dna.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.OffenderAssociable;

/**
 * Offender DNA Sample
 * <p>
 * DNA profiles are encrypted sets of numbers that reflect a person's DNA 
 * makeup, which can also be used as the person's identifier.
 * <p>
 * DNA Samples are often required by state and 
 * federal law enforcement when a person commits a 
 * crime.
 * <p>
 * Offender DNA samples only need to be taken once and
 * are submitted to a federal crime lab.
 * 
 * @author Jason Nelson
 * @author Joel Norris
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.1 (February 23, 2015)
 * @since OMIS 3.0
 */
 public interface DnaSample extends OffenderAssociable, Creatable, Updatable  {
	
	 /**
	  * Returns the id.
	  * @return id
	  */
	public Long getId();

	/**
	 * Sets the id.
	 * @param id id
	 */
	public void setId(Long id);

	/**
	 * Returns the collection employee.
	 * @return collection employee
	 */
	public String getCollectionEmployee();

	/**
	 * Sets the collection employee.
	 * @param collectionEmployee collection employee
	 */
	public void setCollectionEmployee(String collectionEmployee);

	/**
	 * Returns the date.
	 * @return date
	 */
	public Date getDate();

	/**
	 * Sets the date.
	 * @param date date
	 */
	public void setDate(Date date);
	
	/**
	 * Returns the time.
	 * 
	 * @return time
	 */
	public Date getTime();
	
	/**
	 * Sets the time.
	 * 
	 * @param time time
	 */
	public void setTime(Date time);
	
	/**
	 * Returns the location.
	 * @return location
	 */
	public String getLocation();

	/**
	 * Sets the location.
	 * @param location location
	 */
	public void setLocation(String location);

	/**
	 * Returns the witness.
	 * @return witness
	 */
	public String getWitness();

	/**
	 * Sets the witness.
	 * @param witness witness
	 */
	public void setWitness(String witness);

	/**
	 * Returns the comment.
	 * @return comment
	 */
	public String getComment();

	/**
	 * Sets the comment.
	 * @param comment comment
	 */
	public void setComment(String comment);
	 
	 /**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
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
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}