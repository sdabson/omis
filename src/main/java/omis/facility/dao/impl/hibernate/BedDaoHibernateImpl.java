/**
 * 
 */
package omis.facility.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.facility.dao.BedDao;
import omis.facility.domain.Bed;
import omis.facility.domain.Room;

import org.hibernate.SessionFactory;

/**
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.1  (Aug 2, 2017)
 * @since OMIS 3.0
 */
public class BedDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Bed> 
	implements BedDao {

	/* Query Names */
	
	private static final String FIND_BEDS_BY_ROOM_QUERY_NAME = "findBedsByRoom";
	
	private static final String FIND_QUERY_NAME = "findBed";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = "findBedExcluding";
	
	/* Parameter names */
	
	private static final String ROOM_PARAM_NAME = "room";
	
	private static final String NUMBER_PARAM_NAME = "number";
	
	private static final String EXCLUDED_BED_PARAM_NAME = "excludedBed";
	
	/* Constructors */
	/**
	 * Instantiates a data access object for bed with the specified session
	 * factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public BedDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Bed> findByRoom(final Room room) {
		@SuppressWarnings("unchecked")
		List<Bed> beds = getSessionFactory().getCurrentSession()
		.getNamedQuery(FIND_BEDS_BY_ROOM_QUERY_NAME)
		.setParameter(ROOM_PARAM_NAME, room)
		.list();
		return beds;
	}

	/** {@inheritDoc} */
	@Override
	public Bed find(Room room, Integer number) {
		Bed bed = (Bed) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(ROOM_PARAM_NAME, room)
				.setParameter(NUMBER_PARAM_NAME, number)
				.uniqueResult();
		return bed;
	}

	/** {@inheritDoc} */
	@Override
	public Bed findExcluding(Room room, Integer number, Bed excludedBed) {
		Bed bed = (Bed) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(ROOM_PARAM_NAME, room)
				.setParameter(NUMBER_PARAM_NAME, number)
				.setParameter(EXCLUDED_BED_PARAM_NAME, excludedBed)
				.uniqueResult();
		return bed;
	}
}