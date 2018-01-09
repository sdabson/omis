/**
 * 
 */
package omis.facility.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.facility.dao.RoomDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Room;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;

import org.hibernate.SessionFactory;

/**
 * Room data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Feb 25, 2015)
 * @since OMIS 3.0
 */
public class RoomDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Room> 
	implements RoomDao {
	
	/* Query names. */
	
	private static final String FIND_ROOMS_BY_FACILITY_QUERY_NAME
		="findRoomsByFacility";
	
	private static final String FIND_ROOMS_BY_COMPLEX_QUERY_NAME
		="findRoomsByComplex";
	
	private static final String FIND_ROOMS_BY_UNIT_QUERY_NAME
		="findRoomsByUnit";
	
	private static final String FIND_ROOMS_BY_SECTION_QUERY_NAME
		="findRoomsBySection";
	
	private static final String FIND_ROOMS_BY_LEVEL_QUERY_NAME
		="findRoomsByLevel";
	
	private static final String FIND_ROOMS_QUERY_NAME = "findRooms";
	
	private static final String FIND_QUERY_NAME = "findRoom";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = "findRoomExcluding";
	
	/* Property names. */
	
	private static final String FACILITY_PROPERTY_NAME = "facility";
	
	private static final String COMPLEX_PROPERTY_NAME = "complex";
	
	private static final String UNIT_PROPERTY_NAME = "unit";
	
	private static final String SECTION_PROPERTY_NAME = "section";
	
	private static final String LEVEL_PROPERTY_NAME = "level";
	
	/* Parameter names. */
	
	private static final String FACILITY_PARAM_NAME = "facility";
	
	private static final String COMPLEX_PARAM_NAME = "complex";
	
	private static final String UNIT_PARAM_NAME = "unit";
	
	private static final String SECTION_PARAM_NAME = "section";
	
	private static final String LEVEL_PARAM_NAME = "level";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_ROOM_PARAM_NAME = "excludedRoom";
	
	/**
	 * Instantiates a data access object for room with the specified session
	 * factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public RoomDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer countBedsByRoom(final Room room) {
		Integer count = 0;
		count = (Integer) getSessionFactory().getCurrentSession()
				.getNamedQuery("countBedsByRoom")
				.setParameter("room", room)
				.uniqueResult();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public List<Room> findByFaciility(Facility facility) {
		@SuppressWarnings("unchecked")
		List<Room> rooms = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ROOMS_BY_FACILITY_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.list();
		return rooms;
	}

	/** {@inheritDoc} */
	@Override
	public List<Room> findByComplex(Complex complex) {
		@SuppressWarnings("unchecked")
		List<Room> rooms = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ROOMS_BY_COMPLEX_QUERY_NAME)
				.setParameter(COMPLEX_PARAM_NAME, complex)
				.list();
		return rooms;
	}

	/** {@inheritDoc} */
	@Override
	public List<Room> findByUnit(Unit unit) {
		@SuppressWarnings("unchecked")
		List<Room> rooms = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ROOMS_BY_UNIT_QUERY_NAME)
				.setParameter(UNIT_PARAM_NAME, unit)
				.list();
		return rooms;
	}

	/** {@inheritDoc} */
	@Override
	public List<Room> findBySection(Section section) {
		@SuppressWarnings("unchecked")
		List<Room> rooms = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ROOMS_BY_SECTION_QUERY_NAME)
				.setParameter(SECTION_PARAM_NAME, section)
				.list();
		return rooms;
	}

	/** {@inheritDoc} */
	@Override
	public List<Room> findByLevel(Level level) {
		@SuppressWarnings("unchecked")
		List<Room> rooms = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ROOMS_BY_LEVEL_QUERY_NAME)
				.setParameter(LEVEL_PARAM_NAME, level)
				.list();
		return rooms;
	}

	/** {@inheritDoc} */
	@Override
	public List<Room> findRooms(final Facility facility, final Complex complex, 
			final Unit unit, final Section section, final Level level) {
		@SuppressWarnings("unchecked")
		List<Room> rooms = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ROOMS_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME, facility,
						this.getEntityPropertyType(FACILITY_PROPERTY_NAME))
				.setParameter(COMPLEX_PARAM_NAME, complex,
						this.getEntityPropertyType(COMPLEX_PROPERTY_NAME))
				.setParameter(UNIT_PARAM_NAME, unit,
						this.getEntityPropertyType(UNIT_PROPERTY_NAME))
				.setParameter(SECTION_PARAM_NAME, section,
						this.getEntityPropertyType(SECTION_PROPERTY_NAME))
				.setParameter(LEVEL_PARAM_NAME, level,
						this.getEntityPropertyType(LEVEL_PROPERTY_NAME))
				.list();
		return rooms;
	}

	/** {@inheritDoc} */
	@Override
	public Room find(final String name, final Facility facility) {
		Room room = (Room) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.uniqueResult();
		return room;
	}

	/** {@inheritDoc} */
	@Override
	public Room findExcluding(final String name, final Facility facility, 
			final Room excludedRoom) {
		Room room = (Room) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.setParameter(EXCLUDED_ROOM_PARAM_NAME, excludedRoom)
				.uniqueResult();
		return room;
	}
}