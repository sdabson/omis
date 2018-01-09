package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.docket.domain.Docket;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Pre sentence investigation request data access object.
 * 
 * @author Joel Norris
 * @author Annie Jacques
 * @version 0.1.1 (May 16, 2017)
 * @since OMIS 3.0
 */
public interface PresentenceInvestigationRequestDao 
	extends GenericDao<PresentenceInvestigationRequest>{

	/**
	 * Returns the presentence investigation request with the specified docket
	 * 
	 * @param Docket docket
	 * @return presentence investigation request
	 */
	PresentenceInvestigationRequest find(Docket docket);
	
	/** 
	 * Returns the presentence investiation request with the specified docket,
	 * excluding the specified presentence investigation request.
	 * 
	 * @param pressentenceInvestigationRequest presentence investigation request
	 * @param Docket docket
	 * @return presentence investigation request
	 */
	PresentenceInvestigationRequest findExcluding(
			PresentenceInvestigationRequest pressentenceInvestigationRequest,
			Docket docket);
}