package omis.caseload.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.caseload.dao.CaseloadContactDao;
import omis.caseload.domain.CaseloadContact;
import omis.caseload.domain.ContactCategory;
import omis.caseload.domain.OffenderCaseAssignment;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.domain.Person;

/**
 * 
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 1, 2016)
 * @since OMIS 3.0
 */
public class CaseloadContactDaoHibernateImpl extends GenericHibernateDaoImpl<CaseloadContact> 
	implements CaseloadContactDao {
	
	/* Queries. */
	private static final String FIND_CONTACT_QUERY_NAME = "findCaseloadContact";
	private static final String FIND_CONTACT_EXCLUDING_QUERY_NAME 
		= "findCaseloadContactExcluding";
	
	/* Parameters. */
	private static final String CONTACT_BY_PARAM_NAME = "contactBy";
	private static final String CONTACT_CATEGORY_PARAM_NAME = "category";
	private static final String CONTACT_PARAM_NAME = "contact";
	private static final String DATE_PARAM_NAME =  "contactDate";
	private static final String OFFENDER_CASE_ASSIGNMENT_PARAM_NAME 
		= "offenderCaseAssignment";
	
	/** Instantiates a hibernate implementation of the data access object 
	 * for contact.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CaseloadContactDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CaseloadContact> findAll() {
		// TODO Implement or remove - SV
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/** {@inheritDoc} */
	@Override
	public CaseloadContact find(final OffenderCaseAssignment offenderCaseAssignment, 
			final Date contactDate, final Person contactBy,
			ContactCategory category) {
		CaseloadContact contact = (CaseloadContact) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_CONTACT_QUERY_NAME)
				.setParameter(OFFENDER_CASE_ASSIGNMENT_PARAM_NAME, 
						offenderCaseAssignment)
				.setParameter(DATE_PARAM_NAME, contactDate)
				.setParameter(CONTACT_BY_PARAM_NAME, contactBy)
				.setParameter(CONTACT_CATEGORY_PARAM_NAME, category)
				.uniqueResult();
		return contact;
	}

	/** {@inheritDoc} */
	@Override
	public CaseloadContact findExcluding(
			final OffenderCaseAssignment offenderCaseAssignment, 
			final Date contactDate, final Person contactBy, 
			final ContactCategory category, final CaseloadContact contact) {
		CaseloadContact thisContact = (CaseloadContact) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_CONTACT_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_CASE_ASSIGNMENT_PARAM_NAME, 
						offenderCaseAssignment)
				.setParameter(DATE_PARAM_NAME, contactDate)
				.setParameter(CONTACT_BY_PARAM_NAME, contactBy)
				.setParameter(CONTACT_CATEGORY_PARAM_NAME, category)
				.setParameter(CONTACT_PARAM_NAME, contact)
				.uniqueResult();
		return thisContact;
	}
}