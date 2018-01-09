package omis.stg.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.stg.dao.SecurityThreatGroupChapterDao;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupChapter;

/**
 * Hibernate implementation of data access object for security threat group
 * chapters.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupChapterDaoHibernateImpl
		extends GenericHibernateDaoImpl<SecurityThreatGroupChapter>
		implements SecurityThreatGroupChapterDao {

	private static final String FIND_ALL_QUERY_NAME
		= "findSecurityThreatGroupChapters";
	
	private static final String FIND_BY_GROUP_QUERY_NAME
		= "findSecurityThreatGroupChaptersByGroup";
	
	private static final String FIND_QUERY_NAME
	= "findSecurityThreatGroupChapter";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
	= "findSecurityThreatGroupChapterExcluding";

	private static final String GROUP_PARAM_NAME = "securityThreatGroup";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_CHAPTER_PARAM_NAME = "excludedChapter";
	
	/**
	 * Instantiates an implementation of data access object for security
	 * threat groups.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SecurityThreatGroupChapterDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupChapter> findAll() {
		@SuppressWarnings("unchecked")
		List<SecurityThreatGroupChapter> chapters
			= this.getSessionFactory().getCurrentSession().getNamedQuery(
					FIND_ALL_QUERY_NAME).list();
		return chapters;
	}

	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupChapter> findChaptersByGroup(
			SecurityThreatGroup group) {
		@SuppressWarnings("unchecked")
		List<SecurityThreatGroupChapter> chapters = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_GROUP_QUERY_NAME)
				.setParameter(GROUP_PARAM_NAME, group)
				.list();
		return chapters;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupChapter find(String name, 
			SecurityThreatGroup securityThreatGroup) {
		SecurityThreatGroupChapter chapter = (SecurityThreatGroupChapter) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(GROUP_PARAM_NAME, securityThreatGroup)
				.uniqueResult();
		return chapter;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupChapter findExcluding(String name, 
			SecurityThreatGroup securityThreatGroup,
			SecurityThreatGroupChapter excludedChapter) {
		SecurityThreatGroupChapter chapter = (SecurityThreatGroupChapter) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(GROUP_PARAM_NAME, securityThreatGroup)
				.setParameter(EXCLUDED_CHAPTER_PARAM_NAME, excludedChapter)
				.uniqueResult();
		return chapter;
	}
	
}