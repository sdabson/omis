package omis.condition.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.condition.dao.AgreementDao;
import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.docket.domain.Docket;
import omis.offender.domain.Offender;

/**
 * Agreement Dao Hibernate Implementation.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Wahl
 * @version 0.1.2 (Nov 28, 2017)
 * @since OMIS 3.0
 */
public class AgreementDaoHibernateImpl extends GenericHibernateDaoImpl
	<Agreement> implements AgreementDao {
	
	/* Query names. */
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findAgreementsByOffender";
	
	private static final String FIND_QUERY_NAME = "findAgreement";

	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findAgreementExcluding";

	private static final String FIND_AGREEMENTS_QUERY_NAME = 
			"findAgreementsByDocket";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String END_DATE_PARAM_NAME = "endDate";

	private static final String AGREEMENT_PARAM_NAME = "agreement";

	private static final String DOCKET_PARAM_NAME = "docket";

	private static final String DESCRIPTION_PARAM_NAME = "description";

	private static final String CATEGORY_PARAM_NAME = "category";

	/* Constructor. */
	
	/**
	 * Instantiates a data access object for offender cautions with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AgreementDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public Agreement find(final Offender offender, final Date startDate, 
			final Date endDate, final String description,
			final AgreementCategory category) {
		Agreement agreement = (Agreement) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.uniqueResult();
		return agreement;
	}

	/** {@inheritDoc} */
	@Override
	public List<Agreement> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<Agreement> agreements = (List<Agreement>) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return agreements;
	}

	/** {@inheritDoc} */
	@Override
	public Agreement findExcluding(final Agreement agreement, 
			final Date startDate, final Date endDate, final String description,
			final AgreementCategory category) {
		Agreement newAgreement = (Agreement) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, agreement.getOffender())
				.setParameter(AGREEMENT_PARAM_NAME, agreement)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.uniqueResult();
		return newAgreement;
	}

	@Override
	public List<Agreement> findByDocket(final Docket docket) {
		@SuppressWarnings("unchecked")
		List<Agreement> agreements = (List<Agreement>) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_AGREEMENTS_QUERY_NAME)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.list();
		return agreements;
	}

}
