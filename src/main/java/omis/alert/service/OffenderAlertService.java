package omis.alert.service;

import java.util.Date;
import java.util.List;

import omis.alert.domain.OffenderAlert;
import omis.alert.domain.component.AlertResolution;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/**
 * Service for alerts.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.1 (September 13, 2012)
 * @since OMIS 3.0
 */
public interface OffenderAlertService {
	
	/**
	 * Returns alerts for the offender.
	 * 
	 * @param offender offender the alerts to return
	 * @return alerts for offender
	 */
	List<OffenderAlert> findByOffender(Offender offender);
	
	/**
	 * Saves an alert.
	 * 
	 * @param offender offender
	 * @param expireDate expire date
	 * @param description description
	 * @param resolution resolution
	 * @return saved alert
	 * @throws DuplicateEntityFoundException if the alert already exists
	 */
	OffenderAlert save(
			Offender offender, Date expireDate, String description,
			AlertResolution resolution) throws DuplicateEntityFoundException;
	
	/**
	 * Updates an alert.
	 * 
	 * @param offenderAlert offender alert
	 * @param expireDate expire date
	 * @param description description
	 * @param resolution resolution
	 * @return updated alert
	 * @throws DuplicateEntityFoundException if the alert already exists
	 */
	OffenderAlert update(
			OffenderAlert offenderAlert, Date expireDate, String description,
			AlertResolution resolution) throws DuplicateEntityFoundException;
	
	/**
	 * Removes the alert.
	 * 
	 * @param alert alert to remove
	 */
	void remove(OffenderAlert alert);
}