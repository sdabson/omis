package omis.condition.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.condition.dao.AgreementNoteDao;
import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementNote;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Agreement Implementation.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @version 0.1.1 (May 21, 2017)
 * @since OMIS 3.0
 */
public class AgreementNoteDaoHibernateImpl extends GenericHibernateDaoImpl
<AgreementNote> implements AgreementNoteDao{
	
	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findAgreementNote";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findAgreementNoteExcluding";
	
	private static final String FIND_AGREEMENT_NOTES_BY_AGREEMENT_QUERY_NAME =
			"findAgreementNotesByAgreement";
	
	/* Parameter names. */
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String AGREEMENT_PARAM_NAME = "agreement";
	
	private static final String AGREEMENT_NOTE_PARAM_NAME = "agreementNote";
	
	/* Constructor. */
	
	/**
	 * Instantiates a data access object for offender cautions with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AgreementNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	@Override
	public AgreementNote find(final Date date, final String description,
			final Agreement agreement) {
		AgreementNote agreementNote = (AgreementNote)
				this.getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_QUERY_NAME)
					.setDate(DATE_PARAM_NAME, date)
					.setParameter(DESCRIPTION_PARAM_NAME, description)
					.setParameter(AGREEMENT_PARAM_NAME, agreement)
					.uniqueResult();
			return agreementNote;
	}

	@Override
	public AgreementNote findExcluding(final AgreementNote agreementNote, 
			final Date date, final String description,
			final Agreement agreement) {
		AgreementNote newAgreementNote = (AgreementNote)
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(AGREEMENT_NOTE_PARAM_NAME, agreementNote)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(AGREEMENT_PARAM_NAME, agreement)
				.uniqueResult();
		return newAgreementNote;
	}

	/**{@inheritDoc} */
	@Override
	public List<AgreementNote> findByAgreement(final Agreement agreement) {
		@SuppressWarnings("unchecked")
		List<AgreementNote> agreementNotes = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_AGREEMENT_NOTES_BY_AGREEMENT_QUERY_NAME)
				.setParameter(AGREEMENT_PARAM_NAME, agreement)
				.list();
		return agreementNotes;
	}
}
