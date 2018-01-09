package omis.stg.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.stg.dao.SecurityThreatGroupAffiliationDao;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupAffiliation;

/**
 * Hibernate implementation of data access object for security threat group
 * affiliation.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupAffiliationDaoHibernateImpl
		extends GenericHibernateDaoImpl<SecurityThreatGroupAffiliation>
		implements SecurityThreatGroupAffiliationDao {

	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findSecurityThreatGroupAffiliationsByOffender";
	
	private static final String FIND_QUERY_NAME
		= "findSecurityThreatGroupAffiliation";

	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findSecurityThreatGroupAffiliationExcluding";
	
	private static final String OFFENDER_PARAM_NAME = "offender";

	private static final String GROUP_PARAM_NAME = "group";

	private static final String START_DATE_PARAM_NAME = "startDate";

	private static final String END_DATE_PARAM_NAME = "endDate";

	private static final String EXCLUDED_AFFILIATION_PARAM_NAME
		= "excludedAffiliation";

	/**
	 * Instantiates an Hibernate implementation of security threat group
	 * affiliations.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SecurityThreatGroupAffiliationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupAffiliation> findByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<SecurityThreatGroupAffiliation> affiliations
			= this.getSessionFactory().getCurrentSession().getNamedQuery(
					FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender).list();
		return affiliations;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupAffiliation find(final Offender offender,
			final SecurityThreatGroup group, final Date startDate,
			final Date endDate) {
		SecurityThreatGroupAffiliation affiliation
			= (SecurityThreatGroupAffiliation) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(GROUP_PARAM_NAME, group)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate)
				.uniqueResult();
		return affiliation;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupAffiliation findExcluding(final Offender offender,
			final SecurityThreatGroup group, final Date startDate,
			final Date endDate,
			final SecurityThreatGroupAffiliation excludedAffiliation) {
		SecurityThreatGroupAffiliation affiliation
			= (SecurityThreatGroupAffiliation) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(GROUP_PARAM_NAME, group)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate)
				.setParameter(EXCLUDED_AFFILIATION_PARAM_NAME,
						excludedAffiliation)
				.uniqueResult();
		return affiliation;
	}
}