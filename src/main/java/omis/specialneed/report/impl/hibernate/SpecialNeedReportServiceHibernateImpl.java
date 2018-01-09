package omis.specialneed.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.report.SpecialNeedReportService;
import omis.specialneed.report.SpecialNeedSummary;

/**
 * Hibernate implementation of the special need report service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 9, 2016)
 * @since OMIS 3.0
 */
public class SpecialNeedReportServiceHibernateImpl 
	implements SpecialNeedReportService {

	/* Queries. */
	private static final String
	SUMMARIZE_SPECIAL_NEED_BY_OFFENDER_AND_EFFECTIVE_DATE_QUERY_NAME
		= "summarizeSpecialNeedByOffenderAndEffectiveDate";
	
	private static final String FIND_SPECIAL_NEEDS_QUERY_NAME = "findSpecialNeeds";
	
	/* Parameters.*/ 
	private static final String OFFENDER_PARAMETER_NAME = "offender";
	
	private static final String EFFECTIVE_PARAMETER_NAME = "effectiveDate";
	
	/* Members. */
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor.
	 *
	 * @param sessionFactory session factory
	 */
	public SpecialNeedReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<SpecialNeedSummary> summarizeByOffender(
			final Offender offender, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<SpecialNeedSummary> specialNeedSummaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(
			SUMMARIZE_SPECIAL_NEED_BY_OFFENDER_AND_EFFECTIVE_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAMETER_NAME, offender)
				.setDate(EFFECTIVE_PARAMETER_NAME, effectiveDate)
				.list();

		return specialNeedSummaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<SpecialNeed> find() {
		@SuppressWarnings("unchecked")
		List<SpecialNeed> specialNeeds = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_SPECIAL_NEEDS_QUERY_NAME).list();
		return specialNeeds;
	}
}