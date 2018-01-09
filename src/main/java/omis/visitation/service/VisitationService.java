package omis.visitation.service;

import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.visitation.domain.VisitationAssociation;

/**
 * Visitation service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sep 18, 2014)
 * @since OMIS 3.0
 * @deprecated This is not used and should likely be removed.
 */
@Deprecated
public interface VisitationService {
	
	/**
	 * Approve the specified person to visit the specified offender as of the
	 * specified decision date.
	 * 
	 * @param offender offender
	 * @param visitor visitor
	 * @param decisionDate decision date
	 * @param dateRange date range
	 * @param money whether money is accepted or not
	 * @param nonContact whether a non contact order is in place
	 * @param courtOrder whether visitation is the result of a court order 
	 * @param specialVisit whether this association is subject to special
	 * visit authorization
	 * @return approved visitation association
	 * @throws DuplicateEntityFoundException thrown when a visitation 
	 * association is found with the specified start date and relationship
	 * participants
	 * @throws DateConflictException thrown when another visitation 
	 * association's date range overlaps the specified date range
	 */
	VisitationAssociation approve(Offender offender, Person visitor, 
			Date decisionDate, DateRange dateRange, Boolean money, 
			Boolean nonContact, Boolean courtOrder, Boolean specialVisit)
		throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Deny the specified person to visit the specified offender as of the
	 * specified decision date.
	 * 
	 * @param offender offender
	 * @param visitor visitor
	 * @param decisionDate decision date
	 * @param dateRange date range
	 * @param money whether money is accepted or not
	 * @param nonContact whether a non contact order is in place
	 * @param courtOrder whether visitation is the result of a court order 
	 * @param specialVisit whether this association is subject to special
	 * visit authorization
	 * @return denied visitation association
	 * @throws DuplicateEntityFoundException thrown when a visitation 
	 * association is found with the specified start date and relationship
	 * participants
	 * @throws DateConflictException thrown when another visitation 
	 * association's date range overlaps the specified date range
	 */
	VisitationAssociation deny(Offender offender, Person visitor, 
			Date decisionDate, DateRange dateRange, Boolean money, 
			Boolean nonContact, Boolean courtOrder, Boolean specialVisit)
		throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Updates the specified visitation association with the specified
	 * properties.
	 * 
	 * @param visitationAssociation visitation association
	 * @param decisionDate decision date
	 * @param dateRange date range
	 * @param money whether money is accepted or not
	 * @param nonContact whether a non contact order is in place
	 * @param courtOrder whether visitation is the result of a court order 
	 * @param specialVisit whether this association is subject to special
	 * visit authorization
	 * @param approved whether there is approval of visitation
	 * @return updated visitation association
	 * @throws DuplicateEntityFoundException thrown when a visitation 
	 * association is found with the specified start date and the specified
	 * visitation association's relationship
	 * @throws DateConflictException thrown when another visitation 
	 * association's date range overlaps the specified date range
	 */
	VisitationAssociation updateAssociation(
			VisitationAssociation visitationAssociation, Date decisionDate,
			DateRange dateRange, Boolean money, Boolean noncontact, 
			Boolean courtOrder, Boolean specialVisit, Boolean approved)
		throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Returns only visitation associations for the specified offender on the
	 * specified date that have a permanent approved status.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return list of offender's approved visitation associations
	 */
	List<VisitationAssociation> findApprovedAssociationsByOffender(
			Offender offender, Date date);
	
	/**
	 * Returns all visitation associations for the specified offender on the
	 * specified date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return list of offender's visitation associations
	 */
	List<VisitationAssociation> findAssociationsByOffender(Offender offender,
			Date date);
	
	/**
	 * Returns all visitation associations for the specified offender on the
	 * specified date that have the status of special visit.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return list of offender's visitation associations specified as special
	 * visit
	 */
	List<VisitationAssociation> findSpecialVisitAssociationsByOffender(
			Offender offender, Date date);
	
	/**
	 * Removes the specified visitation association.
	 * 
	 * @param visitationAssociation visitation association
	 */
	void remove(VisitationAssociation visitationAssociation);
	
	/**
	 * Returns a list of facilities.
	 * 
	 * @return list of facilities
	 */
	List<Facility> findFacilities();
	
	/**
	 * Ends the specified visitation association on the specified date.
	 * 
	 * @param visitationAssociation visitation association
	 * @param endDate end date
	 * @return ended visitation association
	 */
	VisitationAssociation endVisitationAssociaiton(
			VisitationAssociation visitationAssociation, Date endDate);
}