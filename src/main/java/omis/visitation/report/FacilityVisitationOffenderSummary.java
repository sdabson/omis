package omis.visitation.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Summary object for an offender within facility visitation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (March 3, 2017)
 * @since OMIS 3.0
 */
public class FacilityVisitationOffenderSummary {

	private final Long id;
	private final String offenderFirstName;
	private final String offenderLastName;
	private final String offenderMiddleName;
	private final Integer offenderNumber;
	private final String unitName;
	private final Long photoId;
	private final List<VisitSummary> visitSummaries;
	
	/**
	 * Instantiates an instance of facility visitation offender summary with
	 * the specified name and summaries.
	 * 
	 * @param id id
	 * @param offenderFirstName offender first name
	 * @param offenderLastName offender last name
	 * @param offenderMiddleName offender middle name
	 * @param offenderNumber offender number
	 * @param unitName unit name
	 * @param visitSummaries visit summaries
	 */
	public FacilityVisitationOffenderSummary(final Long id,
			final String offenderFirstName,
			final String offenderLastName, final String offenderMiddleName,
			final Integer offenderNumber, final String unitName,
			final Long photoId, final List<VisitSummary> visitSummaries) {
		this.id = id;
		this.offenderFirstName = offenderFirstName;
		this.offenderLastName = offenderLastName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.unitName = unitName;
		this.photoId = photoId;
		this.visitSummaries = visitSummaries;
	}
	
	/**
	 * Instantiates an instance of facility visitation offender summary.
	 * 
	 * @param id id
	 * @param offenderFirstName offender first name
	 * @param offenderLastName offender last name
	 * @param offenderMiddleName offender middle name
	 * @param offenderNumber offender number
	 * @param unitName unit name
	 */
	public FacilityVisitationOffenderSummary(final Long id,
			final String offenderFirstName,
			final String offenderLastName, final String offenderMiddleName,
			final Integer offenderNumber, final String unitName,
			final Long photoId) {
		this.id = id;
		this.offenderFirstName = offenderFirstName;
		this.offenderLastName = offenderLastName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.unitName = unitName;
		this.photoId = photoId;
		this.visitSummaries = new ArrayList<VisitSummary>();
	}

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Returns the offender first name.
	 * 
	 * @return offender for name
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}

	/**
	 * Returns the offender last name.
	 * 
	 * @return offender last name
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	/**
	 * Returns the offender middle name.
	 * 
	 * @return offender middle name
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}

	/**
	 * Returns the offender number.
	 * 
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}

	/**
	 * Returns the unit name.
	 * 
	 * @return unit name
	 */
	public String getUnitName() {
		return this.unitName;
	}
	
	/**
	 * Returns the photo id.
	 * 
	 * @return photo id
	 */
	public Long getPhotoId() {
		return this.photoId;
	}

	/**
	 * Returns the visit summaries.
	 * 
	 * @return visit summaries
	 */
	public List<VisitSummary> getVisitSummaries() {
		return this.visitSummaries;
	}
	
}