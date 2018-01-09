package omis.courtcase.dao;

import java.util.List;

import omis.courtcase.domain.CourtCase;
import omis.dao.GenericDao;
import omis.docket.domain.Docket;
import omis.person.domain.Person;

/**
 * Data access object for court cases.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.2 (Aug 24, 2017)
 * @since OMIS 3.0
 */
public interface CourtCaseDao
		extends GenericDao<CourtCase> {

	/**
	 * Returns the court cases for the specified defendant.
	 * 
	 * @param defendant defendant the court cases of which to return
	 * @return court cases for specified defendant
	 */
	List<CourtCase> findByDefendant(Person defendant);
	
	/**
	 * Returns the court case with the specified defendant and docket number.
	 * 
	 * @param defendant offender
	 * @param docket docket number
	 * @return court case
	 */
	CourtCase find(Person defendant, String docket);
	
	/**
	 * Returns the court case with the specified defendant and docket number
	 * unless that court case is the one specified.
	 * 
	 * @param defendant offender
	 * @param docket docket number
	 * @param courtCase court case to exclude
	 * @return court case
	 */
	CourtCase findExcluding(Person defendant, String docket, 
			CourtCase courtCase);
	
	/**
	 * Returns the court case for the specified docket.
	 * 
	 * @param docket docket
	 * @return court case
	 */
	CourtCase findByDocket(Docket docket);
}