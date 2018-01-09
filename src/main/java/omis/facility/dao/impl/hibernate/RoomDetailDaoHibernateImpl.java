/**
 * 
 */
package omis.facility.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.facility.dao.RoomDetailDao;
import omis.facility.domain.RoomDetail;

import org.hibernate.SessionFactory;

/**
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 11 2013)
 * @since OMIS 3.0
 */
public class RoomDetailDaoHibernateImpl 
	extends GenericHibernateDaoImpl<RoomDetail> 
	implements RoomDetailDao {

	/**
	 * Instantiates a data access object for room detail with the specified
	 * sesison factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public RoomDetailDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
}