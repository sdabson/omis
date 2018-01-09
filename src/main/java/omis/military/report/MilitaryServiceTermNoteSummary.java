package omis.military.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Military note summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0
 */
public class MilitaryServiceTermNoteSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Long militaryServiceTermId;
	
	private final Date date;
	
	private final String note;
	
	/**
	 * Instantiates a military note summary with the specified values.
	 * 
	 * @param militaryServiceTermId military service term ID
	 * @param date date
	 * @param note note
	 */
	public MilitaryServiceTermNoteSummary(final Long militaryServiceTermId,
			final Date date, final String note) {
		this.militaryServiceTermId = militaryServiceTermId;
		this.date = date;
		this.note = note;
	}

	/**
	 * Returns the military service term ID.
	 * 
	 * @return military service term id.
	 */
	public Long getMilitaryServiceTermId() {
		return this.militaryServiceTermId;
	}

	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Returns the note.
	 * 
	 * @return note
	 */
	public String getNote() {
		return this.note;
	}
}