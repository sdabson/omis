/**
 * DetainerDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 11, 2016)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.detainernotification.dao.DetainerDao;
import omis.detainernotification.domain.Detainer;
import omis.offender.domain.Offender;

public class DetainerDaoHibernateImpl extends GenericHibernateDaoImpl<Detainer> 
	implements DetainerDao {

	/*Query names*/
	
	private static final String FIND_DETAINER_QUERY_NAME = "findDetainer";
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME = 
			"findDetainerByOffender";
	
	private static final String FIND_DETAINER_EXCLUDING_QUERY_NAME = 
			"findDetainerExcluding";
	
	/* Parameter names */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String RECEIVED_DATE_PARAM_NAME = "receivedDate";
	
	private static final String DETAINER_PARAM_NAME = "detainer";
	
	private static final String COURT_CASE_NUMBER_PARAM_NAME = "courtCaseNumber";
	
	private static final String WARRANT_NUMBER_PARAM_NAME = "warrantNumber";
	

	/**
	 * Constructor
	 * @param sessionFactory - session factory
	 * @param entityName - entity name
	 */
	protected DetainerDaoHibernateImpl(SessionFactory sessionFactory, 
			String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations */

	/**{@inheritDoc} */
	@Override
	public Detainer find(final Offender offender, final Date receivedDate,
			final String courtCaseNumber, final String warrantNumber) {
		Detainer detainer = (Detainer) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_DETAINER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(RECEIVED_DATE_PARAM_NAME, receivedDate)
				.setParameter(COURT_CASE_NUMBER_PARAM_NAME, courtCaseNumber)
				.setParameter(WARRANT_NUMBER_PARAM_NAME, warrantNumber)
				.uniqueResult();
		
		return detainer;
	}

	/**{@inheritDoc} */
	@Override
	public Detainer findExcluding(final Offender offender, 
			final Date receivedDate, final String courtCaseNumber,
			final String warrantNumber, final Detainer excludedDetainer) {
		Detainer detainer = (Detainer) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_DETAINER_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(RECEIVED_DATE_PARAM_NAME, receivedDate)
				.setParameter(COURT_CASE_NUMBER_PARAM_NAME, courtCaseNumber)
				.setParameter(WARRANT_NUMBER_PARAM_NAME, warrantNumber)
				.setParameter(DETAINER_PARAM_NAME, excludedDetainer)
				.uniqueResult();
		
		return detainer;
	}

	/**{@inheritDoc} */
	@Override
	public List<Detainer> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<Detainer> detainers = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender).list();
		
		return detainers;
	}

}
