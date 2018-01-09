package omis.condition.dao;

import java.util.Date;
import java.util.List;

import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementCategory;
import omis.dao.GenericDao;
import omis.docket.domain.Docket;
import omis.offender.domain.Offender;

/**
 * Agreement Dao.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Wahl
 * @version 0.1.2 (Nov 27, 2017)
 * @since OMIS 3.0
 */
public interface AgreementDao extends GenericDao<Agreement> {

	/**
	 * Returns Agreement by the specified properties.
	 * @param offender offender we want Agreements from
	 * @param startDate startDate we want Agreements from
	 * @param endDate endDate we want Agreements from
	 * @param description - String
	 * @param category - Agreement Category
	 * @return Agreement with the specified properties or
	 * {@code null} if not found.
	 */
	Agreement find(Offender offender, Date startDate, Date endDate,
			String description, AgreementCategory category);
	
	/**
	 * Returns Agreements by an Offender.
	 * @param offender offender we want Agreements from
	 * @return Agreements relevant to offender, startDate, 
	 * endDate, or
	 * {@code null} if not found.
	 */
	List<Agreement> findByOffender(Offender offender);
	
	/**
	 * Returns Agreement with the specified properties excluding specific
	 * agreement.
	 * @param agreement agreement that we want to exclude from resultset
	 * @param startDate startDate we want Agreements from
	 * @param endDate endDate we want Agreements from
	 * @param description - String
	 * @param category - Agreement Category
	 * @return Agreement  with the specified properties excluding specified 
	 * Agreement, or {@code null} if not found.
	 */
	Agreement findExcluding(Agreement agreement, Date startDate, Date endDate,
			String description, AgreementCategory category);
	
	/**
	 * Returns a list of Agreements found by specified Docket.
	 * @param docket - Docket
	 * @return List of Agreements found by specified Docket
	 */
	List<Agreement> findByDocket(Docket docket);
}
