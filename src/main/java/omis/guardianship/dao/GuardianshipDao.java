package omis.guardianship.dao;

import java.util.Date;

import omis.dao.GenericDao;
import omis.guardianship.domain.Guardianship;
import omis.relationship.domain.Relationship;

/**
 * Guardianship DAO.
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.1 (June 8, 2013)
 * @since OMIS 3.0
 */
public interface GuardianshipDao extends GenericDao<Guardianship> {
	/**
	 * Return a list of all guardians for the specified dependent.
	 * 
	 * @param relationship relationship
	 * @param startDate start date
	 * @param endDate end date
	 * @return guardianship guardianship
	 */
	Guardianship findGuardianship(Relationship relationship, Date startDate, 
		Date endDate);
}
