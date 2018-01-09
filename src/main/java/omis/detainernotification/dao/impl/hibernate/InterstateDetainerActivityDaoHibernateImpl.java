package omis.detainernotification.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.detainernotification.dao.InterstateDetainerActivityDao;
import omis.detainernotification.domain.InterstateAgreementDetainer;
import omis.detainernotification.domain.InterstateDetainerActivity;
import omis.document.domain.Document;

/**
 * InterstateDetainerActivityDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public class InterstateDetainerActivityDaoHibernateImpl
		extends GenericHibernateDaoImpl<InterstateDetainerActivity>
		implements InterstateDetainerActivityDao {
	
	private static final String FIND_INTERSTATE_DETAINER_ACTIVITY_QUERY_NAME =
			"findInterstateDetainerActivity";
	
	private static final String
			FIND_INTERSTATE_DETAINER_ACTIVITY_EXCLUDING_QUERY_NAME =
					"findInterstateDetainerActivityExcluding";
	
	private static final String
			FIND_ACTIVITIES_BY_INTERSTATE_AGREEMENT_DETAINER_QUERY_NAME =
				"findInterstateDetainerActivitiesByInterstateAgreementDetainer";
	
	private static final String INTERSTATE_AGREEMENT_DETAINER_PARAM_NAME =
			"interstateAgreementDetainer";
	
	private static final String ACTIVITY_DATE_PARAM_NAME = "activityDate";
	
	private static final String DOCUMENT_PARAM_NAME = "document";
	
	private static final String INTERSTATE_DETAINER_ACTIVITY_PARAM_NAME =
			"interstateDetainerActivity";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected InterstateDetainerActivityDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	

	/**{@inheritDoc} */
	@Override
	public InterstateDetainerActivity find(
			final InterstateAgreementDetainer interstateAgreementDetainer, 
			final Date activityDate,final Document document) {
		InterstateDetainerActivity activity = (InterstateDetainerActivity)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_INTERSTATE_DETAINER_ACTIVITY_QUERY_NAME)
				.setParameter(INTERSTATE_AGREEMENT_DETAINER_PARAM_NAME,
						interstateAgreementDetainer)
				.setTimestamp(ACTIVITY_DATE_PARAM_NAME, activityDate)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.uniqueResult();
		
		return activity;
	}

	/**{@inheritDoc} */
	@Override
	public InterstateDetainerActivity findExcluding(
			final InterstateAgreementDetainer interstateAgreementDetainer,
			final Date activityDate, final Document document,
			final InterstateDetainerActivity interstateDetainerActivityExcluded) {
		InterstateDetainerActivity activity = (InterstateDetainerActivity)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_INTERSTATE_DETAINER_ACTIVITY_EXCLUDING_QUERY_NAME)
				.setParameter(INTERSTATE_AGREEMENT_DETAINER_PARAM_NAME,
						interstateAgreementDetainer)
				.setTimestamp(ACTIVITY_DATE_PARAM_NAME, activityDate)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(INTERSTATE_DETAINER_ACTIVITY_PARAM_NAME,
						interstateDetainerActivityExcluded)
				.uniqueResult();
		
		return activity;
	}

	/**{@inheritDoc} */
	@Override
	public List<InterstateDetainerActivity> findByInterstateAgreementDetainer(
			final InterstateAgreementDetainer interstateAgreementDetainer) {
		@SuppressWarnings("unchecked")
		List<InterstateDetainerActivity> activities = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					FIND_ACTIVITIES_BY_INTERSTATE_AGREEMENT_DETAINER_QUERY_NAME)
				.setParameter(INTERSTATE_AGREEMENT_DETAINER_PARAM_NAME,
						interstateAgreementDetainer)
				.list();
		
		return activities;
	}

}
