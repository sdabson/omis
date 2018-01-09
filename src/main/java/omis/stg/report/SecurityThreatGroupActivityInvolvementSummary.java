package omis.stg.report;

import java.io.Serializable;

import omis.offender.domain.Offender;
import omis.person.domain.PersonName;
import omis.stg.domain.SecurityThreatGroupActivityInvolvement;

/**
 * Summary of security threat group activity involvement.
 *
 * @author Trevor Isles
 * @version 0.0.1 (Dec 5, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupActivityInvolvementSummary 
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final String offenderSuffix;
	
	private final Integer offenderNumber;
	
	private final String narrative;
	
	/**
	 * Summary of security threat group affiliation.
	 * 
	 * @param id - involvement id
	 * @param offenderName - name of offender
	 * @param offenderNumber - offender's id number
	 * @param narrative - narrative of the involvement
	 */
	public SecurityThreatGroupActivityInvolvementSummary(
			final Long id,
			final SecurityThreatGroupActivityInvolvement involvement,
			final Offender offender,
			final PersonName offenderName) {
		this.id = id;
		this.offenderLastName = offenderName.getLastName();
		this.offenderFirstName = offenderName.getFirstName();
		this.offenderMiddleName = offenderName.getMiddleName();
		this.offenderSuffix = offenderName.getSuffix();
		this.offenderNumber = offender.getOffenderNumber();
		if (involvement != null) {
			this.narrative = involvement.getNarrative();
		} else {
			this.narrative = null;
		}
	}
	
	/**
	 * Returns narrative
	 * 
	 * @return narrative
	 */
	public String getNarrative() {
		return this.narrative;
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
	 * Returns offender last name.
	 * 
	 * @return offender last name
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	/**
	 * Returns offender first name.
	 * 
	 * @return offender first name
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}

	/**
	 * Returns offender middle name.
	 * 
	 * @return offender middle name
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}

	/**
	 * Returns offender suffix.
	 * 
	 * @return offender suffix
	 */
	public String getOffenderSuffix() {
		return this.offenderSuffix;
	}

	/**
	 * Returns offender number.
	 * 
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
}
