package omis.bedplacement.dao;

import java.util.Date;
import java.util.List;

import omis.bedplacement.domain.BedPlacement;
import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.facility.domain.Bed;
import omis.facility.domain.Facility;
import omis.offender.domain.Offender;

/**
 * Data access object for bed placement.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Apr, 04 2013)
 * @since OMIS 3.0
 */
public interface BedPlacementDao extends GenericDao<BedPlacement> {

	/**
	 * Returns a list bed placements for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of bed placements
	 */
	List<BedPlacement> findBedPlacementByOffender(Offender offender);

	/**
	 * Returns a bed placement for the specified offender that is current
	 * for the specified date. If no current bed placement is found, 
	 * return is null.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return the bed placement / null
	 */
	BedPlacement findBedPlacementByOffenderOnDate(Offender offender,
			Date date);
	
	/**
	 * Returns a list of bed placements that are valid for the specified
	 * facility on the specified date.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return list of bed placements
	 */
	List<BedPlacement> findSpecificBedPlacementsByFacility(
			Facility facility, Date date);

	/**
	 * Return a list of bed placements that are planned during the date
	 * specified for the specified facility.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return list of bed placements
	 */
	List<BedPlacement> findSpecificPlannedBedPlacements(Facility facility,
			Date date);

	/**
	 * Returns a list of bed placements that are completed for the date
	 * specified in the facility specified.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return list of bed placements
	 */
	List<BedPlacement> findSpecificCompletedBedPlacements(Facility facility,
			Date date);

	/**
	 * Returns the bed placement for the specified offender with the specified
	 * date range.
	 * 
	 * @param offender offender
	 * @param dateRange date range
	 * @return matching bed placement
	 */
	BedPlacement find(Offender offender, DateRange dateRange);
	
	/**
	 * Returns the bed placement for the specified offender with the specified
	 * date range, unless that placement is the specified bed placement.
	 * 
	 * @param offender offender
	 * @param dateRange date range
	 * @param bedPlacement bed placement to exclude
	 * @return matching bed placement
	 */
	BedPlacement findExcluding(Offender offender, DateRange dateRange, 
			BedPlacement bedPlacement);

	/**
	 * Returns all confirmed bed placements that occur within the specified 
	 * date range for the specified offender.
	 * 
	 * @param dateRange date range
	 * @return list of bed placements
	 */
	List<BedPlacement> findWithinDateRange(Offender offender, 
			DateRange dateRange);

	/**
	 * Returns all confirmed bed placements that occur within the specified 
	 * date range for the specified offender, except the specified 
	 * bed placement.
	 * 
	 * @param offender offender
	 * @param dateRange date range
	 * @param bedPlacement bed placement
	 * @return list of bed placements
	 */
	List<BedPlacement> findWithinDateRangeExcluding(Offender offender,
			DateRange dateRange, BedPlacement bedPlacement);

	/**
	 * Returns the confirmed bed placement that occurs during the specified
	 * date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return confirmed bed placement
	 */
	BedPlacement findConfirmedBedPlacementByOffenderOnDate(Offender offender,
			Date date);

	/**
	 * Returns the bed placement for the specified bed on the specified date.
	 * 
	 * @param date date
	 * @param bed bed
	 * @param offender offender
	 * @return bed placement
	 */
	BedPlacement findConfirmedBedPlacementByBedOnDate(Date date, Bed bed, 
			Offender offender);

	/**
	 * Returns the bed placement for the specified bed on the specified date
	 * excluding the specified bed placement.
	 * 
	 * @param date date
	 * @param bed bed
	 * @param bedPlacement bed placement to exclude
	 * @return bed placement
	 */
	BedPlacement findConfirmedBedPlacementByBedOnDateExcluding(Date date, 
			Bed bed, BedPlacement bedPlacement);
}