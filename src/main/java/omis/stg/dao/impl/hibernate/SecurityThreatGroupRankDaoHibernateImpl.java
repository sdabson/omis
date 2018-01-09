package omis.stg.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.stg.dao.SecurityThreatGroupRankDao;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupRank;

/**
 * Hibernate implementation of data access object for security threat group
 * ranks.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupRankDaoHibernateImpl
		extends GenericHibernateDaoImpl<SecurityThreatGroupRank>
		implements SecurityThreatGroupRankDao {

	private static final String FIND_QUERY_NAME = "findSecurityThreatGroupRank";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findSecurityThreatGroupRankExcluding";
	
	private static final String FIND_RANKS_BY_GROUP_QUERY_NAME 
		= "findSecurityThreatGroupRanksByGroup";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_RANK_PARAM_NAME = "excludedRank";
	
	private static final String SECURITY_THREAT_GROUP_PARAM_NAME 
		= "securityThreatGroup";
	
	/**
	 * Instantiates an Hibernate implementation of security threat group
	 * ranks.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SecurityThreatGroupRankDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupRank find(String name, 
			SecurityThreatGroup securityThreatGroup) {
		SecurityThreatGroupRank rank = (SecurityThreatGroupRank) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(SECURITY_THREAT_GROUP_PARAM_NAME, 
						securityThreatGroup)
				.uniqueResult();
		return rank;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupRank findExcluding(String name, 
			SecurityThreatGroup securityThreatGroup, 
			SecurityThreatGroupRank securityThreatGroupRank) {
		SecurityThreatGroupRank rank = (SecurityThreatGroupRank) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(SECURITY_THREAT_GROUP_PARAM_NAME, 
						securityThreatGroup)
				.setParameter(EXCLUDED_RANK_PARAM_NAME, securityThreatGroupRank)
				.uniqueResult();
		return rank;
	}

	@Override
	public List<SecurityThreatGroupRank> findRanksByGroup(
			SecurityThreatGroup securityThreatGroup) {
		@SuppressWarnings("unchecked")
		List<SecurityThreatGroupRank> ranks = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_RANKS_BY_GROUP_QUERY_NAME)
				.setParameter(SECURITY_THREAT_GROUP_PARAM_NAME, 
						securityThreatGroup)
				.list();
		return ranks;
	}
}