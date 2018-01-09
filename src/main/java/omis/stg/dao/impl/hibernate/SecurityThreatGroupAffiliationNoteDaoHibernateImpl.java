package omis.stg.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.stg.dao.SecurityThreatGroupAffiliationNoteDao;
import omis.stg.domain.SecurityThreatGroupAffiliation;
import omis.stg.domain.SecurityThreatGroupAffiliationNote;

public class SecurityThreatGroupAffiliationNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SecurityThreatGroupAffiliationNote>
	implements SecurityThreatGroupAffiliationNoteDao {

	
	/* Query names. */
	
	private static final String 
		FIND_STG_AFFILIATION_NOTE_EXCLUDING_QUERY_NAME
		= "findSecurityThreatGroupAffiliationNoteExcluding";
	
	private static final String 
	FIND_STG_AFFILIATION_NOTES_BY_STG_AFFILIATION_QUERY_NAME
		= "findSecurityThreatGroupAffilitionNotesBySecurityThreatGroupAffiliation";
	
	private static final String 
	FIND_STG_AFFILIATION_NOTE_QUERY_NAME
		= "findSecurityThreatGroupAffiliationNote";
	
	/* Parameter names. */
	
	private static final String NOTE_PARAM_NAME = "note";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String STG_AFFILIATION_NOTE_PARAM_NAME 
		= "stgAffiliationNote";
	
	private static final String STG_AFFILIATION_PARAM_NAME 
		= "stgAffiliation";
	
	/**
	 * Instantiates a security threat group affiliation note data access object
	 * with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SecurityThreatGroupAffiliationNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupAffiliationNote> findNotes(
			SecurityThreatGroupAffiliation affiliation) {
		@SuppressWarnings("unchecked")
		List<SecurityThreatGroupAffiliationNote> affiliationNotes = 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_STG_AFFILIATION_NOTES_BY_STG_AFFILIATION_QUERY_NAME)
				.setParameter(STG_AFFILIATION_PARAM_NAME, affiliation)
				.list();
		return affiliationNotes;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupAffiliationNote findExcluding(
			SecurityThreatGroupAffiliationNote affiliationNote,
			SecurityThreatGroupAffiliation affiliation,
			Date date, String note) {
		SecurityThreatGroupAffiliationNote stgAffiliationNote = 
				(SecurityThreatGroupAffiliationNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_STG_AFFILIATION_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(NOTE_PARAM_NAME, note)
				.setParameter(STG_AFFILIATION_PARAM_NAME, affiliation)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(STG_AFFILIATION_NOTE_PARAM_NAME, affiliationNote)
				.uniqueResult();
		return stgAffiliationNote;
	}
	

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupAffiliationNote findAffiliationNote(
			SecurityThreatGroupAffiliation affiliation,
			Date date, String note) {
		SecurityThreatGroupAffiliationNote stgAffiliationNote = 
				(SecurityThreatGroupAffiliationNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_STG_AFFILIATION_NOTE_QUERY_NAME)
				.setParameter(NOTE_PARAM_NAME, note)
				.setParameter(STG_AFFILIATION_PARAM_NAME, affiliation)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return stgAffiliationNote;
	}
}
