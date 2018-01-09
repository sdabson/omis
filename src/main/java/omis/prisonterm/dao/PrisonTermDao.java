package omis.prisonterm.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermStatus;

/**
 * Prison term data access object.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (March 21, 2017)
 * @since OMIS 3.0
 */

public interface PrisonTermDao extends GenericDao<PrisonTerm> {
	
	/**
	 * Finds prison terms excluding the specified term
	 * 
	 * @param offender - offender
	 * @param actionDate - date
	 * @param status - status
	 * @param excludedPrisonTerm - prison term to exclude
	 * @return returns the prison term with the specified properties or 
	 * {@code null} if not found
	 */
	PrisonTerm findExcluding(Offender offender, Date actionDate, 
			PrisonTermStatus status, PrisonTerm excludedPrisonTerm);
	
	/**
	 * Finds a prison term.
	 * 
	 * @param offender - offender
	 * @param actionDate - date
	 * @return Finds a prison term.
	 */
	PrisonTerm find(Offender offender, Date actionDate, PrisonTermStatus status);
	
	/**
	 * Returns a list of prison terms for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of prison terms by offender
	 */
	List<PrisonTerm> findByOffender(Offender offender);
	
	/**
	 * Returns the active prison term for the specified offender.
	 * 
	 * @param offender offender
	 * @return active prison term by offender
	 */
	PrisonTerm findExcludingActiveByOffender(Offender offender, 
			PrisonTerm excludedPrisonTerm);
}
