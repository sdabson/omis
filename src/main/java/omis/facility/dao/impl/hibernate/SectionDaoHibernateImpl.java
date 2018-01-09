/**
 * 
 */
package omis.facility.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.facility.dao.SectionDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;

import org.hibernate.SessionFactory;

/**
 * @author Joel Norris 
 * @author Josh Divine
 * @version 0.1.0 (Aug 2, 2017)
 * @since OMIS 3.0
 */
public class SectionDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Section> 
	implements SectionDao {

	/* Query names. */
	
	private static final String FIND_SECTIONS_BY_FACILITY_QUERY_NAME 
		= "findSectionsByFacility";
	
	private static final String FIND_SECTIONS_BY_COMPLEX_QUERY_NAME
		= "findSectionsByComplex";
	
	private static final String FIND_SECTIONS_BY_UNIT_QUERY_NAME
		= "findSectionsByUnit";
	
	private static final String FIND_SECTIONS_BY_LEVEL_QUERY_NAME
		= "findSectionsByLevel";
	
	private static final String FIND_SECTIONS_QUERY_NAME = "findSections";
	
	private static final String FIND_QUERY_NAME = "findSection";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findSectionExcluding";
	
	/* Parameter names. */
	
	private static final String FACILITY_PARAM_NAME = "facility";
	
	private static final String COMPLEX_PARAM_NAME = "complex";
	
	private static final String UNIT_PARAM_NAME = "unit";
	
	private static final String LEVEL_PARAM_NAME = "level";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_SECTION_PARAM_NAME = "excludedSection";
	
	/* Property names. */
	
	private static final String FACILITY_PROPERTY_NAME = "facility";
	
	private static final String COMPLEX_PROPERTY_NAME = "complex";
	
	private static final String UNIT_PROPERTY_NAME = "unit";
	
	private static final String LEVEL_PROPERTY_NAME = "level";
	
	/**
	 * Instantiates a data access object for section with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SectionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Section> findByFacility(final Facility facility) {
		@SuppressWarnings("unchecked")
		List<Section> sections = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_SECTIONS_BY_FACILITY_QUERY_NAME)
				.setEntity(FACILITY_PARAM_NAME, facility)
				.list();
		return sections;
	}

	/** {@inheritDoc} */
	@Override
	public List<Section> findByComplex(final Complex complex) {
		@SuppressWarnings("unchecked")
		List<Section> sections = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_SECTIONS_BY_COMPLEX_QUERY_NAME)
				.setEntity(COMPLEX_PARAM_NAME, complex)
				.list();
		return sections;
	}

	/** {@inheritDoc} */
	@Override
	public List<Section> findByUnit(final Unit unit) {
		@SuppressWarnings("unchecked")
		List<Section> sections = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_SECTIONS_BY_UNIT_QUERY_NAME)
				.setEntity(UNIT_PARAM_NAME, unit)
				.list();
		return sections;
	}

	/** {@inheritDoc} */
	@Override
	public List<Section> findByLevel(final Level level) {
		@SuppressWarnings("unchecked")
		List<Section> sections = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_SECTIONS_BY_LEVEL_QUERY_NAME)
				.setEntity(LEVEL_PARAM_NAME, level)
				.list();
		return sections;
	}

	/** {@inheritDoc} */
	@Override
	public List<Section> findSections(final Facility facility, 
			final Complex complex, final Unit unit, final Level level) {
		@SuppressWarnings("unchecked")
		List<Section> sections = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SECTIONS_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME, facility, 
						this.getEntityPropertyType(FACILITY_PROPERTY_NAME))
				.setParameter(COMPLEX_PARAM_NAME, complex, 
						this.getEntityPropertyType(COMPLEX_PROPERTY_NAME))
				.setParameter(UNIT_PARAM_NAME, unit, 
						this.getEntityPropertyType(UNIT_PROPERTY_NAME))
				.setParameter(LEVEL_PARAM_NAME, level, 
						this.getEntityPropertyType(LEVEL_PROPERTY_NAME))
				.list();
		return sections;
	}

	/** {@inheritDoc} */
	@Override
	public Section find(final String name, final Facility facility) {
		Section section = (Section) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.uniqueResult();
		return section;
	}

	/** {@inheritDoc} */
	@Override
	public Section findExcluding(final String name, final Facility facility, 
			final Section excludedSection) {
		Section section = (Section) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.setParameter(EXCLUDED_SECTION_PARAM_NAME, excludedSection)
				.uniqueResult();
		return section;
	}	
}