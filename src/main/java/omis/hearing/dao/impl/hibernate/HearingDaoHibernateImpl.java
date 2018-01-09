package omis.hearing.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.hearing.dao.HearingDao;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.HearingCategory;
import omis.location.domain.Location;
import omis.offender.domain.Offender;
import omis.staff.domain.StaffAssignment;

/**
 * HearingDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public class HearingDaoHibernateImpl
	extends GenericHibernateDaoImpl<Hearing> implements HearingDao {
	
	/* Query Names */
	
	private static final String FIND_HEARING_QUERY_NAME = "findHearing";
	
	private static final String FIND_HEARING_EXCLUDING_QUERY_NAME =
			"findHearingExcluding";
	
	private static final String FIND_HEARINGS_BY_OFFENDER_QUERY_NAME =
			"findHearingsByOffender";
	
	/* Param Names */
	
	private static final String LOCATION_PARAM_NAME = "location";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String OFFICER_PARAM_NAME = "officer";
	
	private static final String HEARING_PARAM_NAME = "hearing";
	
	private static final String CATEGORY_PARAM_NAME = "category";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected HearingDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public Hearing find(final Location location, final Offender offender,
			final Date date, final StaffAssignment officer,
			final HearingCategory category) {
		Hearing hearing = (Hearing) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_HEARING_QUERY_NAME)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(OFFICER_PARAM_NAME, officer)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.uniqueResult();
		
		return hearing;
	}

	/**{@inheritDoc} */
	@Override
	public Hearing findExcluding(final Location location,
			final Offender offender, final Date date,
			final StaffAssignment officer, final HearingCategory category,
			final Hearing hearing) {
		Hearing hearingExcluding = (Hearing) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_HEARING_EXCLUDING_QUERY_NAME)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(OFFICER_PARAM_NAME, officer)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(HEARING_PARAM_NAME, hearing)
				.uniqueResult();
		
		return hearingExcluding;
	}

	/**{@inheritDoc} */
	@Override
	public List<Hearing> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<Hearing> hearings = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_HEARINGS_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		
		return hearings;
	}

}

