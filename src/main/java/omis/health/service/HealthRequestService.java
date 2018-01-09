package omis.health.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.ProviderLevel;
import omis.health.exception.HealthRequestException;
import omis.offender.domain.Offender;

/**
 * Service for health requests.
 *
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.0 (Apr 13, 2014)
 * @since OMIS 3.0
 */
public interface HealthRequestService {

	/**
	 * Creates open health request.
	 *
	 * @param offender offender
	 * @param facility facility
	 * @param date date
	 * @param labsRequired whether labs are required
	 * @param category category
	 * @param asap whether appointment is to be scheduled ASAP
	 * @param providerLevel provider level
	 * @param notes notes
	 * @return health request
	 * @throws DuplicateEntityFoundException if health request exists
	 */
	HealthRequest request(Offender offender, Facility facility,
			Date date, Boolean labsRequired, HealthRequestCategory category,
			boolean asap, ProviderLevel providerLevel, String notes)
					throws DuplicateEntityFoundException;

	/**
	 * Creates an open request for lab work.
	 *
	 * @param offender offender
	 * @param facility facility
	 * @param date date
	 * @param asap whether appointment is to be scheduled ASAP
	 * @param notes notes
	 * @return request for lab work
	 * @throws DuplicateEntityFoundException if the request for lab work exists
	 */
	HealthRequest requestLabWork(Offender offender, Facility facility,
			Date date, boolean asap, String notes)
					throws DuplicateEntityFoundException;

	/**
	 * Updates a health request.
	 *
	 * @param healthRequest health request
	 * @param category category
	 * @param labsRequired labs required
	 * @param asap whether the referral is to be scheduled ASAP
	 * @param providerLevel provider level
	 * @param notes notes
	 * @return updated health request
	 * @throws DuplicateEntityFoundException if the request already exists
	 * @throws HealthRequestException if the request is not open
	 */
	HealthRequest update(HealthRequest healthRequest,
			HealthRequestCategory category, Boolean labsRequired,
			boolean asap, ProviderLevel providerLevel, String notes)
					throws DuplicateEntityFoundException,
						HealthRequestException;

	/** Cancels a health request.
	 * @param healthRequest healthRequest.
	 * @return healthRequest health request. */
	HealthRequest cancel(HealthRequest healthRequest);

	/**
	 * Returns provider levels.
	 *
	 * @return provider levels
	 */
	List<ProviderLevel> findProviderLevels();
	
	/**
	 * Removes the health request.
	 * 
	 * @param healthRequest health request
	 */
	void remove(HealthRequest healthRequest);
}