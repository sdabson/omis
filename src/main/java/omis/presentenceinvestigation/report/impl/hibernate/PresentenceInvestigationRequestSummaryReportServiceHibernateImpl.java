package omis.presentenceinvestigation.report.impl.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.person.domain.Person;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.report.PresentenceInvestigationRequestSummary;
import omis.presentenceinvestigation.report.PresentenceInvestigationRequestSummaryReportService;
import omis.user.domain.UserAccount;

public class 
	PresentenceInvestigationRequestSummaryReportServiceHibernateImpl 
	implements PresentenceInvestigationRequestSummaryReportService {
	private static final String FIND_BY_USER_QUERY_NAME 
		= "findPresentenceInvestigationRequestSummariesByUser";
	
	private static final String USER_PARAM_NAME = "assignedUser";

	private static final String FIND_BY_OFFENDER_QUERY_NAME 
		= "findPresentenceInvestigationRequestSummariesByOffender";

	private static final String OFFENDER_PARAM_NAME = "offender";

	private static final String FIND_BY_REQUEST_QUERY_NAME 
		= "findPresentenceInvestigationRequestSummary";

	private static final String REQUEST_PARAM_NAME 
		= "presentenceInvestigationRequest";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor. 
	 * @param sessionFactory - session factory. */
	public PresentenceInvestigationRequestSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<PresentenceInvestigationRequestSummary> 
		findPresentenceInvestigationRequestSummariesByUser(
			final UserAccount user) {
			Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
					FIND_BY_USER_QUERY_NAME);
			q.setParameter(USER_PARAM_NAME, user);
			
			return (List<PresentenceInvestigationRequestSummary>) q.list();
	}


	/**{@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<PresentenceInvestigationRequestSummary> 
		findPresentenceInvestigationRequestSummariesByOffender(
			final Person offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_BY_OFFENDER_QUERY_NAME);
		q.setParameter(OFFENDER_PARAM_NAME, offender);
		
		return (List<PresentenceInvestigationRequestSummary>) q.list();
	}


	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationRequestSummary summarize(
			PresentenceInvestigationRequest presentenceInvestigationRequest) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_BY_REQUEST_QUERY_NAME);
		q.setParameter(REQUEST_PARAM_NAME, presentenceInvestigationRequest);
		
		return ((PresentenceInvestigationRequestSummary) q.uniqueResult());
	}
	
	
}
