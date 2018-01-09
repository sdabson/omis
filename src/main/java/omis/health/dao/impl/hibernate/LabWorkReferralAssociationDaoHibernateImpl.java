package omis.health.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.LabWorkReferralAssociationDao;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.LabWorkReferralAssociation;

/**
 * Lab work referral association data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 26, 2014)
 * @since OMIS 3.0
 */
public class LabWorkReferralAssociationDaoHibernateImpl 
	extends GenericHibernateDaoImpl<LabWorkReferralAssociation>
	implements LabWorkReferralAssociationDao {

	/* Query names. */
	
	private static final String FIND_LAB_WORK_REFERRAL_ASSOCIATION_QUERY_NAME
		= "findLabWorkReferralAssociation";
	
	/* Parameter names. */
	
	private static final String LAB_WORK_PARAMETER_NAME = "labWork";
	
	private static final String LAB_WORK_REFERRAL_PARAMETER_NAME 
		= "labWorkReferral";
	
	/* Constructor. */
	
	/**
	 * Instantiates a lab work referral association data access object
	 * with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory 
	 * @param entityName entity name
	 */
	public LabWorkReferralAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Public methods. */
	
	/** {@inheritDoc} */
	@Override
	public LabWorkReferralAssociation find(final LabWork labWork,
			final LabWorkReferral labWorkReferral) {
		LabWorkReferralAssociation association = (LabWorkReferralAssociation) 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_LAB_WORK_REFERRAL_ASSOCIATION_QUERY_NAME)
				.setParameter(LAB_WORK_PARAMETER_NAME, labWork)
				.setParameter(LAB_WORK_REFERRAL_PARAMETER_NAME, labWorkReferral)
				.uniqueResult();
		return association;
	}
}