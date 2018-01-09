/**
 * 
 */
package omis.facility.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.facility.dao.LevelDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;

import org.hibernate.SessionFactory;

/**
 * Level data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.3 (Aug 2, 2017)
 * @since OMIS 3.0
 */
public class LevelDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Level> 
	implements LevelDao  {

	/* Query names. */
	
	private static final String FIND_LEVELS_BY_FACILITY_QUERY_NAME 
		= "findLevelsByFacility";
	
	private static final String FIND_LEVELS_BY_COMPLEX_QUERY_NAME
		= "findLevelsByComplex";
	
	private static final String FIND_LEVELS_BY_UNIT_QUERY_NAME
		= "findLevelsByUnit";
	
	private static final String FIND_LEVELS_BY_SECTION_QUERY_NAME
		= "findLevelsBySection";
	
	private static final String FIND_LEVELS_QUERY_NAME = "findLevels";
	
	private static final String FIND_QUERY_NAME = "findLevel";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findLevelExcluding";
	
	/* Parameter names. */
	
	private static final String FACILITY_PARAM_NAME = "facility";
	
	private static final String COMPLEX_PARAM_NAME = "complex";
	
	private static final String UNIT_PARAM_NAME = "unit";
	
	private static final String SECTION_PARAM_NAME = "section";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_LEVEL_PARAM_NAME = "excludedLevel";
	/* Property names. */
	
	private static final String FACILITY_PROPERTY_NAME = "facility";
	
	private static final String COMPLEX_PROPERTY_NAME = "complex";
	
	private static final String UNIT_PROPERTY_NAME = "unit";
	
	private static final String SECTION_PROPERTY_NAME = "section";
	
	/**
	 * Instantiates a data access object for level with the specified session
	 * factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public LevelDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Level> findByFacility(final Facility facility) {
		@SuppressWarnings("unchecked")
		List<Level> levels = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_LEVELS_BY_FACILITY_QUERY_NAME)
				.setEntity(FACILITY_PARAM_NAME, facility)
				.list();
		return levels;
	}

	/** {@inheritDoc} */
	@Override
	public List<Level> findByComplex(Complex complex) {
		@SuppressWarnings("unchecked")
		List<Level> levels = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_LEVELS_BY_COMPLEX_QUERY_NAME)
				.setEntity(COMPLEX_PARAM_NAME, complex)
				.list();
		return levels;
	}

	/** {@inheritDoc} */
	@Override
	public List<Level> findByUnit(Unit unit) {
		@SuppressWarnings("unchecked")
		List<Level> levels = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_LEVELS_BY_UNIT_QUERY_NAME)
				.setEntity(UNIT_PARAM_NAME, unit)
				.list();
		return levels;
	}

	/** {@inheritDoc} */
	@Override
	public List<Level> findBySection(Section section) {
		@SuppressWarnings("unchecked")
		List<Level> levels = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_LEVELS_BY_SECTION_QUERY_NAME)
				.setEntity(SECTION_PARAM_NAME, section)
				.list();
		return levels;
	}

	/** {@inheritDoc} */
	@Override
	public List<Level> findLevels(final Facility facility, 
			final Complex complex, final Unit unit, final Section section) {
		@SuppressWarnings("unchecked")
		List<Level> levels = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_LEVELS_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME,  facility, 
						this.getEntityPropertyType(FACILITY_PROPERTY_NAME))
				.setParameter(COMPLEX_PARAM_NAME, complex, 
						this.getEntityPropertyType(COMPLEX_PROPERTY_NAME))
				.setParameter(UNIT_PARAM_NAME, unit, 
						this.getEntityPropertyType(UNIT_PROPERTY_NAME))
				.setParameter(SECTION_PARAM_NAME, section, 
						this.getEntityPropertyType(SECTION_PROPERTY_NAME))		
				.list();
		return levels;
	}

	/** {@inheritDoc} */
	@Override
	public Level find(final String name, final Facility facility) {
		Level level = (Level) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.uniqueResult();
		return level;
	}

	/** {@inheritDoc} */
	@Override
	public Level findExcluding(final String name, final Facility facility, 
			final Level excludedLevel) {
		Level level = (Level) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.setParameter(EXCLUDED_LEVEL_PARAM_NAME, excludedLevel)
				.uniqueResult();
		return level;
	}	
}