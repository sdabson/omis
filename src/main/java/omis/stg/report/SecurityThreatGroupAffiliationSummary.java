package omis.stg.report;

import java.io.Serializable;
import java.util.Date;

import omis.offender.domain.Offender;
import omis.person.domain.PersonName;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupActivityLevel;
import omis.stg.domain.SecurityThreatGroupAffiliation;
import omis.stg.domain.SecurityThreatGroupChapter;
import omis.stg.domain.SecurityThreatGroupRank;

/**
 * Summary of security threat group affiliation.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 16, 2015)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupAffiliationSummary
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final String offenderSuffix;
	
	private final Integer offenderNumber;
	
	private final Date startDate;
	
	private final Date endDate;
	
	private final Boolean active;
	
	private final String cityName;
	
	private final String stateName;
	
	private final String moniker;
	
	private final String comment;
	
	private final String groupName;
	
	private final String activityLevelName;
	
	private final String chapterName;
	
	private final String rankName;
	
	/**
	 * Summary of security threat group affiliation.
	 * 
	 * @param id affiliation id
	 * @param affiliation affiliation
	 * @param offender offender
	 * @param offenderName name of offender
	 * @param city city
	 * @param state State
	 * @param group group
	 * @param activityLevel activityLevel
	 * @param chapter chapter
	 * @param rank rank
	 * @param effectiveDate effective date
	 */
	public SecurityThreatGroupAffiliationSummary(
			final Long id,
			final SecurityThreatGroupAffiliation affiliation,
			final Offender offender,
			final PersonName offenderName,
			final City city,
			final State state,
			final SecurityThreatGroup group,
			final SecurityThreatGroupActivityLevel activityLevel,
			final SecurityThreatGroupChapter chapter,
			final SecurityThreatGroupRank rank,
			final Date effectiveDate) {
		this.id = affiliation.getId();
		this.offenderLastName = offenderName.getLastName();
		this.offenderFirstName = offenderName.getFirstName();
		this.offenderMiddleName = offenderName.getMiddleName();
		this.offenderSuffix = offenderName.getSuffix();
		this.offenderNumber = offender.getOffenderNumber();
		if (affiliation.getDateRange() != null) {
			this.startDate = affiliation.getDateRange().getStartDate();
			this.endDate = affiliation.getDateRange().getEndDate();
			if (effectiveDate != null) {
				this.active = affiliation.getDateRange()
						.isActive(effectiveDate);
			} else {
				this.active = null;
			}
		} else {
			this.startDate = null;
			this.endDate = null;
			if (effectiveDate != null) {
				this.active = true;
			} else {
				this.active = null;
			}
		}
		if (city != null) {
			this.cityName = city.getName();
		} else {
			this.cityName = null;
		}
		if (state != null) {
			this.stateName = state.getName();
		} else {
			this.stateName = null;
		}
		this.moniker = affiliation.getMoniker();
		this.comment = affiliation.getComment();
		if (group != null) {
			this.groupName = group.getName();
		} else {
			this.groupName = null;
		}
		if (activityLevel != null) {
			this.activityLevelName = activityLevel.getName();
		} else {
			this.activityLevelName = null;
		}
		if (chapter != null) {
			this.chapterName = chapter.getName();
		} else {
			this.chapterName = null;
		}
		if (rank != null) {
			this.rankName = rank.getName();
		} else {
			this.rankName = null;
		}
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

	/**
	 * Returns start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
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
	 * Returns whether affiliation is considered active.
	 * 
	 * <p>This method returns {@code null} if whether or not the affiliation
	 * is active cannot be determined or is not relevant.
	 * 
	 * @return whether affiliation is considered active
	 */
	public Boolean getActive() {
		return this.active;
	}

	/**
	 * Returns name of city.
	 * 
	 * @return name of city
	 */
	public String getCityName() {
		return this.cityName;
	}

	/**
	 * Returns name of State.
	 * 
	 * @return name of State
	 */
	public String getStateName() {
		return this.stateName;
	}

	/**
	 * Returns moniker.
	 * 
	 * @return moniker
	 */
	public String getMoniker() {
		return this.moniker;
	}

	/**
	 * Returns comment.
	 * 
	 * @return comment
	 */
	public String getComment() {
		return this.comment;
	}
	
	/**
	 * Returns name of group.
	 * 
	 * @return name of group
	 */
	public String getGroupName() {
		return this.groupName;
	}

	/**
	 * Returns name of activity level.
	 * 
	 * @return name of activity level
	 */
	public String getActivityLevelName() {
		return this.activityLevelName;
	}

	/**
	 * Returns name of chapter.
	 * 
	 * @return name of chapter
	 */
	public String getChapterName() {
		return this.chapterName;
	}

	/**
	 * Returns name of rank.
	 * 
	 * @return name of rank
	 */
	public String getRankName() {
		return this.rankName;
	}
}