package omis.identificationnumber.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.identificationnumber.dao.IdentificationNumberDao;
import omis.identificationnumber.domain.IdentificationNumber;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.IdentificationNumberIssuer;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of data access object for identification numbers.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2
 * @since OMIS 3.0
 */
public class IdentificationNumberDaoHibernateImpl
		extends GenericHibernateDaoImpl<IdentificationNumber>
		implements IdentificationNumberDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME
		= "findIdentificationNumber";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findIdentificationNumberExcluding";
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findIdentificationNumbersByOffender";
	
	private static final String FIND_WITHIN_DATES_EXCLUDING_QUERY_NAME
		= "findIdentificationNumberWithinDatesExcluding";

	private static final String FIND_WITHIN_DATES_QUERY_NAME
		= "findIdentificationNumberWithinDates";

	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";

	private static final String ISSUER_PARAM_NAME = "issuer";

	private static final String CATEGORY_PARAM_NAME = "category";

	private static final String VALUE_PARAM_NAME = "value";

	private static final String ISSUE_DATE_PARAM_NAME = "issueDate";

	private static final String EXPIRE_DATE_PARAM_NAME = "expireDate";

	private static final String EXCLUDED_PARAM_NAME
		= "excludedIdentificationNumbers";

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for
	 * identification number.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public IdentificationNumberDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public IdentificationNumber find(
			final Offender offender, final IdentificationNumberIssuer issuer,
			final IdentificationNumberCategory category, final String value,
			final Date issueDate, final Date expireDate) {
		IdentificationNumber identificationNumber
			= (IdentificationNumber) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(ISSUER_PARAM_NAME, issuer)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(VALUE_PARAM_NAME, value)
				.setTimestamp(ISSUE_DATE_PARAM_NAME, issueDate)
				.setTimestamp(EXPIRE_DATE_PARAM_NAME, expireDate)
				.uniqueResult();
		return identificationNumber;
	}

	/** {@inheritDoc} */
	@Override
	public IdentificationNumber findExcluding(
			final Offender offender, final IdentificationNumberIssuer issuer,
			final IdentificationNumberCategory category, final String value,
			final Date issueDate, final Date expireDate,
			final IdentificationNumber... excludedIdentificationNumbers) {
		IdentificationNumber identificationNumber
			= (IdentificationNumber) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(ISSUER_PARAM_NAME, issuer)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(VALUE_PARAM_NAME, value)
				.setTimestamp(ISSUE_DATE_PARAM_NAME, issueDate)
				.setTimestamp(EXPIRE_DATE_PARAM_NAME, expireDate)
				.setParameterList(EXCLUDED_PARAM_NAME,
						excludedIdentificationNumbers)
				.uniqueResult();
		return identificationNumber;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<IdentificationNumber> findAll() {
		throw new UnsupportedOperationException(
				"Find without criteria not allowed");
	}

	/** {@inheritDoc} */
	@Override
	public List<IdentificationNumber> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<IdentificationNumber> identificationNumbers
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return identificationNumbers;
	}

	/**{@inheritDoc} */
	@Override
	public IdentificationNumber findWithinDates(final Offender offender,
			final IdentificationNumberIssuer issuer,
			final IdentificationNumberCategory category,
			final Date issueDate,
			final Date expireDate) {
		IdentificationNumber identificationNumber
		= (IdentificationNumber) this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
					FIND_WITHIN_DATES_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setParameter(ISSUER_PARAM_NAME, issuer)
			.setParameter(CATEGORY_PARAM_NAME, category)
			.setTimestamp(ISSUE_DATE_PARAM_NAME, issueDate)
			.setTimestamp(EXPIRE_DATE_PARAM_NAME, expireDate)
			.uniqueResult();
	return identificationNumber;
	}

	/**{@inheritDoc} */
	@Override
	public IdentificationNumber findWithinDatesExcluding(final Offender offender,
			final IdentificationNumberIssuer issuer,
			final IdentificationNumberCategory category,
			final Date issueDate,
			final Date expireDate,
			final IdentificationNumber... excludedIdentificationNumbers) {
		IdentificationNumber identificationNumber
		= (IdentificationNumber) this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
					FIND_WITHIN_DATES_EXCLUDING_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setParameter(ISSUER_PARAM_NAME, issuer)
			.setParameter(CATEGORY_PARAM_NAME, category)
			.setTimestamp(ISSUE_DATE_PARAM_NAME, issueDate)
			.setTimestamp(EXPIRE_DATE_PARAM_NAME, expireDate)
			.setParameterList(EXCLUDED_PARAM_NAME,
					excludedIdentificationNumbers)
			.uniqueResult();
	return identificationNumber;
	}
}