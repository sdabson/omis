package omis.placement.report;

import java.io.Serializable;

/**
 * Summary of allowed correctional status change.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 21, 2014)
 * @since OMIS 3.0
 */
public class AllowedCorrectionalStatusChangeSummary
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final boolean fromCorrectionalStatusExists;
	
	private final Long fromCorrectionalStatusId;
	
	private final String fromCorrectionalStatusName;
	
	private final boolean toCorrectionalStatusExists;
	
	private final Long toCorrectionalStatusId;
	
	private final String toCorrectionalStatusName;
	
	private final Long actionId;
	
	private final String actionName;
	
	/**
	 * Instantiates summary of allowed correctional status change.
	 * 
	 * @param id ID
	 * @param fromCorrectionalStatusExists whether correctional status from
	 * which to allow change exists
	 * @param fromCorrectionalStatusId ID of correctional status from which to
	 * allow change
	 * @param fromCorrectionalStatusName name of correctional status from which
	 * to allow change
	 * @param toCorrectionalStatusExists whether correctional status to
	 * which to allow change exists
	 * @param toCorrectionalStatusId ID of correctional status to which to allow
	 * change
	 * @param toCorrectionalStatusName name of correctional status to which to
	 * allow change
	 * @param actionId ID of action
	 * @param actionName name of action
	 */
	public AllowedCorrectionalStatusChangeSummary(
			final Long id,
			final boolean fromCorrectionalStatusExists,
			final Long fromCorrectionalStatusId,
			final String fromCorrectionalStatusName,
			final boolean toCorrectionalStatusExists,
			final Long toCorrectionalStatusId,
			final String toCorrectionalStatusName,
			final Long actionId,
			final String actionName) {
		this.id = id;
		this.fromCorrectionalStatusExists = fromCorrectionalStatusExists;
		this.fromCorrectionalStatusId = fromCorrectionalStatusId;
		this.fromCorrectionalStatusName = fromCorrectionalStatusName;
		this.toCorrectionalStatusExists = toCorrectionalStatusExists;
		this.toCorrectionalStatusId = toCorrectionalStatusId;
		this.toCorrectionalStatusName = toCorrectionalStatusName;
		this.actionId = actionId;
		this.actionName = actionName;
	}

	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Returns whether correctional status from which to allow change exists.
	 * 
	 * @return whether correctional status from which to allow change exists
	 */
	public boolean getFromCorrectionalStatusExists() {
		return this.fromCorrectionalStatusExists;
	}
	
	/**
	 * Returns ID of correctional status from which to allow change.
	 * 
	 * @return ID of correctional status from which to allow change
	 */
	public Long getFromCorrectionalStatusId() {
		return this.fromCorrectionalStatusId;
	}

	/**
	 * Returns name of correctional status from which to allow change.
	 * 
	 * @return name of correctional status from which to allow change
	 */
	public String getFromCorrectionalStatusName() {
		return this.fromCorrectionalStatusName;
	}

	/**
	 * Returns whether correctional status to which to allow change exists.
	 * 
	 * @return whether correctional status to which to allow change exists
	 */
	public boolean getToCorrectionalStatusExists() {
		return this.toCorrectionalStatusExists;
	}
	
	/**
	 * Returns ID of correctional status to which to allow change.
	 * 
	 * @return ID of to correctional status to which to allow change
	 */
	public Long getToCorrectionalStatusId() {
		return this.toCorrectionalStatusId;
	}

	/**
	 * Returns name of to correctional status from which to allow change.
	 * 
	 * @return name of to correctional status from which to allow change
	 */
	public String getToCorrectionalStatusName() {
		return this.toCorrectionalStatusName;
	}

	/**
	 * Returns ID of action.
	 * 
	 * @return ID of action
	 */
	public Long getActionId() {
		return this.actionId;
	}

	/**
	 * Returns name of action.
	 * 
	 * @return name of action
	 */
	public String getActionName() {
		return this.actionName;
	}
}