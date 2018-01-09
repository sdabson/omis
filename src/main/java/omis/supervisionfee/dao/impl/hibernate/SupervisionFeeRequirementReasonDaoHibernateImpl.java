/**
 * 
 */
package omis.supervisionfee.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.supervisionfee.dao.SupervisionFeeRequirementReasonDao;
import omis.supervisionfee.domain.SupervisionFeeRequirementReason;

import org.hibernate.SessionFactory;

/** 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Oct 27, 2014)
 * @since OMIS 3.0
 */
public class SupervisionFeeRequirementReasonDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SupervisionFeeRequirementReason>
	implements SupervisionFeeRequirementReasonDao {
	
	/* Query names. */
	private static final String 
	FIND_ALL_SUPERVISION_FEE_REQUIREMENT_REASONS_QUERY_NAME
		= "findAllSupervisionFeeRequirementReasons";

	/**
	 * Instantiates an hibernate implementation of the data access object for 
	 * supervision fee requirement reasons with the specified resources.
	 * 
	 * @param sessionFactory session factory	
	 * @param entityName entity name
	 */
	public SupervisionFeeRequirementReasonDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
			super(sessionFactory, entityName);
		}
		
	/** @return */
	@Override
	public List<SupervisionFeeRequirementReason> 
	findAllSupervisionFeeRequirementReasons() {
		@SuppressWarnings("unchecked")
		List<SupervisionFeeRequirementReason> reasons = getSessionFactory()
		.getCurrentSession().getNamedQuery(
				FIND_ALL_SUPERVISION_FEE_REQUIREMENT_REASONS_QUERY_NAME).list();
		return reasons;
	}
}
