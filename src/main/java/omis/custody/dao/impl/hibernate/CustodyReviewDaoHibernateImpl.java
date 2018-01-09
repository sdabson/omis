package omis.custody.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.custody.dao.CustodyReviewDao;
import omis.custody.domain.CustodyReview;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;

/**
 * Implementation of
 * database access objects for custody review.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 07 2013)
 * @since OMIS 3.0
 */
public class CustodyReviewDaoHibernateImpl 
		extends GenericHibernateDaoImpl<CustodyReview>
		implements CustodyReviewDao {

	/**
	 * Instantiates a data access object for custody review with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CustodyReviewDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CustodyReview> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<CustodyReview> custodyReviews = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findCustodyReviewsByOffender")
				.setParameter("offender", offender)
				.list();
		
		return custodyReviews;
	}

	/** {@inheritDoc} */
	@Override
	public CustodyReview findByOffenderOnDate(final Offender offender,
			final Date date) {
		CustodyReview custodyReview = (CustodyReview) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findSpecificCustodyReviewForOffender")
				.setParameter("offender", offender)
				.setParameter("date", date)
				.uniqueResult();
		
		return custodyReview;
	}

	/** {@inheritDoc} */
	@Override
	public CustodyReview find(final Offender offender, final Date actionDate) {
		CustodyReview custodyReview = (CustodyReview) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findCustodyReview")
				.setParameter("offender", offender)
				.setDate("actionDate", actionDate)
				.uniqueResult();
		
		return custodyReview;
	}

	/** {@inheritDoc} */
	@Override
	public CustodyReview findExcluding(final Offender offender,
			final Date actionDate, final CustodyReview custodyReview) {
		CustodyReview matchingCustodyReview = (CustodyReview) 
				getSessionFactory().getCurrentSession()
				.getNamedQuery("findCustodyReviewExcluding")
				.setParameter("offender", offender)
				.setParameter("excludedCustodyReview", custodyReview)
				.setDate("actionDate", actionDate)
				.uniqueResult();
		
		return matchingCustodyReview;
	}	
}