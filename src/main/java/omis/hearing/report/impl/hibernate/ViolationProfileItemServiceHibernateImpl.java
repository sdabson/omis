package omis.hearing.report.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.hearing.report.ViolationProfileItemService;
import omis.hearing.report.ViolationProfileSummary;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of violation profile item.
 *
 * @author Sheronda Vaughn
 * @author Annie Jacques
 * @version 0.1.0 (Aug 28, 2017)
 * @since OMIS 3.0
 */
public class ViolationProfileItemServiceHibernateImpl 
		implements ViolationProfileItemService {
	
	private final SessionFactory sessionFactory;
	
	private static final String FIND_DISCIPLINARY_UNRESOLVED_COUNT_BY_OFFENDER =
			"findUnresolvedDisciplinaryViolationCountByOffender";
	
	private static final String FIND_SUPERVISION_UNRESOLVED_COUNT_BY_OFFENDER =
			"findUnresolvedSupervisionViolationCountByOffender";
	
	private static final String FIND_DISCIPLINARY_RESOLVED_COUNT_BY_OFFENDER =
			"findResolvedDisciplinaryViolationCountByOffender";
	
	private static final String FIND_SUPERVISION_RESOLVED_COUNT_BY_OFFENDER =
			"findResolvedSupervisionViolationCountByOffender";
		
	private static final String OFFENDER_PARAM_NAME =
			"offender";

	/** Instantiates an implementation of 
	 * ViolationProfileItemServiceHibernateImpl */
	public ViolationProfileItemServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public ViolationProfileSummary findViolationSummaryByOffender(
			final Offender offender) {
		Long unresolvedCount = (Long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_DISCIPLINARY_UNRESOLVED_COUNT_BY_OFFENDER)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		unresolvedCount += (Long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_SUPERVISION_UNRESOLVED_COUNT_BY_OFFENDER)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		Long resolvedCount = (Long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_DISCIPLINARY_RESOLVED_COUNT_BY_OFFENDER)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		resolvedCount += (Long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_SUPERVISION_RESOLVED_COUNT_BY_OFFENDER)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		
		ViolationProfileSummary violationProfileSummary =
				new ViolationProfileSummary(unresolvedCount, resolvedCount);
		
		return violationProfileSummary;
	}
}