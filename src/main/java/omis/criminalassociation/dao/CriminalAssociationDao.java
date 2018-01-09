package omis.criminalassociation.dao;

import java.util.List;

import omis.criminalassociation.domain.CriminalAssociation;
import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.relationship.domain.Relationship;

/**
 * Data access object for criminal association.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.1 (Apr, 14, 2015)
 * @since OMIS 3.0
 */
public interface CriminalAssociationDao  
	extends GenericDao<CriminalAssociation> {

	/**
	 * Returns all of the criminal associations for the specified offender.
	 * 
	 * 
	 * @param offender offender
	 * @return list of criminal associations
	 */
	List<CriminalAssociation> findByOffender(Offender offender);

	/**
	 * Returns all of the criminal associations for the specified offender
	 * as the other offender.
	 * 
	 * @param offender offender
	 * @return list of criminal associations
	 */
	List<CriminalAssociation> findByOtherOffender(Offender offender);

	/**
	 * Returns the criminal association that contains all the specified 
	 * properties of an association's business key. 
	 * If no criminal association is found, 
	 * returns {@code null}.
	 * 
	 * @param relationship relationship
	 * @param dateRange date range
	 * @return criminal association
	 */
	CriminalAssociation findCriminalAssociation(Relationship relationship, 
		DateRange dateRange);
}