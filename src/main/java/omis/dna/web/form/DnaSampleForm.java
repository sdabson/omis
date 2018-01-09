package omis.dna.web.form;

import java.util.Date;


/**
 * Offender Dna Sample form to handle
 * web submissions and data presentation.
 * @author Jason Nelson
 * @author Joel Norris
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.1 (February 23, 2015)
 * @since OMIS 3.0
 */
public class DnaSampleForm {
	
	private Date date;
	
	private Date time;
	
	private String collectionEmployee;
	
	private String location;
	
	private String witness;
	
	private String comment;
	
	/**
	 * Instantiates a default instance of DNA sample form.
	 */
	public DnaSampleForm() {
		//Default constructor
	}

	/**
	 * Returns the date.
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the time.
	 * 
	 * @return time
	 */
	public Date getTime() {
		return this.time;
	}

	/**
	 * Sets the time.
	 * 
	 * @param time time
	 */
	public void setTime(final Date time) {
		this.time = time;
	}

	/**
	 * Returns the collection employee.
	 * @return collection employee
	 */
	public String getCollectionEmployee() {
		return collectionEmployee;
	}

	/**
	 * Sets the collection employee.
	 * @param collectionEmployee collection employee
	 */
	public void setCollectionEmployee(final String collectionEmployee) {
		this.collectionEmployee = collectionEmployee;
	}

	/**
	 * Returns the location.
	 * @return location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Sets the location.
	 * @param location location
	 */
	public void setLocation(final String location) {
		this.location = location;
	}

	/**
	 * Returns the witness.
	 * @return witness
	 */
	public String getWitness() {
		return this.witness;
	}

	/**
	 * Sets the witness.
	 * @param witness witness
	 */
	public void setWitness(final String witness) {
		this.witness = witness;
	}

	/**
	 * Returns the comment.
	 * @return comment
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Sets the comment.
	 * @param comment
	 */
	public void setComment(final String comment) {
		this.comment = comment;
	}
}