package omis.program.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.program.domain.Program;

/**
 * Form for program placements.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ProgramPlacementForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Program program;
	
	private Date startDate;
	
	private Date endDate;
	
	/** Instantiates form for program placements. */
	public ProgramPlacementForm() {
		// Default instantiation
	}

	/**
	 * Returns program.
	 * 
	 * @return program
	 */
	public Program getProgram() {
		return this.program;
	}

	/**
	 * Sets program.
	 * 
	 * @param program program
	 */
	public void setProgram(final Program program) {
		this.program = program;
	}

	/**
	 * Returns start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
}