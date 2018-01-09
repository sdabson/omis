
package omis.hearing.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.hearing.dao.HearingStatusDao;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.HearingStatus;
import omis.hearing.domain.HearingStatusCategory;

/**
 * HearingStatusDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 18, 2017)
 *@since OMIS 3.0
 *
 */
public class HearingStatusDaoHibernateImpl
	extends GenericHibernateDaoImpl<HearingStatus>
	implements HearingStatusDao{
	
	private static final String FIND_HEARING_STATUS_QUERY_NAME =
			"findHearingStatus";
	
	private static final String FIND_HEARING_STATUS_EXCLUDING_QUERY_NAME =
			"findHearingStatusExcluding";
	
	private static final String FIND_LATEST_HEARING_STATUS_QUERY_NAME =
			"findLatestHearingStatus";
	
	private static final String FIND_HEARING_STATUSES_BY_HEARING_QUERY_NAME =
			"findHearingStatusesByHearing";
	
	private static final String HEARING_PARAM_NAME = "hearing";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String CATEGORY_PARAM_NAME = "category";
	
	private static final String HEARING_STATUS_PARAM_NAME = "hearingStatus";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected HearingStatusDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public HearingStatus find(final Hearing hearing, final Date date,
			final HearingStatusCategory category) {
		HearingStatus hearingStatus = (HearingStatus) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_HEARING_STATUS_QUERY_NAME)
				.setParameter(HEARING_PARAM_NAME, hearing)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.uniqueResult();
		
		return hearingStatus;
	}

	/**{@inheritDoc} */
	@Override
	public HearingStatus findExcluding(final Hearing hearing, final Date date,
			final HearingStatusCategory category,
			final HearingStatus hearingStatusExcluded) {
		HearingStatus hearingStatus = (HearingStatus) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_HEARING_STATUS_EXCLUDING_QUERY_NAME)
				.setParameter(HEARING_PARAM_NAME, hearing)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(HEARING_STATUS_PARAM_NAME, hearingStatusExcluded)
				.uniqueResult();
		
		return hearingStatus;
	}

	/**{@inheritDoc} */
	@Override
	public HearingStatus findLatestByHearing(final Hearing hearing) {
		HearingStatus hearingStatus = (HearingStatus) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_LATEST_HEARING_STATUS_QUERY_NAME)
				.setParameter(HEARING_PARAM_NAME, hearing)
				.setMaxResults(1)
				.uniqueResult();
		
		return hearingStatus;
	}

	/**{@inheritDoc} */
	@Override
	public List<HearingStatus> findByHearing(final Hearing hearing) {
		@SuppressWarnings("unchecked")
		List<HearingStatus> hearingStatuses = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_HEARING_STATUSES_BY_HEARING_QUERY_NAME)
				.setParameter(HEARING_PARAM_NAME, hearing)
				.list();
		
		return hearingStatuses;
	}

}
