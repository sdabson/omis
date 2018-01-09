package omis.physicalfeature.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.physicalfeature.report.PhysicalFeatureSummaryReportService;
import omis.physicalfeature.report.PhysicalFeatureSummary;

/**
 * Hibernate implementation of the physical feature report summary service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 6, 2016)
 * @since OMIS 3.0
 */
public class PhysicalFeatureSummaryReportServiceHibernateImpl 
	implements PhysicalFeatureSummaryReportService {
	
	/* Queries. */
	
	private static final String FIND_PHYSICAL_FEATURE_BY_OFFENDER_QUERY_NAME
		= "findPhysicalFeatureByOffender";
	
	/* Parameters.*/ 
	
	private static final String OFFENDER_PARAMETER_NAME = "offender";
	
	/* Members. */
	private final SessionFactory sessionFactory;

	/**
	 * Constructor.
	 *
	 * @param sessionFactory session factory
	 */
	public PhysicalFeatureSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeatureSummary> findByOffender(Offender offender) {
		@SuppressWarnings("unchecked")
		List<PhysicalFeatureSummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_PHYSICAL_FEATURE_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAMETER_NAME, offender).list();
		return summaries;
	}
}