package omis.condition.report.impl.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.condition.domain.Agreement;
import omis.condition.report.AgreementSummary;
import omis.condition.report.ConditionSummary;
import omis.condition.report.ConditionSummaryReportService;
import omis.offender.domain.Offender;

/** Hibernate implementation of report service.
 * @author Jonny Santy
 * @author Annie Jacques
 * @version 0.1.1 (May 21, 2017)
 * @since OMIS 3.0 */
public class ConditionSummaryReportServiceHibernateImpl 
	implements ConditionSummaryReportService {
	
	private static final String FIND_BY_AGREEMENT_QUERY_NAME =
			"findConditionSummariesByAgreement";
	
	private static final String FIND_AGREEMENTS_BY_OFFENDER_QUERY_NAME =
			"findAgreementsByOffenderAndEffectiveDate";
	
	private static final String SUMMARIZE_AGREEMENT_QUERY_NAME =
			"summarizeAgreement";
	
	private static final String AGREEMENT_PARAM_NAME = "agreement";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public ConditionSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ConditionSummary> findByAgreement(final Agreement agreement,
			final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_BY_AGREEMENT_QUERY_NAME);
		q.setEntity(AGREEMENT_PARAM_NAME, agreement);
		//q.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		@SuppressWarnings("unchecked")
		List<ConditionSummary> retVal = (List<ConditionSummary>)q.list();
		return retVal;
	}
	
	/** {@inheritDoc} */
	@Override
	public AgreementSummary summarize(final Agreement agreement) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				SUMMARIZE_AGREEMENT_QUERY_NAME);
		q.setEntity(AGREEMENT_PARAM_NAME, agreement);
		return (AgreementSummary)q.uniqueResult();
	}
	
	/** {@inheritDoc} */
	@Override 
	public List<AgreementSummary> findByOffender(final Offender offender,
			final Date effectiveDate) {
		List<AgreementSummary> agreementSummaries =
				new ArrayList<AgreementSummary>();
		@SuppressWarnings("unchecked")
		List<Agreement> agreements = this.sessionFactory
			.getCurrentSession().getNamedQuery(
				FIND_AGREEMENTS_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		for(Agreement agreement : agreements){
			List<ConditionSummary> conditionSummaries = 
					this.findByAgreement(agreement, effectiveDate);
			AgreementSummary agreementSummary = this.summarize(agreement);
			agreementSummary.setConditionSummaries(conditionSummaries);
			agreementSummaries.add(agreementSummary);
		}
		return agreementSummaries;
	}
}
