package omis.placement.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.program.domain.Program;

/**
 * Form to change program placement.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 2, 2016)
 * @since OMIS 3.0
 */
public class ProgramPlacementChangeForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Program program;

	private Date startDate;

	private Date endDate;
	
	/** Instantiates form to change program placement. */
	public ProgramPlacementChangeForm() {
		// Default instantiation
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
	 * Returns program.
	 * 
	 * @return program
	 */
	public Program getProgram() {
		return this.program;
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
	 * Returns start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Sets end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Returns end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}
}