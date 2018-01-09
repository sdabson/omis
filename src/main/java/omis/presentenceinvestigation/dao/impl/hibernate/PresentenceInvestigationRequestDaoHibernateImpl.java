package omis.presentenceinvestigation.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.docket.domain.Docket;
import omis.presentenceinvestigation.dao.PresentenceInvestigationRequestDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Pre sentence investigation request data access object hibernate
 * implementation. 
 * @author Joel Norris
 * @author Annie Jacques
 * @version 0.1.1 (May 16, 2017)
 * @since OMIS 3.0
 */
public class PresentenceInvestigationRequestDaoHibernateImpl 
extends GenericHibernateDaoImpl<PresentenceInvestigationRequest>
implements PresentenceInvestigationRequestDao {

	/* Query names. */
	
	private static final String
	FIND_PRESENTENCE_INVESTIGATION_REQUEST_QUERY_NAME
	 = "findPresentenceInvestigationRequest";
	
	private static final String
	FIND_PRESENTENCE_INVESTIGATION_REQUEST_EXCLUDING_QUERY_NAME
	= "findPresentenceInvestigationRequestExcluding";
	
	/* Parameter names. */
	
	private static final String DOCKET_PARAM_NAME = "docket";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME
		= "presentenceInvestigationRequest";
	
	/* Constructor. */
	
	public PresentenceInvestigationRequestDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationRequest find(final Docket docket) {
		PresentenceInvestigationRequest presentenceInvestigationRequest =
				(PresentenceInvestigationRequest) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_PRESENTENCE_INVESTIGATION_REQUEST_QUERY_NAME)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.uniqueResult();
		return presentenceInvestigationRequest;
	}

	/** {@inheritDoc} */
	@Override
	public PresentenceInvestigationRequest findExcluding(
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest,
			final Docket docket) {
		PresentenceInvestigationRequest request =
				(PresentenceInvestigationRequest) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					FIND_PRESENTENCE_INVESTIGATION_REQUEST_EXCLUDING_QUERY_NAME)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.uniqueResult();
		return request;
	}
}