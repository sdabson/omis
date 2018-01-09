package omis.health.report;

import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.health.domain.HealthRequestCategory;
import omis.offender.domain.Offender;

/**
 * Service to report health requests.
 *
 * @author Stephen Abson
 * @version 0.1.0 (Apr 13, 2014)
 * @since OMIS 3.0
 */
public interface HealthRequestReportService {

	/**
	 * Returns open health request summaries by category for the facility.
	 *
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @param categories categories
	 * @return open requests by category for facility
	 */
	List<HealthRequestSummary> findOpenByCategory(Facility facility,
			Date effectiveDate,	HealthRequestCategory... categories);

	/**
	 * Returns open health request summaries for the facility.
	 *
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @return open requests for facility
	 */
	List<HealthRequestSummary> findOpen(Facility facility,
			Date effectiveDate);
	
	/**
	 * Returns resolved health request summaries for the facility by category.
	 * 
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @param categories categories
	 * @return resolved health request summaries for facility by category
	 */
	List<HealthRequestSummary> findResolvedByCategory(Facility facility,
			Date effectiveDate, HealthRequestCategory... categories);
	
	/**
	 * Returns resolved health request summaries for facility.
	 * 
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @return resolved health request summaries for facility
	 */
	List<HealthRequestSummary> findResolved(Facility facility,
			Date effectiveDate);
	
	/**
	 * Returns health request summaries by category for facility.
	 * 
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @param categories categories
	 * @return health request summaries by category
	 */
	List<HealthRequestSummary> findByCategory(
			Facility facility, Date effectiveDate,
			HealthRequestCategory... categories);
	
	/**
	 * Returns health request summaries for facility.
	 * 
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @return health request summaries for facility
	 */
	List<HealthRequestSummary> find(Facility facility, Date effectiveDate);

	/**
	 * Returns open health request summaries for offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return open health request summaries for offender
	 */
	List<HealthRequestSummary> findOpenByOffender(Offender offender,
			Date effectiveDate);
}