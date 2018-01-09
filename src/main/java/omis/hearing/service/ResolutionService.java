package omis.hearing.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.HearingStatus;
import omis.hearing.domain.HearingStatusCategory;
import omis.hearing.domain.ImposedSanction;
import omis.hearing.domain.Infraction;
import omis.hearing.domain.component.Resolution;
import omis.offender.domain.Offender;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.DisciplinaryCodeViolation;

/**
 * ResolutionService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 18, 2017)
 *@since OMIS 3.0
 *
 */
public interface ResolutionService {
	
	/**
	 * Creates a new Infraction with the specified properties
	 * @param hearing - Hearing
	 * @param conditionViolation - ConditionViolation
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation
	 * @param resolution - Resolution
	 * @return Newly created Infraction
	 * @throws DuplicateEntityFoundException - When an Infraction already exists
	 * with given Hearing, ConditionViolation, and DisciplinaryCodeViolation.
	 * @throws DuplicateEntityFoundException
	 */
	public Infraction createInfraction(Hearing hearing,
			ConditionViolation conditionViolation,
			DisciplinaryCodeViolation disciplinaryCodeViolation,
			Resolution resolution) throws DuplicateEntityFoundException;
	
	/**
	 * Updates an Infraction with the specified properties
	 * @param infraction - Infraction to update
	 * @param conditionViolation - ConditionViolation
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation
	 * @param resolution - Resolution
	 * @return Updated Infraction
	 * @throws DuplicateEntityFoundException - When an Infraction already exists
	 * with given Hearing, ConditionViolation, and DisciplinaryCodeViolation.
	 */
	public Infraction updateInfraction(Infraction infraction,
			ConditionViolation conditionViolation,
			DisciplinaryCodeViolation disciplinaryCodeViolation,
			Resolution resolution) throws DuplicateEntityFoundException;
	
	/**
	 * Removes an Infraction
	 * @param infraction - Infraction to remove
	 */
	public void removeInfraction(Infraction infraction);
	
	/**
	 * Creates a HearingStatus with the specified properties
	 * @param hearing - Hearing
	 * @param description - String
	 * @param date - Date
	 * @param category - HearingStatusCategory
	 * @return Newly Created HearingStatus
	 * @throws DuplicateEntityFoundException - When a HearingStatus already
	 * exists with specified Date and Category for given Hearing
	 */
	public HearingStatus createHearingStatus(Hearing hearing, String description,
			Date date, HearingStatusCategory category)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a HearingStatus with the specified properties
	 * @param hearingStatus - HearingStatus to update
	 * @param description - String
	 * @param date - Date
	 * @param category - HearingStatusCategory
	 * @return Updated HearingStatus
	 * @throws DuplicateEntityFoundException - When a HearingStatus already
	 * exists with specified Date and Category for given Hearing
	 */
	public HearingStatus updateHearingStatus(HearingStatus hearingStatus,
			String description, Date date,
			HearingStatusCategory category)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a HearingStatus
	 * @param hearingStatus - HearingStatus to remove
	 */
	public void removeHearingStatus(HearingStatus hearingStatus);
	
	/**
	 * Creates an ImposedSanction with the specified properties
	 * @param infraction - Infraction
	 * @param offender - Offender
	 * @param description - String
	 * @return Newly created ImposedSanction
	 * @throws DuplicateEntityFoundException - When an ImposedSanction already
	 * exists with given Infraction and Description
	 */
	public ImposedSanction createImposedSanction(Infraction infraction,
			Offender offender, String description)
			throws DuplicateEntityFoundException;
	
	/**
	 * Updates an ImposedSanction with the specified properties
	 * @param imposedSanction - ImposedSanction to update
	 * @param offender - Offender
	 * @param description - String
	 * @return Updated ImposedSanction
	 * @throws DuplicateEntityFoundException - When an ImposedSanction already
	 * exists with given Infraction and Description
	 */
	public ImposedSanction updateImposedSanction(ImposedSanction imposedSanction,
			Offender offender, String description)
			throws DuplicateEntityFoundException;
	
	/**
	 * Removes an ImposedSanction
	 * @param imposedSanction - ImposedSanction to remove
	 */
	public void removeImposedSanction(ImposedSanction imposedSanction);
	
	/**
	 * Returns a list of Infractions by specified Hearing
	 * @param hearing - Hearing
	 * @return List of Infractions by specified Hearing
	 */
	public List<Infraction> findInfractionsByHearing(Hearing hearing);
	
	/**
	 * Returns an ImposedSanction found by specified Infraction
	 * @param infraction - Infraction
	 * @return ImposedSanction found by specified Infraction
	 */
	public ImposedSanction findImposedSanctionByInfraction(
			Infraction infraction);
	
	/**
	 * Returns a ConditionViolation with the specified ID
	 * @param id - Long
	 * @return ConditionViolation with specified ID
	 */
	public ConditionViolation findConditionViolationById(Long id);
	
	/**
	 * Returns a DisciplinaryCodeViolation with the specified ID
	 * @param id - Long
	 * @return DisciplinaryCodeViolation with specified ID
	 */
	public DisciplinaryCodeViolation findDisciplinaryCodeViolationById(Long id);
	
}
